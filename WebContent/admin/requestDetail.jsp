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
				<h1 class="page-title">需求详情</h1>
				<hr>

				<div class="well">
					<div class="request_padding">
						<h3 class="center">
							<s:property value="requestBean.title" />
						</h3>
						<div class="center color_font_gray">
							<span>
								发布人：
								<s:property value="requestBean.name" />
							</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								发布时间：
								<s:date name="requestBean.createTime" format="yyyy/MM/dd HH:mm:ss" />
							</span>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								咨询方式:
								<s:if test="requestBean.consMode==0">
									线上咨询
								</s:if>
								<s:else>
									线下会面
								</s:else>
							</span>
						</div>
						<div class="center color_font_gray"></div>
						<div>
							<s:property value="requestBean.content" />
						</div>

						<br>
						<div class="user_info_title">
							<strong>需求动态</strong>
						</div>
						<br>
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<div class="tabbable" id="tabs-646177">
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#panel-198844" data-toggle="tab">顾问邀请记录</a>
											</li>
											<li>
												<a href="#panel-594029" data-toggle="tab">顾问回应记录</a>
											</li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="panel-198844">
												<table class="table">
													<tr>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">邀请顾问</th>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">邀请时间</th>
														<th style="border-bottom: 1px solid #dddddd; border-top: none;">状态</th>
													</tr>
													<s:iterator value="requestBean.appoints" id="app">
														<tr>
															<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																<div class="reponse_user">
																	<s:if test="!#app.consIcon">
																		<img src="admin/images/user.png" class="img-circle response_icon">
																	</s:if>
																	<s:else>
																		<img src='../gwb_res/<s:property value="#app.consIcon"/>' class="img-circle response_icon">
																	</s:else>
																	<a
																		href="admin/cons_consultantInfo?consultant.id=<s:property value="#app.consId" />">
																		<s:property value="#app.consName" />
																	</a>
																</div>
															</td>
															<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																<span class="color_font_gray">
																	<s:date name="#app.createTime" format="yyyy/MM/dd HH:mm:ss" />
																</span>
															</td>
															<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																<s:if test="#app.state==0">
																	<label class="label">未处理</label>
																</s:if>
																<s:elseif test="#app.state==1">
																	<label class="label label-warning">已回应</label>
																</s:elseif>
																<%-- <s:elseif test="#app.state==2">
																	<label class="label label-success">同意</label>
																</s:elseif> --%>
																<s:elseif test="#app.state==3">
																	<label class="label label-inverse">超时关闭</label>
																</s:elseif>
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
											<div class="tab-pane" id="panel-594029">
												<table class="table">
													<s:if test="requestBean.responses.size">
														<tr>
															<th style="border-bottom: 1px solid #dddddd; border-top: none;">顾问</th>
															<th style="border-bottom: 1px solid #dddddd; border-top: none;">回应时间</th>
															<th style="border-bottom: 1px solid #dddddd; border-top: none;">回应内容</th>
														</tr>

														<s:iterator value="requestBean.responses" id="resp">
															<tr>
																<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																	<div class="reponse_user">
																		<s:if test="!#resp.icon">
																			<img src="admin/images/user.png" class="img-circle response_icon">
																		</s:if>
																		<s:else>
																			<img src='../gwb_res/<s:property value="#resp.icon"/>' class="img-circle response_icon">
																		</s:else>
																		<a
																			href="admin/cons_consultantInfo?consultant.id=<s:property value="#resp.consId" />">
																			<s:property value="#resp.name" />
																		</a>
																	</div>
																</td>
																<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																	<s:date name="#resp.createTime" format="yyyy/MM/dd HH:mm:ss" />
																</td>
																<td style="border-bottom: 1px solid #dddddd; border-top: none;">
																	<s:property value="#resp.content" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
													<s:else>
														暂无人回应
													</s:else>
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

</body>
</html>


