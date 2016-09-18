package com.gwb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.ali.pay.Alipay;
import com.gwb.bean.AppointTimeBean;
import com.gwb.bean.CancelReasonBean;
import com.gwb.bean.CommentBean;
import com.gwb.bean.ComplaintBean;
import com.gwb.bean.OrderBean;
import com.gwb.bean.Pager;
import com.gwb.bean.PayRecordBean;
import com.gwb.bean.RefundBean;
import com.gwb.bean.RequestBean;
import com.gwb.bean.UserBean;
import com.gwb.dao.AlertDAO;
import com.gwb.dao.AppointTimeDAO;
import com.gwb.dao.CommentDAO;
import com.gwb.dao.ComplaintDAO;
import com.gwb.dao.OrderDAO;
import com.gwb.dao.PayRefundDAO;
import com.gwb.dao.RequestDAO;
import com.gwb.dao.ResponseDAO;
import com.gwb.dao.UserDAO;
import com.gwb.dao.WalletDAO;
import com.gwb.excel.OrderEx;
import com.gwb.model.Alert;
import com.gwb.model.AppointTime;
import com.gwb.model.CancelReason;
import com.gwb.model.Comment;
import com.gwb.model.Complaint;
import com.gwb.model.Consultant;
import com.gwb.model.FinancialDetail;
import com.gwb.model.Income;
import com.gwb.model.Order;
import com.gwb.model.PayRecord;
import com.gwb.model.Refund;
import com.gwb.model.Response;
import com.gwb.model.User;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;
import com.gwb.util.LogUtils;
import com.gwb.wx.pay.WXPay;

@Component("orderService")
public class OrderService
{
	private OrderDAO orderDAO;
	private CommentDAO commentDAO;
	private AppointTimeDAO appointTimeDAO;
	private ComplaintDAO complaintDAO;
	private RequestDAO requestDAO;
	private WalletDAO walletDAO;
	private PayRefundDAO payRefundDAO;
	private ResponseDAO responseDAO;
	private UserDAO userDAO;
	private AlertDAO alertDAO;

	public AlertDAO getAlertDAO()
	{
		return alertDAO;
	}

