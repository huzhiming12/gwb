package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.CancelReasonBean;
import com.gwb.bean.OrderBean;
import com.gwb.bean.Pager;
import com.gwb.model.CancelReason;
import com.gwb.model.Order;

public interface OrderDAO
{
	// 添加订单
	public boolean addOrder(Order order);

	// 添加取消订单的原因
	public boolean addCancelReason(CancelReason reason);

	// 获取用户取消原因
	public List<CancelReasonBean> getCancelReason(int orderId);

	// 更新取消原因
	public void updateCancelReason(int id, String item, String value);

	// 加载所有订单
	public Map<String, Object> loadOrders(Pager pager);

	// 通过id加载订单
	public OrderBean loadOrderById(int id);

	// 通过 orderId查找订单
	public OrderBean loadOrderByOrderId(String orderId);

	// 通过回应id查找订单
	public List<OrderBean> loadOrderByResponseId(int respId);

	// 加载用户订单
	public Map<String, Object> loadUserOrders(String userId, Pager pager, int userType, int flag);

	// 导出订单列表
	public List<OrderBean> exportOrders(Pager pager);

	// 更新订单信息
	public void updateOrder(String item, String value, int orderId);

	// 加载请求订单
	public List<OrderBean> loadRequestOrder(int reqId);

	// 关闭2小时没有支付的订单
	public void closeOutTimeOrder();

	// 加载咨询过服务的用户人数
	public int loadConsultUserNum();

}
