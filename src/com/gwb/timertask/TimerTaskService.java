package com.gwb.timertask;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.Pager;
import com.gwb.bean.RefundBean;
import com.gwb.dao.AppointDAO;
import com.gwb.dao.OrderDAO;
import com.gwb.dao.PayRefundDAO;
import com.gwb.dao.WalletDAO;
import com.gwb.util.LogUtils;
import com.gwb.wx.pay.WXPay;

@Component("timeTaskService")
public class TimerTaskService
{
	private WalletDAO walletDAO;
	private PayRefundDAO payRefundDAO;
	private AppointDAO appointDAO;
	private OrderDAO orderDAO;

	public OrderDAO getOrderDAO()
	{
		return orderDAO;
	}

	@Resource(name = "orderDAO")
	public void setOrderDAO(OrderDAO orderDAO)
	{
		this.orderDAO = orderDAO;
	}

	public AppointDAO getAppointDAO()
	{
		return appointDAO;
	}

	@Resource(name = "appointDAO")
	public void setAppointDAO(AppointDAO appointDAO)
	{
		this.appointDAO = appointDAO;
	}

	public PayRefundDAO getPayRefundDAO()
	{
		return payRefundDAO;
	}

	@Resource(name = "payRefundDAO")
	public void setPayRefundDAO(PayRefundDAO payRefundDAO)
	{
		this.payRefundDAO = payRefundDAO;
	}

	public WalletDAO getWalletDAO()
	{
		return walletDAO;
	}

	@Resource(name = "walletDAO")
	public void setWalletDAO(WalletDAO walletDAO)
	{
		this.walletDAO = walletDAO;
	}

	// 钱包提现次数清零
	@Transactional
	public void walletTimeTask()
	{
		walletDAO.clearWithDrawedNum();
		LogUtils.info("提现次数清零");
	}

	// 关闭过期的邀请（超过12小时的）
	@Transactional
	public void closeOverTimeAppoint()
	{
		appointDAO.closeOutTimeAppoint();
	}

	// 关闭超过1个小时没有支付的订单
	@Transactional
	public void closeOverTimeOrder()
	{
		orderDAO.closeOutTimeOrder();
	}

	// 查询退款状态
	@Transactional
	public void queryRefundState()
	{
		Pager pager = new Pager();
		pager.getCondition().put("payType", "0");
		// 从数据中查询正在处理中的退款申请
		pager.getCondition().put("state", "2");
		Map<String, Object> map;
		do
		{
			map = payRefundDAO.loadRefundList(pager);
			@SuppressWarnings("unchecked")
			List<RefundBean> refundBeans = (List<RefundBean>) map.get("refund");
			pager = (Pager) map.get("pager");
			// 查询页数加1
			pager.setPageNow(pager.getPageNow() + 1);
			Map<String, Object> res = null;
			// 从微信服务器上查询
			for (RefundBean bean : refundBeans)
			{
				res = WXPay.searchRefund(bean.getRefundId());
				// 退款申请已经处理
				if (res != null && 200 == (int) res.get("status"))
				{
					// 将退款状态改为退款成功
					payRefundDAO.updataRefund("refundId", bean.getRefundId(), "state", "1");
				} else
				{
					System.out.println("refundId-" + bean.getRefundId() + ":" + res.get("error"));
				}
			}
		} while (pager.getPageNow() <= pager.getPageNum());
	}

}
