package com.gwb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.AlertBean;
import com.gwb.dao.AlertDAO;
import com.gwb.model.Alert;

@Component("alertDAO")
public class AlertDAOImpl implements AlertDAO
{
	private String sql = "select new com.gwb.bean.AlertBean(a.id,a.consultant.id,a.order.id,a.state)from Alert a ";
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

	@Override
	public void addAlert(Alert alert)
	{
		dataDAO.addItem(alert);
	}

	@Override
	public List<AlertBean> loadConsAlerts(String consId)
	{
		String hql = sql + " where a.consultant.id='" + consId + "' and a.state=0";
		return dataDAO.loadItems(hql);
	}

	@Override
	public void updateAlert(String consId, int orderId)
	{
		String hql = " update Alert a set a.state=1 where a.consultant.id='" + consId + "' and a.order.id=" + orderId
				+ " and a.state=0";
		dataDAO.updateData(hql);

	}

}
