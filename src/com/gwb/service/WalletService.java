package com.gwb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.ali.pay.Alipay;
import com.gwb.bean.Pager;
import com.gwb.bean.WalletBean;
import com.gwb.bean.WithDrawBean;
import com.gwb.dao.WalletDAO;
import com.gwb.excel.WalletEx;
import com.gwb.excel.WithDrawEx;
import com.gwb.model.Consultant;
import com.gwb.model.FinancialDetail;
import com.gwb.model.WithDraw;
import com.gwb.util.DateUtil;
import com.gwb.util.Md5Utils;

@Component("walletService")
public class WalletService
{
	private WalletDAO walletDAO;

	public WalletDAO getWalletDAO()
	{
		return walletDAO;
	}

	@Resource(name = "walletDAO")
	public void setWalletDAO(WalletDAO walletDAO)
	{
		this.walletDAO = walletDAO;
	}

	// 顾问钱包详情
	@Transactional
	public WalletBean walletDetail(String consId)
	{
		return walletDAO.walletDetail(consId);
	}

	// 顾问钱包列表
	@Transactional
	public Map<String, Object> walletList(Pager pager)
	{
		return walletDAO.loadWalletList(pager);
	}

	// 加载顾问的财务明细
	@Transactional
	public Map<String, Object> loadFinancialDetail(String consId, Pager pager)
	{
		return walletDAO.loadFinancialDetail(consId, pager);
	}

	// 顾问提现
	@Transactional
	public Map<String, Object> withdraw(WithDraw withDraw)
	{
		Map<String, Object> res = new HashMap<>();
		WalletBean walletBean = walletDAO.walletDetail(withDraw.getConsultant().getId());
		//当天可提现次数超5次
		if(walletBean.getWithDrawed()<=walletBean.getWithDrawNum())
		{
			res.put("status", 400);
			res.put("error", "当天提现次数已达上限，不可提现！");
			return res;
		}
		List<WithDrawBean> withDrawBeans = walletDAO.loadUserUndealWithDraw(withDraw.getConsultant().getId());
		float unDealMoney = 0.0F;
		for (WithDrawBean bean : withDrawBeans)
		{
			unDealMoney += bean.getMoney();
		}
		if (unDealMoney + withDraw.getMoney() > walletBean.getBalance())
		{
			res.put("status", 400);
			res.put("error", "账户余额不足");
			return res;
		}
		String withNo = DateUtil.DateToString(new Date(), "yyyyMMddHHmmss");
		withNo += new Random().nextInt(1000);
		withDraw.setWithNo(withNo);
		withDraw.setCreateTime(new Date());
		if (walletDAO.addWithdraw(withDraw))
		{
			// 更新提现次数
			walletDAO.updateWallet(walletBean.getConsId(), "withDrawed", (walletBean.getWithDrawed() + 1) + "");
			res.put("status", 200);
		} else
		{
			res.put("status", 400);
			res.put("error", "提现申请失败，稍后重试");
		}
		return res;
	}

	// 更新支付密码
	@Transactional
	public void changePayPassword(String consId, String password)
	{
		walletDAO.updateWallet(consId, "password", Md5Utils.encodeByMD5(password));
	}

	// 校验支付密码
	@Transactional
	public Map<String, Object> checkPayPassword(String consId, String password)
	{
		Map<String, Object> res = new HashMap<>();
		WalletBean walletBean = walletDAO.walletDetail(consId);
		if (Md5Utils.encodeByMD5(password).equals(walletBean.getPassword()))
			res.put("status", 200);
		else
		{
			res.put("status", 400);
			res.put("error", "密码错误");
		}
		return res;
	}

	// admin 获取提现记录
	@Transactional
	public Map<String, Object> loadWithDrawList(Pager pager)
	{
		return walletDAO.getWithDrawList(pager);
	}

