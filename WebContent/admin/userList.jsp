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
				<h1 class="page-title">用户列表</h1>
				<hr>
				<form action="admin/user_userList?pager.pageNow=1" id="search_form" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['condition']"
								value='<s:property value="pager.condition.condition"/>' placeholder="账号、姓名、职位" type="text">
							<button class="btn" id="btn_search" type="submit">搜索</button>
							<button class="btn btn-success" type="button" id="btn_export">
								<i class="icon-signout"></i> 导出列表
							</button>
						</div>
					</div>
				</form>
				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>账号/手机号</th>
								<th>真实姓名</th>
								<th>联系电话</th>
								<th>职务</th>
								<th>用户状态</th>
								<th style="width: 46px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="users" id="u" status="user">
								<tr>
									<td>
										<s:property value="#user.index+1" />
									</td>
									<td>
										<a href="admin/user_userInfo?user.id=<s:property value="#u.id" />">
											<s:property value="#u.mobilePhone" />
										</a>
									</td>
									<td>
										<s:property value="#u.name" />
									</td>
									<td>
										<s:property value="#u.phone" />
									</td>
									<td>
										<s:property value="#u.position" />
									</td>
									<td>
										<s:if test="#u.state==0">
											<label class="label">待审核</label>
										</s:if>
										<s:elseif test="#u.state==1">
											<label class="label label-success">审核通过</label>
										</s:elseif>
										<s:elseif test="#u.state==2">
											<label class="label label-warning">审核驳回</label>
										</s:elseif>
										<s:elseif test="#u.state==4">
											<label class="label label-important">已禁用</label>
										</s:elseif>
									</td>
									<td>
										<s:if test="#u.state==0">
											<a href="#reviewModal" data-toggle="modal"
												onclick="reviewId= '<s:property value='#u.id'/>'" role="button" title="审核">
												<i class="icon-pencil"></i>
											</a>
										</s:if>
										<a href="#myModal" role="button" onclick="delId= '<s:property value='#u.id'/>'"
											data-toggle="modal">
											<i class="icon-remove"></i>
										</a>
										<s:if test="#u.state==1">
											<a href="#lockModal" role="button" title="禁用该用户"
												onclick="lockId='<s:property value="#u.id"/>'" data-toggle="modal">
												<i class="icon-unlock"></i>
											</a>
										</s:if>
										<s:if test="#u.state==4">
											<a href="#unlockModal" role="button" title="启用该用户"
												onclick="lockId='<s:property value="#u.id"/>'" data-toggle="modal">
												<i class="icon-lock"></i>
											</a>
										</s:if>
									</td>
								</tr>
							</s:iterator>

						</tbody>
					</table>
				</div>
				<div class="pagination">
					<s:property value="pagerTool.getPagerBar()" escape="false" />
				</div>

				<div class="modal small hide fade" id="reviewModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">用户审核</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否同意该用户加入顾问帮？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-danger" id="btn_disagree" data-dismiss="modal">驳回</button>
						<button class="btn btn-success" id="btn_agree" data-dismiss="modal">同意</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">确认删除</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否要删除该用户？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" id="btn_del" data-dismiss="modal">删除</button>
					</div>
				</div>


				<div class="modal small hide fade" id="lockModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">禁用用户</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否禁用该用户？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-danger" id="btnLock" data-dismiss="modal">禁用</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>

				<div class="modal small hide fade" id="unlockModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">启用用户</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否启用该用户？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" id="btnUnlock" data-dismiss="modal">启用</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
	var delId;
	var reviewId;
	var lockId;
		$("#btn_search").click(function() {
			$("#search_form").submit();
		});
		
		$("#btn_disagree").click(function() {
			$.post("admin/user_reviewUser",
					  {
					    "user.id": reviewId,
					    "user.state":2
					  },
					  function(data,status){
						  alert(data);
						  location.reload();
					  });
		});
		
		$("#btn_agree").click(function() {
			$.post("admin/user_reviewUser",
					  {
					    "user.id": reviewId,
					    "user.state":1
					  },
					  function(data,status){
						  alert(data);
						  location.reload();
					  });
		});
		
		$("#btn_del").click(function() {
			$.post("admin/user_delUser",
					  {
					    "user.id": delId
					  },
					  function(data,status){
						  if (data==true) {alert("删除成功！");location.reload();}
						  else {alert("删除失败！");}
					  });
		});
		
		$("#btnLock").click(function() {
			$.post("admin/user_lockUser", {
				"user.id" : lockId,
			}, function(data, status) {
				if (data == true) {
					alert("禁用成功！");
					location.reload();
				} else {
					alert("禁用失败！");
				}
			});
		});

		$("#btnUnlock").click(function() {
			$.post("admin/user_unlockUser", {
				"user.id" : lockId,
			}, function(data, status) {
				if (data == true) {
					alert("启用成功！");
					location.reload();
				} else {
					alert("启用失败！");
				}
			});
		});
		
		$("#btn_export").click(function() {
			window.location.href="admin/user_exportUserList";
		});
	</script>

</body>
</html>