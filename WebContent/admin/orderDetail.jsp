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
<link rel="stylesheet" type="text/css" href="admin/stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="admin/stylesheets/zoom.css">
<link rel="stylesheet" href="admin/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="admin/stylesheets/mystyle.css">

<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="admin/lib/bootstrap/js/bootstrap.js"></script>


<!-- Demo page code -->
<script src="admin/javascripts/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
<script src="admin/javascripts/xiangce.js" type="text/javascript"></script>

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.chatImg {
	max-height: 100px;
	width: auto;
}

.chatBox {
	max-height: 500px;
	overflow-y: scroll;
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
				<div class="stats">
					<p class="stat">
						<span class="btn btn-sm btn-default" title="返回" onclick="history.go(-1)">
							<i class="icon-arrow-left"></i>
						</span>
					</p>
				</div>
				<h1 class="page-title">订单详情</h1>
				<hr>
				<div class="well">
					<div class="request_padding">

						<div class="center color_font_gray"></div>
						<br>
						<div class="user_info_title">
							<strong>订单内容</strong>
						</div>

						<table class="table table-bordered">
							<tbody>
								<tr>
									<td width="15%">订单编号</td>
									<td width="35%">
										<s:property value="order.orderId" />
									</td>
									<td width="15%">订单创建时间</td>
									<td width="35%">
										<s:date name="order.createTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
								</tr>
								<tr>
									<td>订单金额</td>
									<td>
										<s:property value="order.money" />
										元
									</td>
									<td>发布人</td>
									<td>
										<a href="admin/user_userInfo?user.id=<s:property value="order.userId"/> ">
											<s:property value="order.username" />
										</a>
									</td>
								</tr>
								<tr>
									<td>订单状态</td>
									<td>
										<s:if test="order.cancelState==0">
											<s:if test="order.state==0">
												<span class="badge badge-info">待预约</span>
											</s:if>
											<s:elseif test="order.state==1">
												<span class="badge">待支付</span>
											</s:elseif>
											<s:elseif test="order.state==2">
												<span class="badge badge-warning">已支付</span>
											</s:elseif>
											<s:elseif test="order.state==3">
												<span class="badge badge-success">待评价</span>
											</s:elseif>
											<s:elseif test="order.state==4">
												<span class="badge badge-inverse">完成</span>
											</s:elseif>
											<s:elseif test="order.state==5">
												<span class="badge badge-important">订单关闭</span>
											</s:elseif>
										</s:if>
										<s:elseif test="order.cancelState==1">
											<span class="badge" style="background: #845729"> 取消待确认 </span>
										</s:elseif>
										<s:elseif test="order.cancelState==2">
											<span class="badge" style="background: #a23"> 已取消 </span>
										</s:elseif>
									</td>
									<td>支付状态</td>
									<td>
										<s:if test="order.payState==0">
											<label class="label">未支付</label>
										</s:if>
										<s:else>
											<label class="label label-success">已支付</label>
										</s:else>
									</td>
								</tr>

								<tr>
									<td>支付时间</td>
									<td>
										<s:date name="map.payrecord.createTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>支付方式</td>
									<td>
										<s:if test="map.payrecord.payType==0">微信支付</s:if>
										<s:elseif test="map.payrecord.payType==1">支付宝支付</s:elseif>
									</td>
								</tr>

								<tr>
									<td>备选时间</td>
									<td>
										<s:iterator value="timeBeans" var="t">
											<label class="label">
												<s:date name="#t.time" format="yyyy-MM-dd HH:mm:ss" />
											</label>
										</s:iterator>
									</td>
									<td>预约时间</td>
									<td>
										<s:date name="order.appointTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
								</tr>
								<tr>
									<td>顾 问</td>
									<td>
										<a href="admin/cons_consultantInfo?consultant.id=<s:property value='order.consId' />">
											<s:property value="order.consName" />
										</a>
									</td>
									<td>完成时间</td>
									<td>
										<s:date name="order.finishTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
								</tr>
							</tbody>
						</table>



						<div class="user_info_title">
							<strong>需求内容 </strong>
						</div>
						<br>
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12" style="min-height: 200px;">
									<h4 align="center">
										<s:property value="order.requestBean.title" />
									</h4>
									<s:property value="order.requestBean.content" />
								</div>
							</div>
						</div>


						<div class="user_info_title">
							<strong>咨询记录(<span id="textmsg"></span> <span id="voicemsg"></span>)
							</strong>
						</div>
						<br>
						<div class="container-fluid chatBox">
							<div class="row-fluid">
								<div class="span12">
									<table id="msgsTable" class="table">
										<thead>
											<tr>
												<th>发送人</th>
												<th>消息内容</th>
												<th>发送时间</th>
											</tr>
										</thead>
									</table>

									<div id="more" class="btn btn-block" style="">更多</div>
									<br>
								</div>
							</div>
						</div>


						<div class="user_info_title">
							<strong>备注</strong>
						</div>
						<br>
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<div class="tabbable" id="tabs-742653">
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#panel-319455" data-toggle="tab">评价内容</a>
											</li>
											<li>
												<a href="#panel-154397" data-toggle="tab">投诉内容</a>
											</li>
											<li>
												<a href="#panel-154393" data-toggle="tab">用户取消</a>
											</li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="panel-319455">
												<p>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<s:if test="comment.id">
														<s:property value="comment.content" />
													</s:if>
													<s:else>
														暂无评价内容
													</s:else>
												</p>
											</div>
											<div class="tab-pane" id="panel-154397">
												<p>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<s:if test="complaint.id">
														<s:property value="complaint.content" />
													</s:if>
													<s:else>
														暂无投诉
													</s:else>
													<s:property value="complaint.content" />
												</p>
											</div>
											<div class="tab-pane" id="panel-154393">
												<table class="table">
													<tr>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">取消原因</th>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">取消时间</th>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">顾问意见</th>
													</tr>
													<s:iterator value="reasonBeans" var="reason">
														<tr>
															<td width="500">
																<s:property value="#reason.content" />
															</td>
															<td>
																<s:date name="#reason.createTime" format="yyyy/MM/dd HH:mm:ss" />
															</td>
															<td>
																<s:if test="#reason.state==0">
																	<label class="label">未处理</label>
																</s:if>
																<s:elseif test="#reason.state==1">
																	<label class="label label-success">同意</label>
																</s:elseif>
																<s:elseif test="#reason.state==2">
																	<label class="label label-important">不同意</label>
																</s:elseif>

															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="admin/javascripts/zoom.min.js"></script>
	<script type="text/javascript">
		var orderId = "<s:property value='order.orderId'/>";
		var user = "u<s:property value='order.userMobile'/>";
		var consultant = "c<s:property value='order.consMobile'/>";
		var page = 1;
		loadMsg(page);
		loadVoiceMsg();
		function loadMsg(page) {
			$.ajax({
				url : "Msg_getMsgsByOrderId",
				type : "post",
				dataType : "json",
				data : {
					"pager.condition['orderId']" : orderId,
					"pager.pageNow" : page
				},
				success : function(results) {
					var msgs = results.res.msg;
					var pager = results.res.pager;
					dealData(pager, msgs);
				}
			});
		}

		function loadVoiceMsg() {
			$.ajax({
				url : "Msg_loadVoiceMsgTime",
				type : "post",
				dataType : "json",
				data : {
					"orderId" : orderId,
				},
				success : function(results) {
					//alert(results.res);
					var time = results.res;
					var min = parseInt(time / 60);
					var sec = time % 60;
					if (min > 0)
						$("#voicemsg").text("通话" + min + "分" + sec + "秒");
					else
						$("#voicemsg").text("通话" + sec + "秒");
				}
			});
		}

		function dealData(pager, msgs) {
			for (var i = 0; i < msgs.length; i++) {
				var content;
				if (msgs[i].type == "txt")
					content = msgs[i].msg;
				else if (msgs[i].type == "img") {
					content = "<ul class=\"gallery\"><li> <a href=\"../gwb_res/"+msgs[i].url+"\"><img src=\"../gwb_res/"+msgs[i].url+"\" class=\"img-polaroid user_card\" /></a></li></ul>";
				}
				var tempUser;
				if (msgs[i].from_user == user)
					tempUser = "<s:property value='order.username'/>";
				else
					tempUser = "<s:property value='order.consName'/>";
				var tr = "<tr><td>" + tempUser + ":</td><td>" + content
						+ "</td><td>" + getLocalTime(msgs[i].timestamp)
						+ "</td></tr>";
				$("#msgsTable").append(tr);
			}
			page = pager.pageNow + 1;
			if (page > pager.pageNum) {
				$("#more").text("更多");
				$("#more").attr("disabled", "disabled");
			}
			$("#textmsg").text("共 " + pager.totalNum + " 条在线咨询记录,");
		}
		$("#more").click(function() {
			loadMsg(page);
		});

		function getLocalTime(nS) {
			return new Date(parseInt(nS)).toLocaleString();
		}
	</script>
</body>
</html>


