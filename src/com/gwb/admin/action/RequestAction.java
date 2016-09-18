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

import com.gwb.bean.Pager;
import com.gwb.bean.RequestBean;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.excel.RequestEx;
import com.gwb.service.RequestService;
import com.gwb.util.PagerTool;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

@Component("adRequestAction")
@Scope("protoType")
public class RequestAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private List<RequestBean> requests;
	private RequestBean requestBean;
	private RequestService requestService;
	private Pager pager;
	private PagerTool pagerTool;
	private int id;
	private String selector;

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public List<RequestBean> getRequests()
	{
		return requests;
	}

	public void setRequests(List<RequestBean> requests)
	{
		this.requests = requests;
	}

	public RequestBean getRequestBean()
	{
		return requestBean;
	}

	public void setRequestBean(RequestBean requestBean)
	{
		this.requestBean = requestBean;
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

	// 获取需求列表
	@SuppressWarnings("unchecked")
	public String requestList()
	{
		Map<String, Object> map = requestService.loadRequestList(pager);
		requests = (List<RequestBean>) map.get("request");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/req_requestList");
		selector = "requestList";
		return SUCCESS;
	}

	// 需求详情
	public String requestDetail()
	{

		requestBean = requestService.loadRequestById(id);
		return SUCCESS;
	}

	// 关闭请求
	public void closeRequest()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", requestService.closeReuqest(id));
		} catch (NullPointerException e)
		{
			result.put("status", 200);
			result.put("error", "参数错误");
		} catch (RuntimeException e)
		{
			result.put("status", 200);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	public void exportRequestList()
	{
		ExcelTool<RequestEx> ex = new ExcelTool<>();
		String[] headers = { "需求编号", "需求标题", "需求内容", "发布人", "咨询领域", "咨询方式", "发布时间" };
		List<RequestEx> reqList = requestService.exportRequest(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("request.xls");
			ex.exportExcel("需求列表", headers, reqList, out);
			out.close();
			DownLoad.download("request.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
