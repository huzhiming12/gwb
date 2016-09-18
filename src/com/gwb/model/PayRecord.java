package com.gwb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pay_record")
public class PayRecord
{
	/**
	 * 用户的支付记录
	 */
	private int id;
	// 支付宝或者微信交易号
	private String tradeNo;
	// 支付金额
	private float money;
	// 支付人
	private User user;
	// 关联订单
	private Order order;
	// 支付时间
	private Date createTime;
	// 支付方式 0：微信支付 1:支付宝支付
	private int payType;

	public PayRecord()
	{
		// TODO Auto-generated constructor stub
	}

	public PayRecord(int id)
	{
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "pay_id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTradeNo()
	{
		return tradeNo;
	}

	public void setTradeNo(String tradeNo)
	{
		this.tradeNo = tradeNo;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getPayType()
	{
		return payType;
	}

	public void setPayType(int payType)
	{
		this.payType = payType;
	}

}
