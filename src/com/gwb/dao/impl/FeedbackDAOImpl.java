package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.FeedbackBean;
import com.gwb.bean.Pager;
import com.gwb.dao.FeedbackDAO;
import com.gwb.model.Feedback;
import com.gwb.util.Config;

@Component("feedbackDAO")
public class FeedbackDAOImpl implements FeedbackDAO
{
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

	// 添加意见反馈
	@Override
	public boolean addFeedback(Feedback feedback)
	{
		feedback.setCreateTime(new Date());
		return dataDAO.addItem(feedback);
	}

	// 加载意见反馈和打分评价
	@Override
	public Map<String, Object> loadFeedbackList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String flag = conditions.get("flag");
		String temp = "";
		if (flag == null)
			flag = "0";
		temp = " where f.flag = " + flag;
		String hql = "select new com.gwb.bean.FeedbackBean(f.id,f.content,f.userId,f.userType,f.flag,f.createTime)from Feedback f "
				+ temp + " order by createTime desc";
		List<FeedbackBean> feedbackBeans = dataDAO.loadItems(hql, pager);
		hql = "select count(*) from Feedback f " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("feedback", feedbackBeans);
		res.put("pager", pager);
		return res;
	}

}
