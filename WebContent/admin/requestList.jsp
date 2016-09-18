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

<script src="admin/javascripts/mydate.js" type="text/javascript"></script>
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
				<h1 class="page-title">需求列表</h1>
				<hr>
				<form action="admin/req_requestList" id="search_form" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['condition']"
								value="<s:property value="pager.condition['condition']"/>" placeholder="标题、发布人、领域"
								type="text">
							<button class="btn" id="btn_search" type="button">搜索</button>

							<input type="text" style="margin-left: 15px;" class="span2" readonly="readonly"
								name="pager.condition['startTime']"
								value="<s:property value="pager.condition['startTime']"/>"
								onfocus="MyCalendar.SetDate(this)" placeholder="开始时间">
							<input class="span2" type="text" name="pager.condition['endTime']" readonly="readonly"
								value="<s:property value="pager.condition['endTime']"/>" onfocus="MyCalendar.SetDate(this)"
								placeholder="结束时间">
							<button class="btn" type="submit">
								<i class="icon-filter"></i> 筛选
							</button>
							<button class="btn" type="button" title="清空条件" id="clear">
								<i class="icon-trash"></i>
							</button>
							<a href="#exportModal" class="btn btn-success" role="button" data-toggle="modal">
								<i class="icon-signout"></i> 导出列表
							</a>
						</div>
					</div>
				</form>
				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>标题</th>
								<th>发布人</th>
								<th>发布时间</th>
								<th>问题领域</th>
								<th>咨询方式</th>
								<th>状态</th>
								<th style="width: 46px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="requests" id="r" status="req">
								<tr>
									<td>
										<s:property value="#req.index+1" />
									</td>
									<td>
										<a href="admin/req_requestDetail?id=<s:property value="#r.id" />">
											<s:property value="#r.title" />
										</a>
									</td>
									<td>
										<a href="admin/user_userInfo?user.id=<s:property value="#r.userId"/> ">
											<s:property value="#r.name" />
										</a>
									</td>
									<td>
										<s:date name="#r.createTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>
									<td>
										<s:iterator value="#r.reqFieldBeans" var="rf">
											<s:property value="#rf.fieldTitle"/>/
										</s:iterator>
									</td>
									<td>
										<s:if test="#r.consMode==0">
											<label class="label label-primary">线上咨询</label>
										</s:if>
										<s:else>
											<label class="label label-info">线下会面</label>
										</s:else>
									</td>
									<td>
										<s:if test="#r.state==0">
											<label class="label label-warning">进行中</label>
										</s:if>
										<s:elseif test="#r.state==1">
											<label class="label">已关闭</label>
										</s:elseif>
										<s:else>
											<label class="label label-success">已解决</label>
										</s:else>
									</td>
									<td>
										<s:if test="#r.state==0">
											<a href="#myModal" role="button" onclick=" reqId =<s:property value="#r.id" />"
												title="关闭需求" data-toggle="modal">
												<i class="icon-remove"></i>
											</a>
										</s:if>
										<s:else> -- </s:else>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<s:property value="pagerTool.getPagerBar()" escape="false" />
				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">确认关闭请求</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 是否要关闭该请求
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" id="btn_close" data-dismiss="modal">关闭</button>
					</div>
				</div>


				<div class="modal small hide fade" id="exportModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">请求导出筛选</h3>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td class="span1"></td>
								<td class="span3">
									<label class="control-label">起始时间:</label>
								</td>
								<td class="span4">
									<input id="exStartTime" type="text" onfocus="MyCalendar.SetDate(this)" placeholder="开始时间">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="control-label">结束时间:</label>
								</td>
								<td>
									<input id="exEndTime" type="text" onfocus="MyCalendar.SetDate(this)" placeholder="结束时间">
								</td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button class="btn btn-small" data-dismiss="modal" aria-hidden="true">取消</button>
						<button id="exportBtn" class="btn btn-small btn-primary" data-dismiss="modal">导出</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		var reqId;
		$("#btn_search").click(function() {
			$("#search_form").submit();
		});
		$("#exportBtn").click(function() {
				window.location.href = "admin/req_exportRequestList"
					+ "?pager.condition['startTime']="
					+ $("#exStartTime").val()
					+ "&pager.condition['endTime']="
					+ $("#exEndTime").val();
		});
		$("#btn_close").click(function() {
			$.post("admin/req_closeRequest",
					  {
					    "id": reqId
					  },
					  function(data,status){
						  if(data.status==200){
						  	alert(data.res);
						  	location.reload();
							  }
						  else
							  alert(data.error);
					  });
		});
		
		//清空输入
		$("#clear").click(function(){
			$("input").attr("value","");
		});
	</script>

</body>
</html>


