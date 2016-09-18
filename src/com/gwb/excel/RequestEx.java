package com.gwb.excel;

import java.util.Date;

public class RequestEx
{
	private int id;
	// 需求标题
	private String title;
	// 需求内容
	private String content;
	// 发布用户
	private String username;
	// 咨询领域
	private String field;
	// 咨询方式
	private String consMode;
	// 发布时间
	private Date createTime;

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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getConsMode()
	{
		return consMode;
	}

	public void setConsMode(String consMode)
	{
		this.consMode = consMode;
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
