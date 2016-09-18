package com.gwb.admin.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.AppointTimeBean;
import com.gwb.bean.CancelReasonBean;
import com.gwb.bean.CommentBean;
import com.gwb.bean.ComplaintBean;
import com.gwb.bean.OrderBean;
import com.gwb.bean.Pager;
import com.gwb.bean.RefundBean;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.excel.OrderEx;
import com.gwb.excel.RefundEx;
import com.gwb.service.OrderService;
import com.gwb.service.PayRefundService;
import com.gwb.util.PagerTool;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adOrderAction")
@Scope("protoType")
public class OrderAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private List<OrderBean> orders;
	private OrderBean order;
	private OrderService orderService;
	private PayRefundService payRefundService;
	private int id;
	private Pager pager;
	private PagerTool pagerTool;
	private CommentBean comment;
	private List<CancelReasonBean> reasonBeans;
	private ComplaintBean complaint;
	private List<AppointTimeBean> timeBeans;
	private List<RefundBean> refundBeans;
	private String selector;
	private String refundId;
	private Map<String, Object> map = new HashMap<>();

	public Map<String, Object> getMap()
	{
		return map;
	}

	public void setMap(Map<String, Object> map)
	{
		this.map = map;
	}

	public String getRefundId()
	{
		return refundId;
	}

	public void setRefundId(String refundId)
	{
		this.refundId = refundId;
	}

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public PayRefundService getPayRefundService()
	{
		return payRefundService;
	}

	@Resource(name = "payRefundService")
	public void setPayRefundService(PayRefundService payRefundService)
	{
		this.payRefundService = payRefundService;
	}

	public List<CancelReasonBean> getReasonBeans()
	{
		return reasonBeans;
	}

	public void setReasonBeans(List<CancelReasonBean> reasonBeans)
	{
		this.reasonBeans = reasonBeans;
	}

	public CommentBean getComment()
	{
		return comment;
	}

	public void setComment(CommentBean comment)
	{
		this.comment = comment;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public PagerTool getPagerTool()
	{
		return pagerTool;
	}

	public void setPagerTool(PagerTool pagerTool)
	{
		this.pagerTool = pagerTool;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public OrderBean getOrder()
	{
		return order;
	}

	public void setOrder(OrderBean order)
	{
		this.order = order;
	}

	public List<OrderBean> getOrders()
	{
		return orders;
	}

	public void setOrders(List<OrderBean> orders)
	{
		this.orders = orders;
	}

	public List<AppointTimeBean> getTimeBeans()
	{
		return timeBeans;
	}

	public void setTimeBeans(List<AppointTimeBean> timeBeans)
	{
		this.timeBeans = timeBeans;
	}

	public OrderService getOrderService()
	{
		return orderService;
	}

	@Resource(name = "orderService")
	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	public ComplaintBean getComplaint()
	{
		return complaint;
	}

	public void setComplaint(ComplaintBean complaint)
	{
		this.complaint = complaint;
	}

	public List<RefundBean> getRefundBeans()
	{
		return refundBeans;
	}

	public void setRefundBeans(List<RefundBean> refundBeans)
	{
		this.refundBeans = refundBeans;
	}

	// 获取订单列表
	@SuppressWarnings("unchecked")
	public String orderList()
	{
		Map<String, Object> map = orderService.loadOrders(pager);
		orders = (List<OrderBean>) map.get("order");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/order_orderList");
		selector = "orderList";
		return SUCCESS;
	}

	// 获取订单详情
	public String orderDetail()
	{
		order = orderService.orderDetail(id);
		comment = orderService.loadOrderComment(id);
		reasonBeans = orderService.loadCancelReason(id);
		complaint = orderService.loadOrderComplaint(id);
		timeBeans = orderService.loadAppointTime(id);
		map.put("payrecord", payRefundService.payRecordDetail(id));
		return SUCCESS;
	}

	// 退款申请列表
	@SuppressWarnings("unchecked")
	public String refundList()
	{
		Map<String, Object> map = payRefundService.loadRefundList(pager);
		refundBeans = (List<RefundBean>) map.get("refund");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/order_refundList");
		selector = "refundList";
		return SUCCESS;
	}

	// 微信处理退款
	public void refund()
	{
		String result = "";
		try
		{
			Map<String, Object> res = orderService.dealRefund(refundId);
			if (200 == (int) res.get("status"))
				result = "退款成功！";
			else
				result = (String) res.get("error");

		} catch (Exception e)
		{
			e.printStackTrace();
			result = "参数有误，退款失败";
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 支付宝退款列表
	public String alipayRefundList()
	{
		map = payRefundService.loadAlipayRefundList();
		return SUCCESS;
	}

	// 导出订单列表
	public void exportOrder()
	{
		ExcelTool<OrderEx> ex = new ExcelTool<>();
		String[] headers = { "ID", "订单编号NO.", "订单金额", "创建时间", "用户姓名", "用户账号", "顾问姓名", "顾问账号", "支付状态", "订单状态", "预约时间",
				"完成时间", "请求编号" };
		List<OrderEx> orderExs = orderService.exportOrders(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("orders.xls");
			ex.exportExcel("订单列表", headers, orderExs, out);
			out.close();
			DownLoad.download("orders.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 导出退款申请
	public void exportRefund()
	{
		ExcelTool<RefundEx> ex = new ExcelTool<>();
		String[] headers = { "退款编号No.", "订单编号No.", "退款金额", "用户姓名", "用户账号", "申请时间", "支付方式", "处理状态" };
		List<RefundEx> refundExs = payRefundService.exportRefund(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("refund.xls");
			ex.exportExcel("退款申请列表", headers, refundExs, out);
			out.close();
			DownLoad.download("refund.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
