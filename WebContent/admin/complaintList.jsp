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
				<h1 class="page-title">用户投诉</h1>
				<hr>
				<form action="admin/feedback_complaintList" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['keyword']"
								value="<s:property value="pager.condition['keyword']"/>" placeholder="用户、顾问、订单" type="text">
							<button class="btn" type="submit">搜索</button>
						</div>
					</div>
				</form>
				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th width="100">投诉用户</th>
								<th width="100">被投诉顾问</th>
								<th width="100">投诉订单</th>
								<th>投诉内容</th>
								<th width="150">日期</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="complaintBeans" id="c" status="com">
								<tr>
									<td>
										<s:property value="#com.index+1" />
									</td>
									<td>
										<s:property value="#c.userName" />
									</td>
									<td>
										<s:property value="#c.consName" />
									</td>
									<td>
										<s:property value="#c.orderId" />
									</td>
									<td>
										<s:property value="#c.content" />
									</td>
									<td>
										<s:date name="#c.createTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>

								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>

				<div class="pagination">
					<s:property value="pagerTool.getPagerBar()" escape="false" />
				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">确认删除</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否要删除该城市？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button id="delBtn" class="btn btn-danger" data-dismiss="modal">删除</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		var delId;
		function addcity() {
			window.self.location = "admin/setting_addCity";
		}

		$("#delBtn").click(function() {
			$.post("admin/setting_delCity", {
				"city.id" : delId
			}, function(data, status) {
				if (data == true) {
					alert("添加成功");
					window.location.href = "admin/setting_cityList";
				} else
					alert("添加失败");
			});
		});
	</script>
</body>
</html>


