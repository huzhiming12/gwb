package com.gwb.bean;

public class SaleStatisticsBean
{
	// 顾问姓名
	private String consName;
	// 顾问账号/姓名
	private String consMobilePhone;
	// 销售金额
	private Float money;
	// 数量
	private Integer num;

	public SaleStatisticsBean(String consName, String consMobilePhone, Double money, Long num)
	{
		super();
		this.consName = consName;
		this.consMobilePhone = consMobilePhone;
		if (money != null)
			this.money = new Float(money);
		else
			this.money = 0F;
		if (num != null)
			this.num = num.intValue();
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

	public Float getMoney()
	{
		return money;
	}

	public void setMoney(Float money)
	{
		this.money = money;
	}

	public Integer getNum()
	{
		return num;
	}

	public void setNum(Integer num)
	{
		this.num = num;
	}

	@Override
	public String toString()
	{
		return "SaleStatisticsBean [consName=" + consName + ", consMobilePhone=" + consMobilePhone + ", money=" + money
				+ ", num=" + num + "]";
	}

}
