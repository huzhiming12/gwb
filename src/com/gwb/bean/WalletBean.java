package com.gwb.bean;

public class WalletBean
{
	/**
	 * 钱包类
	 */

	private int id;
	// 账户余额
	private float balance;
	// 每天可提现次数
	private int withDrawNum;
	// 当天已经提现次数
	private int withDrawed;
	// 提现密码
	private String password;
	// 顾问id
	private String consId;
	private String name;
	private String mobilePhone;

	public WalletBean(int id, float balance, int withDrawNum, int withDrawed, String password, String consId,
			String name, String mobilePhone)
	{
		super();
		this.id = id;
		this.balance = balance;
		this.withDrawNum = withDrawNum;
		this.withDrawed = withDrawed;
		this.password = password;
		this.consId = consId;
		this.name = name;
		this.mobilePhone = mobilePhone;
	}

	
	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getMobilePhone()
	{
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	public int getWithDrawNum()
	{
		return withDrawNum;
	}

	public void setWithDrawNum(int withDrawNum)
	{
		this.withDrawNum = withDrawNum;
	}

	public int getWithDrawed()
	{
		return withDrawed;
	}

	public void setWithDrawed(int withDrawed)
	{
		this.withDrawed = withDrawed;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
	}

}
