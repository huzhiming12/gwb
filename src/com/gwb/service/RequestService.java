package com.gwb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.AppointBean;
import com.gwb.bean.OrderBean;
import com.gwb.bean.Pager;
import com.gwb.bean.ReqFieldBean;
import com.gwb.bean.RequestBean;
import com.gwb.bean.ResponseBean;
import com.gwb.dao.AppointDAO;
import com.gwb.dao.OrderDAO;
import com.gwb.dao.RequestDAO;
import com.gwb.dao.ResponseDAO;
import com.gwb.dao.UserDAO;
import com.gwb.excel.RequestEx;
import com.gwb.model.Appoint;
import com.gwb.model.Consultant;
import com.gwb.model.Field;
import com.gwb.model.ReqField;
import com.gwb.model.Request;
import com.gwb.model.Response;
import com.gwb.model.Uninterested;

@Component("requestService")
public class RequestService
{
	private OrderDAO orderDAO;
	private AppointDAO appointDAO;
	private ResponseDAO responseDAO;
	private RequestDAO requestDAO;
	private UserDAO userDAO;

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource(name = "userDAO")
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
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

	public AppointDAO getAppointDAO()
	{
		return appointDAO;
	}

	@Resource(name = "appointDAO")
	public void setAppointDAO(AppointDAO appointDAO)
	{
		this.appointDAO = appointDAO;
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

	public RequestDAO getRequestDAO()
	{
		return requestDAO;
	}

	@Resource(name = "requestDAO")
	public void setRequestDAO(RequestDAO requestDAO)
	{
		this.requestDAO = requestDAO;
	}

	// *****************************************************

	// 添加需求
	@Transactional
	public Map<String, Object> addRequest(Request request, String fieldString)
	{
		// Appoint appoint = new Appoint();
		// appoint.setConsultant(new Consultant(cons_id));
		// appoint.setCreateTime(new Date());
		// appoint.setRequest(request);

		List<ReqField> reqFields = new ArrayList<>();
		String[] temp = fieldString.split(";");
		for (String fieldId : temp)
		{
			ReqField reqField = new ReqField();
			reqField.setRequest(request);
			reqField.setField(new Field(new Integer(fieldId)));
			reqFields.add(reqField);
		}
		request.getReqFields().addAll(reqFields);
		request.setCreateTime(new Date());
		request.setAppointState(1);
		// request.getAppoints().add(appoint);
		Map<String, Object> res = new HashMap<>();
		if (requestDAO.addReq(request))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "需求添加失败,稍后重试");
		}
		return res;
	}

	// user 添加邀请
	@Transactional
	public Map<String, Object> addAppoint(Appoint appoint)
	{
		Map<String, Object> res = new HashMap<>();
		List<ResponseBean> responseBeans = responseDAO.loadResponseByReqId(appoint.getRequest().getId());
		if (responseBeans != null)
		{
			for (ResponseBean bean : responseBeans)
			{
				if (bean.getConsId().equals(appoint.getConsultant().getId()))
				{
					res.put("status", 400);
					res.put("error", "顾问已回应，不能再邀请！");
					return res;
				}
			}
		}
		List<AppointBean> appointBeans = appointDAO.loadAppointsByReqId(appoint.getRequest().getId());
		if (appointBeans != null)
		{
			for (AppointBean bean : appointBeans)
			{
				if (bean.getConsId().equals(appoint.getConsultant().getId()))
				{
					res.put("status", 400);
					res.put("error", "您已经邀请过该顾问，不能再邀请！");
					return res;
				}
			}
		}
		// 添加完邀请必须把 邀请状态置为否
		int reqId = appoint.getRequest().getId();
		appoint.setCreateTime(new Date());
		// 添加邀请
		boolean flag = appointDAO.addAppoint(appoint);
		// 将请求的可邀请状态置为否
		requestDAO.updateRequest(reqId, "appointState", "0");
		if (flag)
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "邀请添加失败,稍后重试");
		}
		return res;

	}

	// 获取请求回应次数
	@Transactional
	public int loadResponseNum(int id)
	{
		return responseDAO.getResponseNum(id);
	}

	// 添加请求回应
	@Transactional
	public Map<String, Object> addResponse(Response response)
	{
		Map<String, Object> res = new HashMap<>();
		// 普通邀请
		if (response.getRespType() == 0)
		{
			List<ResponseBean> responseBeans = responseDAO.loadResponseByReqId(response.getRequest().getId());
			if (responseBeans != null)
			{
				for (ResponseBean bean : responseBeans)
				{
					if (bean.getConsId().equals(response.getConsultant().getId()))
					{
						res.put("status", 400);
						res.put("error", "您已经回应，不能重复回应！");
						return res;
					}
				}
			}
		} else
		{
			// 如果是邀请回应，则需要更改邀请状态
			List<AppointBean> appointBeans = appointDAO.loadAppointsByReqId(response.getRequest().getId());
			if (appointBeans != null && appointBeans.isEmpty())
			{
				AppointBean bean = appointBeans.get(0);
				if (bean.getState() == 0)
				{
					// 更新成已回应
					appointDAO.updateAppointState(bean.getId(), 1);
				}

			}
		}
		response.setCreateTime(new Date());
		if (responseDAO.addResponse(response))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "回应失败，稍后重试");
		}
		return res;
	}

	// 通过id查找请求信息
	@Transactional
	public RequestBean loadRequestById(int id)
	{
		RequestBean request = requestDAO.loadRequestById(id);
		request.setAppoints(appointDAO.loadAppointsByReqId(id));
		request.setResponses(responseDAO.loadResponseByReqId(id));
		return request;
	}

	// 通过请求id查找回应列表
	@Transactional
	public List<ResponseBean> loadResponseById(int id)
	{
		List<ResponseBean> responseBeans = responseDAO.loadResponseByReqId(id);
		for (ResponseBean bean : responseBeans)
		{
			List<OrderBean> list = orderDAO.loadOrderByResponseId(bean.getId());
			if (!list.isEmpty())
			{
				bean.setOrderBeans(list.get(0));
			}
		}
		return responseBeans;
	}

	// 通过请求id查找请求的邀请
	@Transactional
	public List<AppointBean> loadAppointById(int id)
	{
		return appointDAO.loadAppointsByReqId(id);
	}

	// 加载顾问收到的邀请
	@Transactional
	public Map<String, Object> loadConsAppoint(Pager pager, String consId)
	{
		return appointDAO.loadConsAppoint(pager, consId);
	}

	// admin 加载需求列表
	@Transactional
	public Map<String, Object> loadRequestList(Pager pager)
	{
		Map<String, Object> map = requestDAO.loadRequest(pager);
		@SuppressWarnings("unchecked")
		List<RequestBean> requestBeans = (List<RequestBean>) map.get("request");
		for (RequestBean bean : requestBeans)
		{
			// 查询咨询领域
			List<ReqFieldBean> reqFieldBeans = requestDAO.loadReqFields(bean.getId());
			bean.setReqFieldBeans(reqFieldBeans);
		}
		return map;
	}

	// // user 用户处理回应
	// @Transactional
	// public Map<String, Object> dealResponse(Response response)
	// {
	// responseDAO.updateResponseState(response.getId(),
	// response.getUserState());
	// boolean flag = true;
	// // 同意顾问的回应
	// if (response.getUserState() == 2)
	// {
	// response = responseDAO.responseDetail(response.getId());
	// RequestBean requestBean =
	// requestDAO.loadRequestById(response.getRequest().getId());
	// UserBean userBean =
	// userDAO.loadUserByPhone(requestBean.getMobilePhone());
	// Order order = new Order();
	// User user = new User();
	// user.setId(userBean.getId());
	// order.setConsultant(response.getConsultant());
	// order.setUser(user);
	// order.setRequest(response.getRequest());
	// order.setCreateTime(new Date());
	// order.setOrderId(createOrderId());
	// if (requestBean.getConsMode() == 0)
	// order.setMoney(Config.OFFLINE_CONSULT_PRICE);
	// else
	// order.setMoney(Config.ONLINE_CONSULT_PRICE);
	//
	// flag = orderDAO.addOrder(order);
	// response.setOrder(order);
	// responseDAO.updateItem(response);
	// }
	// Map<String, Object> res = new HashMap<>();
	// if (flag)
	// res.put("status", 200);
	// else
	// {
	// res.put("status", 400);
	// res.put("error", "邀请处理失败，稍后重试");
	// }
	// return res;
	// }

	// // 生成订单号
	// private String createOrderId()
	// {
	// String orderId = "";
	// Long time = System.currentTimeMillis();
	// orderId += time;
	// Random random = new Random();
	// orderId += String.format("%05d", random.nextInt(10000));
	// return orderId;
	// }

	// user 加载用户请求
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> loadUserRequests(String userId, Pager pager)
	{
		Map<String, Object> map = requestDAO.getUserRequests(userId, pager);
		List<RequestBean> requestBeans = (List<RequestBean>) map.get("request");
		for (RequestBean bean : requestBeans)
		{
			List<ResponseBean> responseBeans = responseDAO.loadResponseByReqId(bean.getId());
			List<ReqFieldBean> reqFieldBeans = requestDAO.loadReqFields(bean.getId());
			bean.setResponses(responseBeans);
			bean.setReqFieldBeans(reqFieldBeans);
		}
		return map;
	}

	// 加载用户还未邀请顾问的请求
	@Transactional
	public List<RequestBean> loadUnAppointRequest(String userId)
	{
		return requestDAO.loadUnAppointRequest(userId);
	}

	// cons 顾问对某一请求不感兴趣
	@Transactional
	public Map<String, Object> uninterestReq(String consId, int req_id)
	{
		Uninterested uninterested = new Uninterested();
		uninterested.setConsultant(new Consultant(consId));
		Request request = new Request();
		request.setId(req_id);
		uninterested.setRequest(request);
		Map<String, Object> res = new HashMap<>();
		if (requestDAO.uninterestReq(uninterested))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "操作失败，稍后重试");
		}
		return res;
	}

	// cons 顾问加载请求
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> loadConsRequest(String cons_id, Pager pager)
	{
		Map<String, Object> map = requestDAO.loadConsRequest(cons_id, pager);
		List<RequestBean> requestBeans = (List<RequestBean>) map.get("request");
		for (RequestBean bean : requestBeans)
		{
			// 检查这个请求是否邀请了该顾问
			if (appointDAO.isConsAppoint(bean.getId(), cons_id))
			{
				bean.setIsAppoint(1);
			} else
				bean.setIsAppoint(0);

			List<ReqFieldBean> reqFieldBeans = requestDAO.loadReqFields(bean.getId());
			bean.setReqFieldBeans(reqFieldBeans);
		}
		return map;
	}

	// 关闭请求
	@Transactional
	public String closeReuqest(int reqId)
	{
		List<OrderBean> orderBeans = orderDAO.loadRequestOrder(reqId);
		boolean flag = true;
		String result = "请求关闭成功！";
		// 只能关闭订单已经结束或者还没有产生订单的请求
		if (orderBeans != null)
		{
			for (OrderBean bean : orderBeans)
			{
				// 订单既没有完成评价 也没有取消 即订单还在进行中
				if (bean.getState() != 4 && bean.getCancelState() != 2)
				{
					result = "该请求还有订单未完成,不能关闭";
					flag = false;
					break;
				}
			}
		}
		if (flag)
			requestDAO.updateRequest(reqId, "state", "1");
		return result;
	}

	// 获取请求的导出列表
	@Transactional
	public List<RequestEx> exportRequest(Pager pager)
	{
		List<RequestEx> reqExs = new ArrayList<>();
		List<RequestBean> beans = requestDAO.exportRequest(pager);
		for (RequestBean bean : beans)
		{

			// 查询咨询领域
			List<ReqFieldBean> reqFieldBeans = requestDAO.loadReqFields(bean.getId());
			RequestEx ex = new RequestEx();
			ex.setId(bean.getId());
			ex.setTitle(bean.getTitle());
			ex.setContent(bean.getContent());
			// ex.setField(bean.getFieldTitle());
			String fieldString = "";
			for (ReqFieldBean fieldBean : reqFieldBeans)
			{
				fieldString += fieldBean.getFieldTitle() + "/";
			}
			ex.setField(fieldString);
			ex.setCreateTime(bean.getCreateTime());
			ex.setUsername(bean.getName());
			if (bean.getConsMode() == 0)
				ex.setConsMode("线上咨询");
			else
				ex.setConsMode("线下会面");
			reqExs.add(ex);
		}
		return reqExs;
	}

}
