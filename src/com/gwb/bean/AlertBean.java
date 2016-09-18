package com.gwb.bean;

public class AlertBean
{
	private int id;
	// 提醒的顾问
	private String consId;
	// 订单编号
	private int oId;
	// 状态 0：未读 1：已读
	private int state;

	public AlertBean(int id, String consId, int oId, int state)
	{
		super();
		this.id = id;
		this.consId = consId;
		this.oId = oId;
		this.state = state;
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

	public int getoId()
	{
		return oId;
	}

	public void setoId(int oId)
	{
		this.oId = oId;
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
