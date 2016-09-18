<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>加载聊天记录</p>
	<form action="Msg_getMsgs" method="post">
		订单编号orderId:
		<input type="text" name="orderId">
		<br> 时间戳：
		<input type="text" name="timestamp">
		<input type="submit" value="提交">
	</form>

	<p>添加语音聊天记录</p>
	<form action="Msg_addVoiceMsg" method="post">
		订单编号orderId
		<input type="text" name="voiceMsg.orderId">
		<br> 持续时间:
		<input type="text" name="voiceMsg.duration">
		<!-- <br>聊天时间2016-07-13 12:20:30
		<input type="text" name="voiceMsg.createTime"> -->
		<br>
		<input type="submit" value="提交">
	</form>

	<p>获取语音聊天的总时间</p>
	<form action="Msg_loadVoiceMsgTime" method="post">
		订单编号orderId
		<input type="text" name="orderId">
		<input type="submit" value="提交">
	</form>

	<p>获取新订单提醒</p>
	<form action="Msg_loadConsAlerts" method="post">
		顾问编号
		<input type="text" name="alert.consultant.id">
		<input type="submit" value="提交">
	</form>

	<p>移除新订单提醒</p>
	<form action="Msg_removeAlerts" method="post">
		顾问编号
		<input type="text" name="alert.consultant.id">
		<br>订单编号
		<input type="text" name="alert.order.id">
		<input type="submit" value="提交">
	</form>



</body>
</html>