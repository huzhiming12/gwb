package com.gwb.bean;

import java.util.Date;

/**
 * 财务明细
 */
public class FinancialDetailBean
{

	private int id;
	// 明细类型 0：收入 1：支出
	private int type;
	// 收入（支出）金额
	private float money;
	// 明细表创建时间
	private Date createTime;
	// 顾问id
	private String consId;
	// 关联的收入记录
	private Integer incomeId;
	// 关联的提现记录
	private Integer withDrawId;

	public FinancialDetailBean(int id, int type, float money, Date createTime, String consId, Integer incomeId,
			Integer withDrawId)
	{
		super();
		this.id = id;
		this.type = type;
		this.money = money;
		this.createTime = createTime;
		this.consId = consId;
		this.incomeId = incomeId;
		this.withDrawId = withDrawId;
	}

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

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
	}

	public Integer getIncomeId()
	{
		return incomeId;
	}

	public void setIncomeId(Integer incomeId)
	{
		this.incomeId = incomeId;
	}

	public Integer getWithDrawId()
	{
		return withDrawId;
	}

	public void setWithDrawId(Integer withDrawId)
	{
		this.withDrawId = withDrawId;
	}

}
