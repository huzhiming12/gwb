package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.bean.WalletBean;
import com.gwb.bean.WithDrawBean;
import com.gwb.model.FinancialDetail;
import com.gwb.model.Wallet;
import com.gwb.model.WithDraw;

public interface WalletDAO
{
	// 添加钱包
	public boolean addWallet(Wallet wallet);

	// 添加财务明细
	public boolean addFinancialDetail(FinancialDetail detail);

	// 添加提现申请
	public boolean addWithdraw(WithDraw withDraw);

	// 获取提现记录
	public Map<String, Object> getWithDrawList(Pager pager);

	// 获取未处理的提现记录
	public List<WithDrawBean> loadAlipayWithdrawList();

	// 更新提现记录
	public void updateWithdraw(String conditionItem, String conditionValue, String item, String value);

	// 获取提现详情
	public WithDrawBean withdrawDetail(String conditionItem, String conditionValue);

	// 更新钱包信息
	public void updateWallet(String consId, String item, String value);

	// 钱包详情
	public WalletBean walletDetail(String consId);

	// 加载顾问的财务明细
	public Map<String, Object> loadFinancialDetail(String id, Pager pager);

	// 钱包列表
	public Map<String, Object> loadWalletList(Pager pager);

	// 导出提现列表
	public List<WithDrawBean> exportWithDrawList(Pager pager);

	// 导出顾问钱包列表
	public List<WalletBean> exportWallet();

	// 清除提现次数
	public void clearWithDrawedNum();

	// 查询顾问未处理的提现请求
	public List<WithDrawBean> loadUserUndealWithDraw(String consId);

}
