package com.gwb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order
{
	/**
	 * 订单类
	 */
	private int id;
	// 订单编号
	private String orderId;
	// 订单价格
	private float money;
	// 平台抽成
	private float percent;
	// 创建时间
	private Date createTime;
	// 关联请求
	private Request request;
	// 订单用户
	private User user;
	// 顾问
	private Consultant consultant;
	// 关联的回应
	private Response response;
	// 订单状态 1:待支付 2：已支付 3：咨询完成 4：完成评价（订单完成）5:订单关闭
	private int state;
	// 预约时间状态 0:未设定备选时间 1：已设定备选时间 2：待协商 3：预约时间确定
	private int appointTimeState;
	// 订单取消状态 0：正常 1：取消待确认 2：取消
	private int cancelState;
	// 支付状态 0：未支付 1：已支付
	private int payState;
	// 预约时间
	private Date appointTime;
	// 订单完成时间
	private Date finishTime;

	public Order(int id)
	{
		super();
		this.id = id;
	}

	public Order()
	{
	}

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public float getMoney()
	{
		return money;
	}

	public void setMoney(float money)
	{
		this.money = money;
	}

	public float getPercent()
	{
		return percent;
	}

	public void setPercent(float percent)
	{
		this.percent = percent;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	@ManyToOne
	@JoinColumn(name = "req_id", referencedColumnName = "req_id")
	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
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

	@ManyToOne
	@JoinColumn(name = "cons_id", referencedColumnName = "cons_id")
	public Consultant getConsultant()
	{
		return consultant;
	}

	public void setConsultant(Consultant consultant)
	{
		this.consultant = consultant;
	}

	@ManyToOne
	@JoinColumn(name = "resp_id", referencedColumnName = "resp_id")
	public Response getResponse()
	{
		return response;
	}

	public void setResponse(Response response)
	{
		this.response = response;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public int getAppointTimeState()
	{
		return appointTimeState;
	}

	public void setAppointTimeState(int appointTimeState)
	{
		this.appointTimeState = appointTimeState;
	}

	public Date getAppointTime()
	{
		return appointTime;
	}

	public void setAppointTime(Date appointTime)
	{
		this.appointTime = appointTime;
	}

	public int getCancelState()
	{
		return cancelState;
	}

	public void setCancelState(int cancelState)
	{
		this.cancelState = cancelState;
	}

	public Date getFinishTime()
	{
		return finishTime;
	}

	public void setFinishTime(Date finishTime)
	{
		this.finishTime = finishTime;
	}

	public int getPayState()
	{
		return payState;
	}

	public void setPayState(int payState)
	{
		this.payState = payState;
	}

}
