package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.bean.PayRecordBean;
import com.gwb.bean.RefundBean;
import com.gwb.dao.PayRefundDAO;
import com.gwb.model.PayRecord;
import com.gwb.model.Refund;
import com.gwb.util.Config;
import com.gwb.util.DateUtil;

/**
 * 支付退款操作类
 */
@Component("payRefundDAO")
public class PayRefundDAOImpl implements PayRefundDAO
{

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

	private String paySQL = "select new com.gwb.bean.PayRecordBean(p.id,p.tradeNo, p.money,"
			+ "p.user.name, p.user.id, p.order.id, p.order.orderId, p.createTime,p.payType)from PayRecord p ";

	private String refundSQL = "select new com.gwb.bean.RefundBean(r.refundId, r.payRecord.tradeNo,"
			+ "r.payRecord.user.name,r.payRecord.user.mobilePhone,r.payRecord.user.id, r.payRecord.order.id, "
			+ "r.payRecord.order.orderId, r.payRecord.money,r.createTime, r.payRecord.payType,r.state) from Refund r ";

	// 添加用户的支付记录
	@Override
	public boolean addPayRecord(PayRecord record)
	{
		return dataDAO.addItem(record);
	}

	/**
	 * 添加退款申请
	 */
	@Override
	public boolean addRefund(Refund refund)
	{
		return dataDAO.addItem(refund);
	}

	// 支付记录详情
	@Override
	public PayRecordBean payRecordDetail(int orderId)
	{
		String hql = paySQL + " where p.order.id=" + orderId;
		List<PayRecordBean> list = dataDAO.loadItems(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	// 退款申请详情
	@Override
	public RefundBean refundDetail(String refundId)
	{
		String hql = refundSQL + "where r.refundId='" + refundId + "'";
		List<RefundBean> beans = dataDAO.loadItems(hql);
		if (beans != null && beans.size() > 0)
		{
			return beans.get(0);
		}
		return null;
	}

	// 更新退款信息
	@Override
	public void updataRefund(String conditionItem, String conditionValue, String item, String value)
	{
		String dateStr = DateUtil.DateToString(new Date());
		String hql = "update Refund   set scTime='" + dateStr + "', " + item + "='" + value + "' where " + conditionItem
				+ "='" + conditionValue + "'";
		dataDAO.updateData(hql);
	}

	// 更新支付宝 退款申请
	@Override
	public void updateRefund(String tradeNo, String value)
	{
		String dateStr = DateUtil.DateToString(new Date());
		String sql = "update t_refund r,t_pay_record p set r.state=" + value + ",r.scTime='" + dateStr + "'"
				+ " where r.state!=1 and r.pay_id=p.pay_id and p.tradeNo='" + tradeNo + "'";
		dataDAO.SQLUpdate(sql);
	}

	// 加载支付宝退款列表
	@Override
	public List<RefundBean> loadAlipayRefund()
	{
		String hql = refundSQL + " where r.state=0 and r.payRecord.payType=1";
		return dataDAO.loadItems(hql);
	}

	// 加载退款列表
	@Override
	public Map<String, Object> loadRefundList(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.ADMIN_PAGER_SIZE);

		Map<String, String> conditions = pager.getCondition();
		String temp = " where r.id!=0";
		if (!conditions.isEmpty())
		{
			String keyword = conditions.get("keyword");
			if (keyword != null && !"".equals(keyword))
			{
				temp += " and (r.payRecord.user.name like '%" + keyword + "%' or r.payRecord.user.mobilePhone like '%"
						+ keyword + "%' or r.payRecord.user.name like '%" + keyword
						+ "%' or r.payRecord.order.orderId like '%" + keyword + "%' or r.refundId like '%" + keyword
						+ "%')";
			}
			String payType = conditions.get("payType");
			if (payType != null && !"-1".equals(payType))
			{
				temp += " and r.payRecord.payType=" + payType;
			}
			String state = conditions.get("state");
			if (state != null && !"-1".equals(state))
			{
				temp += " and r.state=" + state;
			}
		}

		String hql = refundSQL + temp + " order by r.createTime desc";
		List<RefundBean> list = dataDAO.loadItems(hql, pager);
		hql = "select count(*) from Refund r " + temp;
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();

		Map<String, Object> res = new HashMap<>();
		res.put("refund", list);
		res.put("pager", pager);
		return res;
	}

	// 导出退款申请
	@Override
	public List<RefundBean> exportRefund(Pager pager)
	{
		Map<String, String> conditions = pager.getCondition();
		String temp = " where r.refundId!=''";
		if (!conditions.isEmpty())
		{
			String state = conditions.get("state");
			if (state != null && !state.equals("-1"))
			{
				temp += " and r.state=" + state;
			}
			String payType = conditions.get("payType");
			if (payType != null && !payType.equals("-1"))
			{
				temp += " and r.payRecord.payType=" + payType;
			}
		}
		String hql = refundSQL + temp + " order by r.createTime desc";
		return dataDAO.loadItems(hql);
	}

}
