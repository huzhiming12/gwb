package com.gwb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "t_consultant")
public class Consultant
{
	/**
	 * 顾问
	 */
	// 用户唯一标识
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
	private City city;
	// 注册时间
	private Date registerTime;
	// 密码
	private String password;
	// 顾问的状态 0：待审核 1：审核通过(正常状态) 2：审核驳回 3：删除 4：禁用
	private int state;
	// 擅长领域列表
	private transient List<GoodAtField> fields = new ArrayList<>();

	public Consultant()
	{

	}

	public Consultant(String id)
	{
		super();
		this.id = id;
	}

	@Id
	@Column(name = "cons_id")
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(length = 64)
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
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
	public String getE_mail()
	{
		return e_mail;
	}

	public void setE_mail(String e_mail)
	{
		this.e_mail = e_mail;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "city_id", name = "city_id")
	public City getCity()
	{
		return city;
	}

	public void setCity(City city)
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

	@Column(length = 64)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@OneToMany(mappedBy = "consultant", fetch = FetchType.LAZY)
	@OrderBy(clause = "id")
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	public List<GoodAtField> getFields()
	{
		return fields;
	}

	public void setFields(List<GoodAtField> fields)
	{
		this.fields = fields;
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
		return "Consultant [id=" + id + ", mobilePhone=" + mobilePhone + ", phone=" + phone + ", icon=" + icon
				+ ", name=" + name + ", position=" + position + ", e_mail=" + e_mail + ", introduction=" + introduction
				+ ", city=" + city + ", registerTime=" + registerTime + ", password=" + password + ", state=" + state
				+ "]";
	}

}
