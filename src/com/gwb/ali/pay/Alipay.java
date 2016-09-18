package com.gwb.ali.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.alipay.api.AlipayApiException;
import com.gwb.bean.RefundBean;
import com.gwb.bean.WithDrawBean;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;
import com.gwb.util.LogUtils;

public class Alipay
{
	private static String rsa_private_key;
	private static String rsa_public_key;

	static
	{
		// 获取文件中的私钥和公钥
		String basePath = Alipay.class.getClassLoader().getResource("").getPath();
		rsa_private_key = AlipayUtils.readFile(basePath + "/" + Config.private_key);
		rsa_public_key = AlipayUtils.readFile(basePath + "/" + Config.public_key);
		rsa_private_key = rsa_private_key.replace("-----BEGIN PRIVATE KEY-----", "")
				.replace("-----END PRIVATE KEY-----", "");
		rsa_public_key = rsa_public_key.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----",
				"");
		// System.out.println(rsa_private_key);
		// System.out.println(rsa_public_key);
	}

	// 创建支付宝支付订单签名串
	public static String createAlipayOrderString(String orderId, float money)
	{
		String result = "";
		Map<String, String> map = new TreeMap<>();
		map.put("partner", Config.AL_PID);
		map.put("service", "mobile.securitypay.pay");
		map.put("_input_charset", "utf-8");
		map.put("notify_url", Config.Alipay_notify_url);
		map.put("out_trade_no", orderId);
		map.put("subject", "顾问咨询服务");
		map.put("payment_type", "1");
		map.put("seller_id", Config.AL_PID);
		map.put("total_fee", "0.01");
		map.put("body", "顾问帮 顾问咨询服务");
		String signString = AlipayUtils.mapToString(map);
		// System.out.println(signString);
		String sign = RSA.sign(signString, rsa_private_key, "utf-8");
		try
		{
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = signString + "&sign=\"" + sign + "\"&sign_type=\"RSA\"";
		LogUtils.info("支付宝预支付信息" + result);
		// System.out.println(sign);
		return result;
	}

	// 校验支付结果
	public static boolean checkPayResult(Map<String, String> map)
	{
		String re_sign = map.get("sign");
		// 移除签名 和签名类型
		map.remove("sign");
		map.remove("sign_type");
		// 用返回结果重新构建签名
		String content = AlipayUtils.mapToString(map).replace("\"", "");
		// 验证返回的签名
		boolean signCheck = RSA.verify(content, re_sign, Config.alipay_public_key, "utf-8");
		// 验证消息是否是支付宝发的
		if (signCheck)
		{
			String url = "https://mapi.alipay.com/gateway.do?service=notify_verify&partner=" + Config.AL_PID
					+ "&notify_id=" + map.get("notify_id");
			String responseStr = AlipayUtils.doPost("", url);
			if (responseStr.equals("true"))
				return true;
			else
			{
				LogUtils.warn("支付宝服务器异步通知 通知来源不是支付宝");
				return false;
			}
		}
		LogUtils.warn("支付宝服务器异步通知 签名校验失败");
		return false;
	}

	// 创建退款申请
	public static Map<String, String> createRefundForm(List<RefundBean> refundBeans)
	{
		if (refundBeans == null || refundBeans.isEmpty())
			return null;
		Map<String, String> map = new TreeMap<>();
		map.put("service", "refund_fastpay_by_platform_pwd");
		map.put("partner", Config.AL_PID);
		map.put("_input_charset", "utf-8");
		map.put("notify_url", Config.Alipay_refund_notify_url);
		map.put("seller_email", Config.seller_id);
		map.put("seller_user_id", Config.AL_PID);
		map.put("refund_date", DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		map.put("batch_no", DateUtil.DateToString(new Date(), "yyyyMMddHHmmss") + new Random().nextInt(1000));
		map.put("batch_num", refundBeans.size() + "");
		String dataString = "";
		for (RefundBean bean : refundBeans)
		{
			dataString += bean.getTradeNo() + "^"
					+ String.format("%.2f", 0.01/* bean.getMoney() */) + "^refund#";
		}
		dataString = dataString.substring(0, dataString.length() - 1);
		map.put("detail_data", dataString);
		String signContent = AlipayUtils.mapToString(map);
		signContent = signContent.replace("\"", "");
		// 签名
		String sign = RSA.sign(signContent, rsa_private_key, "utf-8");
		map.put("sign_type", "RSA");
		map.put("sign", sign);
		LogUtils.info("支付宝批量退款详情：" + map.toString());
		return map;
	}

	// 创建批量转账订单
	public static Map<String, String> createWithdrawForm(List<WithDrawBean> beans)
	{
		if (beans == null || beans.isEmpty())
			return null;
		Map<String, String> map = new TreeMap<>();
		map.put("service", "batch_trans_notify");
		map.put("partner", Config.AL_PID);
		map.put("_input_charset", "utf-8");

		map.put("notify_url", Config.Alipay_withdraw_notify_url);
		map.put("account_name", Config.seller_name);
		map.put("email", Config.seller_id);
		map.put("pay_date", DateUtil.DateToString(new Date(), "yyyyMMdd"));
		map.put("batch_no", DateUtil.DateToString(new Date(), "yyyyMMddHHmmss") + new Random().nextInt(100));
		map.put("batch_num", beans.size() + "");
		float total = 0f;
		String dataString = "";
		for (WithDrawBean bean : beans)
		{
			total += bean.getMoney();
			dataString += bean.getWithNo() + "^" + bean.getAccount() + "^" + bean.getName() + "^"
					+ String.format("%.2f", bean.getMoney()) + "^提现|";
		}
		dataString = dataString.substring(0, dataString.length() - 1);
		map.put("batch_fee", String.format("%.2f", total));
		map.put("detail_data", dataString);
		// 代签名字符串
		String signString = AlipayUtils.mapToString(map).replace("\"", "");
		map.put("sign_type", "RSA");
		map.put("sign", RSA.sign(signString, rsa_private_key, "utf-8"));
		LogUtils.info("支付宝批量转账详情：" + map.toString());
		return map;
	}

	public static void main(String[] args) throws AlipayApiException
	{
	}

}
