package com.gwb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gwb.bean.AlertBean;
import com.gwb.bean.MsgBean;
import com.gwb.bean.Pager;
import com.gwb.dao.AlertDAO;
import com.gwb.dao.MsgDAO;
import com.gwb.model.VoiceMsg;

@Component("msgService")
public class MsgService
{

	private MsgDAO msgDAO;
	private AlertDAO alertDAO;

	public MsgDAO getMsgDAO()
	{
		return msgDAO;
	}

	@Resource(name = "msgDAO")
	public void setMsgDAO(MsgDAO msgDAO)
	{
		this.msgDAO = msgDAO;
	}

	public AlertDAO getAlertDAO()
	{
		return alertDAO;
	}

	@Resource(name="alertDAO")
	public void setAlertDAO(AlertDAO alertDAO)
	{
		this.alertDAO = alertDAO;
	}

	/**
	 * @author:huzhiming
	 * @function：加载聊天信息
	 */
	@Transactional
	public List<MsgBean> loadMsgs(String orderId)
	{
		return msgDAO.loadMsgs(orderId);
	}

	// 添加语音聊天记录
	@Transactional
	public void addVoiceMsg(VoiceMsg voiceMsg)
	{
		msgDAO.addVoiceMsg(voiceMsg);
	}

	// 获取语音聊天的总时间
	@Transactional
	public int loadVoiceMsgTime(String orderId)
	{
		return msgDAO.loadVoiceMsgTime(orderId);
	}

	// 加载聊天记录
	@Transactional
	public Map<String, Object> loadMsgs(Pager pager)
	{
		return msgDAO.loadMsgs(pager);
	}

	// 加载顾问新订单提醒
	@Transactional
	public List<AlertBean> loadConsAlerts(String consId)
	{
		return alertDAO.loadConsAlerts(consId);
	}
	
	//去除新消息提醒
	@Transactional
	public void removeAlerts(String consId,int oid)
	{
		alertDAO.updateAlert(consId, oid);
	}

}
