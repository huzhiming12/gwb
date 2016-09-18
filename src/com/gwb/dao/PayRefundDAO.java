package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.Pager;
import com.gwb.bean.PayRecordBean;
import com.gwb.bean.RefundBean;
import com.gwb.model.PayRecord;
import com.gwb.model.Refund;

public interface PayRefundDAO
{
	// 添加用户支付记录
	public boolean addPayRecord(PayRecord record);

	// 添加退款申请
	public boolean addRefund(Refund refund);

	// 支付记录详情
	public PayRecordBean payRecordDetail(int orderId);

	// 退款申请记录详情
	public RefundBean refundDetail(String refund_id);

	// 加载退款申请
	public Map<String, Object> loadRefundList(Pager pager);

	// 加载阿里退款订单
	public List<RefundBean> loadAlipayRefund();

	// 导出退款申请
	public List<RefundBean> exportRefund(Pager pager);

	// 更新退款申请
	public void updataRefund(String conditionItem, String conditionValue, String item, String value);

	// 更新支付宝退款详情
	public void updateRefund(String tradeNo, String value);

}
