package com.gwb.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.interceptor.NotLoginException;
import com.gwb.model.Appoint;
import com.gwb.model.Request;
import com.gwb.model.Response;
import com.gwb.service.RequestService;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("requestAction")
@Scope("protoType")
public class RequestAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private RequestService requestService;
	// 顾问id
	private String id;
	// 领域id字符串
	private String fieldString;
	// 请求
	private Request req;
	// 回应
	private Response resp;
	// 邀请
	private Appoint appoint;
	// 分页
	private Pager pager;

	public String getFieldString()
	{
		return fieldString;
	}

	public void setFieldString(String fieldString)
	{
		this.fieldString = fieldString;
	}

	public Pager getPager()
	{
		return pager;
	}

	@Resource(name = "pager")
	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public Appoint getAppoint()
	{
		return appoint;
	}

	public void setAppoint(Appoint appoint)
	{
		this.appoint = appoint;
	}

	public RequestService getRequestService()
	{
		return requestService;
	}

	@Resource(name = "requestService")
	public void setRequestService(RequestService requestService)
	{
		this.requestService = requestService;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Request getReq()
	{
		return req;
	}

	public void setReq(Request req)
	{
		this.req = req;
	}

	public Response getResp()
	{
		return resp;
	}

	public void setResp(Response resp)
	{
		this.resp = resp;
	}

	// **********************************************************

	/**
	 * @author:huzhiming
	 * @function：添加请求
	 * @field:
	 * @void:
	 */
	public void addRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = requestService.addRequest(req,fieldString);
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
	 * @function：添加回应
	 * @field:
	 * @void:
	 */
	public void addResponse()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = requestService.addResponse(resp);
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
	 * @function：添加邀请
	 * @field:
	 * @void:
	 */
	public void addAppoint()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = requestService.addAppoint(appoint);
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
	 * @function：查询请求回应的次数
	 * @field:
	 * @return_type:
	 */
	public void getResponseNum()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadResponseNum(req.getId()));
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
	 * @function：获取请求的具体信息
	 * @field:
	 * @return_type:
	 */
	public void requestDetail()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadRequestById(req.getId()));
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
	 * @function：根据需求id获取顾问回应列表
	 * @field:
	 * @void:
	 */
	public void getResponses()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadResponseById(req.getId()));
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
	 * @function：根据需求id获取邀请列表
	 * @field:
	 * @void:
	 */
	// public void getAppoints()
	// {
	// Map<String, Object> result = new HashMap<>();
	// try
	// {
	// result.put("status", 200);
	// result.put("res", requestService.loadAppointById(req.getId()));
	// } catch (NullPointerException e)
	// {
	// e.printStackTrace();
	// result.put("status", 400);
	// result.put("error", "参数错误");
	// } catch (NotLoginException e)
	// {
	// result.put("status", 400);
	// result.put("error", "未登录或登录超时,请重新登录");
	// } catch (RuntimeException e)
	// {
	// e.printStackTrace();
	// // 说明是数据库操作有问题
	// result.put("status", 400);
	// result.put("error", "操作失败，请稍后重试");
	// }
	// ResultUtils.toJson(ServletActionContext.getResponse(), result);
	// }

	// // 顾问获取邀请列表
	// public void loadConsAppoint()
	// {
	// Map<String, Object> result = new HashMap<>();
	// try
	// {
	// result.put("status", 200);
	// result.put("res", requestService.loadConsAppoint(pager, id));
	// } catch (NullPointerException e)
	// {
	// e.printStackTrace();
	// result.put("status", 400);
	// result.put("error", "参数错误");
	// } catch (NotLoginException e)
	// {
	// result.put("status", 400);
	// result.put("error", "未登录或登录超时,请重新登录");
	// } catch (RuntimeException e)
	// {
	// e.printStackTrace();
	// // 说明是数据库操作有问题
	// result.put("status", 400);
	// result.put("error", "操作失败，请稍后重试");
	// }
	// ResultUtils.toJson(ServletActionContext.getResponse(), result);
	// }

	/**
	 * @author:huzhiming
	 * @function：用户处理顾问回应
	 * @field:
	 * @void:
	 */
	// public void dealResponse()
	// {
	// Map<String, Object> result = new HashMap<>();
	// try
	// {
	// result = requestService.dealResponse(resp);
	// } catch (NotLoginException e)
	// {
	// result.put("status", 400);
	// result.put("error", "未登录或登录超时,请重新登录");
	// } catch (RuntimeException e)
	// {
	// e.printStackTrace();
	// // 说明是数据库操作有问题
	// result.put("status", 400);
	// result.put("error", "操作失败，请稍后重试");
	// }
	// ResultUtils.toJson(ServletActionContext.getResponse(), result);
	// }

	/**
	 * @author:huzhiming
	 * @function：加载用户请求（咨询）列表
	 * @field: id-用户id pager.pageNow-获取数据的页数
	 * @return_type:
	 */
	public void getUserRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadUserRequests(id, pager));
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

	// 加载用户还未邀请的请求
	public void loadUnAppointRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadUnAppointRequest(id));
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
	 * @function：需求大厅
	 * @field: id:顾问id pager.pagerNow 第几页
	 * @return_type:
	 */
	public void getConsRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.loadConsRequest(id, pager));
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
	 * @function：顾问将某一需求置为不感兴趣
	 * @field: id:顾问id req.id 需求id
	 * @@return_type: true false
	 */
	public void uninterestRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result = requestService.uninterestReq(id, req.getId());
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
