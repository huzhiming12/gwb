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
<script src="admin/javascripts/mydate.js" type="text/javascript"></script>
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
				<h1 class="page-title">支付宝批量退款</h1>
				<hr>

				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>退款单号</th>
								<th>订单号</th>
								<th>退款金额</th>
								<th>用户账号</th>
								<th>用户姓名</th>
								<th>支付方式</th>
								<th>申请时间</th>
								<th>退款状态</th>
							</tr>
						</thead>
						<tbody>
							<s:if test="map.refund.size==0">
								<tr>
									<td colspan="9">暂无退款申请</td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="map.refund" var="r" status="beans">
									<tr>
										<td>
											<s:property value="#beans.index+1" />
										</td>
										<td>
											<s:property value="#r.refundId" />
										</td>
										<td>
											<a href="admin/order_orderDetail?id=<s:property value='#r.oid'/> ">
												<s:property value="#r.orderId" />
											</a>
										</td>
										<td>
											￥
											<s:property value="#r.money" />
										</td>
										<td>
											<s:property value="#r.mobilePhone" />
										</td>
										<td>
											<s:property value="#r.username" />
										</td>
										<td>
											<s:if test="#r.payType==0">
												<span class="badge badge-success">微信</span>
											</s:if>
											<s:elseif test="#r.payType==1">
												<span class="badge badge-warning">支付宝</span>
											</s:elseif>
										</td>
										<td>
											<s:date name="#r.createTime" format="yyyy/MM/dd HH:mm:ss" />
										</td>
										<td>
											<s:if test="#r.state==0">
												<span class="badge" style="background: #34672"> 未处理 </span>
											</s:if>
											<s:elseif test="#r.state==1">
												<span class="badge badge-success"> 退款成功</span>
											</s:elseif>
											<s:else>
												<span class="badge" style="background: #ab2343">处理中</span>
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</s:else>
						</tbody>
					</table>

					<s:if test="map.refund.size!=0">
						<form action="https://mapi.alipay.com/gateway.do" method="post" target="_blank">
							<s:iterator value="map.form" id="re">
								<input type="hidden"	 name="<s:property value="key" />"
									value="<s:property value="value" />">
							</s:iterator>
							<div style="width: 100%; text-align: center;">
								<button type="submit" class="btn btn-block btn-primary">确认退款</button>
							</div>
						</form>
					</s:if>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>


