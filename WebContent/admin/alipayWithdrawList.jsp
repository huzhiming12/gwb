<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>

<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="admin/stylesheets/theme.css">
<link rel="stylesheet" href="admin/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="admin/stylesheets/mystyle.css">

<script language="JavaScript" src="admin/javascripts/mydate.js"></script>
<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="admin/lib/bootstrap/js/bootstrap.js"></script>


<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">


</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
	<!--<![endif]-->

	<%@ include file="head.jsp"%>

	<div class="container-fluid" style="min-height: 700px;">

		<div class="row-fluid">
			<div class="span3">
				<jsp:include page="left_menu.jsp"></jsp:include>
			</div>
			<div class="span9">
				<div class="stats">
					<p class="stat">
						<span class="btn btn-sm btn-default" title="返回" onclick="history.go(-1)">
							<i class="icon-arrow-left"></i>
						</span>
					</p>
				</div>
				<h1 class="page-title">支付宝批量转账</h1>
				<hr>
				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>顾问账号</th>
								<th>提现顾问</th>
								<th>提现支付宝账号</th>
								<th>真实姓名</th>
								<th>提现金额</th>
								<th>提现时间</th>
								<th>提现状态</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="res.withdraw" var="w" status="wi">
								<tr>
									<td>
										<s:property value="#wi.index+1" />
									</td>
									<td>
										<s:property value="#w.consMobilePhone" />
									</td>
									<td>
										<s:property value="#w.consName" />
									</td>
									<td>
										<s:property value="#w.account" />
									</td>
									<td>
										<s:property value="#w.name" />
									</td>
									<td>
										￥
										<s:property value="#w.money" />
									</td>
									<td>
										<s:date name="#w.createTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td class="text-success">
										<s:if test="#w.state==0">
											<label class="label">未处理</label>
										</s:if>
										<s:elseif test="#w.state==1">
											<label class="label label-success">提现成功</label>
										</s:elseif>
										<s:elseif test="#w.state==1">
											<label class="label label-important">提现失败</label>
										</s:elseif>
									</td>
									<!-- <td>
										<a href="user.html">
											<i class="icon-pencil"></i>
										</a>
										<a href="#myModal" role="button" data-toggle="modal">
											<i class="icon-remove"></i>
										</a>
									</td> -->
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<form action="https://mapi.alipay.com/gateway.do" method="get" target="_bank">
						<s:iterator value="res.form" var="f">
							<%-- <s:property value="key" /> --%>
							<input type="hidden" name="<s:property value="key"/>" value="<s:property value="value"/>">
						</s:iterator>
						<button type="submit" class="btn btn-block btn-primary">确认转账</button>
					</form>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>


