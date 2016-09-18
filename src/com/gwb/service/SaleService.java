package com.gwb.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.Pager;
import com.gwb.bean.SaleRecordBean;
import com.gwb.dao.SaleDAO;
import com.gwb.excel.AppSaleEx;
import com.gwb.excel.SaleRecordEx;
import com.gwb.excel.SaleStatisticEx;

@Component("saleService")
public class SaleService
{

	private SaleDAO saleDAO;

	public SaleDAO getSaleDAO()
	{
		return saleDAO;
	}

	@Resource(name = "saleDAO")
	public void setSaleDAO(SaleDAO saleDAO)
	{
		this.saleDAO = saleDAO;
	}

	// 获取销售记录
	@Transactional
	public Map<String, Object> getSaleRecord(Pager pager)
	{
		return saleDAO.loadSaleRecord(pager);
	}

	// 导出顾问的销售列表
	@Transactional
	public List<SaleRecordEx> exportSaleList(Pager pager)
	{
		List<SaleRecordEx> list = new ArrayList<>();
		List<SaleRecordBean> beans = saleDAO.exportSaleList(pager);
		for (SaleRecordBean bean : beans)
		{
			SaleRecordEx ex = new SaleRecordEx();
			ex.setOid(bean.getOid());
			ex.setOrderId(bean.getOrderId());
			ex.setTotal(bean.getMoney() + bean.getPercent());
			ex.setConsMobilePhone(bean.getConsMobilePhone());
			ex.setConsName(bean.getConsName());
			ex.setMoney(bean.getMoney());
			ex.setPercent(bean.getPercent());
			ex.setCreateTime(bean.getCreateTime());
			list.add(ex);
		}
		return list;
	}

	// 销售统计
	@Transactional
	public Map<String, Object> loadSaleStatics(Pager pager)
	{
		Map<String, Object> map = saleDAO.loadConsSaleStatics(pager);
		return map;
	}

	// 导出顾问销售总额
	@Transactional
	public List<SaleStatisticEx> exportSaleStatistic(Pager pager)
	{
		List<Object[]> objects = saleDAO.exportSaleStatics(pager);
		List<SaleStatisticEx> list = new ArrayList<>();
		for (Object[] object : objects)
		{
			SaleStatisticEx ex = new SaleStatisticEx();
			ex.setConsMobilePhone((String) object[0]);
			ex.setConsName((String) object[1]);
			if (object[2] == null)
				ex.setTotal(0F);
			else
				ex.setTotal(new Float((double) object[2]));
			if (object[3] == null)
				ex.setMoney(0F);
			else
				ex.setMoney(new Float((double) object[3]));

			if (object[4] == null)
				ex.setPercent(0F);
			else
				ex.setPercent(new Float((double) object[4]));
			ex.setNum(((BigInteger) object[5]).intValue());
			list.add(ex);
		}
		return list;
	}

	// 平台销售统计
	@Transactional
	public Map<String, Object> loadAppSaleStatistics(Pager pager)
	{
		Map<String, String> condition = pager.getCondition();
		if (!condition.isEmpty())
		{
			if ("1".equals(condition.get("flag")))
				return saleDAO.loadAppSaleStatisticsByMonth(pager);
		}
		return saleDAO.loadAppSaleStatisticsByDay(pager);
	}

	// 导出平台收入
	@Transactional
	public List<AppSaleEx> expertAppSaleStatistic(Pager pager, int flag)
	{
		List<Object[]> objects=null;
		if (flag==0)
			objects = saleDAO.exportAppSaleStatisticByDay(pager);
		else 
			objects = saleDAO.exportAppSaleStatisticByMonth(pager);
		List<AppSaleEx> list = new ArrayList<>();
		for (Object[] object : objects)
		{
			AppSaleEx ex = new AppSaleEx();
			ex.setTime((String) object[0]);
			if (object[1] == null)
				ex.setTotal(0);
			else
				ex.setTotal((double) object[1]);
			if (object[2] == null)
				ex.setMoney(0);
			else
				ex.setMoney((double) object[2]);
			if (object[3] == null)
				ex.setPercent(0);
			else
				ex.setPercent((double) object[3]);
			if (object[4] == null)
				ex.setNum(0);
			else
				ex.setNum(((BigInteger) object[4]).intValue());
			list.add(ex);
		}
		return list;

	}

}
