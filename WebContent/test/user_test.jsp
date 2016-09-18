<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户测试</title>
</head>
<style>
p {
	color: red;
}
</style>
<body>
	<p>文件上传</p>
	<form action="Tool_uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="上传">
	</form>

	<p>电话号码检测</p>
	<form action="User_phoneCheck" method="post">
		<input type="text" name="user.mobilePhone">
		<input type="submit" value="检测号码">
	</form>

	<p>获取验证码</p>
	<form action="Tool_getVerificationCode" method="post">
		<input type="text" name="mobilePhone">
		<input type="submit" value="获取验证码">
	</form>

	<p>用户注册</p>
	<form action="User_register" method="post">
		用户手机号：
		<input type="text" name="user.mobilePhone">
		<br>密码：
		<input type="text" name="user.password">
		<br>职位：
		<input type="text" name="user.position">
		<br>姓名：
		<input type="text" name="user.name">
		<br>头像：
		<input type="text" name="user.icon">
		<br>名片2：
		<input type="text" name="card">
		
		<input type="submit" value="注册">
	</form>

	<p>更换用户信息</p>

	<form action="User_updateInfo" method="post">
		id <input type="text" name="user.id"><br>
		头像<input type="text" name="user.icon"><br>
		姓名<input type="text" name="user.name"><br>
		职位<input type="text" name="user.position"><br>
		邮件<input type="text" name="user.e_mail"><br>
		电话<input type="text" name="user.phone"><br>
		介绍<input type="text" name="user.introduction"><br>
		<input type="submit" value="更换">
	</form>

	<p>更新真实姓名</p>

	<form action="User_changeName" method="post">
		用户id：
		<input type="text" name="user.id">
		<br>姓名：
		<input type="text" name="user.name">
		<input type="submit" value="更新">
	</form>

	<p>更新企业职务</p>

	<form action="User_changePosition" method="post">
		用户id：
		<input type="text" name="user.id">
		<br>职务：
		<input type="text" name="user.position">
		<input type="submit" value="更新">
	</form>

	<p>上传名片信息</p>

	<form action="User_addCards" method="post" enctype="multipart/form-data">
		用户id：
		<input type="text" name="user.id">
		<br>选择名片：
		<!-- 可以设置多文件上传 -->
		<input type="text" name="card">
		<input type="submit" value="上传">
	</form>

	<p>更新密码</p>

	<form action="User_changePassword" method="post" enctype="multipart/form-data">
		用户id：
		<input type="text" name="user.id">
		<br>密码：
		<input type="text" name="user.password">
		<input type="text" name="oldPassword">
		<input type="submit" value="更改">
	</form>

	<p>用户登录</p>
	<form action="User_login" method="post">
		用户id：
		<input type="text" name="user.mobilePhone">
		<br>密码：
		<input type="text" name="user.password">
		<input type="submit" value="登录">
	</form>

	<p>更改email</p>
	<form action="User_changeE_mail" method="post">
		用户id：
		<input type="text" name="user.id">
		<br>e_mail：
		<input type="text" name="user.e_mail">
		<input type="submit" value="更改">
	</form>

	<p>更改电话号码</p>
	<form action="User_changePhone" method="post">
		用户id：
		<input type="text" name="user.id">
		<br>电话号码：
		<input type="text" name="user.phone">
		<input type="submit" value="更改">
	</form>

	<p>更改个人介绍</p>
	<form action="User_changeIntroduction" method="post">
		用户id：
		<input type="text" name="user.id">
		<br>个人介绍：
		<input type="text" name="user.introduction">
		<input type="submit" value="更改">
	</form>

	<p>个人信息</p>
	<form action="User_userInfo" method="post">
		用户id：
		<input type="text" name="user.id">
		<input type="submit" value="加载">
	</form>


</body>
</html>