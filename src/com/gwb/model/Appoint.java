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
@Table(name = "t_appoint")
public class Appoint
{
	/**
	 * 邀请顾问列表
	 */
	private int id;
	// 邀请的顾问
	private Consultant consultant;
	// 状态 0：未处理 1：拒绝 2：接受 3：关闭(超过12小时未处理，自动关闭)
	private int state;
	// 邀请时间
	private Date createTime;
	// 顾问处理时间
	private Date dealTime;

	private transient Request request;

	@Id
	@GeneratedValue
	@Column(name = "appoint_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cons_id", referencedColumnName = "cons_id")
	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
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

}
