package com.gwb.excel;

import java.util.Date;

/**
 * 
 * 顾问销售记录
 */
public class SaleRecordEx
{
	// 关联订单
	private int oid;
	private String orderId;
	// 总金额
	private float total;
	// 顾问
	private String consMobilePhone;
	private String consName;
	// 平台抽成
	private float percent;
	// 顾问收入
	private float money;
	// 创建时间
	private Date createTime;

	public int getOid()
	{
		return oid;
	}

	public void setOid(int oid)
	{
		this.oid = oid;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public float getTotal()
	{
		return total;
	}

	public void setTotal(float total)
	{
		this.total = total;
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

}
