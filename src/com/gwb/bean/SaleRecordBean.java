package com.gwb.bean;

import java.util.Date;

public class SaleRecordBean
{
	private int id;
	// 平台抽成
	private float percent;
	// 顾问收入
	private float money;
	// 创建时间
	private Date createTime;
	// 关联订单
	private String orderId;
	private int oid;
	// 顾问
	private String consId;
	private String consMobilePhone;
	private String consName;

	public SaleRecordBean(int id, float percent, float money, Date createTime, String orderId, int oid, String consId,
			String consMobilePhone, String consName)
	{
		super();
		this.id = id;
		this.percent = percent;
		this.money = money;
		this.createTime = createTime;
		this.orderId = orderId;
		this.oid = oid;
		this.consId = consId;
		this.consMobilePhone = consMobilePhone;
		this.consName = consName;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getPercent()
	{
		return percent;
	}

	public void setPercent(float percent)
	{
		this.percent = percent;
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

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
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

	public int getOid()
	{
		return oid;
	}

	public void setOid(int oid)
	{
		this.oid = oid;
	}

}
