package com.gwb.bean;

import java.util.Date;

public class FeedbackBean
{
	private int id;
	// 反馈内容
	private String content;
	private String userId;
	// 用户类型 0：用户 1：顾问
	private int userType;
	private String userName;
	// 反馈类型 0：打分评价 1：意见反馈
	private int flag;

	private Date createTime;

	public FeedbackBean(int id, String content, String userId, int userType, int flag, Date createTime)
	{
		super();
		this.id = id;
		this.content = content;
		this.userId = userId;
		this.userType = userType;
		this.flag = flag;
		this.createTime = createTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
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

	public int getUserType()
	{
		return userType;
	}

	public void setUserType(int userType)
	{
		this.userType = userType;
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

}
