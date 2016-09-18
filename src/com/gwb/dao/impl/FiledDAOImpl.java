package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gwb.bean.FieldBean;
import com.gwb.bean.Pager;
import com.gwb.dao.FieldDAO;
import com.gwb.model.Field;
import com.gwb.util.Config;

@Component("fieldDAO")
public class FiledDAOImpl implements FieldDAO
{

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

	/**
	 * @author:huzhiming
	 * @function：添加领域列表
	 * @field:
	 * @return_type:
	 */
	@Override
	public boolean addField(Field field)
	{
		return dataDAO.addItem(field);
	}

	/**
	 * @author:huzhiming
	 * @function：删除咨询领域
	 * @field:
	 * @return_type:
	 */
	@Override
	public void delField(Field field)
	{
		String hql = "update Field c set state=1 where c.id = " + field.getId();
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：加载所有领域
	 * @field:
	 * @return_type:
	 */
	@Override
	public Map<String, Object> loadAllFields(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = " where f.state=0 ";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!keyword.equals(""))
			{
				temp += " and (f.title like '%" + keyword + "%' or f.description like '%" + keyword + "%')";
			}
		}
		String hql = "from Field f " + temp;
		List<FieldBean> fieldBeans = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Field f " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> res = new HashMap<>();
		res.put("field", fieldBeans);
		res.put("pager", pager);
		return res;
	}

	@Override
	public List<FieldBean> loadAllFields()
	{
		String hql = " from Field f where f.state=0";
		return dataDAO.loadItems(hql);
	}

}
