package com.gwb.bean;

public class FieldBean
{
	private int id;
	// 领域标题
	private String title;
	// 领域描述
	private String description;

	public FieldBean(int id, String title, String description)
	{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
