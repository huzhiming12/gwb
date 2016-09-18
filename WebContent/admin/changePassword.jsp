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
.editBox {
	width: 100%;
	text-align: center;
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
				<h1 class="page-title">密码修改</h1>
				<hr>

				<div class="well">
					<div class="dialog span4">
						<table>
							<tr>
								<td class="span3">
									<label class="control-label">原密码</label>
								</td>
								<td>
									<input id="oldpwd" type="password">
								</td>
							</tr>

							<tr>
								<td class="span3">
									<label class="control-label">新密码</label>
								</td>
								<td>
									<input id="newpwd" type="password">
								</td>
							</tr>

							<tr>
								<td class="span3">
									<label class="control-label">密码确认</label>
								</td>
								<td>
									<input id="confirm" type="password">
								</td>
							</tr>

							<tr>
								<td class="span3"></td>
								<td>
									<button id="changeBtn" type="button" class="btn btn-primary pull-right">修改</button>
									<button id="resetBtn" type="button" class="btn pull-right" style="margin-right: 20px;">重置</button>
								</td>
							</tr>
						</table>
					</div>
				</div>


			</div>
		</div>
	</div>
	<script type="text/javascript">
		var user ="<%=session.getAttribute("username")%>";
		$("#resetBtn").click(function() {
			$("input").val("");
		});
		$("#changeBtn").click(function() {
			if ($("#oldpwd").val() == "") {
				alert("原密码不能为空！");
				return;
			}
			if ($("#newpwd").val() == "") {
				alert("新密码不能为空！");
				return;
			}
			if ($("#confirm").val() == "") {
				alert("密码确认不能为空！");
				return;
			}
			if ($("#newpwd").val().length < 6) {
				alert("密码不能少于6位数！");
				return;
			}
			if ($("#newpwd").val() != $("#confirm").val()) {
				alert("密码前后不一致！");
				return;
			}

			$.ajax({
				url : "admin/index_changePwdResult",
				type : "post",
				dataType : "json",
				data : {
					"admin.userName" : user,
					"admin.password" : $("#newpwd").val(),
					"oldpwd" : $("#oldpwd").val(),
				},
				success : function(results) {
					var status = results.status;
					if (status == 200) {
						switch (results.res) {
						case 1:
							alert("账号不存在！");
							break;
						case 2:
							alert("密码输入错误！");
							break;
						case 3:
							alert("密码修改成功");
							location.reload();
							break;
						default:
							break;
						}
					}
					else
						alert(results.error);
				}
			});

		});
	</script>
	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>


