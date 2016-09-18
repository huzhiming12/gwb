package com.gwb.bean;

import java.util.Date;

public class ResponseBean
{
	private int id;
	// 回应内容
	private String content;
	// 顾问id
	private String consId;
	// 顾问手机号
	private String mobilePhone;
	// 顾问name
	private String name;
	// 顾问头像
	private String icon;
	// 状态 0：未处理(未生成订单 预约) 1：已成订单
	private int state;
	// 回应类型 0：普通回应 1:邀请回应 (邀请顾问之后，顾问再回应)
	private int respType;
	// 回应时间
	private Date createTime;
	// 处理时间
	private Date dealTime;

	private OrderBean orderBeans;

	public ResponseBean(int id, String content, String consId, String mobilePhone, String name, String icon,
			int userState, int respType, Date createTime, Date dealTime)
	{
		super();
		this.id = id;
		this.content = content;
		this.consId = consId;
		this.mobilePhone = mobilePhone;
		this.name = name;
		this.icon = icon;
		this.state = userState;
		this.createTime = createTime;
		this.dealTime = dealTime;
		this.respType = respType;
	}

	public int getRespType()
	{
		return respType;
	}

	public void setRespType(int respType)
	{
		this.respType = respType;
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

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
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

	public OrderBean getOrderBeans()
	{
		return orderBeans;
	}

	public void setOrderBeans(OrderBean orderBeans)
	{
		this.orderBeans = orderBeans;
	}

}
