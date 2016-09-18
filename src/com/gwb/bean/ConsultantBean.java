package com.gwb.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultantBean
{
	private String id;
	// 手机号
	private String mobilePhone;
	// 联系电话
	private String phone;
	// 头像
	private String icon;
	// 真实姓名
	private String name;
	// 职务
	private String position;
	// e_mail
	private String e_mail;
	// 个人介绍
	private String introduction;
	// 所在城市
	private String city;
	// 注册时间
	private Date registerTime;
	// 密码
	private String password;
	// 顾问的状态 0：待审核 1：审核通过 2：审核驳回
	private int state;
	// 擅长领域
	private List<GoodAtFieldBean> fields = new ArrayList<>();
	// 名片
	private List<CardBean> cards = new ArrayList<>();

	public ConsultantBean(String id, String mobilePhone, String phone, String icon, String name, String position,
			String e_mail, String introduction, String city, Date registerTime, String password, int state)
	{
		super();
		this.id = id;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.icon = icon;
		this.name = name;
		this.position = position;
		this.e_mail = e_mail;
		this.introduction = introduction;
		this.city = city;
		this.registerTime = registerTime;
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

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public Date getRegisterTime()
	{
		return registerTime;
	}

	public void setRegisterTime(Date registerTime)
	{
		this.registerTime = registerTime;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public List<GoodAtFieldBean> getFields()
	{
		return fields;
	}

	public void setFields(List<GoodAtFieldBean> fields)
	{
		this.fields = fields;
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
