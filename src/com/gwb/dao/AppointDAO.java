package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.AppointBean;
import com.gwb.bean.Pager;
import com.gwb.model.Appoint;

public interface AppointDAO
{
	// 更新邀请状态
	public void updateAppointState(int appId,int value);

	// 拉取邀请信息
	public Appoint appointDetail(int id);

	public void updateItem(Appoint appoint);

	// 添加邀请
	public boolean addAppoint(Appoint appoint);

	// 根据请求id查找邀请记录
	public List<AppointBean> loadAppointsByReqId(int id);
	
	//某个请求是否邀请了某个顾问
	public boolean isConsAppoint(int reqId,String consId);
	
	//加载顾问收到的邀请
	public Map<String, Object>loadConsAppoint(Pager pager,String consId);

	// 关闭过期的的邀请
	public void closeOutTimeAppoint();
}
