package com.gwb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "t_request")
public class Request
{
	private int id;
	// 标题
	private String title;
	// 内容
	private String content;
	// 发布人
	private User user;
	// 咨询方式 0:线上咨询 1：线下会面
	private int consMode;
	// 咨询领域
	private List<ReqField> reqFields = new ArrayList<>();
	//private Field field;
	// 创建时间
	private Date createTime;
	// 邀请列表
	private List<Appoint> appoints = new ArrayList<>();
	// 邀请状态 1：可邀请 0：不可邀请
	private int appointState;
	// 需求状态 0：正常 1：关闭 2：咨询完成
	private int state;
	// 回应列表
	private List<Response> responses = new ArrayList<>();
	// 线下会面 选择城市
	private City city;

	public Request()
	{
	}

	public Request(int id)
	{
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "req_id")
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

	@Lob
	@Column(columnDefinition = "TEXT", length = 65535)
	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(nullable = false)
	public int getConsMode()
	{
		return consMode;
	}

	public void setConsMode(int consMode)
	{
		this.consMode = consMode;
	}

	@OneToMany(mappedBy="request",fetch=FetchType.EAGER)
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	public List<ReqField> getReqFields()
	{
		return reqFields;
	}

	public void setReqFields(List<ReqField> reqFields)
	{
		this.reqFields = reqFields;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@OrderBy(value = "createTime")
	public List<Appoint> getAppoints()
	{
		return appoints;
	}

	public void setAppoints(List<Appoint> appoints)
	{
		this.appoints = appoints;
	}

	@OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@OrderBy(value = "createTime")
	public List<Response> getResponses()
	{
		return responses;
	}

	public void setResponses(List<Response> responses)
	{
		this.responses = responses;
	}

	public int getAppointState()
	{
		return appointState;
	}

	public void setAppointState(int appointState)
	{
		this.appointState = appointState;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	@OneToOne
	@JoinColumn(name = "city_id", referencedColumnName = "city_id")
	public City getCity()
	{
		return city;
	}

	public void setCity(City city)
	{
		this.city = city;
	}

}
