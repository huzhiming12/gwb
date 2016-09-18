package com.gwb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_good_at_filed")
public class GoodAtField
{
	/**
	 * 顾问擅长领域列表
	 */

	private int id;
	// 领域
	private Field field;

	private transient Consultant Consultant;

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filed_id", referencedColumnName = "field_id")
	public Field getField()
	{
		return field;
	}

	public void setField(Field field)
	{
		this.field = field;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "cons_id", name = "cons_id")
	public Consultant getConsultant()
	{
		return Consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		Consultant = consultant;
	}

}
