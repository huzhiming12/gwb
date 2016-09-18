package com.gwb.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gwb.bean.ResponseBean;
import com.gwb.dao.ResponseDAO;
import com.gwb.model.Response;
import com.gwb.util.DateUtil;

@Component("responseDAO")
public class ResponseDAOImpl implements ResponseDAO
{
	String sql = "select new com.gwb.bean.ResponseBean(r.id,r.content,r.consultant.id,"
			+ "r.consultant.mobilePhone,r.consultant.name,r.consultant.icon,r.userState,"
			+ "r.respType,r.createTime,r.dealTime) from Response r ";

	private HibernateTemplate hibernateTemplate;
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

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	// 获取回应次数
	@Override
	public int getResponseNum(int id)
	{
		String hql = "select count(*) from Response where respType=0 and request.id = " + id;
		List<Long> num = dataDAO.loadItems(hql);
		if (num != null && num.size() > 0)
		{
			return num.get(0).intValue();
		}
		return 0;
	}

	// 添加回应
	@Override
	public boolean addResponse(Response response)
	{
		return dataDAO.addItem(response);
	}

	// 更新回应内容
	@Override
	public void updateResponse(String item, String value, int id)
	{
		String hql = "update Response set " + item + " =" + value + " where id = " + id;
		dataDAO.updateData(hql);
	}

	// 更新条目
	@Override
	public void updateItem(Response response)
	{
		dataDAO.updateItem(response);
	}

	// 更新状态
	@Override
	public void updateResponseState(int id, int state)
	{
		String date = DateUtil.DateToString(new Date());
		String hql = "update Response set userState=" + state + ",dealTime='" + date + "'where id=" + id;
		dataDAO.updateData(hql);
	}

	// 获取回应的详细内容
	@Override
	public Response responseDetail(int id)
	{
		String hql = "from Response where id = " + id;
		List<Response> responses = dataDAO.loadItems(hql);
		if (responses != null && responses.size() > 0)
		{
			return responses.get(0);
		}
		return null;
	}

	// 通过请求id查找回应
	@Override
	public List<ResponseBean> loadResponseByReqId(int id)
	{
		String hql = sql + " where r.request.id = " + id;
		return dataDAO.loadItems(hql);
	}

}
