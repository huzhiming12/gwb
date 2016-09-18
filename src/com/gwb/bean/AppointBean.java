package com.gwb.bean;

import java.util.Date;

public class AppointBean
{
	/**
	 * 邀请顾问列表
	 */
	private int id;
	// 顾问id
	private String consId;
	private String consName;
	private String consIcon;
	// 状态 0：未处理 1:已回应 2：关闭(超过12小时未处理，自动关闭)
	private int state;
	// 邀请时间
	private Date createTime;
	// 顾问处理时间
	private Date dealTime;
	// 关联请求
	private int reqId;

	public AppointBean(int id, String consId, String consName, String consIcon, int state, Date createTime,
			Date dealTime, int reqId)
	{
		super();
		this.id = id;
		this.consId = consId;
		this.state = state;
		this.createTime = createTime;
		this.dealTime = dealTime;
		this.reqId = reqId;
		this.consIcon = consIcon;
		this.consName = consName;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
	}

	public String getConsIcon()
	{
		return consIcon;
	}

	public void setConsIcon(String consIcon)
	{
		this.consIcon = consIcon;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getDealTime()
	{
		return dealTime;
	}

	public void setDealTime(Date dealTime)
	{
		this.dealTime = dealTime;
	}

	public int getReqId()
	{
		return reqId;
	}

	public void setReqId(int reqId)
	{
		this.reqId = reqId;
	}

}