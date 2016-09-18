package com.gwb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 顾问收入表
 */
@Entity
@Table(name = "t_income")
public class Income
{
	private int id;
	// 平台抽成
	private float percent;
	// 顾问收入
	private float money;
	// 创建时间
	private Date createTime;
	// 关联订单
	private Order order;
	// 顾问
	private Consultant consultant;

	@Id
	@GeneratedValue
	@Column(name = "income_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getPercent()
	{
		return percent;
	}

	public void setPercent(float percent)
	{
		this.percent = percent;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
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

}
