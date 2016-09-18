package com.gwb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.AppointTimeBean;
import com.gwb.dao.AppointTimeDAO;
import com.gwb.model.AppointTime;

@Component("appointTimeDAO")
public class AppointTimeDAOImpl implements AppointTimeDAO
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

	/**
	 * @author:huzhiming
	 * @function：添加备选时间
	 */
	@Override
	public boolean addAppointTime(AppointTime time)
	{
		return dataDAO.addItem(time);
	}

	/**
	 * @author:huzhiming
	 * @function：加载备选时间
	 */
	@Override
	public List<AppointTimeBean> loadAppointTime(int orderId)
	{
		String hql = "select new com.gwb.bean.AppointTimeBean(a.id,a.time)from AppointTime a where a.order.id="
				+ orderId;
		return dataDAO.loadItems(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：清空备选时间
	 */
	@Override
	public void delAppointTime(int orderId)
	{
		String hql = "update AppointTime a set a.order=null where a.order.id=" + orderId;
		dataDAO.updateData(hql);
	}

}
