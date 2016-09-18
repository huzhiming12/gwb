package com.gwb.dao;

import java.util.List;

import com.gwb.bean.AppointTimeBean;
import com.gwb.model.AppointTime;

public interface AppointTimeDAO
{
	// 添加备选时间
	public boolean addAppointTime(AppointTime time);

	// 加载备选时间
	public List<AppointTimeBean> loadAppointTime(int id);

	// 重置备选时间
	public void delAppointTime(int orderId);
}
