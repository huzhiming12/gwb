package com.gwb.action;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gwb.ali.pay.Alipay;
import com.gwb.service.OrderService;
import com.gwb.service.PayRefundService;
import com.gwb.service.WalletService;
import com.gwb.util.Config;
import com.gwb.util.LogUtils;
import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AlipayAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private PayRefundService payRefundService;
	private WalletService walletService;
	private OrderService orderService;

	public PayRefundService getPayRefundService()
	{
		return payRefundService;
	}

	@Resource(name = "payRefundService")
	public void setPayRefundService(PayRefundService payRefundService)
	{
		this.payRefundService = payRefundService;
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

	public OrderService getOrderService()
	{
		return orderService;
	}

	@Resource(name = "orderService")
	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	// 支付宝批量退款 异步通知
	public void alipayRefundNotify()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> map = new TreeMap<>();
		map.put("notify_time", request.getParameter("notify_time"));
		map.put("notify_type", request.getParameter("notify_type"));
		map.put("notify_id", request.getParameter("notify_id"));
		map.put("sign_type", request.getParameter("sign_type"));
		map.put("sign", request.getParameter("sign"));
		map.put("batch_no", request.getParameter("batch_no"));
		map.put("success_num", request.getParameter("success_num"));
		map.put("result_details", request.getParameter("result_details"));
		LogUtils.warn("支付宝批量退款通知：" + map.toString());
		if (Alipay.checkPayResult(map))
		{
			payRefundService.alipayRefund(map.get("result_details"));
			ResultUtils.print(ServletActionContext.getResponse(), "success");
		}
	}

	// 提现 异步通知处理接口
	public void alipayWithdrawNotify()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> map = new TreeMap<>();
		map.put("notify_time", request.getParameter("notify_time"));
		map.put("notify_type", request.getParameter("notify_type"));
		map.put("notify_id", request.getParameter("notify_id"));
		map.put("sign_type", request.getParameter("sign_type"));
		map.put("sign", request.getParameter("sign"));
		map.put("batch_no", request.getParameter("batch_no"));
		map.put("pay_user_id", request.getParameter("pay_user_id"));
		map.put("pay_user_name", request.getParameter("pay_user_name"));
		map.put("pay_account_no", request.getParameter("pay_account_no"));
		map.put("success_details", request.getParameter("success_details"));
		map.put("fail_details", request.getParameter("fail_details"));
		LogUtils.warn("批量付款到支付宝返回结果："+map.toString());
		// 校验结果正确性
		if (Alipay.checkPayResult(map))
		{
			walletService.alipayWithdraw(map.get("success_details"));
			walletService.alipayWithdraw(map.get("fail_details"));
			ResultUtils.print(ServletActionContext.getResponse(), "success");
		}
	}

	// 支付宝异步支付返回的结果
	public void alipayOrderNotify()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> result = new TreeMap<String, String>();
		result.put("notify_time", request.getParameter("notify_time"));
		result.put("notify_type", request.getParameter("notify_type"));
		result.put("notify_id", request.getParameter("notify_id"));
		result.put("sign_type", request.getParameter("sign_type"));
		result.put("sign", request.getParameter("sign"));
		result.put("out_trade_no", request.getParameter("out_trade_no"));
		result.put("subject", request.getParameter("subject"));
		result.put("payment_type", request.getParameter("payment_type"));
		result.put("trade_no", request.getParameter("trade_no"));
		result.put("trade_status", request.getParameter("trade_status"));
		result.put("seller_id", request.getParameter("seller_id"));
		result.put("seller_email", request.getParameter("seller_email"));
		result.put("buyer_id", request.getParameter("buyer_id"));
		result.put("buyer_email", request.getParameter("buyer_email"));
		result.put("total_fee", request.getParameter("total_fee"));
		result.put("quantity", request.getParameter("quantity"));
		result.put("price", request.getParameter("price"));
		result.put("body", request.getParameter("body"));
		result.put("gmt_create", request.getParameter("gmt_create"));
		result.put("gmt_payment", request.getParameter("gmt_payment"));
		result.put("is_total_fee_adjust", request.getParameter("is_total_fee_adjust"));
		result.put("use_coupon", request.getParameter("use_coupon"));
		result.put("discount", request.getParameter("discount"));
		result.put("refund_status", request.getParameter("refund_status"));
		result.put("gmt_refund", request.getParameter("gmt_refund"));

		LogUtils.warn("支付宝支付完成返回结果：" + result.toString());
		// 校验返回的结果
		if (Alipay.checkPayResult(result))
		{
			orderService.payOrder(result.get("out_trade_no"), result.get("trade_no"), Config.AL_PAY_TYPE);
			ResultUtils.print(ServletActionContext.getResponse(), "success");
		}
	}

}
