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

	<%@ include  file="head.jsp"%>

	<div class="container-fluid" style="min-height: 700px;">

		<div class="row-fluid">
			<div class="span3">
				<jsp:include page="left_menu.jsp"></jsp:include>
			</div>
			<div class="span9">
				<h1 class="page-title">顾问销售列表</h1>
				<hr>
				<form action="admin/sale_saleList" method="post">
					<div class="btn-toolbar">
						<div class="input-append">

							<input class="span3" name="pager.condition['keyword']"
								value="<s:property value="pager.condition['keyword']"/>" placeholder="订单号、顾问、顾问账号"
								type="text">
							<button class="btn" type="submit">搜索</button>

							<input type="text" class="span2" name="pager.condition['startTime']" readonly="readonly"
								value="<s:property value="pager.condition['startTime']"/>"
								onfocus="MyCalendar.SetDate(this)" placeholder="起始时间" style="margin-left: 8px;">
							<input class="span2" type="text" name="pager.condition['endTime']" readonly="readonly"
								value="<s:property value="pager.condition['endTime']"/>" onfocus="MyCalendar.SetDate(this)"
								placeholder="结束时间">

							<button class="btn" type="submit">
								<i class="icon-filter"></i> 筛选
							</button>
							<!-- <button class="btn" type="button" title="清空条件" id="clear">
								<i class="icon-trash"></i>
							</button> -->
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
								<th>订单号</th>
								<th>总金额</th>
								<th>顾问账号</th>
								<th>顾问姓名</th>
								<th>顾问收入</th>
								<th>平台收入</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="recordBeans" var="r" status="re">
								<tr>
									<td>
										<s:property value="#re.index+1" />
									</td>
									<td>
										<a href="admin/order_orderDetail?id=<s:property value='#r.oid'/> ">
											<s:property value="#r.orderId" />
										</a>
									</td>
									<td>
										￥
										<s:property value="#r.money+#r.percent" />

									</td>
									<td>
										<s:property value="#r.consMobilePhone" />
									</td>
									<td>
										<s:property value="#r.consName" />
									</td>
									<td>
										￥
										<s:property value="#r.money" />
									</td>
									<td>
										￥
										<s:property value="#r.percent" />
									</td>
									<td>
										<s:date name="#r.createTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>
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
						<h3 id="myModalLabel">顾问销售列表导出条件筛选</h3>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td class="span1"></td>
								<td class="span3">
									<label class="control-label">顾问账号:</label>
								</td>
								<td class="span4">
									<input id="exMobilePhone" type="text" placeholder="顾问账号\手机号">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="control-label">起始时间:</label>
								</td>
								<td>
									<input id="exStartTime" value="<s:property value="pager.condition['startTime']"/>" type="text" onfocus="MyCalendar.SetDate(this)" placeholder="开始时间" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="control-label">结束时间:</label>
								</td>
								<td>
									<input id="exEndTime" value="<s:property value="pager.condition['endTime']"/>" type="text"
										onfocus="MyCalendar.SetDate(this)" placeholder="结束时间" readonly="readonly">
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
		$("#exportBtn").click(
				function() {
					window.location.href = "admin/sale_exportSaleList"
							+ "?pager.condition['keyword']="
							+ $("#exMobilePhone").val()
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


