package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_reqField")
public class ReqField
{
	private int id;
	// 领域
	private Field field;
	// 请求
	private Request request;

	@Id
	@GeneratedValue
	@Column(name="reqField_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="field_id",referencedColumnName="field_id")
	public Field getField()
	{
		return field;
	}

	public void setField(Field field)
	{
		this.field = field;
	}

	@ManyToOne
	@JoinColumn(name="req_id",referencedColumnName="req_id")
	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
	}

}
