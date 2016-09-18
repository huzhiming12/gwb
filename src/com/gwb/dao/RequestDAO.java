package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.bean.ReqFieldBean;
import com.gwb.bean.RequestBean;
import com.gwb.model.Request;
import com.gwb.model.Uninterested;
import com.gwb.model.User;

public interface RequestDAO
{
	// 添加请求
	public boolean addReq(Request request);

	// 通过用户删除请求
	public void delReqByUser(User user);

	// 通过请求id删除请求
	public void delReqById(int id);

	// 通过id查找请求
	public RequestBean loadRequestById(int id);

	// 更新请求
	public void updateRequest(int reqId, String item, String value);

	// 加载所有的请求
	public Map<String, Object> loadRequest(Pager pager);

	// 获取用户的请求
	public Map<String, Object> getUserRequests(String user_id, Pager pager);

	// 加载用户还未邀请顾问的请求
	public List<RequestBean> loadUnAppointRequest(String userId);

	// 添加顾问不感兴趣列表
	public boolean uninterestReq(Uninterested uninterested);

	// 顾问加载请求
	public Map<String, Object> loadConsRequest(String cons_id, Pager pager);

	// 获取用户的导出列表
	public List<RequestBean> exportRequest(Pager pager);
	
	//获取请求的咨询领域
	public List<ReqFieldBean>loadReqFields(int reqId);

}
