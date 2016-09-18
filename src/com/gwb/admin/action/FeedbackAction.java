package com.gwb.admin.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.ComplaintBean;
import com.gwb.bean.FeedbackBean;
import com.gwb.bean.Pager;
import com.gwb.service.FeedbackService;
import com.gwb.util.PagerTool;
import com.opensymphony.xwork2.ActionSupport;

@Component("adfeedbackAction")
@Scope("protoType")
public class FeedbackAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String selector;
	FeedbackService feedbackService;
	private Pager pager;
	private PagerTool pagerTool;
	private List<ComplaintBean> complaintBeans;
	private List<FeedbackBean> feedbackBeans;

	public List<ComplaintBean> getComplaintBeans()
	{
		return complaintBeans;
	}

	public void setComplaintBeans(List<ComplaintBean> complaintBeans)
	{
		this.complaintBeans = complaintBeans;
	}

	public List<FeedbackBean> getFeedbackBeans()
	{
		return feedbackBeans;
	}

	public void setFeedbackBeans(List<FeedbackBean> feedbackBeans)
	{
		this.feedbackBeans = feedbackBeans;
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

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public FeedbackService getFeedbackService()
	{
		return feedbackService;
	}

	@Resource(name = "feedbackService")
	public void setFeedbackService(FeedbackService feedbackService)
	{
		this.feedbackService = feedbackService;
	}

	// 投诉列表
	@SuppressWarnings("unchecked")
	public String complaintList()
	{
		selector = "complaintList";
		Map<String, Object> map = feedbackService.loadComplaintList(pager);
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/feedback_complaintList");
		complaintBeans = (List<ComplaintBean>) map.get("complaint");
		return SUCCESS;
	}

	// 反馈列表
	@SuppressWarnings("unchecked")
	public String feedbackList()
	{
		selector = "feedbackList";
		Map<String, Object> map = feedbackService.loadFeedbackList(pager);
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/feedback_feedbackList");
		feedbackBeans = (List<FeedbackBean>) map.get("feedback");
		return SUCCESS;
	}

}
