package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.SaleRecordBean;
import com.gwb.dao.SaleDAO;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;

@Component("saleDAO")
public class SaleDAOImpl implements SaleDAO
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

	// 获取销售记录
	@Override
	public Map<String, Object> loadSaleRecord(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);

		Map<String, String> conditions = pager.getCondition();
		String temp = "where i.id!=0 ";

		String keyword = conditions.get("keyword");
		String startTime = conditions.get("startTime");
		String endTime = conditions.get("endTime");

		if (!"".equals(keyword) && keyword != null)
		{
			temp += " and (i.order.orderId like '%" + keyword + "%' or i.consultant.name like '%" + keyword
					+ "%' or i.consultant.mobilePhone like '%" + keyword + "%')";
		}

		if ("".equals(startTime) || startTime == null)
		{
			// 起始时间为空
			startTime = DateUtil.DateToString(new Date(), "yyyy-MM") + "-01";
			conditions.put("startTime", startTime);
		}
		if ("".equals(endTime) || endTime == null)
		{
			endTime = DateUtil.DateToString(new Date(), "yyyy-MM-dd");
			conditions.put("endTime", endTime);
		}

		temp += " and i.createTime >'" + startTime + " 00:00:00'";
		temp += " and i.createTime <'" + endTime + " 23:59:59'";

		String hql = "select new com.gwb.bean.SaleRecordBean(i.id,i.percent,i.money,"
				+ "i.createTime, i.order.orderId,i.order.id, i.consultant.id,i.consultant.mobilePhone,i.consultant.name)"
				+ "from Income i " + temp + " order by i.createTime desc";
		List<SaleRecordBean> beans = dataDAO.loadItems(hql, pager);
		hql = "select count(*) from Income i " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("saleRecord", beans);
		res.put("pager", pager);
		return res;
	}

	// 导出顾问的销售列表
	@Override
	public List<SaleRecordBean> exportSaleList(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = "where i.id!=0 ";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!"".equals(keyword))
			{
				temp += " and i.consultant.mobilePhone like '%" + keyword + "%'";
			}
			String startTime = conditions.get("startTime");
			if (!"".equals(startTime))
			{
				temp += " and i.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!"".equals(endTime))
			{
				temp += " and i.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String hql = "select new com.gwb.bean.SaleRecordBean(i.id,i.percent,i.money,"
				+ "i.createTime, i.order.orderId,i.order.id, i.consultant.id,i.consultant.mobilePhone,i.consultant.name)"
				+ "from Income i " + temp + " order by i.createTime";
		List<SaleRecordBean> beans = dataDAO.loadItems(hql);
		return beans;
	}

	// 顾问销售统计
	@Override
	public Map<String, Object> loadConsSaleStatics(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = "";
		String with = "";

		String startTime = conditions.get("startTime");
		String endTime = conditions.get("endTime");
		String keyword = conditions.get("keyword");
		if (!"".equals(keyword) && keyword != null)
		{
			temp += " where (c.name like '%" + keyword + "%' or c.mobilePhone like '%" + keyword + "%')";
		}

		if ("".equals(startTime) || startTime == null)
		{
			// 起始时间为空
			startTime = DateUtil.DateToString(new Date(), "yyyy-MM") + "-01";
			conditions.put("startTime", startTime);
		}
		if ("".equals(endTime) || endTime == null)
		{
			endTime = DateUtil.DateToString(new Date(), "yyyy-MM-dd");
			conditions.put("endTime", endTime);
		}

		with += " and i.createTime >'" + startTime + " 00:00:00'";
		with += " and i.createTime <'" + endTime + " 23:59:59'";

		String sql = "select c.name consName,c.mobilePhone consMobilePhone,sum(i.percent+i.money),sum(i.money),"
				+ " sum(i.percent),count(i.income_id) num from t_income i right join t_consultant c on i.cons_id=c.cons_id"
				+ with + temp + " group by c.cons_id order by money desc";
		List<Object[]> res = dataDAO.SQLQuery(sql, pager);
		sql = "select count(*) from Consultant c" + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(sql));
		pager.caculatePageNum();
		Map<String, Object> map = new HashMap<>();
		map.put("res", res);
		map.put("pager", pager);
		return map;
	}

	// 导出顾问销售总额统计
	@Override
	public List<Object[]> exportSaleStatics(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String with = "";
		if (!conditions.isEmpty())
		{
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				with += " and i.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				with += " and i.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String sql = "select c.name consName,c.mobilePhone consMobilePhone,sum(i.percent+i.money),sum(i.money),"
				+ "sum(i.percent),count(i.income_id) num from t_income i right join t_consultant c on i.cons_id=c.cons_id"
				+ with + " group by c.cons_id order by money desc";
		List<Object[]> res = dataDAO.SQLQuery(sql);
		return res;
	}

	// 平台日销售统计
	@Override
	public Map<String, Object> loadAppSaleStatisticsByDay(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = "";
		String startTime = conditions.get("startTime");
		String endTime = conditions.get("endTime");

		if ("".equals(startTime) || startTime == null)
		{
			// 起始时间为空
			startTime = DateUtil.DateToString(new Date(), "yyyy-MM") + "-01";
			conditions.put("startTime", startTime);
		}
		if ("".equals(endTime) || endTime == null)
		{
			endTime = DateUtil.DateToString(new Date(), "yyyy-MM-dd");
			conditions.put("endTime", endTime);
		}

		temp += " where t.d >='" + startTime + "'";
		temp += " and t.d <='" + endTime + "'";

		String sql = "SELECT DATE_FORMAT(t.d, \"%Y-%m-%d\") as dateTime,sum(i.money+i.percent),"
				+ "sum(i.money),sum(i.percent),count(i.income_id) FROM t_tempDate t LEFT outer "
				+ "JOIN t_income i ON t.d = DATE_FORMAT(i.createTime, \"%Y-%m-%d\") ";
		sql += temp + " GROUP BY dateTime ORDER BY dateTime";

		List<Object[]> list = dataDAO.SQLQuery(sql, pager);
		sql = "select count(*) from TempDate t " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(sql));
		pager.caculatePageNum();

		Map<String, Object> res = new HashMap<>();
		res.put("list", list);
		res.put("pager", pager);
		return res;
	}

	// 导出平台日销售统计
	@Override
	public List<Object[]> exportAppSaleStatisticByDay(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = "";
		String startTime = conditions.get("startTime");
		String endTime = conditions.get("endTime");

		temp += " where t.d >='" + startTime + "'";
		temp += " and t.d <='" + endTime + "'";

		String sql = "SELECT DATE_FORMAT(t.d, \"%Y-%m-%d\") as dateTime,sum(i.money+i.percent),"
				+ "sum(i.money),sum(i.percent),count(i.income_id) FROM t_tempDate t LEFT outer "
				+ "JOIN t_income i ON t.d = DATE_FORMAT(i.createTime, \"%Y-%m-%d\") ";
		sql += temp + " GROUP BY dateTime ORDER BY dateTime";

		List<Object[]> list = dataDAO.SQLQuery(sql);
		return list;
	}

	// 平台月销售统计
	@Override
	public Map<String, Object> loadAppSaleStatisticsByMonth(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = "";
		String year = conditions.get("yearChoose");
		if ("".equals(year) || year == null)
		{
			year = DateUtil.DateToString(new Date(), "yyyy");
			conditions.put("yearChoose", year);
		}
		temp = " where DATE_FORMAT(i.createTime,\"%Y\")='" + year + "'";

		String sql = "SELECT DATE_FORMAT(i.createTime, \"%Y-%m\")mon,sum(i.money+i.percent),"
				+ "sum(i.money),sum(i.percent),count(i.income_id) FROM t_income i";

		sql += temp + " GROUP BY mon";
		List<Object[]> list = dataDAO.SQLQuery(sql);

		for (int i = 1; i <= 12; i++)
		{
			String mon = year + String.format("-%02d", i);
			boolean flag = true;
			for (Object[] object : list)
			{
				if (object[0].equals(mon))
				{
					flag = false;
					break;
				}
			}
			if (flag)
			{
				Object[] objects = new Object[5];
				objects[0] = mon;
				objects[1] = 0;
				objects[2] = 0;
				objects[3] = 0;
				list.add(i - 1, objects);
			}
		}

		Map<String, Object> res = new HashMap<>();
		res.put("list", list);
		res.put("pager", pager);
		return res;
	}

	// 导出平台月销售统计
	@Override
	public List<Object[]> exportAppSaleStatisticByMonth(Pager pager)
	{
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = "";
		String year = conditions.get("yearChoose");
		temp = " where DATE_FORMAT(i.createTime,\"%Y\")='" + year + "'";

		String sql = "SELECT DATE_FORMAT(i.createTime, \"%Y-%m\")mon,sum(i.money+i.percent),"
				+ "sum(i.money),sum(i.percent),count(i.income_id) FROM t_income i";

		sql += temp + " GROUP BY mon";
		List<Object[]> list = dataDAO.SQLQuery(sql);

		for (int i = 1; i <= 12; i++)
		{
			String mon = year + String.format("-%02d", i);
			boolean flag = true;
			for (Object[] object : list)
			{
				if (object[0].equals(mon))
				{
					flag = false;
					break;
				}
			}
			if (flag)
			{
				Object[] objects = new Object[5];
				objects[0] = mon;
				objects[1] = 0.0;
				objects[2] = 0.0;
				objects[3] = 0.0;
				list.add(i - 1, objects);
			}
		}
		return list;
	}

}
