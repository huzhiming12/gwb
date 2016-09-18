package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_uninsterested")
public class Uninterested
{
	/**
	 * 顾问不感兴趣列表
	 */
	private int id;
	private Consultant consultant;
	private Request request;

	@Id
	@GeneratedValue
	@Column(name = "uninter_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cons_id", referencedColumnName = "cons_id")
	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
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
