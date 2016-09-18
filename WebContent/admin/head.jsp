<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/* if (session == null || session.getAttribute("username") == null
			|| !session.getAttribute("userType").equals("Admin"))
	{
		response.sendRedirect("index_login");
	} */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顾问帮后台管理系统</title>
<style type="text/css">
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
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
					<li id="fat-menu" class="dropdown">
						<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-user"></i>
							<%=session.getAttribute("username")%>
							<i class="icon-caret-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a tabindex="-1" href="admin/index_changePassword">
									<i class="icon-lock"></i> 修改密码
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<a tabindex="-1" href="admin/index_logout">
									<i class="icon-off"></i> &nbsp;&nbsp;&nbsp;注 销
								</a>
							</li>
						</ul>
					</li>
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

</body>
</html>