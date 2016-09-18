package com.gwb.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gwb.bean.Pager;
import com.gwb.interceptor.NotLoginException;
import com.gwb.model.Alert;
import com.gwb.model.VoiceMsg;
import com.gwb.service.MsgService;
import com.gwb.util.ResultUtils;

@Component("msgAction")
@Scope("protoType")
public class MsgAction
{
	private String orderId;
	private Long timestamp;
	private MsgService msgService;
	private Pager pager;
	private VoiceMsg voiceMsg;
	private Alert alert;

	public Alert getAlert()
	{
		return alert;
	}

	public void setAlert(Alert alert)
	{
		this.alert = alert;
	}

	public VoiceMsg getVoiceMsg()
	{
		return voiceMsg;
	}

	public void setVoiceMsg(VoiceMsg voiceMsg)
	{
		this.voiceMsg = voiceMsg;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public MsgService getMsgService()
	{
		return msgService;
	}

	@Resource(name = "msgService")
	public void setMsgService(MsgService msgService)
	{
		this.msgService = msgService;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * @author:huzhiming
	 * @function：查询聊天记录
	 * @field: id:订单id fromUser: 发送方id toUser: 接收方id timestamp:时间戳（数字类型的时间戳）
	 * @void:
	 */
	public void getMsgs()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", msgService.loadMsgs(orderId));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 添加语音聊天记录
	public void addVoiceMsg()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			msgService.addVoiceMsg(voiceMsg);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 添加语音聊天的总时间
	public void loadVoiceMsgTime()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", msgService.loadVoiceMsgTime(orderId));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	// 管理员获取聊天记录
	public void getMsgsByOrderId()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", msgService.loadMsgs(pager));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

	//加载顾问新订单提醒
	public void loadConsAlerts()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			result.put("res", msgService.loadConsAlerts(alert.getConsultant().getId()));
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}
	
	//去除新订单提醒
	public void removeAlerts()
	{
		Map<String, Object> result = new HashMap<>();
		try
		{
			result.put("status", 200);
			msgService.removeAlerts(alert.getConsultant().getId(), alert.getOrder().getId());
		} catch (NullPointerException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "参数错误");
		} catch (NotLoginException e)
		{
			result.put("status", 400);
			result.put("error", "未登录或登录超时,请重新登录");
		} catch (RuntimeException e)
		{
			e.printStackTrace();
			result.put("status", 400);
			result.put("error", "操作失败，稍后重试");
		}
		ResultUtils.toJson(ServletActionContext.getResponse(), result);
	}

}
