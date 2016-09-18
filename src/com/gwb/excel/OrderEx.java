package com.gwb.excel;

import java.util.Date;

public class OrderEx
{
	private int id;
	// 订单编号
	private String orderId;
	// 订单价格
	private float money;
	// 创建时间
	private Date createTime;
	// 订单用户
	private String username;
	private String userMobile;
	// 顾问
	private String consName;
	private String consMobile;

	private String payState;
	private String state;
	// 预约时间
	private Date appointTime;
	// 订单完成时间
	private Date finishTime;
	// 关联请求
	private int reqId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUserMobile()
	{
		return userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
	}

	public String getConsMobile()
	{
		return consMobile;
	}

	public void setConsMobile(String consMobile)
	{
		this.consMobile = consMobile;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Date getAppointTime()
	{
		return appointTime;
	}

	public void setAppointTime(Date appointTime)
	{
		this.appointTime = appointTime;
	}

	public Date getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(Date finishTime)
	{
		this.finishTime = finishTime;
	}

	public int getReqId()
	{
		return reqId;
	}

	public void setReqId(int reqId)
	{
		this.reqId = reqId;
	}

	public String getPayState()
	{
		return payState;
	}

	public void setPayState(String payState)
	{
		this.payState = payState;
	}

}
