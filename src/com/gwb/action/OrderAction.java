package com.gwb.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.interceptor.NotLoginException;
import com.gwb.model.CancelReason;
import com.gwb.model.Comment;
import com.gwb.model.Complaint;
import com.gwb.service.OrderService;
import com.gwb.util.Config;
import com.gwb.util.LogUtils;
import com.gwb.util.ResultUtils;
import com.gwb.wx.pay.XMLUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("orderAction")
@Scope("protoType")
public class OrderAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	private String id;
	private String time;
	private OrderService orderService;
	private Pager pager;
	private int state;
	private int reasonId;
	private int flag;
	private int responseId;

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public int getResponseId()
	{
		return responseId;
	}

	public void setResponseId(int responseId)
	{
		this.responseId = responseId;
	}

	public int getReasonId()
	{
		return reasonId;
	}

	public void setReasonId(int reasonId)
	{
		this.reasonId = reasonId;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	// 评论
	private Comment comment;
	// 取消原因
	private CancelReason reason;
	// 投诉
	private Complaint complaint;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
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

	public Comment getComment()
	{
		return comment;
	}

	public void setComment(Comment comment)
	{
		this.comment = comment;
	}

	public CancelReason getReason()
	{
		return reason;
	}

	public void setReason(CancelReason reason)
	{
		this.reason = reason;
	}

	public Complaint getComplaint()
	{
		return complaint;
	}

	public void setComplaint(Complaint complaint)
	{
		this.complaint = complaint;
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户订单
	 * @field: id:用户id pager.pageNow:查询的页数
	 * @return_type:
	 */
	public void loadUserOrders()
	{

		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadUserOrders(id, pager, 0, flag));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：加载顾问订单
	 * @field: id:顾问id pager.pageNow:查询的页数
	 * @void:
	 */
	public void loadConsOrders()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadUserOrders(id, pager, 1, flag));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：通过订单id查询订单详情
	 * @field: id:订单id
	 * @return_type:
	 */
	public void orderDetail()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.orderDetail(new Integer(id)));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：用户设置备选时间
	 * @field: time:时间字符串 格式"2016-06-02 15:00:00;2016-06-02 15:00:00" 用;分隔
	 *         id:订单编号
	 * @return_type:
	 */

	public void appointTimeSetting()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = orderService.appointTimeSetting(time, responseId);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 协商预约
	public void appointTimeResetting()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.appointTimeResetting(new Integer(id), time);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：查看备选时间
	 * @field: id:订单编号
	 * @void:
	 */
	public void loadAppointTime()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadAppointTime(new Integer(id)));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：顾问从备选时间中选取时间
	 * @field: time:时间 字符串 格式：2016-06-05 12：00：00 id:订单id
	 * @void:
	 */
	public void chooseAppointTime()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.chooseAppointTime(time, new Integer(id));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function:重置备选时间（顾问请用户重新选择时间）
	 * @field: id:订单id
	 * @return_type:
	 */
	public void resetAppointTime()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.resetAppointTime(new Integer(id));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：微信预支付接口
	 * @field:
	 * @return_type:
	 */
	public void wxPrePay()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			result = orderService.prePayOrder(id, ip);
		} catch (Exception e)
		{
			result.put("status", 400);
			result.put("error", "参数错误");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 关闭系统订单
	public void closeOrder()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.closeOrder(id);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);

	}

	// 关闭微信订单
	public void closeWXOrder()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.closeWXOrder(id);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：微信服务器异步返回支付结果
	 * @field:
	 * @return_type:
	 */
	public void wxPayOrder()
	{
		LogUtils.warn("微信返回支付结果");
		String result = "<xml>" + "<return_code>FAIL</return_code>" + "<return_msg></return_msg></xml>";
		try
		{
			InputStream stream = ServletActionContext.getRequest().getInputStream();
			String temp = IOUtils.toString(stream).replace("<![CDATA[", "").replace("]]>", "");
			LogUtils.warn("微信支付返回结果：" + temp);
			Map<String, String> map = XMLUtils.parseXml(temp);
			System.out.println(temp);
			if (map.get("return_code").equals("SUCCESS"))
			{
				if (map.get("result_code").equals("SUCCESS"))
				{
					result = "<xml>" + "<return_code>SUCCESS</return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg></xml>";
					orderService.payOrder(map.get("out_trade_no"), map.get("transaction_id"), Config.WX_PAY_TYPE);
				}
			}
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		PrintWriter out;
		try
		{
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// Map<String, Object> result = new HashMap<>();
		// try
		// {
		// result.put("status", 200);
		// orderService.payOrder(id,0);
		// } catch (NullPointerException e)
		// {
		// e.printStackTrace();
		// result.put("status", 400);
		// result.put("error", "参数错误");
		// } catch (RuntimeException e)
		// {
		// e.printStackTrace(); // 说明是数据库操作有问题
		// result.put("status", 400);
		// result.put("error", "操作失败，请稍后重试");
		// }
		// ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 获取支付宝支付的订单签名信息
	public void loadAlipaySign()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.alipaySign(id));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：确认咨询完成
	 * @field: id:订单id
	 * @return_type:
	 */
	public void finishConsult()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.finishConsult(new Integer(id));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：添加订单评论
	 * @field: comment.content-评价内容 comment.order.id 订单id
	 * @return_type:
	 */
	public void addComment()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = orderService.addComment(comment);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：加载订单评价
	 * @field: id:订单id
	 * @@return_type: CommentBean
	 */
	public void getOrderComment()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadOrderComment(new Integer(id)));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：用户取消订单
	 * @field:reason.order.id-订单id reason.content-取消原因
	 * @return_type:
	 */
	public void cancelOrder()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = orderService.cancelOrder(reason);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：获取用户取消原因
	 */
	public void getCancelReason()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadCancelReason(new Integer(id)));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：顾问处理用户取消咨询的请求
	 * @field: id:订单id state: 0-同意取消咨询 1-不同意
	 * @return_type:
	 */
	public void cancelConfirm()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			orderService.cancelConfirm(new Integer(id), reasonId, state);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	/**
	 * @author:huzhiming
	 * @function：添加顾问投诉
	 * @field: complaint.content-内容 complaint.user.id-用户id
	 *         complaint.cosultant.id-顾问id complaint.order.id 订单id
	 * @void:
	 */
	public void addComplaint()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = orderService.addComplaint(complaint);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 加载该软用户咨询的比例
	public void loadConsultPercentage()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", orderService.loadConsultPercentage());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			// 说明是数据库操作有问题
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

}
