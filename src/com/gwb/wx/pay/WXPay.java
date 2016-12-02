package com.gwb.wx.pay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.gwb.util.Config;
import com.gwb.util.DateUtil;
import com.gwb.util.LogUtils;

@SuppressWarnings("deprecation")
public class WXPay
{
	// 提交预支付订单
	public static Map<String, Object> payCharge(String orderId, int money, String ip)
	{
		// 先关闭前面未支付的订单
		// closeOrder(orderId);
		PayInfo payInfo = new PayInfo();
		payInfo.setNonce_str(WXUtils.createNonceStr());
		payInfo.setBody("顾问帮一对一咨询服务");
		payInfo.setOut_trade_no(orderId);
		LogUtils.info("支付金额：" + money);
		payInfo.setTotal_fee(money);
		payInfo.setSpbill_create_ip(ip);
		payInfo.setTime_start(DateUtil.DateToString(new Date(), "yyyyMMddHHmmss"));
		// 获取签名
		payInfo.setSign(WXUtils.createSign(WXUtils.transBean2Map(payInfo)));
		// 发送请求
		String res = doPost(XMLUtils.ObjectToXMl(payInfo), Config.WX_PRE_PAY_URL);

		res = res.replace("<![CDATA[", "").replace("]]>", "");
		// System.out.println(res);
		LogUtils.info("微信预支付订单详情：\n" + res);
		Map<String, String> responseMap = XMLUtils.parseXml(res);
		Map<String, Object> result = new HashMap<>();
		result.put("status", 400);
		// 通信成功
		if (responseMap.get("return_code").equals("SUCCESS"))
		{
			// 交易成功 即预付订单生成成功
			if (responseMap.get("result_code").equals("SUCCESS"))
			{
				Map<String, Object> temp = new TreeMap<>();
				temp.put("appid", payInfo.getAppid());
				temp.put("partnerid", payInfo.getMch_id());
				temp.put("prepayid", responseMap.get("prepay_id"));
				temp.put("noncestr", WXUtils.createNonceStr());
				temp.put("timestamp", (System.currentTimeMillis() / 1000) + "");
				temp.put("package", "Sign=WXPay");
				temp.put("sign", WXUtils.createSign(temp));
				result.put("status", 200);
				result.put("res", temp);
			} else
				result.put("error", responseMap.get("err_code_des"));
		} else
			result.put("error", responseMap.get("return_msg"));
		return result;
	}

	// 关闭订单
	public static Map<String, Object> closeOrder(String orderId)
	{
		Map<String, Object> map = new TreeMap<>();
		map.put("appid", Config.WX_APP_ID);
		map.put("mch_id", Config.WX_MCH_ID);
		map.put("out_trade_no", orderId);
		map.put("nonce_str", WXUtils.createNonceStr());
		String sign = WXUtils.createSign(map);
		map.put("sign", sign);

		String resp = doPost(XMLUtils.mapToString(map), Config.WX_CLOSE_ORDER_URL);
		resp = resp.replace("<![CDATA[", "").replace("]]>", "");
		// System.out.println(resp);
		LogUtils.info("微信订单关闭返回数据：\n" + resp);
		Map<String, String> responseMap = XMLUtils.parseXml(resp);
		Map<String, Object> result = new HashMap<>();
		result.put("status", 400);
		if ("SUCCESS".equals(responseMap.get("return_code")))
		{
			if ("SUCCESS".equals(responseMap.get("result_code")))
				result.put("status", 200);
			else
				result.put("error", responseMap.get("err_code_des"));
		} else
			result.put("error", responseMap.get("return_msg"));
		return result;

	}