	// 获取支付宝 提现列表
	@Transactional
	public Map<String, Object> alipayWithdrawList()
	{
		Map<String, Object> res = new HashMap<>();
		List<WithDrawBean> withDrawBeans = walletDAO.loadAlipayWithdrawList();
		res.put("withdraw", withDrawBeans);
		res.put("form", Alipay.createWithdrawForm(withDrawBeans));
		return res;
	}

	// 支付宝提现结果处理
	@Transactional
	public void alipayWithdraw(String resultString)
	{
		if (resultString == null || resultString.equals(""))
			return;
		String[] res = resultString.split("\\|");
		for (String item : res)
		{
			String temp[] = item.split("\\^");
			WithDrawBean withDrawBean = walletDAO.withdrawDetail("withNo", temp[0]);
			// 如果提现记录已处理 则返回
			if (withDrawBean.getState() != 0)
				return;
			String date = temp[7];
			String dateStr = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " "
					+ date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
			// 转账成功
			if (temp[4].equals("S"))
			{
				FinancialDetail detail = new FinancialDetail();
				detail.setCreateTime(new Date());
				detail.setConsultant(new Consultant(withDrawBean.getConsId()));
				detail.setWithDraw(new WithDraw(withDrawBean.getId()));
				detail.setMoney(withDrawBean.getMoney());
				detail.setType(1); // 支出
				walletDAO.addFinancialDetail(detail);
				walletDAO.updateWithdraw("withNo", temp[0], "state", "2");
				System.out.println(temp[0]);
			} else // 提现失败
			{
				System.out.println(temp[0] + temp[5]);
				walletDAO.updateWithdraw("withNo", temp[0], "state", "1");
				walletDAO.updateWithdraw("withNo", temp[0], "reason", temp[5]);
			}
			// 更新处理时间
			walletDAO.updateWithdraw("withNo", temp[0], "dealTime", dateStr);
		}

	}

	public static void main(String[] args)
	{
		new WalletService().alipayWithdraw(
				"0315001^gonglei1@handsome.com.cn^龚本林^20.00^S^null^200810248427067^20081024143652|0315006^xinjie_xj@163.com^星辰公司1^20.00^F^TXN_RESULT_TRANSFER_OUT_CAN_NOT_EQUAL_IN^200810248427065^20081024143651");
	}

	// 导出用户提现列表
	@Transactional
	public List<WithDrawEx> exportWithDrawList(Pager pager)
	{
		List<WithDrawBean> beans = walletDAO.exportWithDrawList(pager);
		List<WithDrawEx> list = new ArrayList<>();
		for (WithDrawBean bean : beans)
		{
			WithDrawEx ex = new WithDrawEx();
			ex.setWithNo(bean.getWithNo());
			ex.setConsMobilePhone(bean.getConsMobilePhone());
			ex.setConsName(bean.getConsName());
			ex.setAccount(bean.getAccount());
			ex.setName(bean.getName());
			ex.setMoney(bean.getMoney());
			ex.setCreateTime(bean.getCreateTime());
			ex.setDealTime(bean.getDealTime());
			if (bean.getState() == 0)
				ex.setState("未处理");
			else if (bean.getState() == 1)
			{
				ex.setState("提现失败");
			} else if (bean.getState() == 2)
			{
				ex.setState("提现成功");
			}
			list.add(ex);
		}
		return list;
	}

	// 导出顾问钱包
	@Transactional
	public List<WalletEx> exportWallet()
	{
		List<WalletBean> beans = walletDAO.exportWallet();
		List<WalletEx> list = new ArrayList<>();
		for (WalletBean bean : beans)
		{
			WalletEx ex = new WalletEx();
			ex.setMobilePhone(bean.getMobilePhone());
			ex.setName(bean.getName());
			ex.setBalance(bean.getBalance());
			ex.setCanNotWithDraw(bean.getBalance() % 100);
			ex.setCanWithDraw(bean.getBalance() - ex.getCanNotWithDraw());
			ex.setWithDrawed(bean.getWithDrawed());
			ex.setWithDrawNum(bean.getWithDrawNum());
			list.add(ex);
		}
		return list;
	}

}
