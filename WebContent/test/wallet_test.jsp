<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>顾问提现测试</p>
	<form action="Wallet_withdraw" method="post">
		顾问id：
		<input type="text" name="withDraw.consultant.id">
		<br> 提现金额：
		<input type="text" name="withDraw.money">
		<br> 提现人姓名：
		<input type="text" name="withDraw.name">
		<br> 提现人账号：
		<input type="text" name="withDraw.account">
		<input type="submit" value="提现">
	</form>

	<p>顾问钱包详情</p>
	<form action="Wallet_walletDetail" method="post">
		顾问id：
		<input type="text" name="id">
		<input type="submit" value="查询">
	</form>

	<p>顾问财务出入明细</p>
	<form action="Wallet_loadFinancialDetal" method="post">
		顾问id：
		<input type="text" name="id">
		<br> 第几页：
		<input type="text" name="pager.pageNow">
		<input type="submit" value="查询">
	</form>

</body>
</html>