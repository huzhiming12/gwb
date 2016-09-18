package com.gwb.pingxx;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.OrderBean;
import com.gwb.dao.OrderDAO;
import com.gwb.pingxx.ChargePay;
import com.gwb.util.Config;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;

@Component("payService")
public class PayService
{
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

	// 生成支付订单
	@Transactional
	public Charge charge(String orderId, String channel, String ip)
	{
		OrderBean orderBean = orderDAO.loadOrderByOrderId(orderId);
		Pingpp.apiKey = Config.PING_API_Key;
		Pingpp.privateKeyPath = Config.PING_privateKeyPath;
		Map<String, Object> order = new HashMap<>();
		order.put("amount", (int) (orderBean.getMoney() * 100));
		order.put("subject", "顾问帮咨询服务");
		order.put("body", "顾问帮一对一咨询服务");
		order.put("order_no", orderBean.getOrderId());
		order.put("channel", channel);
		order.put("client_ip", ip);
		//order.put("success_url", order.get("success_url"));
		ChargePay pay = new ChargePay();
		Charge charge = pay.createCharge(order);

		return charge;
	}

}
