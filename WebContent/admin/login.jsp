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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顾问帮后台管理系统</title>

<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="admin/stylesheets/theme.css">
<link rel="stylesheet" href="admin/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="admin/stylesheets/mystyle.css">

<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="admin/lib/bootstrap/js/bootstrap.js"></script>

</head>
<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
	<!--<![endif]-->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">

				</ul>
				<a class="brand" href="index.html">
					<span class="first">
						<img alt="" src="admin/images/logo.png">
					</span>
					<span class="second">管理系统</span>
				</a>
			</div>
		</div>
	</div>

	<div class="container-fluid">

		<div class="row-fluid">
			<div class="dialog span4">
				<div class="block">
					<div class="block-heading">登录</div>
					<div class="block-body">
						<form>
							<label>用户名</label>
							<input id="userName" type="text" class="span12">
							<label>密码</label>
							<input id="password" type="password" class="span12">
							<button id="loginBtn" type="button" class="btn btn-primary pull-right">登录</button>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="margin-top: 340px;">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		$("#loginBtn").click(function() {
			$.ajax({
				url : "admin/index_loginCheck",
				type : "post",
				dataType : "json",
				data : {
					"admin.userName" : $("#userName").val(),
					"admin.password" : $("#password").val()
				},
				success : function(results) {
					var res = results.res
					var status = results.status
					if (res == 0) {
						alert("账号不存在！")
					} else if (res == 1) {
						alert("密码不正确！")
					} else {
						window.location.href="admin/user_userList";
					}
				}
			});
		});
	</script>
</body>
</html>

