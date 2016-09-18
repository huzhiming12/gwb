package com.gwb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User
{
	/**
	 * 用户
	 */
	// 用户唯一标识码
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
	// 状态 0：未处理 1：审核通过(正常状态) 2：驳回 3：删除 4：禁用
	private int state;

	public User()
	{
		// TODO Auto-generated constructor stub
	}

	public User(String id)
	{
		this.id = id;
	}


	@Id
	@Column(name = "user_id")
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(length = 64)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// 唯一值 且不为空
	@Column(nullable = false, length = 64)
	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}

	@Column(length = 64)
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

	@Column(length = 64)
	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	@Column(length = 64)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Date getRegistTime()
	{
		return registTime;
	}

	public void setRegistTime(Date registTime)
	{
		this.registTime = registTime;
	}

	@Lob
	@Column(columnDefinition = "TEXT", length = 65535)
	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	@Column(length = 64)
	public String getE_mail()
	{
		return e_mail;
	}

	public void setE_mail(String e_mail)
	{
		this.e_mail = e_mail;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", icon=" + icon + ", name=" + name + ", position=" + position + ", e_mail=" + e_mail
				+ ", mobilePhone=" + mobilePhone + ", phone=" + phone + ", introduction=" + introduction
				+ ", registTime=" + registTime + ", password=" + password + "]";
	}
}
