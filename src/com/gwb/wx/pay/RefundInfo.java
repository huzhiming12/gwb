package com.gwb.wx.pay;

import com.gwb.util.Config;

public class RefundInfo
{
	private String appid; // 微信分配的公众账号ID（企业号corpid即为此appId）
	private String mch_id; // 微信支付分配的商户号
	private String nonce_str; // 随机字符串，不长于32位。
	private String sign; // 签名
	private String out_trade_no; // 商户系统内部的订单号,32个字符内、
	private String out_refund_no;// 商户退款单号
	private int total_fee; // 订单总金额，单位为分，
	private int refund_fee;// 退款金额;
	private String op_user_id;// 操作员账号

	public RefundInfo()
	{
		super();
		this.appid = Config.WX_APP_ID;
		this.mch_id = Config.WX_MCH_ID;
		this.op_user_id = Config.WX_MCH_ID;
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

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no()
	{
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no)
	{
		this.out_refund_no = out_refund_no;
	}

	public int getTotal_fee()
	{
		return total_fee;
	}

	public void setTotal_fee(int total_fee)
	{
		this.total_fee = total_fee;
	}

	public int getRefund_fee()
	{
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee)
	{
		this.refund_fee = refund_fee;
	}

	public String getOp_user_id()
	{
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id)
	{
		this.op_user_id = op_user_id;
	}

}
