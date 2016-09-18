<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<s:if test="#session.name!=null">
		welcome <s:property value="#session.name" /> |<a href="front/User_logout">logout</a>
		<br><br>
	</s:if>
	<a href="front/User_register">userRegister</a>&nbsp;&nbsp; |
	&nbsp;&nbsp;
	<a href="front/User_list">userList</a>&nbsp;&nbsp; | &nbsp;&nbsp;
	<a href="front/User_login">userLogin</a>&nbsp;&nbsp; | &nbsp;&nbsp;



	<s:debug></s:debug>

</body>
</html>

