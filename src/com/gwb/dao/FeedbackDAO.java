package com.gwb.dao;

import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.model.Feedback;

public interface FeedbackDAO
{
	// 添加意见反馈
	public boolean addFeedback(Feedback feedback);

	// 加载反馈列表
	public Map<String, Object> loadFeedbackList(Pager pager);

}
