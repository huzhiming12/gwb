package com.gwb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gwb.bean.CardBean;
import com.gwb.dao.CardDAO;
import com.gwb.model.Card;

@Component("cardDAO")
public class CardDAOImpl implements CardDAO
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
	 * @function：添加名片
	 */
	@Override
	public boolean addcard(Card card)
	{
		return dataDAO.addItem(card);
	}

	

	/**
	 * @author:huzhiming
	 * @function：通过名片id删除名片
	 * @field:
	 * @return_type:
	 */
	@Override
	public boolean delCardById(int id)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @author:huzhiming
	 * @function：加载用户名片
	 * @field:
	 * @return_type:
	 */
	@Override
	public List<CardBean> loadCards(String userId)
	{
		String hql = "select new com.gwb.bean.CardBean(c.id,c.url)from Card c where c.userId='" + userId + "'";
		List<CardBean> cards = dataDAO.loadItems(hql);
		return cards;
	}

}
