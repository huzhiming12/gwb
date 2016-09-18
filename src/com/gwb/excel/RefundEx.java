package com.gwb.excel;

import java.util.Date;

public class RefundEx
{
	private String refundId;
	// 订单信息
	private String orderId;
	private float money;
	// 用户信息
	private String username;
	private String mobilePhone;
	// 申请时间
	private Date createTime;
	// 支付方式
	private String payType;
	// 处理状态 0：未处理 1：已处理
	private String state;

	public String getRefundId()
	{
		return refundId;
	}

	public void setRefundId(String refundId)
	{
		this.refundId = refundId;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getPayType()
	{
		return payType;
	}

	public void setPayType(String payType)
	{
		this.payType = payType;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
