package com.gwb.bean;

import java.util.Date;

public class CancelReasonBean
{
	private int id;
	// 取消原因
	private String content;
	// 取消时间
	private Date createTime;
	// 顾问处理态度 0：未处理 1：同意 2：不同意
	private int state;
	// 关联订单
	private int orderId;

	public CancelReasonBean(int id, String content, Date createTime, int state, int orderId)
	{
		super();
		this.id = id;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
		this.orderId = orderId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

}
