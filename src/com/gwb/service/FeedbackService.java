package com.gwb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.ConsultantBean;
import com.gwb.bean.FeedbackBean;
import com.gwb.bean.Pager;
import com.gwb.bean.UserBean;
import com.gwb.dao.ComplaintDAO;
import com.gwb.dao.ConsultantDAO;
import com.gwb.dao.FeedbackDAO;
import com.gwb.dao.UserDAO;
import com.gwb.model.Feedback;

@Component("feedbackService")
public class FeedbackService
{
	private FeedbackDAO feedbackDAO;
	private ComplaintDAO complaintDAO;
	private UserDAO userDAO;
	private ConsultantDAO consultantDAO;

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource(name = "userDAO")
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public ConsultantDAO getConsultantDAO()
	{
		return consultantDAO;
	}

	@Resource(name = "consultantDAO")
	public void setConsultantDAO(ConsultantDAO consultantDAO)
	{
		this.consultantDAO = consultantDAO;
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

	public FeedbackDAO getFeedbackDAO()
	{
		return feedbackDAO;
	}

	@Resource(name = "feedbackDAO")
	public void setFeedbackDAO(FeedbackDAO feedbackDAO)
	{
		this.feedbackDAO = feedbackDAO;
	}

	// 添加意见反馈
	@Transactional
	public Map<String, Object> addFeedback(Feedback feedback)
	{
		Map<String, Object> res = new HashMap<>();
		if (feedbackDAO.addFeedback(feedback))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "反馈失败，稍后重试");
		}
		return res;
	}

	// 加载用户投诉
	@Transactional
	public Map<String, Object> loadComplaintList(Pager pager)
	{
		return complaintDAO.loadComplaintList(pager);
	}

	// 加载用户反馈
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<String, Object> loadFeedbackList(Pager pager)
	{
		Map<String, Object> map = feedbackDAO.loadFeedbackList(pager);
		List<FeedbackBean> feedbackBeans = (List<FeedbackBean>) map.get("feedback");
		for (FeedbackBean bean : feedbackBeans)
		{
			if (bean.getUserType() == 0)
			{
				UserBean userBean = userDAO.loadUserInfo(bean.getUserId());
				bean.setUserName(userBean.getName());
			} else
			{
				ConsultantBean consultantBean = consultantDAO.loadConsBeanById(bean.getUserId());
				bean.setUserName(consultantBean.getName());
			}
		}

		return map;
	}

}
