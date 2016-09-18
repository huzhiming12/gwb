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
				<h1 class="page-title">订单列表</h1>
				<hr>
				<form action="admin/order_orderList" id="search_form" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['keyword']"
								value="<s:property value="pager.condition['keyword']"/>" placeholder="订单号、发布人、标题、顾问"
								type="text">
							<button class="btn" type="submit">搜索</button>

							<select id="state" name="pager.condition['state']" style="Width: 90px; margin-left: 15px;">
								<option value="-1" <s:if test="pager.condition['state']==-1">selected</s:if>>全部订单</option>
								<!-- <option value="0" <s:if test="pager.condition['state']==0">selected</s:if>>待预约</option> -->
								<option value="1" <s:if test="pager.condition['state']==1">selected</s:if>>待支付</option>
								<option value="2" <s:if test="pager.condition['state']==2">selected</s:if>>已支付</option>
								<option value="3" <s:if test="pager.condition['state']==3">selected</s:if>>待评价</option>
								<option value="4" <s:if test="pager.condition['state']==4">selected</s:if>>已完成</option>
								<!-- <option value="5" <s:if test="pager.condition['state']==5">selected</s:if>>已关闭</option> -->
								<option value="6" <s:if test="pager.condition['state']==6">selected</s:if>>取消待确认</option>
								<option value="7" <s:if test="pager.condition['state']==7">selected</s:if>>已取消</option>
							</select>
							<input type="text" class="span2" name="pager.condition['startTime']" readonly="readonly"
								value="<s:property value="pager.condition['startTime']"/>"
								onfocus="MyCalendar.SetDate(this)" placeholder="开始时间">
							&nsub;&nsub;
							<input class="span2" type="text" name="pager.condition['endTime']" readonly="readonly"
								value="<s:property value="pager.condition['endTime']"/>" onfocus="MyCalendar.SetDate(this)"
								placeholder="结束时间">
							<button class="btn">
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
								<th>订单编号No.</th>
								<th>标题</th>
								<th>发布人</th>
								<th>发布时间</th>
								<th>顾问</th>
								<th>支付状态</th>
								<th>订单状态</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="orders" var="order" status="orders">
								<tr>
									<td>
										<s:property value="#orders.index+1" />
									</td>
									<td>
										<a href="admin/order_orderDetail?id=<s:property value="#order.id" />">
											<s:property value="#order.orderId" />
										</a>
									</td>
									<td>
										<s:property value="#order.requestBean.title" />
									</td>
									<td>
										<s:property value="#order.username" />
									</td>
									<td>
										<s:date name="#order.createTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>
									<td>
										<s:property value="#order.consName" />
									</td>
									<td>
										<s:if test="#order.payState==0">
											<label class="label">未支付</label>
										</s:if>
										<s:else>
											<label class="label label-success">已支付</label>
										</s:else>
									</td>
									<td>
										<s:if test="#order.cancelState==0">
											<s:if test="#order.state==1">
												<span class="badge badge-info">待支付</span>
											</s:if>
											<s:elseif test="#order.state==2">
												<span class="badge badge-warning">已支付</span>
											</s:elseif>
											<s:elseif test="#order.state==3">
												<span class="badge badge-success">待评价</span>
											</s:elseif>
											<s:elseif test="#order.state==4">
												<span class="badge badge-inverse">已完成</span>
											</s:elseif>
											<s:elseif test="#order.state==5">
												<span class="badge badge-inverse">已关闭</span>
											</s:elseif>
										</s:if>
										<s:elseif test="#order.cancelState==1">
											<span class="badge" style="background: #845729"> 取消待确认 </span>
										</s:elseif>
										<s:elseif test="#order.cancelState==2">
											<span class="badge"> 已取消 </span>
										</s:elseif>
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
									<label class="control-label">订单状态:</label>
								</td>
								<td class="span4">
									<select id="exState">
										<option value="-1">全部订单</option>
										<option value="1">待支付</option>
										<option value="2">已支付</option>
										<option value="3">待评价(咨询完成)</option>
										<option value="4">已完成(已评价)</option>
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
		$("#state").change(function() {
			$("#search_form").submit();
		});

		$("#exportBtn").click(
				function() {
					window.location.href = "admin/order_exportOrder"
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


