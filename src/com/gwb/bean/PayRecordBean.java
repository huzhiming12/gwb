package com.gwb.bean;

import java.util.Date;

public class PayRecordBean
{
	private int id;
	// 微信或支付宝订单号
	private String tradeNo;
	// 支付金额
	private float money;
	// 支付人
	private String uname;
	private String userId;
	// 关联订单
	private int oid;
	private String orderId;
	// 支付时间
	private Date createTime;
	// 支付方式 0：微信支付 1:支付宝支付
	private int payType;

	public PayRecordBean(int id, String tradeNo, float money, String uname, String userId, int oid, String orderId,
			Date createTime, int payType)
	{
		super();
		this.id = id;
		this.tradeNo = tradeNo;
		this.money = money;
		this.uname = uname;
		this.userId = userId;
		this.oid = oid;
		this.orderId = orderId;
		this.createTime = createTime;
		this.payType = payType;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public String getUname()
	{
		return uname;
	}

	public void setUname(String uname)
	{
		this.uname = uname;
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

}
