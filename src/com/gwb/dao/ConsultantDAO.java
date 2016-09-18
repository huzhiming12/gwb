package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.ConsultantBean;
import com.gwb.bean.GoodAtFieldBean;
import com.gwb.bean.Pager;
import com.gwb.model.Consultant;

public interface ConsultantDAO
{
	// 查找顾问
	public ConsultantBean loadConsBeanById(String consId);

	public Consultant loadConsultantById(String consId);

	public ConsultantBean loadConsultantByPhone(String mobilePhone);

	// 添加顾问
	public boolean addConsult(Consultant consultant);

	// 删除顾问
	public void delConsultant(String consId);

	// 更新信息 item：更新项目 value：更新值 consId：id
	public void updateInfo(String item, String value, String consId);

	// 更新顾问信息
	public void updateInfo(Consultant consultant);

	// 查询
	public Map<String, Object> searchConsultant(String hql, String hql1, Pager pager);

	// 加载顾问擅长领域
	public List<GoodAtFieldBean> loadFields(String consId);

	// 获取用户列表
	public List<ConsultantBean> getConsList();
	
	//删除顾问擅长邻域
	public void delConsGoodAtFields(String consId);

}
