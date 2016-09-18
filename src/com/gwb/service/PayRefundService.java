package com.gwb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.ali.pay.Alipay;
import com.gwb.bean.Pager;
import com.gwb.bean.PayRecordBean;
import com.gwb.bean.RefundBean;
import com.gwb.dao.PayRefundDAO;
import com.gwb.excel.RefundEx;

@Component("payRefundService")
public class PayRefundService
{

	private PayRefundDAO payRefundDAO;

	public PayRefundDAO getPayRefundDAO()
	{
		return payRefundDAO;
	}

	@Resource(name = "payRefundDAO")
	public void setPayRefundDAO(PayRefundDAO payRefundDAO)
	{
		this.payRefundDAO = payRefundDAO;
	}

	// 获取退款申请
	@Transactional
	public Map<String, Object> loadRefundList(Pager pager)
	{
		return payRefundDAO.loadRefundList(pager);
	}

	// 获取支付宝退款订单
	@Transactional
	public Map<String, Object> loadAlipayRefundList()
	{
		Map<String, Object> res = new HashMap<>();
		List<RefundBean> list = payRefundDAO.loadAlipayRefund();
		// 组装退款订单
		res.put("form", Alipay.createRefundForm(list));
		res.put("refund", list);
		return res;
	}

	// 支付记录详情
	@Transactional
	public PayRecordBean payRecordDetail(int id)
	{
		return payRefundDAO.payRecordDetail(id);
	}

	// 处理支付宝返回的异步退款通知
	@Transactional
	public void alipayRefund(String dataString)
	{
		String[] temp = dataString.split("#");
		for (String str : temp)
		{
			String tradeNo = "";
			String result = "";
			String[] strArr = str.split("\\^");
			tradeNo = strArr[0];
			if (strArr.length == 5)// 不退手续费的订单
			{
				int size = strArr[2].indexOf("$");
				result = strArr[2].substring(0, size);
			} else // 退手续的订单
				result = strArr[2];
			System.out.println(tradeNo + "--" + result);
			if (result.equals("SUCCESS"))
			{
				payRefundDAO.updateRefund(tradeNo, "1");
			} else
			{
				payRefundDAO.updateRefund(tradeNo, "0");
			}
		}

	}

	// 导出退款申请
	@Transactional
	public List<RefundEx> exportRefund(Pager pager)
	{
		List<RefundEx> list = new ArrayList<>();
		List<RefundBean> beans = payRefundDAO.exportRefund(pager);
		for (RefundBean bean : beans)
		{
			RefundEx ex = new RefundEx();
			ex.setRefundId(bean.getRefundId());
			ex.setOrderId(bean.getOrderId());
			ex.setMoney(bean.getMoney());
			ex.setUsername(bean.getUsername());
			ex.setMobilePhone(bean.getMobilePhone());
			ex.setCreateTime(bean.getCreateTime());
			if (bean.getState() == 0)
				ex.setState("未处理");
			else if (bean.getState() == 1)
				ex.setState("退款成功");
			else
				ex.setState("处理中");
			if (bean.getPayType() == 0)
				ex.setPayType("微信支付");
			else
				ex.setPayType("支付宝支付");
			list.add(ex);
		}
		return list;
	}

}
