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

/**
 * 提现记录表
 */
@Entity
@Table(name = "t_withdraw")
public class WithDraw
{
	private int id;
	private String withNo;
	// 提现人姓名
	private String name;
	// 提现人支付宝账号
	private String account;
	// 提现金额
	private float money;
	// 提现顾问
	private Consultant consultant;
	// 提现时间
	private Date createTime;
	// 申请处理时间
	private Date dealTime;
	// 提现状态 0:未处理 1：提现失败 2：提现成功
	private int state;
	// 提现失败原因
	private String reason;

	public WithDraw()
	{
	}

	public WithDraw(int id)
	{
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "withdraw_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getWithNo()
	{
		return withNo;
	}

	public void setWithNo(String withNo)
	{
		this.withNo = withNo;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
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

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
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
