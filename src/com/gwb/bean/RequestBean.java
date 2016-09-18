package com.gwb.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestBean
{
	private int id;
	// 标题
	private String title;
	// 内容
	private String content;
	// 发布人id
	private String userId;
	// 发布人手机号
	private String mobilePhone;
	// 发布人姓名
	private String name;
	// 发布人头像
	private String icon;
	// 发布人职位
	private String position;
	// 咨询方式 0:线上咨询 1：线下会面
	private int consMode;
	// 需求领域数组
	private List<ReqFieldBean> reqFieldBeans;
	// private String fieldTitle;
	// 创建时间
	private Date createTime;
	// 邀请状态 1：可邀请 0：不可邀请
	private int appointState;
	// 顾问是否被邀请 （用于需求大厅标识）
	private int isAppoint;
	// 需求状态 0：正常 1：关闭
	private int state;
	// 线下会面的城市
	private String cityName;

	// 邀请列表
	private List<AppointBean> appoints = new ArrayList<>();
	// 回应列表
	private List<ResponseBean> responses = new ArrayList<>();

	public RequestBean(int id, String title, String content, String userId, String mobilePhone, String name,
			String icon, String postion, int consMode, Date createTime, int appointState, int state, String cityName)
	{
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.mobilePhone = mobilePhone;
		this.name = name;
		this.icon = icon;
		this.consMode = consMode;
		this.createTime = createTime;
		this.appointState = appointState;
		this.state = state;
		this.position = postion;
		this.cityName = cityName;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public RequestBean(int id, String title)
	{
		super();
		this.id = id;
		this.title = title;
	}

	public int getIsAppoint()
	{
		return isAppoint;
	}

	public void setIsAppoint(int isAppoint)
	{
		this.isAppoint = isAppoint;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
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

	public int getConsMode()
	{
		return consMode;
	}

	public void setConsMode(int consMode)
	{
		this.consMode = consMode;
	}

	public List<ReqFieldBean> getReqFieldBeans()
	{
		return reqFieldBeans;
	}

	public void setReqFieldBeans(List<ReqFieldBean> reqFieldBeans)
	{
		this.reqFieldBeans = reqFieldBeans;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getAppointState()
	{
		return appointState;
	}

	public void setAppointState(int appointState)
	{
		this.appointState = appointState;
	}

	public List<AppointBean> getAppoints()
	{
		return appoints;
	}

	public void setAppoints(List<AppointBean> appoints)
	{
		this.appoints = appoints;
	}

	public List<ResponseBean> getResponses()
	{
		return responses;
	}

	public void setResponses(List<ResponseBean> responses)
	{
		this.responses = responses;
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
