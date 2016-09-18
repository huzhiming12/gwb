package com.gwb.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBean
{
	private String id;
	// 头像
	private String icon;
	// 真实姓名
	private String name;
	// 职务
	private String position;
	// 邮箱
	private String e_mail;
	// 手机号
	private String mobilePhone;
	// 联系电话
	private String phone;
	// 个人介绍
	private String introduction;
	// 注册时间
	private Date registTime;
	// 用户密码
	private String password;
	// 用户状态 0:未处理 1：通过审核 2：驳回
	private int state;
	// 名片
	private List<CardBean> cards = new ArrayList<>();

	public UserBean(String id, String icon, String name, String position, String e_mail, String mobilePhone,
			String phone, String introduction, Date registTime, String password, int state)
	{
		super();
		this.id = id;
		this.icon = icon;
		this.name = name;
		this.position = position;
		this.e_mail = e_mail;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.introduction = introduction;
		this.registTime = registTime;
		this.password = password;
		this.state = state;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
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

	public String getE_mail()
	{
		return e_mail;
	}

	public void setE_mail(String e_mail)
	{
		this.e_mail = e_mail;
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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public List<CardBean> getCards()
	{
		return cards;
	}

	public void setCards(List<CardBean> cards)
	{
		this.cards = cards;
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
