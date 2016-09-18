package com.gwb.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gwb.bean.MsgBean;
import com.gwb.bean.Pager;
import com.gwb.dao.MsgDAO;
import com.gwb.model.Msg;
import com.gwb.model.VoiceMsg;
import com.gwb.util.Config;

@Component("msgDAO")
public class MsgDAOImpl implements MsgDAO
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

	@Override
	public boolean addMsg(Msg msg)
	{
		return dataDAO.addItem(msg);
	}

	// 加载聊天记录
	@Override
	public List<MsgBean> loadMsgs(String orderId)
	{
		String hql = "from Msg where orderId='" + orderId + "'";
		return dataDAO.loadItems(hql);
	}

	// 添加语音聊天记录
	@Override
	public void addVoiceMsg(VoiceMsg voiceMsg)
	{
		voiceMsg.setCreateTime(new Date());
		dataDAO.addItem(voiceMsg);
	}

	// 获取语音聊天的总时间
	@Override
	public int loadVoiceMsgTime(String orderId)
	{
		String hql = "select sum(duration) from VoiceMsg where orderId='" + orderId + "'";
		int duration = dataDAO.loadItemsNum(hql);
		return duration;
	}

	// 管理员加载聊天记录
	public Map<String, Object> loadMsgs(Pager pager)
	{
		if (pager.getPageNow() == 0)
			pager.setPageNow(1);
		pager.setPageSize(Config.MSG_PAGER_SIZE);
		Map<String, String> conditions = pager.getCondition();
		String orderId = "";
		if (!conditions.isEmpty())
		{
			orderId = conditions.get("orderId");
		}
		String hql = "from Msg where orderId='" + orderId + "' order by timestamp";
		List<MsgBean> list = dataDAO.loadItems(hql, pager);
		hql = "select count(*)from Msg where orderId='" + orderId + "'";
		pager.setTotalNum(dataDAO.loadItemsNum(hql));
		pager.caculatePageNum();
		Map<String, Object> res = new HashMap<>();
		res.put("msg", list);
		res.put("pager", pager);
		return res;
	}

}
