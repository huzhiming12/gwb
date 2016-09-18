package com.gwb.bean;

import java.util.Date;

public class RefundBean
{
	private String refundId;
	// 微信或支付宝交易订单号
	private String tradeNo;
	// 用户信息
	private String username;
	private String mobilePhone;
	private String userId;
	// 订单信息
	private int oid;
	private String orderId;
	private float money;
	// 申请时间
	private Date createTime;
	// 支付方式
	private int payType;
	// 处理状态 0：未处理 1：退款成功 2：处理中
	private int state;

	public RefundBean(String id, String tradeNo, String username, String mobilePhone, String userId, int oid,
			String orderId, Float money, Date createTime, int payType, int state)
	{
		super();
		this.refundId = id;
		this.tradeNo = tradeNo;
		this.username = username;
		this.mobilePhone = mobilePhone;
		this.userId = userId;
		this.oid = oid;
		this.orderId = orderId;
		this.createTime = createTime;
		this.payType = payType;
		this.state = state;
		this.money = money;
	}

	public String getTradeNo()
	{
		return tradeNo;
	}

	public void setTradeNo(String tradeNo)
	{
		this.tradeNo = tradeNo;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public String getRefundId()
	{
		return refundId;
	}

	public void setRefundId(String refundId)
	{
		this.refundId = refundId;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public int getOid()
	{
		return oid;
	}

	public void setOid(int oid)
	{
		this.oid = oid;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getPayType()
	{
		return payType;
	}

	public void setPayType(int payType)
	{
		this.payType = payType;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

}
