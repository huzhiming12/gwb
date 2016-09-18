package com.gwb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_tempDate")
public class TempDate
{
	private Date d;

	@Id
	@Temporal(TemporalType.DATE)
	public Date getD()
	{
		return d;
	}

	public void setD(Date d)
	{
		this.d = d;
	}

}
