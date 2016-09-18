package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.bean.SaleRecordBean;

public interface SaleDAO
{
	// 获取销售记录
	public Map<String, Object> loadSaleRecord(Pager pager);

	// 导出顾问销售列表
	public List<SaleRecordBean> exportSaleList(Pager pager);

	// 顾问销售统计
	public Map<String, Object> loadConsSaleStatics(Pager pager);

	// 导出顾问销售总额
	public List<Object[]> exportSaleStatics(Pager pager);

	// 获取平台的日销售记录
	public Map<String, Object> loadAppSaleStatisticsByDay(Pager pager);

	// 导出平台的日销售记录
	public List<Object[]> exportAppSaleStatisticByDay(Pager pager);

	// 获取平台的月销售记录
	public Map<String, Object> loadAppSaleStatisticsByMonth(Pager pager);

	// 导出平台的日销售记录
	public List<Object[]> exportAppSaleStatisticByMonth(Pager pager);
}
