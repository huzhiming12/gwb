package com.gwb.dao;

import java.util.Map;

import com.gwb.bean.ComplaintBean;
import com.gwb.bean.Pager;
import com.gwb.model.Complaint;

public interface ComplaintDAO
{
	// 添加投诉
	public boolean addComplaint(Complaint complaint);

	// 查询订单投诉
	public ComplaintBean getOrderComplaint(int orderId);

	// 加载用户投诉
	public Map<String, Object> loadComplaintList(Pager pager);

}
