package com.gwb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_response")
public class Response
{
	private int id;
	// 回应内容
	private String content;
	// 顾问
	private Consultant consultant;
	// 用户状态 0：未处理 1：拒绝 2：接受
	private int userState;
	// 回应类型 0：普通回应 1:邀请回应 (邀请顾问之后，顾问再回应)
	private int respType;
	// 回应的请求transient:不对该字段解析
	private transient Request request;
	// 回应时间
	private Date createTime;
	// 处理时间
	private Date dealTime;
	

	@Id
	@GeneratedValue
	@Column(name = "resp_id")
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

	public int getRespType()
	{
		return respType;
	}

	public void setRespType(int respType)
	{
		this.respType = respType;
	}

	@ManyToOne
	@JoinColumn(name = "cons_id", referencedColumnName = "cons_id")
	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
	}

	public int getUserState()
	{
		return userState;
	}

	public void setUserState(int userState)
	{
		this.userState = userState;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "req_id", referencedColumnName = "req_id")
	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
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

}
