package com.gwb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * 退款申请
 */
@Entity
@Table(name = "t_refund")
public class Refund
{
	private String refundId;
	// 关联的支付记录
	private PayRecord payRecord;
	// 申请时间
	private Date createTime;
	// 处理状态 0：未处理 1：退款成功 2：处理中
	private int state;
	// 退款成功的时间
	private Date scTime;

	@Id
	public String getRefundId()
	{
		return refundId;
	}

	public void setRefundId(String refundId)
	{
		this.refundId = refundId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_id", referencedColumnName = "pay_id")
	public PayRecord getPayRecord()
	{
		return payRecord;
	}

	public void setPayRecord(PayRecord payRecord)
	{
		this.payRecord = payRecord;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public Date getScTime()
	{
		return scTime;
	}

	public void setScTime(Date scTime)
	{
		this.scTime = scTime;
	}
}