	// 微信退款申请
	public static Map<String, Object> refund(String orderId, String refundId, int total_fee, int refund_fee)
	{
		RefundInfo refundInfo = new RefundInfo();
		refundInfo.setOut_trade_no(orderId);
		refundInfo.setOut_refund_no(refundId);
		refundInfo.setTotal_fee(total_fee);
		refundInfo.setRefund_fee(refund_fee);
		// 产生随机数
		refundInfo.setNonce_str(WXUtils.createNonceStr());
		// 获取签名
		refundInfo.setSign(WXUtils.createSign(WXUtils.transBean2Map(refundInfo)));

		// 证书校验
		KeyStore keyStore;
		FileInputStream instream;
		SSLContext sslcontext;
		SSLConnectionSocketFactory sslsf = null;
		File file = new File(WXPay.class.getClassLoader().getResource("").getPath() + "apiclient_cert.p12");
		try
		{
			keyStore = KeyStore.getInstance("PKCS12");
			// 读取证书（商户后台下载p12证书）
			instream = new FileInputStream(file);
			keyStore.load(instream, Config.WX_MCH_ID.toCharArray());
			instream.close();
			// Trust own CA and all self-signed certs
			sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Config.WX_MCH_ID.toCharArray()).build();
			// Allow TLSv1 protocol only
			sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		} catch (KeyStoreException e)
		{
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (CertificateException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (KeyManagementException e)
		{
			e.printStackTrace();
		} catch (UnrecoverableKeyException e)
		{
			e.printStackTrace();
		}
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpPost httpPost = new HttpPost(Config.WX_REFUND_URL);
		HttpEntity entity = new StringEntity(XMLUtils.ObjectToXMl(refundInfo), "utf-8");
		httpPost.setEntity(entity);
		// System.out.println("executing request" + httpPost.getRequestLine());
		String res = "";
		try
		{
			CloseableHttpResponse response = httpclient.execute(httpPost);
			entity = response.getEntity();
			res = EntityUtils.toString(entity, "utf-8");
			response.close();
			httpclient.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		res = res.replace("<![CDATA[", "").replace("]]>", "");
		// System.out.println(res);
		LogUtils.info("微信退款申请返回数据：\n" + res);
		Map<String, String> responseMap = XMLUtils.parseXml(res);
		Map<String, Object> result = new HashMap<>();
		result.put("status", 400);
		if ("SUCCESS".equals(responseMap.get("return_code")))
		{
			if ("SUCCESS".equals(responseMap.get("result_code")))
				result.put("status", 200);
			else
				result.put("error", responseMap.get("err_code_des"));
		} else
			result.put("error", responseMap.get("return_msg"));
		return result;
	}

	// 查询退款状态
	public static Map<String, Object> searchRefund(String refundId)
	{
		RefundQuery refundQuery = new RefundQuery();
		refundQuery.setNonce_str(WXUtils.createNonceStr());
		refundQuery.setOut_refund_no(refundId);
		// 生成签名
		refundQuery.setSign(WXUtils.createSign(WXUtils.transBean2Map(refundQuery)));
		String res = doPost(XMLUtils.ObjectToXMl(refundQuery), Config.WX_REFUND_QUERY_URL);
		res = res.replace("<![CDATA[", "").replace("]]>", "");
		// System.out.println(res);
		LogUtils.info("微信退款订单状态查询数据：\n" + res);
		Map<String, String> responseMap = XMLUtils.parseXml(res);
		Map<String, Object> result = new HashMap<>();
		result.put("status", 400);
		if ("SUCCESS".equals(responseMap.get("return_code")))
		{
			if ("SUCCESS".equals(responseMap.get("result_code")))
				result.put("status", 200);
			else
				result.put("error", responseMap.get("err_code_des"));
		} else
			result.put("error", responseMap.get("return_msg"));
		return result;
	}

	// 发送http_Post请求
	public static String doPost(String content, String url)
	{
		CloseableHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = null;
		entity = new StringEntity(content, "utf-8");
		httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
		httpPost.setEntity(entity);
		String res = "";
		try
		{
			// 获取返回结果
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			res = EntityUtils.toString(httpEntity, "utf-8");
			response.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			httpClient.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String[] args) throws Exception
	{
		// String str =
		// "<xml><result_code>SUCCES</result_code><return_code>SUCCESS</return_code></xml>";
		// HttpClient client = new DefaultHttpClient();
		// HttpPost post = new
		// HttpPost("http://127.0.0.1:8080/gwb/Order_payOrder");
		// HttpResponse response = null;
		// try
		// {
		// post.setEntity(new StringEntity(str));
		// response = client.execute(post);
		// HttpEntity entity = response.getEntity();
		// String result = EntityUtils.toString(entity, "utf-8");
		// System.out.println(result);
		// } catch (Exception e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// searchRefund("2016070409001");
		// payCharge("sdfsfe23", 100, "127.0.0.1");
		// refund("2334556", "2354556", 100, 100);
		closeOrder("1469008340614054");
	}

}
