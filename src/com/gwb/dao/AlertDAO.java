package com.gwb.dao;

import java.util.List;

import com.gwb.bean.AlertBean;
import com.gwb.model.Alert;

public interface AlertDAO
{
	// 添加提醒
	public void addAlert(Alert alert);

	// 加载顾问的新订单提醒
	public List<AlertBean> loadConsAlerts(String consId);

	// 将提醒置为已读
	public void updateAlert(String consId, int orderId);

}
