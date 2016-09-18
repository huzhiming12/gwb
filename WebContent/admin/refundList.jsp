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
				<h1 class="page-title">退款申请列表</h1>
				<hr>
				<form action="admin/order_refundList" id="search_form" method="post">
					<div class="btn-toolbar">
						<div class="input-append">
							<input class="span3" name="pager.condition['keyword']"
								value="<s:property value="pager.condition['keyword']"/>" placeholder="退款单号、订单号、用户账号、姓名"
								type="text">
							<button class="btn" type="submit">搜索</button>

							<select id="payType" name="pager.condition['payType']"
								style="Width: 90px; margin-left: 15px;">
								<option value="-1" <s:if test="pager.condition['payType']==-1">selected</s:if>>全部订单</option>
								<option value="0" <s:if test="pager.condition['payType']==0">selected</s:if>>微信支付</option>
								<option value="1" <s:if test="pager.condition['payType']==1">selected</s:if>>支付宝支付</option>
							</select> <select id="state" name="pager.condition['state']" style="Width: 90px; margin-left: 15px;">
								<option value="-1" <s:if test="pager.condition['state']==-1">selected</s:if>>全 部</option>
								<option value="0" <s:if test="pager.condition['state']==0">selected</s:if>>未处理</option>
								<option value="1" <s:if test="pager.condition['state']==1">selected</s:if>>已处理</option>
							</select>

							<a href="#exportModal" class="btn btn-success" role="button" data-toggle="modal">
								<i class="icon-signout"></i> 导出列表
							</a>
							<a href="admin/order_alipayRefundList" class="btn btn-primary">
								<i class="icon-money"></i> 支付宝批量退款
							</a>

						</div>
					</div>
				</form>

				<div class="well">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>退款单号</th>
								<th>订单编号No.</th>
								<th>退款金额</th>
								<!-- <th>用户账号</th> -->
								<th>用户姓名</th>
								<th>支付方式</th>
								<th>申请时间</th>
								<th>退款状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="refundBeans" var="r" status="beans">
								<tr>
									<td>
										<s:property value="#beans.index+1" />
									</td>
									<td>
										<s:property value="#r.refundId" />
									</td>
									<td>
										<a href="admin/order_orderDetail?id=<s:property value='#r.oid'/> ">
											<s:property value="#r.orderId" />
										</a>
									</td>
									<td>
										￥
										<s:property value="#r.money" />
									</td>
									<%-- <td>
										<s:property value="#r.mobilePhone" />
									</td> --%>
									<td>
										<a href="admin/user_userInfo?user.id=<s:property value="#r.userId"/>">
											<s:property value="#r.username" />
										</a>
									</td>
									<td>
										<s:if test="#r.payType==0">
											<span class="badge badge-success">微信</span>
										</s:if>
										<s:elseif test="#r.payType==1">
											<span class="badge badge-warning">支付宝</span>
										</s:elseif>
									</td>
									<td>
										<s:date name="#r.createTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>
									<td>
										<s:if test="#r.state==0">
											<span class="badge" style="background: #34672"> 未处理 </span>
										</s:if>
										<s:elseif test="#r.state==1">
											<span class="badge badge-success">已退款</span>
										</s:elseif>
										<s:else>
											<span class="badge" style="background: #ab2343">处理中</span>
										</s:else>
									</td>
									<td>
										<s:if test="#r.state==0&&#r.payType==0">
											<a class="btn btn-small btn-success" href="#refundModal" role="button"
												onclick="refundId= '<s:property value='#r.refundId'/>'" data-toggle="modal">退款</a>
										</s:if>
										<s:else> &nbsp;&nbsp;-- </s:else>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="pagination">
					<s:property value="pagerTool.getPagerBar()" escape="false" />
				</div>

				<div class="modal small hide fade" id="refundModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">确认退款</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i> 退款后将不能撤销，是否要退款？
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<button class="btn btn-danger" id="refundBtn" data-dismiss="modal">确定</button>
					</div>
				</div>


				<div class="modal small hide fade" id="exportModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">退款申请列表导出筛选</h3>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td class="span1"></td>
								<td class="span3">
									<label class="control-label">支付方式:</label>
								</td>
								<td class="span4">
									<select id="exPayType">
										<option value="-1">全部订单</option>
										<option value="0">微信支付</option>
										<option value="1">支付宝支付</option>
									</select>
								</td>
							</tr>

							<tr>
								<td class="span1"></td>
								<td class="span3">
									<label class="control-label">处理状态:</label>
								</td>
								<td class="span4">
									<select id="exState">
										<option value="-1">全部订单</option>
										<option value="0">未处理</option>
										<option value="1">已处理</option>
									</select>
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
	var refundId;
		$("#state").change(function() {
			$("#search_form").submit();
		});
		$("#payType").change(function() {
			$("#search_form").submit();
		});

		$("#exportBtn").click(
				function() {
					window.location.href = "admin/order_exportRefund"
							+ "?pager.condition['state']="
							+ $("#exState").val()
							+ "&pager.condition['payType']="
							+ $("#exPayType").val();
				});
		
		$("#refundBtn").click(function(){
			$.post("admin/order_refund",
					  {
					    "refundId": refundId
					  },
					  function(data,status){
						  alert(data);
						  location.reload();
					  });
		});
	</script>

</body>
</html>


