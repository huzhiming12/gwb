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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "t_financial_detail")
public class FinancialDetail
{
	/**
	 * 财务明细
	 */

	private int id;
	// 明细类型 0：收入 1：支出
	private int type;
	// 收入（支出）金额
	private float money;
	// 明细表创建时间
	private Date createTime;
	// 顾问
	private Consultant consultant;
	// 关联的收入记录
	private Income income;
	// 关联的提现记录
	private WithDraw withDraw;

	@Id
	@GeneratedValue
	@Column(name = "fin_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
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

	@ManyToOne
	@JoinColumn(name = "income_id", referencedColumnName = "income_id")
	@Cascade(value={CascadeType.SAVE_UPDATE})
	public Income getIncome()
	{
		return income;
	}

	public void setIncome(Income income)
	{
		this.income = income;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "withdraw_id", referencedColumnName = "withdraw_id")
	public WithDraw getWithDraw()
	{
		return withDraw;
	}

	public void setWithDraw(WithDraw withDraw)
	{
		this.withDraw = withDraw;
	}

}
