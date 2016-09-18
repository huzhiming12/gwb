package com.gwb.admin.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.WalletBean;
import com.gwb.bean.WithDrawBean;
import com.gwb.excel.DownLoad;
import com.gwb.excel.ExcelTool;
import com.gwb.excel.WalletEx;
import com.gwb.excel.WithDrawEx;
import com.gwb.service.WalletService;
import com.gwb.util.PagerTool;
import com.opensymphony.xwork2.ActionSupport;

@Component("adWalletAction")
@Scope("protoType")
public class WalletAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;

	private Pager pager;
	private PagerTool pagerTool;
	private List<WithDrawBean> withDrawBeans;
	private List<WalletBean> walletBeans;
	private WalletService walletService;
	private String selector;
	private Map<String, Object> res;

	public Map<String, Object> getRes()
	{
		return res;
	}

	public void setRes(Map<String, Object> res)
	{
		this.res = res;
	}

	public String getSelector()
	{
		return selector;
	}

	public void setSelector(String selector)
	{
		this.selector = selector;
	}

	public List<WalletBean> getWalletBeans()
	{
		return walletBeans;
	}

	public void setWalletBeans(List<WalletBean> walletBeans)
	{
		this.walletBeans = walletBeans;
	}

	public PagerTool getPagerTool()
	{
		return pagerTool;
	}

	public void setPagerTool(PagerTool pagerTool)
	{
		this.pagerTool = pagerTool;
	}

	public WalletService getWalletService()
	{
		return walletService;
	}

	@Resource(name = "walletService")
	public void setWalletService(WalletService walletService)
	{
		this.walletService = walletService;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<WithDrawBean> getWithDrawBeans()
	{
		return withDrawBeans;
	}

	public void setWithDrawBeans(List<WithDrawBean> withDrawBeans)
	{
		this.withDrawBeans = withDrawBeans;
	}

	// 提现记录
	@SuppressWarnings("unchecked")
	public String withDrawList()
	{
		Map<String, Object> map = walletService.loadWithDrawList(pager);
		withDrawBeans = (List<WithDrawBean>) map.get("withDraw");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/wallet_withDrawList");
		selector = "withDrawList";
		return SUCCESS;
	}

	// 钱包列表
	@SuppressWarnings("unchecked")
	public String walletList()
	{
		Map<String, Object> map = walletService.walletList(pager);
		walletBeans = (List<WalletBean>) map.get("wallet");
		pager = (Pager) map.get("pager");
		pagerTool = new PagerTool(pager, "admin/wallet_walletList");
		selector = "walletList";
		return SUCCESS;
	}

	// 支付宝提现列表
	public String alipayWithdrawList()
	{
		res = walletService.alipayWithdrawList();
		return SUCCESS;
	}


	// 导出提现列表
	public void exportWithDrawList()
	{
		ExcelTool<WithDrawEx> ex = new ExcelTool<>();
		String[] headers = { "流水号No", "顾问账号", "顾问姓名", "提现支付宝账号", "真实姓名", "提现金额", "提现时间", "处理时间", "处理状态" };
		List<WithDrawEx> withDrawExs = walletService.exportWithDrawList(pager);
		OutputStream out;
		try
		{
			out = new FileOutputStream("withDrawList.xls");
			ex.exportExcel("提现申请列表", headers, withDrawExs, out);
			out.close();
			DownLoad.download("withDrawList.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 导出钱包列表
	public void exportWallet()
	{
		ExcelTool<WalletEx> ex = new ExcelTool<>();
		String[] headers = { "顾问账号", "顾问姓名", "账户余额", "可提现金额", "暂不可提现金额", "可提现次数", "今日已提现次数" };
		List<WalletEx> walletExs = walletService.exportWallet();
		OutputStream out;
		try
		{
			out = new FileOutputStream("WalletList.xls");
			ex.exportExcel("顾问资产列表", headers, walletExs, out);
			out.close();
			DownLoad.download("WalletList.xls", ServletActionContext.getResponse());
		} catch (Exception e)
		{
			e.printStackTrace();

		}

	}
}
