<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>加载用户订单</p>
	<form action="Order_loadUserOrders">
		用户id
		<input type="text" name="id">
		<br> 页数：
		<input type="text" name="pager.pageNow">
		<br> flag:0:已取消 3待评价 4：结束
		<input type="text" name="flag">
		<br>
		<input type="submit" value="查询">
	</form>

	<p>加载顾问订单</p>
	<form action="Order_loadConsOrders">
		顾问id:
		<input type="text" name="id">
		<br> 页数：
		<input type="text" name="pager.pageNow">
		<br> 0:我的项目 1：待评价 2：已结束
		<input type="text" name="flag">
		<input type="submit" value="查询">
	</form>

	<p>订单详情</p>
	<form action="Order_orderDetail">
		订单编号：
		<input type="text" name="id">
		<input type="submit" value="查询">
	</form>

	<p>预约（选择备选时间）</p>
	<form action="Order_appointTimeSetting" method="post">
		回应编号id：
		<input type="text" name="responseId">
		<br> 时间:
		<input type="text" name="time">
		<input type="submit" value="设置">
	</form>

	<p>协商预约（选择备选时间）</p>
	<form action="Order_appointTimeResetting" method="post">
		订单id：
		<input type="text" name="id">
		<br> 时间:
		<input type="text" name="time">
		<input type="submit" value="设置">
	</form>

	<p>查看备选时间</p>
	<form action="Order_loadAppointTime" method="post">
		订单id：
		<input type="text" name="id">
		<input type="submit" value="查看">
	</form>

	<p>顾问从备选时间选择时间</p>
	<form action="Order_chooseAppointTime" method="post">
		订单id：
		<input type="text" name="id">
		<br> 时间：
		<input type="text" name="time">
		<input type="submit" value="选择">
	</form>

	<p>顾问请用户重新选择时间</p>
	<form action="Order_resetAppointTime" method="post">
		订单id：
		<input type="text" name="id">
		<input type="submit" value="重置">
	</form>

	<p>确认支付订单</p>
	<form action="Order_wxPayOrder" method="post">
		订单id：
		<input type="text" name="id">
		<input type="submit" value="确认">
	</form>

	<p>确认咨询完成</p>
	<form action="Order_finishConsult" method="post">
		订单id：
		<input type="text" name="id">
		<input type="submit" value="确认">
	</form>

	<p>用户取消订单</p>
	<form action="Order_cancelOrder" method="post">
		订单id：
		<input type="text" name="reason.order.id">
		<br> 取消原因：
		<input type="text" name="reason.content">
		<input type="submit" value="取消">
	</form>

	<p>加载用户取消订单原因</p>
	<form action="Order_getCancelReason" method="post">
		订单id：
		<input type="text" name="id">
		<input type="submit" value="查询">
	</form>


	<p>顾问处理用户取消咨询的请求</p>
	<form action="Order_cancelConfirm" method="post">
		订单id：
		<input type="text" name="id">
		<br> 处理意见：0 同意 1 不同意
		<input type="text" name="state">
		<br>取消原因id：
		<input type="text" name="reasonId">
		<input type="submit" value="取消">
	</form>

	<p>用户添加评论</p>
	<form action="Order_addComment" method="post">
		订单id：
		<input type="text" name="comment.order.id">
		<br> 评价内容：
		<input type="text" name="comment.content">
		<input type="submit" value="取消">
	</form>

	<p>用户投诉</p>
	<form action="Order_addComplaint" method="post">
		订单id：
		<input type="text" name="complaint.order.id">
		<br> 用户id：
		<input type="text" name="complaint.user.id">
		<br> 顾问id：
		<input type="text" name="complaint.consultant.id">
		<br> 投诉内容：
		<input type="text" name="complaint.content">
		<input type="submit" value="取消">
	</form>
</body>
</html>

