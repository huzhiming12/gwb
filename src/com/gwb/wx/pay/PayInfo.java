package com.gwb.wx.pay;

import com.gwb.util.Config;

/**
 * 
 * 微信支付 支付信息
 */
public class PayInfo
{
	private String appid; // 微信分配的公众账号ID（企业号corpid即为此appId）
	private String mch_id; // 微信支付分配的商户号
	private String nonce_str; // 随机字符串，不长于32位。
	private String sign; // 签名
	private String body; // 商品或支付单简要描述
	private String out_trade_no; // 商户系统内部的订单号,32个字符内、
	private int total_fee; // 订单总金额，单位为分，
	private String spbill_create_ip; // APP和网页支付提交用户端ip
	private String time_start; // 订单生成时间，格式为yyyyMMddHHmmss，
	private String notify_url; // 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	private String trade_type; // 取值如下：JSAPI，NATIVE，APP，

	public PayInfo()
	{
		super();
		this.appid = Config.WX_APP_ID;
		this.mch_id = Config.WX_MCH_ID;
		this.notify_url = Config.NOTIFY_URL;
		this.trade_type = Config.TRADE_TYPE;
	}

	public String getAppid()
	{
		return appid;
	}

	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	public String getMch_id()
	{
		return mch_id;
	}

	public void setMch_id(String mch_id)
	{
		this.mch_id = mch_id;
	}

	public String getNonce_str()
	{
		return nonce_str;
	}

	public void setNonce_str(String nonce_str)
	{
		this.nonce_str = nonce_str;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(int total_fee)
	{
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip()
	{
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip)
	{
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start()
	{
		return time_start;
	}

	public void setTime_start(String time_start)
	{
		this.time_start = time_start;
	}

	public String getNotify_url()
	{
		return notify_url;
	}

	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	public String getTrade_type()
	{
		return trade_type;
	}

	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

}
