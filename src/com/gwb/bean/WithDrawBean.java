package com.gwb.bean;

import java.util.Date;

public class WithDrawBean
{
	private int id;
	// 提现流水号
	private String withNo;
	// 提现人姓名
	private String name;
	// 提现人支付宝账号
	private String account;
	// 提现金额
	private float money;
	// 提现顾问信息
	private String consId;
	private String consName;
	private String consMobilePhone;
	// 提现时间
	private Date createTime;
	// 处理时间
	private Date dealTime;
	// 提现状态 0:未处理 1：提现失败 2：提现成功 
	private int state;
	// 提现失败的原因
	private String reason;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
	}

	public String getConsMobilePhone()
	{
		return consMobilePhone;
	}

	public void setConsMobilePhone(String consMobilePhone)
	{
		this.consMobilePhone = consMobilePhone;
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

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public WithDrawBean(int id, String withNo, String name, String account, float money, String consId, String consName,
			String consMobilePhone, Date createTime, Date dealTime, int state, String reason)
	{
		super();
		this.id = id;
		this.withNo = withNo;
		this.name = name;
		this.account = account;
		this.money = money;
		this.consId = consId;
		this.consName = consName;
		this.consMobilePhone = consMobilePhone;
		this.createTime = createTime;
		this.dealTime = dealTime;
		this.state = state;
		this.reason = reason;
	}

}
