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
<title>顾问注册</title>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="admin/stylesheets/theme.css">
<link rel="stylesheet" href="admin/stylesheets/mystyle.css">

<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>


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
	height: 85%;
	margin: 0 auto;
	padding: 1em;
	display: table;
}

.content {
	text-align: center;
	line-height: 100%;
	vertical-align: middle;
	display: table-cell;
}

.img {
	width: 68px;
	height: 60px;
}

.notic_text {
	line-height: 30px;
	font-weight: 800;
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

html, body {
	margin: 0;
	height: 100%;
}
</style>

</head>
<body>
	<!--<![endif]-->
	<div id="my-form">
		<div class="content">
			<s:if test="card=='true'">
				<img class="img" src="admin/images/success.png">
				<br>
				<br>
				<div class="notic_text">
					注册成功<br>审核通过后我们会及时通知您
				</div>
			</s:if>
			<s:else>
				<img class="img" src="admin/images/fail.png">
				<br>
				<br>
				<div class="notic_text">
					注册失败<br>请检查信息稍后重试
				</div>
			</s:else>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>


