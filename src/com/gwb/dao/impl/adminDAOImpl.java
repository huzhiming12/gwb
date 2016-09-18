package com.gwb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.dao.AdminDAO;
import com.gwb.model.Admin;

@Component("adminDAO")
public class adminDAOImpl implements AdminDAO
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

	// 管理员信息
	@Override
	public Admin adminInfo(String userName)
	{
		String hql = "from Admin where userName='" + userName + "'";
		List<Admin> list = dataDAO.loadItems(hql);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	// 更新信息
	@Override
	public void updateInfo(String userName, String item, String value)
	{
		String hql = "update Admin set "+item+"='"+value+"' where userName='"+userName+"'";
		dataDAO.updateData(hql);
	}

}
