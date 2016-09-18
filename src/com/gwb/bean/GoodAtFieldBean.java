package com.gwb.bean;

public class GoodAtFieldBean
{

	/**
	 * 顾问擅长领域列表
	 */

	private int id;
	// 领域
	private String field;

	public GoodAtFieldBean(int id, String field)
	{
		super();
		this.id = id;
		this.field = field;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

}
