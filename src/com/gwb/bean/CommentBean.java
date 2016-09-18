package com.gwb.bean;

import java.util.Date;

public class CommentBean
{
	/**
	 * 用户评价
	 */
	private int id;
	// 评价内容
	private String content;
	// 评价者信息
	private String userMobilePhone;
	private String userName;
	private String userIcon;
	private String userPosition;
	// 评价顾问信息
	private String consMobilePhone;
	// 关联订单id
	private String orderId;
	// 评论时间
	private Date createTime;

	public CommentBean(int id, String content, String userMobilePhone, String userName, String userIcon,
			String userPosition, String consMobilePhone, String orderId, Date createTime)
	{
		super();
		this.id = id;
		this.content = content;
		this.userMobilePhone = userMobilePhone;
		this.userName = userName;
		this.userIcon = userIcon;
		this.userPosition = userPosition;
		this.consMobilePhone = consMobilePhone;
		this.orderId = orderId;
		this.createTime = createTime;
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

	public String getUserMobilePhone()
	{
		return userMobilePhone;
	}

	public void setUserMobilePhone(String userMobilePhone)
	{
		this.userMobilePhone = userMobilePhone;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserIcon()
	{
		return userIcon;
	}

	public void setUserIcon(String userIcon)
	{
		this.userIcon = userIcon;
	}

	public String getUserPosition()
	{
		return userPosition;
	}

	public void setUserPosition(String userPosition)
	{
		this.userPosition = userPosition;
	}

	public String getConsMobilePhone()
	{
		return consMobilePhone;
	}

	public void setConsMobilePhone(String consMobilePhone)
	{
		this.consMobilePhone = consMobilePhone;
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
