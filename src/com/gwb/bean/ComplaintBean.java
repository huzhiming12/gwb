package com.gwb.bean;

import java.util.Date;

public class ComplaintBean
{
	private int id;
	// 投诉内容
	private String content;
	// 投诉人
	private String userId;
	private String userName;
	// 投诉顾问
	private String consId;
	private String consName;
	// 关联订单
	private String orderId;
	// 投诉时间
	private Date createTime;

	public ComplaintBean(int id, String content, String userId, String userName, String consId, String consName,
			String orderId, Date createTime)
	{
		super();
		this.id = id;
		this.content = content;
		this.userId = userId;
		this.userName = userName;
		this.consId = consId;
		this.consName = consName;
		this.orderId = orderId;
		this.createTime = createTime;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
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

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
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

}
