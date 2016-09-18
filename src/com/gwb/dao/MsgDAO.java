package com.gwb.dao;

import java.util.List;
import java.util.Map;

import com.gwb.bean.MsgBean;
import com.gwb.bean.Pager;
import com.gwb.model.Msg;
import com.gwb.model.VoiceMsg;

public interface MsgDAO
{

	public boolean addMsg(Msg msg);

	// 用户加载聊天记录
	public List<MsgBean> loadMsgs(String orderId);

	// 添加语音聊天记录
	public void addVoiceMsg(VoiceMsg voiceMsg);

	// 获取语音聊天的总时间
	public int loadVoiceMsgTime(String orderId);

	// 加载聊天记录
	public Map<String, Object> loadMsgs(Pager pager);
}
