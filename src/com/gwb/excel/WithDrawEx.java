package com.gwb.excel;

import java.util.Date;

public class WithDrawEx
{
	private String withNo;
	// 提现顾问信息
	private String consMobilePhone;
	private String consName;
	// 提现人姓名
	private String name;
	// 提现人支付宝账号
	private String account;
	// 提现金额
	private float money;
	// 提现时间
	private Date createTime;
	// 处理时间
	private Date dealTime;
	// 提现状态 0:未处理 1：提现失败 2：提现成功
	private String state;

	public String getWithNo()
	{
		return withNo;
	}

	public void setWithNo(String withNo)
	{
		this.withNo = withNo;
	}

	public String getConsMobilePhone()
	{
		return consMobilePhone;
	}

	public void setConsMobilePhone(String consMobilePhone)
	{
		this.consMobilePhone = consMobilePhone;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
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

	public Date getDealTime()
	{
		return dealTime;
	}

	public void setDealTime(Date dealTime)
	{
		this.dealTime = dealTime;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
