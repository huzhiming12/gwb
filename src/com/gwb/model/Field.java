package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_field")
public class Field
{
	/**
	 * 咨询领域
	 */

	private int id;
	// 领域标题
	private String title;
	// 领域描述
	private String description;
	// 0：正常 1：删除
	private int state;

	public Field()
	{
	}

	public Field(int id)
	{
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "field_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Column(length = 64, nullable = false)
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
