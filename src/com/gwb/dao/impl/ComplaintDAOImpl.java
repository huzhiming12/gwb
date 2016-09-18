package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.ComplaintBean;
import com.gwb.bean.Pager;
import com.gwb.dao.ComplaintDAO;
import com.gwb.model.Complaint;
import com.gwb.util.Config;

@Component("complaintDAO")
public class ComplaintDAOImpl implements ComplaintDAO
{
	String sql = "select new com.gwb.bean.ComplaintBean(c.id,c.content,c.user.id,"
			+ "c.user.name,c.consultant.id,c.consultant.name,c.order.orderId,c.createTime)from Complaint c";

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
	 * @function：添加投诉
	 */
	@Override
	public boolean addComplaint(Complaint complaint)
	{
		return dataDAO.addItem(complaint);
	}

	/**
	 * @author:huzhiming
	 * @function：查询订单投诉
	 */
	@Override
	public ComplaintBean getOrderComplaint(int orderId)
	{
		String hql = sql + " where c.order.id =" + orderId;
		List<ComplaintBean> beans = dataDAO.loadItems(hql);
		if (beans != null && beans.size() > 0)
			return beans.get(0);
		return null;
	}

	// 加载投诉列表
	@Override
	public Map<String, Object> loadComplaintList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> condition = pager.getCondition();
		String keyword = condition.get("keyword");
		String temp = "";
		if (keyword != null && !"".equals("keyword"))
		{
			temp = " where c.user.name like '%" + keyword + "%' or c.consultant.name like '%" + keyword
					+ "%' or c.order.orderId like '%" + keyword + "%'";
		}
		String hql = sql + temp;
		List<ComplaintBean> list = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Complaint c " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("complaint", list);
		res.put("pager", pager);
		return res;
	}

}
