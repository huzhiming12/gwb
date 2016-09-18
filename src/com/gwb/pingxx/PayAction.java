package com.gwb.pingxx;

import java.io.InputStream;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.gwb.util.ResultUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;

public class PayAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String channel;
	private PayService payService;

	public PayService getPayService()
	{
		return payService;
	}

	public void setPayService(PayService payService)
	{
		this.payService = payService;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public String getChannel()
	{
		return channel;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}
	// ********************************************************

	// 预支付支付订单
	public void prePayOrder()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			String ip = ServletActionContext.getRequest().getRemoteAddr();
			result.put("charge", payService.charge(orderId, channel, "127.0.0.1"));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，请稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 接收ping++返回的付款成功通知
	public void payOrderResult() throws Exception
	{
		InputStream stream;
		HttpServletRequest request = ServletActionContext.getRequest();

		String signature = request.getHeader("x-pingplusplus-signature");
		PublicKey publicKey = WebhooksVerifyExample.getPubKey();
		stream = ServletActionContext.getRequest().getInputStream();
		String dataString = IOUtils.toString(stream);
		boolean verifyResult = WebhooksVerifyExample.verifyData(dataString, signature, publicKey);
		if (verifyResult)
		{

			Event event = Webhooks.eventParse(dataString);
			switch (event.getType())
			{
			case "charge.succeeded":
				Object object = Webhooks.getObject(event.getObject());

				break;
			case "refund.succeeded":
				break;

			default:
				break;
			}

		} else
		{

		}
		Map<String, Object> result = new HashMap<>();
		// result.put("data", dataString);
		result.put("signature", signature);
		result.put("res", verifyResult);
		ResultUtils.toJson(ServletActionContext.getResponse(), result);

	}

}
