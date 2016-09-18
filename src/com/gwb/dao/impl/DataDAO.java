package com.gwb.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;

@Component("dataDAO")
public class DataDAO
{
	private HibernateTemplate hibernateTemplate;

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
	 * @function：从数据库中加载信息
	 * @field: hql语句
	 * @List<T>:
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> loadItems(String hql)
	{
		List<T> items = new ArrayList<>();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		for (Object object : query.list())
		{
			items.add((T) object);
		}
		// transaction.commit();
		return items;
	}

	/**
	 * @author:huzhiming
	 * @function：从数据库中分页加载
	 * @field: hql语句
	 * @List<T>:
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> loadItems(String hql, Pager pager)
	{
		List<T> items = new ArrayList<>();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setFirstResult((pager.getPageNow() - 1) * pager.getPageSize()); // 开始记录
		query.setMaxResults(pager.getPageSize());// 显示条数
		for (Object object : query.list())
		{
			items.add((T) object);
		}
		// transaction.commit();
		return items;
	}

	// 原生sql语句
	@SuppressWarnings("unchecked")
	public List<Object[]> SQLQuery(String sql, Pager pager)
	{

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		query.setFirstResult((pager.getPageNow() - 1) * pager.getPageSize());
		// // 开始记录
		query.setMaxResults(pager.getPageSize());// 显示条数

		return query.list();

	}

	// 原生sql语句
	@SuppressWarnings("unchecked")
	public List<Object[]> SQLQuery(String sql)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		return query.list();
	}

	public void SQLUpdate(String hql)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createSQLQuery(hql).executeUpdate();
		// transaction.commit();
		// session.close();
	}
	
	public int SQLItemsNum(String sql)
	{
		int num = 0;
		// 这个session不用手动关闭
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		Object object = query.uniqueResult();
		if (object != null)
			num = ((BigInteger) object).intValue();
		return num;
	}

	/**
	 * @author:huzhiming
	 * @function：查找数据条数
	 */
	public int loadItemsNum(String hql)
	{
		int num = 0;
		// 这个session不用手动关闭
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(hql);
		Object object = query.uniqueResult();
		if (object != null)
			num = ((Long) object).intValue();
		return num;
	}
	
	

	/**
	 * @author:huzhiming
	 * @function：更新数据
	 * @field:
	 * @boolean:
	 */
	public void updateData(String hql)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		// transaction.commit();
		// session.close();
	}

	/**
	 * @author:huzhiming
	 * @function：添加数据
	 * @field:
	 * @return_type:
	 */
	public <T> boolean addItem(T item)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Serializable num = session.save(item);
		// transaction.commit();
		// session.close();
		if (num != null)
			return true;
		else
			return false;
	}

	public <T> int addItemForId(T item)
	{
		int result = (int) hibernateTemplate.save(item);
		return result;
	}

	/**
	 * @author:huzhiming
	 * @function：删除数据
	 * @field:
	 * @boolean:
	 */
	public <T> void delItem(T item)
	{
		hibernateTemplate.delete(item);
	}

	/**
	 * @author:huzhiming
	 * @function：更新单条数据
	 * @field:
	 * @return_type:
	 */
	public <T> void updateItem(T item)
	{
		hibernateTemplate.update(item);
	}

}