	@Resource(name = "alertDAO")
	public void setAlertDAO(AlertDAO alertDAO)
	{
		this.alertDAO = alertDAO;
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource(name = "userDAO")
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public ResponseDAO getResponseDAO()
	{
		return responseDAO;
	}

	@Resource(name = "responseDAO")
	public void setResponseDAO(ResponseDAO responseDAO)
	{
		this.responseDAO = responseDAO;
	}

	public PayRefundDAO getPayRefundDAO()
	{
		return payRefundDAO;
	}

	@Resource(name = "payRefundDAO")
	public void setPayRefundDAO(PayRefundDAO payRefundDAO)
	{
		this.payRefundDAO = payRefundDAO;
	}

	public WalletDAO getWalletDAO()
	{
		return walletDAO;
	}

	@Resource(name = "walletDAO")
	public void setWalletDAO(WalletDAO walletDAO)
	{
		this.walletDAO = walletDAO;
	}

	public RequestDAO getRequestDAO()
	{
		return requestDAO;
	}

	@Resource(name = "requestDAO")
	public void setRequestDAO(RequestDAO requestDAO)
	{
		this.requestDAO = requestDAO;
	}

	public OrderDAO getOrderDAO()
	{
		return orderDAO;
	}

	@Resource(name = "orderDAO")
	public void setOrderDAO(OrderDAO orderDAO)
	{
		this.orderDAO = orderDAO;
	}

	public CommentDAO getCommentDAO()
	{
		return commentDAO;
	}

	@Resource(name = "commentDAO")
	public void setCommentDAO(CommentDAO commentDAO)
	{
		this.commentDAO = commentDAO;
	}

	public AppointTimeDAO getAppointTimeDAO()
	{
		return appointTimeDAO;
	}

	@Resource(name = "appointTimeDAO")
	public void setAppointTimeDAO(AppointTimeDAO appointTimeDAO)
	{
		this.appointTimeDAO = appointTimeDAO;
	}

	public ComplaintDAO getComplaintDAO()
	{
		return complaintDAO;
	}

	@Resource(name = "complaintDAO")
	public void setComplaintDAO(ComplaintDAO complaintDAO)
	{
		this.complaintDAO = complaintDAO;
	}

	// 加载所有订单 admin
	@Transactional
	public Map<String, Object> loadOrders(Pager pager)
	{
		Map<String, Object> map = orderDAO.loadOrders(pager);
		@SuppressWarnings("unchecked")
		List<OrderBean> orderBeans = (List<OrderBean>) map.get("order");
		for (OrderBean orderBean : orderBeans)
		{
			// 查询关联的请求
			RequestBean requestBean = requestDAO.loadRequestById(orderBean.getReqId());
			orderBean.setRequestBean(requestBean);
		}
		map.put("order", orderBeans);
		return map;
	}

	// 加载用户(顾问)订单 User
	@Transactional
	public Map<String, Object> loadUserOrders(String user_id, Pager pager, int userType, int flag)
	{
		Map<String, Object> map = orderDAO.loadUserOrders(user_id, pager, userType, flag);
		@SuppressWarnings("unchecked")
		List<OrderBean> orderBeans = (List<OrderBean>) map.get("order");
		for (OrderBean orderBean : orderBeans)
		{
			// 查询关联的请求
			RequestBean requestBean = requestDAO.loadRequestById(orderBean.getReqId());
			orderBean.setRequestBean(requestBean);
			orderBean.setCommentBean(commentDAO.loadOrderComment(orderBean.getId()));
		}
		map.put("order", orderBeans);
		return map;
	}

	// 订单详情
	@Transactional
	public OrderBean orderDetail(int id)
	{
		OrderBean orderBean = orderDAO.loadOrderById(id);
		if (orderBean != null)
		{
			RequestBean requestBean = requestDAO.loadRequestById(orderBean.getReqId());
			orderBean.setRequestBean(requestBean);
		}
		return orderBean;
	}

	// 设置备选时间
	@Transactional
	public Map<String, Object> appointTimeSetting(String time, int responseId)
	{
		Map<String, Object> result = new HashMap<>();
		result.put("status", 200);
		Response response = responseDAO.responseDetail(responseId);
		// 预约加生成订单
		// 添加订单
		RequestBean requestBean = requestDAO.loadRequestById(response.getRequest().getId());
		UserBean userBean = userDAO.loadUserByPhone(requestBean.getMobilePhone());
		Order order = new Order();
		order.setConsultant(response.getConsultant());
		order.setUser(new User(userBean.getId()));
		order.setRequest(response.getRequest());
		order.setCreateTime(new Date());
		order.setState(1); // 待支付
		order.setAppointTimeState(1); // 已设定备选时间
		order.setOrderId(createOrderId());
		order.setResponse(response);
		if (requestBean.getConsMode() == 1)
		{
			order.setMoney(Config.OFFLINE_CONSULT_PRICE);
			order.setPercent(Config.OFFLINE_PERCENT);
		} else
		{
			order.setMoney(Config.ONLINE_CONSULT_PRICE);
			order.setPercent(Config.ONLINE_PERCENT);
		}
		orderDAO.addOrder(order);

		OrderBean bean = orderDAO.loadOrderByOrderId(order.getOrderId());
		// 添加备选时间
		addAppointTime(time, order);
		// 已生成订单
		responseDAO.updateResponse("userState", "1", responseId);
		result.put("orderId", order.getOrderId());
		result.put("id", bean.getId());
		return result;
	}

	// 重新预约时间
	@Transactional
	public void appointTimeResetting(int order_id, String time)
	{
		// 删除以前设置的备选时间
		appointTimeDAO.delAppointTime(order_id);
		// 添加备选时间
		addAppointTime(time, new Order(order_id));
		// 更新备选时间状态
		orderDAO.updateOrder("appointTimeState", "1", order_id);
	}

	// 生成订单号
	private String createOrderId()
	{
		String orderId = "";
		Long time = System.currentTimeMillis();
		orderId += time;
		Random random = new Random();
		orderId += String.format("%05d", random.nextInt(10000));
		return orderId;
	}

	// 添加备选时间
	private void addAppointTime(String time, Order order)
	{
		String[] str = time.split(";");
		for (String s : str)
		{
			AppointTime appointTime = new AppointTime();
			appointTime.setTime(DateUtil.stringToDate(s));
			appointTime.setOrder(order);
			// 插入时间
			appointTimeDAO.addAppointTime(appointTime);
		}
	}

	// 根据订单id加载备选时间
	@Transactional
	public List<AppointTimeBean> loadAppointTime(int id)
	{
		return appointTimeDAO.loadAppointTime(id);
	}

	// 顾问从备选时间中选择时间
	@Transactional
	public void chooseAppointTime(String time, int orderId)
	{
		orderDAO.updateOrder("appointTime", time, orderId);
		orderDAO.updateOrder("appointTimeState", "3", orderId);
	}

	// 将备选时间状态设为待协商
	@Transactional
	public void resetAppointTime(int orderId)
	{
		orderDAO.updateOrder("appointTimeState", "2", orderId);
	}

	// 微信预支付接口
	@Transactional
	public Map<String, Object> prePayOrder(String orderId, String ip)
	{
		OrderBean bean = orderDAO.loadOrderByOrderId(orderId);
		return WXPay.payCharge(orderId, (int) (bean.getMoney() * 100), ip);
	}

	// 支付宝获取签名和支付信息
	@Transactional
	public String alipaySign(String orderId)
	{
		OrderBean bean = orderDAO.loadOrderByOrderId(orderId);
		return Alipay.createAlipayOrderString(orderId, bean.getMoney());
	}

	// 支付订单
	@Transactional
	public void payOrder(String orderId, String tradeNo, int type/* 支付方式 */)
	{
		OrderBean orderBean = orderDAO.loadOrderByOrderId(orderId);
		// 防止微信或者支付宝重复通知
		if (orderBean.getPayState() == 0)
		{
			PayRecord record = new PayRecord();
			record.setCreateTime(new Date());
			record.setMoney(orderBean.getMoney());
			record.setOrder(new Order(orderBean.getId()));
			record.setUser(new User(orderBean.getUserId()));
			record.setPayType(type);
			record.setTradeNo(tradeNo);
			// 添加用户的支付记录
			payRefundDAO.addPayRecord(record);
			// 更新订单状态(已支付)
			orderDAO.updateOrder("state", "2", orderBean.getId());
			// 更新支付状态
			orderDAO.updateOrder("payState", "1", orderBean.getId());
			LogUtils.warn("支付成功！");
			
			// 添加信息订单提醒
			Alert alert = new Alert();
			alert.setConsultant(new Consultant(orderBean.getConsId()));
			alert.setOrder(new Order(orderBean.getId()));
			alertDAO.addAlert(alert);
			
		}
	}

	// 处理退款申请
	@Transactional
	public Map<String, Object> dealRefund(String refundId)
	{
		RefundBean refundBean = payRefundDAO.refundDetail(refundId);
		Map<String, Object> res = null;
		// 订单通过微信支付
		if (refundBean.getPayType() == 0)
		{
			// 提交退款申请
			res = WXPay.refund(refundBean.getOrderId(), refundBean.getRefundId(), (int) (refundBean.getMoney() * 100),
					(int) (refundBean.getMoney() * 100));
		}
		if (res != null && 200 == (int) res.get("status")) // 退款申请提交成功
		{
			// 订单更新为 处理中
			payRefundDAO.updataRefund("refundId", refundId, "state", "2");
		}
		return res;
	}

	// 确认完成咨询
	@Transactional(propagation = Propagation.REQUIRED)
	public void finishConsult(int orderId)
	{
		OrderBean orderBean = orderDAO.loadOrderById(orderId);
		Consultant consultant = new Consultant(orderBean.getConsId());
		Date time = new Date();
		// 创建顾问收入记录
		Income income = new Income();
		income.setConsultant(consultant);
		income.setCreateTime(time);
		income.setOrder(new Order(orderId));
		// 创建财务明细
		FinancialDetail detail = new FinancialDetail();
		detail.setType(0);
		detail.setCreateTime(time);
		detail.setConsultant(consultant);

		income.setPercent(orderBean.getPercent());
		income.setMoney(orderBean.getMoney() - orderBean.getPercent());
		detail.setMoney(orderBean.getMoney() - orderBean.getPercent());

		detail.setIncome(income);
		// 更新订单状态(完成咨询完成)
		orderDAO.updateOrder("state", "3", orderId);
		// // 更新请求状态
		// requestDAO.updateRequest(orderBean.getReqId(), "state", "1");
		// 添加财务明细
		walletDAO.addFinancialDetail(detail);
	}

	// 添加评论
	@Transactional
	public Map<String, Object> addComment(Comment comment)
	{
		OrderBean bean = orderDAO.loadOrderById(comment.getOrder().getId());
		comment.setCreateTime(new Date());
		comment.setConsultant(new Consultant(bean.getConsId()));
		comment.setUser(new User(bean.getUserId()));
		// 更新订单状态（评价完成/订单完成）
		orderDAO.updateOrder("state", "4", comment.getOrder().getId());
		orderDAO.updateOrder("finishTime", DateUtil.DateToString(new Date()), bean.getId());
		// 更新订单为已完成
		requestDAO.updateRequest(bean.getReqId(), "state", "2");
		boolean flag = commentDAO.addComment(comment);

		Map<String, Object> res = new HashMap<>();
		if (flag)
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "操作失败，稍后重试");
		}
		return res;
	}

	// 加载订单评价
	@Transactional
	public CommentBean loadOrderComment(int id)
	{
		return commentDAO.loadOrderComment(id);
	}

	// 取消订单
	@Transactional
	public Map<String, Object> cancelOrder(CancelReason reason)
	{
		// 设置时间
		reason.setCreateTime(new Date());
		// 更新订单状态（取消待确认）
		orderDAO.updateOrder("cancelState", "1", reason.getOrder().getId());
		boolean flag = orderDAO.addCancelReason(reason);
		Map<String, Object> res = new HashMap<>();
		if (flag)
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "取消失败，稍后重试");
		}
		return res;
	}

	@Transactional
	public List<CancelReasonBean> loadCancelReason(int orderId)
	{
		return orderDAO.getCancelReason(orderId);
	}

	// 顾问取消确认
	@Transactional
	public void cancelConfirm(int orderId, int reasonId, int state)
	{
		// 同意取消订单
		if (state == 0)
		{
			OrderBean orderBean = orderDAO.loadOrderById(orderId);
			// 如果订单已经付款 则需要创建退款申请
			if (orderBean.getState() == 2)
			{
				Refund refund = new Refund();
				String refundId = DateUtil.DateToString(new Date(), "yyyyMMddHHmmss")
						+ String.format("%05d", new Random().nextInt(1000));
				refund.setRefundId(refundId);
				refund.setCreateTime(new Date());
				PayRecordBean payRecordBean = payRefundDAO.payRecordDetail(orderId);
				refund.setPayRecord(new PayRecord(payRecordBean.getId()));
				payRefundDAO.addRefund(refund);
			}
			orderDAO.updateOrder("cancelState", "2", orderId);
			orderDAO.updateCancelReason(reasonId, "state", "1");
		} else // 不同意 订单继续
		{
			orderDAO.updateOrder("cancelState", "0", orderId);
			orderDAO.updateCancelReason(reasonId, "state", "2");
		}
	}

	// 添加投诉
	@Transactional
	public Map<String, Object> addComplaint(Complaint complaint)
	{
		complaint.setCreateTime(new Date());
		Map<String, Object> res = new HashMap<>();
		if (complaintDAO.addComplaint(complaint))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "操作失败，稍后重试");
		}
		return res;
	}

	// 获取订单投诉
	@Transactional
	public ComplaintBean loadOrderComplaint(int orderId)
	{
		return complaintDAO.getOrderComplaint(orderId);
	}

	// 关闭订单
	@Transactional
	public void closeOrder(String orderId)
	{
		OrderBean bean = orderDAO.loadOrderByOrderId(orderId);
		orderDAO.updateOrder("state", "5", bean.getId());
	}

	// 关闭微信订单
	@Transactional
	public Map<String, Object> closeWXOrder(String orderId)
	{
		return WXPay.closeOrder(orderId);
	}

	// 加载该软用户咨询的比例
	@Transactional
	public float loadConsultPercentage()
	{
		// 咨询过的用户人数
		int consultUserNum = orderDAO.loadConsultUserNum();
		// 用户人数
		int userNum = userDAO.loadUserNum();
		return (consultUserNum * 1.0F / userNum);
	}

	// 导出订单
	@Transactional
	public List<OrderEx> exportOrders(Pager pager)
	{
		List<OrderEx> list = new ArrayList<>();
		List<OrderBean> beans = orderDAO.exportOrders(pager);
		for (OrderBean bean : beans)
		{
			OrderEx ex = new OrderEx();
			ex.setId(bean.getId());
			ex.setOrderId(bean.getOrderId());
			ex.setMoney(bean.getMoney());
			ex.setCreateTime(bean.getCreateTime());
			ex.setUsername(bean.getUsername());
			ex.setUserMobile(bean.getUserMobile());
			ex.setConsName(bean.getConsName());
			ex.setConsMobile(bean.getConsMobile());
			ex.setAppointTime(bean.getAppointTime());
			ex.setFinishTime(bean.getFinishTime());
			ex.setReqId(bean.getReqId());
			if (bean.getPayState() == 0)
				ex.setPayState("未支付");
			else
				ex.setPayState("已支付");
			if (bean.getCancelState() == 0)
			{
				switch (bean.getState())
				{
				case 0:
					ex.setState("待预约");
					break;
				case 1:
					ex.setState("待支付");
					break;
				case 2:
					ex.setState("已支付");
					break;
				case 3:
					ex.setState("咨询结束(待评价)");
					break;
				case 4:
					ex.setState("订单完成(已评价)");
					break;
				case 5:
					ex.setState("订单关闭");
					break;
				}
			} else if (bean.getCancelState() == 1)
				ex.setState("取消待确认");
			else
				ex.setState("已取消");
			list.add(ex);
		}
		return list;
	}
}
