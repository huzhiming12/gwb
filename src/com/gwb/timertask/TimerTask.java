package com.gwb.timertask;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gwb.util.LogUtils;
import com.hx.server.imp.ChatMessageApimpl;

@Component("timerTask")
public class TimerTask
{
	private ChatMessageApimpl msgImpl;
	private TimerTaskService timerTaskService;

	public ChatMessageApimpl getMsgImpl()
	{
		return msgImpl;
	}

	@Resource(name = "msgImpl")
	public void setMsgImpl(ChatMessageApimpl msgImpl)
	{
		this.msgImpl = msgImpl;
	}

	public TimerTaskService getTimerTaskService()
	{
		return timerTaskService;
	}

	@Resource(name = "timeTaskService")
	public void setTimerTaskService(TimerTaskService timerTaskService)
	{
		this.timerTaskService = timerTaskService;
	}

	// 每隔20分钟定时到环信服务器加载聊天记录
	@Scheduled(cron = "0 0/20 * * * ?")
	public void loadMsg()
	{
		msgImpl.loadMsg();
		LogUtils.info("每隔20分钟到环信服务器拉取聊天记录");
	}

	// 每隔20分钟定时到微信服务器上查询退款状态
	@Scheduled(cron = "0 0/20 * * * ?")
	public void queryRefundState()
	{
		timerTaskService.queryRefundState();
		LogUtils.info("每隔20分钟到微信服务器查询退款状态");
	}

	// 每天0点提现次数清零
	@Scheduled(cron = "0 0 0 * * ?")
	public void updateWallet()
	{
		timerTaskService.walletTimeTask();
		LogUtils.info("零点钱包提现次数清零");
	}

	// 每隔10分钟定时轮询关闭超过12小时未回应的邀请
	@Scheduled(cron = "0 0/10 * * * ?")
	public void closeOutTimeAppoint()
	{
		timerTaskService.closeOverTimeAppoint();
		LogUtils.info("关闭超过12小时未回应的邀请");
	}

	// 每隔10分钟定时轮询关闭超过2小时未支付的订单
	@Scheduled(cron = "0 0/10 * * * ?")
	public void closeOutTimeOrder()
	{
		timerTaskService.closeOverTimeOrder();
		LogUtils.info("关闭超过2小时未支付的订单");
	}

}
