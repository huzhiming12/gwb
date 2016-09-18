<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顾问测试</title>
</head>
<style>
p {
	color: red;
}
</style>
<body>

	<p>文件上传</p>
	<form action="Tool_uploadFile" method="post" enctype="multipart/form-data">
		顾问id：
		<input type="file" name="file">
		<input type="submit" value="上传">
	</form>

	<p>手机号码检测</p>
	<form action="Consultant_phoneCheck">
		手机号码：
		<input type="text" name="consultant.mobilePhone">
		<input type="submit" value="检测">
	</form>

	<p>顾问注册</p>
	<form action="Consultant_register">
		手机号：
		<input type="text" name="consultant.mobilePhone">
		<br> 密码:
		<input type="text" name="consultant.password">
		<input type="submit" value="注册">
	</form>

	<p>更新顾问信心</p>
	<form action="Consultant_updateInfo" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 头像地址:
		<input type="text" name="consultant.icon"><br>
		姓名：<input type="text" name="consultant.name"><br>
		职位<input type="text" name="consultant.position"><br>
		城市编号<input type="text" name="consultant.city.id"><br>
		邮箱<input type="text" name="consultant.e_mail"><br>
		电话<input type="text" name="consultant.phone"><br>
		介绍<input type="text" name="consultant.introduction"><br>
		领域<input type="text" name="fieldString"><br>
		<input type="submit" value="更换">
	</form>

	<p>更新姓名</p>
	<form action="Consultant_changeName" method="post">
		id：
		<input type="text" name="consultant.id">
		<br> 姓名:
		<input type="text" name="consultant.name">
		<input type="submit" value="更新">
	</form>

	<p>更新职位</p>
	<form action="Consultant_changePosition" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 职务:
		<input type="text" name="consultant.position">
		<input type="submit" value="更新">
	</form>

	<p>更新电话</p>
	<form action="Consultant_changePhone" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 电话:
		<input type="text" name="consultant.phone">
		<input type="submit" value="更新">
	</form>

	<p>更新e_mail</p>
	<form action="Consultant_changeE_mail" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> e_mail:
		<input type="text" name="consultant.e_mail">
		<input type="submit" value="更新">
	</form>

	<p>更新个人简介</p>
	<form action="Consultant_changeIntroduction" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 个人介绍:
		<input type="text" name="consultant.introduction">
		<input type="submit" value="更新">
	</form>

	<p>加载所有城市信息</p>
	<form action="Tool_loadCitys" method="post">
		<input type="submit" value="加载">
	</form>


	<p>编辑顾问所在城市</p>
	<form action="Consultant_changeCity" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 城市编号:
		<input type="text" name="consultant.city.id">
		<input type="submit" value="更新">
	</form>

	<p>编辑顾问擅长领域</p>
	<form action="Consultant_changeFields" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 领域编号:以;隔开
		<input type="text" name="fieldString">
		<input type="submit" value="更新">
	</form>


	<p>添加名片</p>
	<form action="Consultant_addCards" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 名片地址:地址之间以;格隔开
		<input type="text" name="card">
		<input type="submit" value="添加">
	</form>


	<p>更改密码</p>
	<form action="Consultant_changePassword" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 密码:
		<input type="text" name="consultant.password">
		<br> 原密码:
		<input type="text" name="oldPassword">
		<input type="submit" value="更新">
	</form>

	<p>顾问登录</p>
	<form action="Consultant_login" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<br> 密码:
		<input type="text" name="consultant.password">
		<input type="submit" value="登录">
	</form>

	<p>加载名片</p>
	<form action="Consultant_loadCards" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<input type="submit" value="加载">
	</form>


	<p>加载顾问信息</p>
	<form action="Consultant_loadInfo" method="post">
		顾问id：
		<input type="text" name="consultant.id">
		<input type="submit" value="加载">
	</form>

</body>
</html>