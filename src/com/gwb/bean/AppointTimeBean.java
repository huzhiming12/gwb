package com.gwb.bean;

import java.util.Date;

public class AppointTimeBean
{
	private int id;
	private Date time;

	public AppointTimeBean(int id, Date time)
	{
		super();
		this.id = id;
		this.time = time;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

}
