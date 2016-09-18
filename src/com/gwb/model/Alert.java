package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * 消息提醒
 *
 */
@Entity
@Table(name = "t_alert")
public class Alert
{
	private int id;
	// 提醒的顾问
	private Consultant consultant;
	// 哪个订单产生的
	private Order order;
	// 状态 0：未读 1：已读
	private int state;

	@Id
	@GeneratedValue
	@Column(name="alert_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="cons_id",referencedColumnName="cons_id")
	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
	}

	@ManyToOne
	@JoinColumn(name="order_id",referencedColumnName="id")
	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
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
