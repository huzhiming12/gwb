package com.gwb.excel;

import java.util.Date;

public class UserEx
{
	private String id;
	// 真实姓名
	private String name;
	// 职务
	private String position;
	// 邮箱
	private String email;
	// 手机号
	private String mobilePhone;
	// 联系电话
	private String phone;
	// 个人介绍
	private String introduction;
	// 注册时间
	private Date registTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public Date getRegistTime()
	{
		return registTime;
	}

	public void setRegistTime(Date registTime)
	{
		this.registTime = registTime;
	}

}
