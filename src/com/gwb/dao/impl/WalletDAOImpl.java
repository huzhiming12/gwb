package com.gwb.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.FinancialDetailBean;
import com.gwb.bean.Pager;
import com.gwb.bean.WalletBean;
import com.gwb.bean.WithDrawBean;
import com.gwb.dao.WalletDAO;
import com.gwb.model.FinancialDetail;
import com.gwb.model.Wallet;
import com.gwb.model.WithDraw;
import com.gwb.util.Config;

@Component("walletDAO")
public class WalletDAOImpl implements WalletDAO
{

	private String str = "select new com.gwb.bean.WalletBean(w.id, w.balance, "
			+ "w.withDrawNum, w.withDrawed, w.password, w.consultant.id,w.consultant.name,w.consultant.mobilePhone)from Wallet w ";

	private String withSQL = "select new com.gwb.bean.WithDrawBean(w.id,w.withNo,w.name,w.account,w.money,"
			+ "w.consultant.id,w.consultant.name,w.consultant.mobilePhone,w.createTime,"
			+ "w.dealTime,w.state,w.reason)from WithDraw w ";
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
	 * @function：添加钱包
	 */
	@Override
	public boolean addWallet(Wallet wallet)
	{
		return dataDAO.addItem(wallet);
	}

	/**
	 * @author:huzhiming
	 * @function：添加财务明细
	 */
	@Override
	public boolean addFinancialDetail(FinancialDetail detail)
	{
		WalletBean walletBean = walletDetail(detail.getConsultant().getId());
		float money = walletBean.getBalance();
		String str = "";
		if (detail.getType() == 0)
			money = money + detail.getMoney();
		else
		{
			money = money - detail.getMoney();
			str = " ,withDrawed=withDrawed+1 ";
		}
		String hql = "update Wallet set balance=" + money + str + "where id=" + walletBean.getId();
		// 更改钱包余额
		dataDAO.updateData(hql);
		return dataDAO.addItem(detail);
	}

	// 添加提现申请
	@Override
	public boolean addWithdraw(WithDraw withDraw)
	{
		return dataDAO.addItem(withDraw);
	}

	// 获取提现记录
	@Override
	public Map<String, Object> getWithDrawList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = " where w.id!=0";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!keyword.equals(""))
			{
				temp += " and (w.consultant.name like '%" + keyword + "%' or w.account like '%" + keyword
						+ "%' or w.name like '%" + keyword + "%')";
			}
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and w.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and w.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String hql = withSQL + temp + " order by w.createTime desc";
		List<WithDrawBean> beans = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from WithDraw w " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("withDraw", beans);
		res.put("pager", pager);
		return res;
	}

	// 获取支付宝未处理的提现记录
	@Override
	public List<WithDrawBean> loadAlipayWithdrawList()
	{
		String hql = withSQL + " where w.state=0 order by createTime desc";
		return dataDAO.loadItems(hql);
	}

	// 更新提现记录
	@Override
	public void updateWithdraw(String conditionItem, String conditionValue, String item, String value)
	{
		String hql = "update WithDraw set " + item + "= '" + value + "' where " + conditionItem + "='" + conditionValue
				+ "'";
		dataDAO.updateData(hql);
	}

	// 获取提现详情
	@Override
	public WithDrawBean withdrawDetail(String conditionItem, String conditionValue)
	{
		String hql = withSQL + " where w." + conditionItem + "='" + conditionValue + "'";
		List<WithDrawBean> list = dataDAO.loadItems(hql);
		if (list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;

	}

	// 导出提现申请
	@Override
	public List<WithDrawBean> exportWithDrawList(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = " where w.id!=0";
		if (!conditions.isEmpty())
		{
			String state = conditions.get("state");
			if (!state.equals("-1"))
			{
				temp += " and w.state=" + state;
			}
			String startTime = conditions.get("startTime");
			if (!startTime.equals(""))
			{
				temp += " and w.createTime >'" + startTime + " 00:00:00'";
			}
			String endTime = conditions.get("endTime");
			if (!endTime.equals(""))
			{
				temp += " and w.createTime <'" + endTime + " 23:59:59'";
			}
		}
		String hql = withSQL + temp + " order by w.state, w.createTime desc";
		List<WithDrawBean> beans = dataDAO.loadItems(hql);
		return beans;
	}

	/**
	 * @author:huzhiming
	 * @function：更新钱包信息
	 */
	@Override
	public void updateWallet(String consId, String item, String value)
	{
		String hql = "update Wallet set " + item + "='" + value + "' where consultant.id='" + consId + "'";
		dataDAO.updateData(hql);
	}

	/**
	 * @author:huzhiming
	 * @function：钱包信息
	 */
	@Override
	public WalletBean walletDetail(String consId)
	{
		String hql = str + " where w.consultant.id='" + consId + "'";
		List<WalletBean> walletBeans = dataDAO.loadItems(hql);
		if (walletBeans != null && walletBeans.size() > 0)
		{
			return walletBeans.get(0);
		}
		return null;
	}

	// 查询顾问未处理的提现请求
	@Override
	public List<WithDrawBean> loadUserUndealWithDraw(String consId)
	{
		String hql = withSQL + " where w.consultant='" + consId + "' and w.state=0";
		List<WithDrawBean> list = dataDAO.loadItems(hql);
		return list;
	}

	/**
	 * @author:huzhiming
	 * @function：加载顾问的财务明细
	 */
	@Override
	public Map<String, Object> loadFinancialDetail(String consId, Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.FINANCIAL_DETAIL_PAGER_SIZE);
		String hql = "select new com.gwb.bean.FinancialDetailBean(f.id, f.type, "
				+ "f.money, f.createTime,f.consultant.id,i.id,w.id)from FinancialDetail f "
				+ "left join f.income i left join f.withDraw w where f.consultant.id ='" + consId + "'"
				+ " order by f.createTime desc";
		List<FinancialDetailBean> detailBeans = dataDAO.loadItems(hql, pager);
		hql = "select count(*) from FinancialDetail f where f.consultant.id='" + consId + "'";
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> map = new HashMap<>();
		map.put("detail", detailBeans);
		map.put("pager", pager);

		return map;
	}

	// 钱包列表
	@Override
	public Map<String, Object> loadWalletList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String temp = " where w.consultant.state=1";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (!keyword.equals(""))
			{
				temp += " and (w.consultant.name like '%" + keyword + "%' or w.consultant.mobilePhone like '%" + keyword
						+ "')";
			}
		}
		String hql = str + temp + " order by w.balance desc ";
		List<WalletBean> beans = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Wallet w " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("wallet", beans);
		res.put("pager", pager);
		return res;
	}

	// 导出钱包列表
	@Override
	public List<WalletBean> exportWallet()
	{
		String hql = str + " order by w.balance desc ";
		List<WalletBean> beans = dataDAO.loadItems(hql);
		return beans;
	}

	// 体现次数清零
	@Override
	public void clearWithDrawedNum()
	{
		String hql = "update Wallet set withDrawed=0";
		dataDAO.updateData(hql);
	}

}
