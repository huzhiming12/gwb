package com.gwb.excel;

public class WalletEx
{
	private String mobilePhone;
	private String name;
	// 账户余额
	private float balance;
	private float canWithDraw;
	private float canNotWithDraw;
	// 每天可提现次数
	private int withDrawNum;
	// 当天已经提现次数
	private int withDrawed;

	public String getMobilePhone()
	{
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone)
	{
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

	public float getBalance()
	{
		return balance;
	}

	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	public float getCanWithDraw()
	{
		return canWithDraw;
	}

	public void setCanWithDraw(float canWithDraw)
	{
		this.canWithDraw = canWithDraw;
	}

	public float getCanNotWithDraw()
	{
		return canNotWithDraw;
	}

	public void setCanNotWithDraw(float canNotWithDraw)
	{
		this.canNotWithDraw = canNotWithDraw;
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

}
