<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请求测试</title>
</head>
<style>
p {
	color: red;
}
</style>
<body>

	<p>加载领域分类</p>
	<form action="Tool_loadFields" method="post">
		<input type="submit" value="加载">
	</form>

	<p>加载该领域顾问</p>
	<form action="Consultant_loadConsultantByField" method="post">
		领域id：
		<input type="text" name="id">
		<br>第几页：
		<input type="text" name="pager.pageNow">
		<input type="submit" value="加载">
	</form>

	<p>添加需求测试</p>
	<form action="Request_addRequest" method="post">
		需求标题：
		<input type="text" name="req.title">
		<br> 需求内容：
		<input type="text" name="req.content">
		<br> 需求领域 每个领域之间以;相隔：
		<input type="text" name="fieldString">
		<br> 发布人编号：
		<input type="text" name="req.user.id">
		<br> 请求方式：0：线上咨询 1：线下会面
		<input type="text" name="req.consMode">
		<br> 城市编号:
		<input type="text" name="req.city.id">

		<input type="submit" value="添加">
	</form>

	<p>查询请求回应次数</p>
	<form action="Request_getResponseNum" method="post">
		请求编号：
		<input type="text" name="req.id">
		<input type="submit" value="查询">
	</form>


	<p>添加需求回应</p>
	<form action="Request_addResponse" method="post">
		请求编号：
		<input type="text" name="resp.request.id">
		<br> 回应内容：
		<input type="text" name="resp.content">
		<br> 顾问id：
		<input type="text" name="resp.consultant.id"><br>
		回应类型：0 普通回应  1:邀请回应
		<input type="text" name="resp.respType">
		<input type="submit" value="添加">
	</form>

	<p>添加需求邀请</p>
	<form action="Request_addAppoint" method="post">
		请求编号：
		<input type="text" name="appoint.request.id">
		<br> 顾问id：
		<input type="text" name="appoint.consultant.id">
		<input type="submit" value="邀请">
	</form>


	<p>需求的详细内容</p>
	<form action="Request_requestDetail" method="post">
		请求编号：
		<input type="text" name="req.id">
		<input type="submit" value="查询">
	</form>

	<p>查询回应列表</p>
	<form action="Request_getResponses" method="post">
		请求编号：
		<input type="text" name="req.id">
		<input type="submit" value="查询">
	</form>

	<p>用户处理顾问回应</p>
	<form action="Request_dealResponse" method="post">
		回应编号：
		<input type="text" name="resp.id">
		<br> 1：拒绝 2：接受
		<input type="text" name="resp.userState">
		<input type="submit" value="处理">
	</form>

	<p>获取用户的请求</p>
	<form action="Request_getUserRequest" method="post">
		用户id：
		<input type="text" name="id">
		<br> 第几页
		<input type="text" name="pager.pageNow">
		<input type="submit" value="查询">
	</form>
	
	<p>加载用户还未邀请顾问的请求</p>
	<form action="Request_loadUnAppointRequest" method="post">
		用户id：
		<input type="text" name="id">
		<input type="submit" value="查询">
	</form>


	<p>顾问将请求置为不感兴趣</p>
	<form action="Request_uninterestRequest" method="post">
		顾问id：
		<input type="text" name="id">
		<br>请求id
		<input type="text" name="req.id">
		<input type="submit" value="不感兴趣">
	</form>

	<p>顾问加载请求</p>
	<form action="Request_getConsRequest" method="post">
		顾问id：
		<input type="text" name="id">
		<br>第几页
		<input type="text" name="pager.pageNow">
		<input type="submit" value="加载">
	</form>

</body>
</html>
