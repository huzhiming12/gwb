package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.CancelReasonBean;
import com.gwb.bean.OrderBean;
import com.gwb.bean.Pager;
import com.gwb.dao.OrderDAO;
import com.gwb.model.CancelReason;
import com.gwb.model.Order;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;

@Component("orderDAO")
public class OrderDAOImpl implements OrderDAO
{

	private String str = "select new com.gwb.bean.OrderBean(o.id, o.orderId, o.money,o.percent, "
			+ "o.createTime, o.user.id,o.user.name,o.user.mobilePhone,o.user.icon,o.consultant.id,"
			+ "o.consultant.name,o.consultant.mobilePhone,o.consultant.icon,o.consultant.position,o.state,o.appointTimeState,"
			+ "o.payState,o.cancelState,o.appointTime,o.finishTime,o.request.id,o.response.id)from Order o ";

	private DataDAO dataDAO;

	public DataDAO getDataDAO()
	{
		return dataDAO;
	}

	@Resource(name = "dataDAO")
	public void setDataDAO(DataDAO dataDAO)
	{
		this.dataDAO = dataDAO;
	}

	/**
	 * @author:huzhiming
	 * @function：添加订单
	 */
	@Override
	public boolean addOrder(Order order)
	{
		return dataDAO.addItem(order);
	}

	/**
	 * @author:huzhiming
	 * @function：添加取消订单的原因
	 */
	@Override
	public boolean addCancelReason(CancelReason reason)
	{
		return dataDAO.addItem(reason);
	}

	/**
	 * @author:huzhiming
	 * @function：获取用户取消订单的原因
	 */
	@Override
	public List<CancelReasonBean> getCancelReason(int orderId)
	{
		String hql = "select new com.gwb.bean.CancelReasonBean(c.id,c.content,c.createTime,"
				+ "c.state,c.order.id)from CancelReason c where c.order.id=" + orderId + " order by c.createTime desc";
		return dataDAO.loadItems(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：更新取消原因
	 */
	@Override
	public void updateCancelReason(int id, String item, String value)
	{
		String hql = "update CancelReason c set c." + item + "='" + value + "' where c.id =" + id;
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载订单 admin
	 */
	@Override
	public Map<String, Object> loadOrders(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		// 过滤已关闭的订单
		String temp = " where o.state!=5";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!keyword.equals(""))
			{
				temp += " and (o.orderId like '%" + keyword + "%' or o.request.title like '%" + keyword
						+ "%' or o.user.name like '%" + keyword + "%' or o.consultant.name like '%" + keyword + "%')";
			}
			int state = new Integer(conditions.get("state"));
			if (state != -1)
			{
				if (state > 5)
				{
					temp += " and o.cancelState=" + (state - 5);
				} else
					temp += " and (o.cancelState=0 and o.state=" + state + ")";
			}
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and o.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and o.createTime <'" + endTime + " 23:59:59'";
			}

		}
		String hql = str + temp + " order by o.createTime desc";
		List<OrderBean> orders = dataDAO.loadItems(hql, pager);
		// 查询条数
		hql = "select count(*)from Order o" + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> map = new HashMap<>();
		map.put("order", orders);
		map.put("pager", pager);

		return map;
	}

	// 获取导出订单列表数据
	@Override
	public List<OrderBean> exportOrders(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = " where o.state!=5";
		if (!conditions.isEmpty())
		{
			String state = conditions.get("state");
			if (!state.equals("-1"))
			{
				temp += " and o.state=" + state;
			}
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and o.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and o.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String hql = str + temp + " order by o.createTime desc";
		List<OrderBean> orders = dataDAO.loadItems(hql);
		return orders;
	}

	/**
	 * @author:huzhiming
	 * @function：通过id加载订单
	 */
	@Override
	public OrderBean loadOrderById(int id)
	{
		String hql = str + " where o.id = " + id;
		List<OrderBean> orders = dataDAO.loadItems(hql);
		if (orders != null && orders.size() > 0)
		{
			return orders.get(0);
		}
		return null;
	}

	/**
	 * @author:huzhiming
	 * @function：通过orderId加载订单
	 */
	@Override
	public OrderBean loadOrderByOrderId(String orderId)
	{
		String hql = str + " where o.orderId = '" + orderId + "'";
		List<OrderBean> orders = dataDAO.loadItems(hql);
		if (orders != null && orders.size() > 0)
		{
			return orders.get(0);
		}
		return null;
	}

	// 通过回应id 查找订单
	@Override
	public List<OrderBean> loadOrderByResponseId(int respId)
	{
		// 过滤未支付、已取消、已结束的、已关闭的
		String hql = str + " where o.payState=1 and o.cancelState!=2 and o.state!=5 and o.state!=4 and  o.response.id="
				+ respId + " order by o.createTime desc";
		return dataDAO.loadItems(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户订单
	 */
	@Override
	public Map<String, Object> loadUserOrders(String userId, Pager pager, int userType, int flag)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		// 设置每页显示条数
		pager.setPageSize(Config.USER_ORDER_PAGER_SIZE);

		String item = "";
		// 用户
		if (userType == 0)
		{
			if (flag == 0)
				item = " o.cancelState=2 and o.user.id";
			else
				item = " o.state=" + flag + " and o.user.id";
		} else
		{
			// 我的项目
			if (flag == 0)
				item = " (o.state=2 or o.cancelState!=0) and ";
			// 待评价
			else if (flag == 1)
				item = " (o.state=3 and o.cancelState=0) and ";
			// 已结束 (已取消或已结束)
			else if (flag == 2)
				item = " ((o.state=4 and o.cancelState=0) or (o.cancelState=2)) and ";
			item += " o.consultant.id";
		}

		// 加载已支付，且没有关闭的订单
		String hql = str + " where o.payState=1 and o.state!=5 and " + item + "='" + userId
				+ "' order by o.createTime desc";
		List<OrderBean> orderBeans = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Order o where " + item + "='" + userId + "'";
		// 查询数据条数
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> map = new HashMap<>();
		map.put("order", orderBeans);
		map.put("pager", pager);
		return map;
	}

	/**
	 * @author:huzhiming
	 * @function：更新订单信息
	 */
	@Override
	public void updateOrder(String item, String value, int orderId)
	{
		String hql = "update Order set " + item + "= '" + value + "' where id=" + orderId;
		dataDAO.updateData(hql);
	}

	// 加载请求的订单
	@Override
	public List<OrderBean> loadRequestOrder(int reqId)
	{
		String hql = str + " where o.request.id=" + reqId;
		return dataDAO.loadItems(hql);
	}

	// 关闭2小时没有支付的请求
	@Override
	public void closeOutTimeOrder()
	{
		Date now = new Date();
		// 1小时前的时间
		Date past = new Date(now.getTime() - 1 * 60 * 60 * 1000);
		String hql = "update Order o set o.state=5 where o.state=1 and o.createTime<'" + DateUtil.DateToString(past)
				+ "'";
		dataDAO.updateData(hql);
	}

	// 加载咨询过的用户人数
	@Override
	public int loadConsultUserNum()
	{
		String sql = "select count(distinct(user_id))from t_order o where o.state=3 or o.state=4";
		int num = dataDAO.SQLItemsNum(sql);
		return num;
	}

}
