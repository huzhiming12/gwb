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

	<%@ include  file="head.jsp"%>

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
				<h1 class="page-title">详细信息</h1>
				<hr>

				<div class="well">
					<table width="100%">
						<tr>
							<td>
								<div>
									<ul class="gallery">
										<li>
											<s:if test="userBean.icon==''">
												<a href="admin/images/user.png">
													<img src="admin/images/user.png" class="img-polaroid user_icon" />
												</a>
											</s:if>
											<s:else>
											<a href="../gwb_res/<s:property value="userBean.icon" />">
													<img src="../gwb_res/<s:property value="userBean.icon" />" class="img-polaroid user_icon" />
												</a>
											</s:else>
										</li>
									</ul>
								</div>
							</td>
							<td style="padding-left: 50px;">
								<table>
									<tr class="user_detail_line">
										<td>用户名/手机号：</td>
										<td class="user_info_width">
											<s:property value="userBean.mobilePhone" />
										</td>
										<td>姓名：</td>
										<td class="user_info_width">
											<s:property value="userBean.name" />
										</td>
									</tr>
									<tr class="user_detail_line">
										<td>联系电话：</td>
										<td class="user_info_width">
											<s:property value="userBean.phone" />
										</td>
										<td>邮箱：</td>
										<td class="user_info_width">
											<s:property value="userBean.e_mail" />
										</td>
									</tr>
									<tr class="user_detail_line">
										<td>职务：</td>
										<td class="user_info_width">
											<s:property value="userBean.position" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="user_info_title">个人简介</div>
								<div class="user_info_introduce">
									<s:if test="!userBean.introduction">
										暂无个人简介
									</s:if>
									<s:else>
										<s:property value="userBean.introduction" />
									</s:else>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="user_info_title">名片</div>
								<div class="user_info_introduce">
									<s:if test="userBean.cards.size">
										<ul class="gallery">

											<s:iterator value="userBean.cards" id="c">
												<li>
													<a href='../gwb_res/<s:property value="#c.url"/>'>
														<!-- 弹出图片 -->
														<img src="../gwb_res/<s:property value="#c.url"/>" class="img-polaroid user_card" />
													</a>
												</li>
											</s:iterator>
										</ul>
									</s:if>
									<s:else>
										<span>暂无名片</span>
									</s:else>
								</div>
							</td>
						</tr>

					</table>
				</div>
			</div>
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
			<button class="btn btn-danger" data-dismiss="modal">删除</button>
		</div>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>
	<script src="admin/javascripts/zoom.min.js"></script>

</body>
</html>


