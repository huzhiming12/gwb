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

<script language="JavaScript" src="admin/javascripts/mydate.js"></script>
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
				<h1 class="page-title">提现列表</h1>
				<hr>
				<form action="admin/wallet_withDrawList" id="search_form" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['keyword']"
								value="<s:property value="pager.condition['keyword']"/>" placeholder="顾问、支付宝账号、姓名"
								type="text">
							<button class="btn" type="submit">搜索</button>

							<input type="text" class="span2" onfocus="MyCalendar.SetDate(this)" placeholder="开始时间"
								readonly="readonly" name="pager.condition['startTime']"
								value="<s:property value="pager.condition['startTime']"/>" style="margin-left: 8px;">
							<input class="span2" type="text" onfocus="MyCalendar.SetDate(this)" readonly="readonly"
								name="pager.condition['endTime']" value="<s:property value="pager.condition['endTime']"/>"
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
							<a class="btn btn-primary" href="admin/wallet_alipayWithdrawList">
								<i class="icon-money"></i> 支付宝批量转账
							</a>
						</div>
					</div>
				</form>
				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>顾问账号</th>
								<th>提现顾问</th>
								<th>提现支付宝账号</th>
								<th>真实姓名</th>
								<th>提现金额</th>
								<th>提现时间</th>
								<th>提现状态</th>
								<th>备注</th>
								<!-- <th style="width: 46px;">操作</th> -->
							</tr>
						</thead>
						<tbody>
							<s:iterator value="withDrawBeans" var="w" status="wi">
								<tr>
									<td>
										<s:property value="#wi.index+1" />
									</td>
									<td>
										<s:property value="#w.consMobilePhone" />
									</td>
									<td>
										<s:property value="#w.consName" />
									</td>
									<td>
										<s:property value="#w.account" />
									</td>
									<td>
										<s:property value="#w.name" />
									</td>
									<td>
										￥
										<s:property value="#w.money" />
									</td>
									<td>
										<s:date name="#w.createTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td class="text-success">
										<s:if test="#w.state==0">
											<label class="label">未处理</label>
										</s:if>
										<s:elseif test="#w.state==1">
											<label class="label label-important">提现失败</label>
										</s:elseif>
										<s:elseif test="#w.state==2">
											<label class="label label-success">提现成功</label>
										</s:elseif>
									</td>
									<td>
										<s:if test="#w.reason!=null">
											<a class="element" data-placement="left" data-content="<s:property value='#w.reason'/>"
												title="失败原因" style="font-size: 20px;">
												&nbsp; <i class=" icon-file"></i>
											</a>
										</s:if>
										<s:else>&nbsp;&nbsp;&nbsp;--</s:else>
									</td>
									<!-- <td>
										<a href="user.html">
											<i class="icon-pencil"></i>
										</a>
										<a href="#myModal" role="button" data-toggle="modal">
											<i class="icon-remove"></i>
										</a>
									</td> -->
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<s:property value="pagerTool.getPagerBar()" escape="false" />
				</div>

				<div class="modal small hide fade" id="exportModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">订单导出筛选</h3>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td class="span1"></td>
								<td class="span3">
									<label class="control-label">处理状态:</label>
								</td>
								<td class="span4">
									<select id="exState">
										<option value="-1">全部</option>
										<option value="0">未处理</option>
										<option value="1">已处理</option>
									</select>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="control-label">起始时间:</label>
								</td>
								<td>
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
		$('.element').popover('hide');
		$("#exportBtn").click(
				function() {
					window.location.href = "admin/wallet_exportWithDrawList"
							+ "?pager.condition['state']="
							+ $("#exState").val()
							+ "&pager.condition['startTime']="
							+ $("#exStartTime").val()
							+ "&pager.condition['endTime']="
							+ $("#exEndTime").val();
				});
		//清空输入
		$("#clear").click(function() {
			$("input").attr("value", "");
		});
	</script>

</body>
</html>


