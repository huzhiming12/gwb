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
<script language="JavaScript" src="admin/lib/bootstrap/js/mydate.js"></script>
<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="admin/lib/bootstrap/js/bootstrap.js"></script>

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

#my-form {
	width: 85%;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 1em;
	border-radius: 3px;
	box-shadow: 0 0 1px rgba(0, 0, 0, .2);
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

	<%@ include  file="head.jsp"%>

	<div class="container-fluid" style="min-height: 700px;">

		<div class="row-fluid">
			<div class="span3">
				<jsp:include page="left_menu.jsp"></jsp:include>
			</div>
			<div class="span9">
				<h1 class="page-title">添加城市</h1>
				<hr>
				<div style="width: 100%; text-align: center">
					<p class="text-warning">
						<em>欢迎来到城市添加页面！</em>
					</p>
					<p class="text-warning">
						<span>添加的城市将直接加入到城市列表中</span>
					</p>
					<p class="text-warning">
						<span>顾问可以通过城市列表选择自己城市！</span>
					</p>
				</div>
				<br>
				<form id="my-form">
					<div style="width: 100%; text-align: center">
						<table style="margin: auto">
							<tr>
								<td style="width: 25%;" height="34" align="center">
									<label class="control-label" for="inputPassword">城市名称:</label>
								</td>
								<td colspan="2" align="center">
									<input style="width: 90%;" id="name" type="text" placeholder="请输入城市名称" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td height="37" colspan="2" align="center">
									<button id="addBtn" class="btn btn-warning btn-block" type="button">添&nbsp;&nbsp;加</button>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#addBtn").click(function() {
			if ($("#name").val() == "") {
				alert("城市名称不能为空！");
				return;
			}
			$.post("admin/setting_addCityResult", {
				"city.name" : $("#name").val()
			}, function(data, status) {
				if (data == true) {
					alert("添加成功");
					window.location.href = "admin/setting_cityList";
				} else
					alert("添加失败");
			});
		});
	</script>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>


