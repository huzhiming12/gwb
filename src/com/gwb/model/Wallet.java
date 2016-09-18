package com.gwb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_wallet")
public class Wallet
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
	private transient Consultant consultant;

	@Id
	@GeneratedValue
	@Column(name = "wallet_id")
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

	@OneToOne(fetch = FetchType.LAZY)
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
