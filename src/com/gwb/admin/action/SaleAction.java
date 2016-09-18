package com.gwb.admin.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.SaleRecordBean;
import com.gwb.excel.AppSaleEx;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.excel.SaleRecordEx;
import com.gwb.excel.SaleStatisticEx;
import com.gwb.service.SaleService;
import com.gwb.util.DateUtil;
import com.gwb.util.PagerTool;
import com.opensymphony.xwork2.ActionSupport;

@Component("adSaleAction")
@Scope("protoType")
public class SaleAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private SaleService saleService;
	private List<SaleRecordBean> recordBeans;
	private Pager pager;
	private PagerTool pagerTool;
	private List<Object[]> statisticsBeans;
	private String selector;

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public List<Object[]> getStatisticsBeans()
	{
		return statisticsBeans;
	}

	public void setStatisticsBeans(List<Object[]> statisticsBeans)
	{
		this.statisticsBeans = statisticsBeans;
	}

	public List<SaleRecordBean> getRecordBeans()
	{
		return recordBeans;
	}

	public void setRecordBeans(List<SaleRecordBean> recordBeans)
	{
		this.recordBeans = recordBeans;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public PagerTool getPagerTool()
	{
		return pagerTool;
	}

	public void setPagerTool(PagerTool pagerTool)
	{
		this.pagerTool = pagerTool;
	}

	// =====================================

	public SaleService getSaleService()
	{
		return saleService;
	}

	@Resource(name = "saleService")
	public void setSaleService(SaleService saleService)
	{
		this.saleService = saleService;
	}

	// 销售记录
	@SuppressWarnings("unchecked")
	public String saleList()
	{
		Map<String, Object> map = saleService.getSaleRecord(pager);
		recordBeans = (List<SaleRecordBean>) map.get("saleRecord");
		pager = (Pager) map.get("pager");
		// pager.getCondition().put("yearNow", DateUtil.DateToString(new Date(),
		// "yyyy"));
		pagerTool = new PagerTool(pager, "admin/sale_saleList");
		selector = "saleList";
		return SUCCESS;
	}

	// 导出销售记录
	public void exportSaleList()
	{
		ExcelTool<SaleRecordEx> ex = new ExcelTool<>();
		String[] headers = { "订单ID", "订单编号NO.", "总金额", "顾问账号", "顾问姓名", "顾问收入", "平台收入", "创建时间" };
		List<SaleRecordEx> saleRecordExs = saleService.exportSaleList(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("SaleRecord.xls");
			ex.exportExcel("顾问销售记录", headers, saleRecordExs, out);
			out.close();
			DownLoad.download("SaleRecord.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	// 销售统计
	@SuppressWarnings("unchecked")
	public String saleStatistic()
	{
		Map<String, Object> res = saleService.loadSaleStatics(pager);
		statisticsBeans = (List<Object[]>) res.get("res");
		pager = (Pager) res.get("pager");
		pagerTool = new PagerTool(pager, "admin/sale_saleStatistic");
		selector = "saleStatistic";
		return SUCCESS;
	}

	// 导出销售统计
	public void exportSaleStatistic()
	{
		ExcelTool<SaleStatisticEx> ex = new ExcelTool<>();
		String[] headers = { "顾问账号", "顾问姓名", "销售总额", "顾问收入", "平台收入", "销售数量" };
		List<SaleStatisticEx> saleStatisticExs = saleService.exportSaleStatistic(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("SaleStatistic.xls");
			ex.exportExcel("顾问销售总额统计", headers, saleStatisticExs, out);
			out.close();
			DownLoad.download("SaleStatistic.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	// 平台收入统计
	@SuppressWarnings("unchecked")
	public String appSaleStatistic()
	{
		Map<String, Object> map = saleService.loadAppSaleStatistics(pager);
		statisticsBeans = (List<Object[]>) map.get("list");
		pager = (Pager) map.get("pager");
		pager.getCondition().put("yearNow", DateUtil.DateToString(new Date(), "yyyy"));

		pagerTool = new PagerTool(pager, "admin/sale_appSaleStatistic");
		selector = "appSaleStatistic";
		return SUCCESS;
	}

	// 导出平台日收入（销售）统计
	public void exportAppDaySaleStatistic()
	{
		ExcelTool<AppSaleEx> ex = new ExcelTool<>();
		String[] headers = { "日期", "销售总额", "顾问收入", "平台收入", "销售数量" };
		List<AppSaleEx> appSaleExs = saleService.expertAppSaleStatistic(pager,0);
		OutputStream out;
		try
		{
			out = new FileOutputStream("AppSaleStatistic.xls");
			ex.exportExcel("平台日销售额统计", headers, appSaleExs, out);
			out.close();
			DownLoad.download("AppSaleStatistic.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//导出平台月销售总额
	public void exportAppMonthSaleStatistic()
	{
		ExcelTool<AppSaleEx> ex = new ExcelTool<>();
		String[] headers = { "月份", "销售总额", "顾问收入", "平台收入", "销售数量" };
		List<AppSaleEx> appSaleExs = saleService.expertAppSaleStatistic(pager,1);
		OutputStream out;
		try
		{
			out = new FileOutputStream("AppSaleStatistic.xls");
			ex.exportExcel("平台月销售额统计", headers, appSaleExs, out);
			out.close();
			DownLoad.download("AppSaleStatistic.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
