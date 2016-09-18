<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="sidebar-nav" style="width: 300px">
		<div class="nav-header" data-toggle="collapse" data-target="#accounts-menu">
			<i class="icon-user-md"></i>用户管理
		</div>
		<ul id="accounts-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='userList'">class="active"</s:if>>
				<a href="admin/user_userList">用户列表</a>
			</li>
			<li <s:if test="selector=='consultantList'">class="active"</s:if>>
				<a href="admin/cons_consultantList">顾问列表</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#request-menu">
			<i class="icon-pushpin"></i>需求管理
		</div>
		<ul id="request-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='requestList'">class="active"</s:if>>
				<a href="admin/req_requestList">需求列表</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#order-menu">
			<i class="icon-flag"></i>订单管理
		</div>
		<ul id="order-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='orderList'">class="active"</s:if>>
				<a href="admin/order_orderList">订单列表</a>
			</li>
			<li <s:if test="selector=='refundList'">class="active"</s:if>>
				<a href="admin/order_refundList">退款申请</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#sales-menu">
			<i class="icon-legal"></i>销售管理
		</div>
		<ul id="sales-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='saleList'">class="active"</s:if>>
				<a href="admin/sale_saleList">顾问销售列表</a>
			</li>
			<li <s:if test="selector=='saleStatistic'">class="active"</s:if>>
				<a href="admin/sale_saleStatistic">顾问销售统计</a>
			</li>
			<li <s:if test="selector=='appSaleStatistic'">class="active"</s:if>>
				<a href="admin/sale_appSaleStatistic">平台收入统计</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#cash-menu">
			<i class="icon-money"></i>钱包管理
		</div>
		<ul id="cash-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='walletList'">class="active"</s:if>>
				<a href="admin/wallet_walletList">钱包列表</a>
			</li>
			<li <s:if test="selector=='withDrawList'">class="active"</s:if>>
				<a href="admin/wallet_withDrawList">提现列表</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#cash-menu">
			<i class="icon-comments-alt"></i>用户反馈
		</div>
		<ul id="cash-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='complaintList'">class="active"</s:if>>
				<a href="admin/feedback_complaintList">用户投诉</a>
			</li>
			<li <s:if test="selector=='feedbackList'">class="active"</s:if>>
				<a href="admin/feedback_feedbackList">打分评价</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse" data-target="#set-menu">
			<i class="icon-cogs"></i>系统设置
		</div>
		<ul id="set-menu" class="nav nav-list collapse in">
			<li <s:if test="selector=='priceSetting'">class="active"</s:if>>
				<a href="admin/setting_priceSetting">价格配置</a>
			</li>
			<li <s:if test="selector=='fieldList'">class="active"</s:if>>
				<a href="admin/setting_fieldList">领域设置</a>
			</li>
			<li <s:if test="selector=='cityList'">class="active"</s:if>>
				<a href="admin/setting_cityList">城市设置</a>
			</li>
		</ul>
	</div>
</body>
<script type="text/javascript">
	/* $("li").click(function() {
		$(this).addClass("active");
	}); */
</script>
</html>