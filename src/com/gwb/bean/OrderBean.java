package com.gwb.bean;

import java.util.Date;

public class OrderBean
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
	// 订单用户
	private String userId;
	private String username;
	private String userMobile;
	private String userIcon;
	// 顾问
	private String consId;
	private String consName;
	private String consMobile;
	private String consIcon;
	private String consPosition;

	// 订单状态 1:待支付 2：已支付 3：咨询完成（待评价） 4：完成评价（订单完成）5:订单关闭
	private int state;
	// 预约时间状态 0:未设定备选时间 1：已设定备选时间 2：待协商 3：预约时间确定
	private int appointTimeState;
	// 订单取消状态 0：正常 1：取消待确认 2：已取消
	private int cancelState;
	// 订单的支付状态 0：未支付 1：已支付
	private int payState;
	// 预约时间
	private Date appointTime;
	// 订单完成时间
	private Date finishTime;

	// 关联的回应id
	private int respId;
	// 关联请求
	private int reqId;
	private RequestBean requestBean;
	private CommentBean commentBean;

	public OrderBean(int id, String orderId, float money, float percent, Date createTime, String userId,
			String username, String userMobile, String userIcon, String consId, String consName, String consMobile,
			String consIcon, String consPosition, int state, int appointTimeState, int payState, int cancelState,
			Date appointTime, Date finishTime, int reqId, int respid)
	{
		super();
		this.userIcon = userIcon;
		this.consIcon = consIcon;
		this.id = id;
		this.orderId = orderId;
		this.money = money;
		this.percent = percent;
		this.createTime = createTime;
		this.userId = userId;
		this.username = username;
		this.userMobile = userMobile;
		this.consId = consId;
		this.consName = consName;
		this.consMobile = consMobile;
		this.consPosition = consPosition;
		this.state = state;
		this.appointTimeState = appointTimeState;
		this.cancelState = cancelState;
		this.payState = payState;
		this.appointTime = appointTime;
		this.finishTime = finishTime;
		this.reqId = reqId;
		this.respId = respid;
	}

	public CommentBean getCommentBean()
	{
		return commentBean;
	}

	public void setCommentBean(CommentBean commentBean)
	{
		this.commentBean = commentBean;
	}

	public int getRespId()
	{
		return respId;
	}

	public void setRespId(int respId)
	{
		this.respId = respId;
	}

	public String getUserIcon()
	{
		return userIcon;
	}

	public void setUserIcon(String userIcon)
	{
		this.userIcon = userIcon;
	}

	public String getConsIcon()
	{
		return consIcon;
	}

	public void setConsIcon(String consIcon)
	{
		this.consIcon = consIcon;
	}

	public OrderBean()
	{
		// TODO Auto-generated constructor stub
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUserMobile()
	{
		return userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public String getConsName()
	{
		return consName;
	}

	public void setConsName(String consName)
	{
		this.consName = consName;
	}

	public String getConsMobile()
	{
		return consMobile;
	}

	public void setConsMobile(String consMobile)
	{
		this.consMobile = consMobile;
	}

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

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getConsId()
	{
		return consId;
	}

	public void setConsId(String consId)
	{
		this.consId = consId;
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

	public int getReqId()
	{
		return reqId;
	}

	public void setReqId(int reqId)
	{
		this.reqId = reqId;
	}

	public RequestBean getRequestBean()
	{
		return requestBean;
	}

	public void setRequestBean(RequestBean requestBean)
	{
		this.requestBean = requestBean;
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

	public String getConsPosition()
	{
		return consPosition;
	}

	public void setConsPosition(String consPosition)
	{
		this.consPosition = consPosition;
	}

}
