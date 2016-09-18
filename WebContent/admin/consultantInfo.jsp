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
											<s:if test="!consultantBean.icon">
												<a href="admin/images/user.png">
													<img src="admin/images/user.png" class=" user_icon img-polaroid" />
												</a>
											</s:if>
											<s:else>
												<a href="../gwb_res/<s:property value="consultantBean.icon" />">
													<img src="../gwb_res/<s:property value="consultantBean.icon" />" class="user_icon img-polaroid" />
												</a>
											</s:else>
										</li>
									</ul>
								</div>
								<!-- <div class="user_info_rank">头衔：二级顾问</div> -->
							</td>
							<td style="padding-left: 50px;">
								<table>
									<tr class="user_detail_line">
										<td>用户名/手机号：</td>
										<td class="user_info_width">
											<s:property value="consultantBean.mobilePhone" />
										</td>
										<td>姓名：</td>
										<td class="user_info_width">
											<s:property value="consultantBean.name" />
										</td>
									</tr>
									<tr class="user_detail_line">
										<td>联系电话：</td>
										<td class="user_info_width">
											<s:property value="consultantBean.phone" />
										</td>
										<td>邮箱：</td>
										<td class="user_info_width">
											<s:property value="consultantBean.e_mail" />
										</td>
									</tr>
									<tr class="user_detail_line">
										<td>所在城市：</td>
										<td class="user_info_width">
											<s:property value="consultantBean.city" />
										</td>
										<td>擅长领域：</td>
										<td class="user_info_width">
											<s:iterator value="consultantBean.fields" id="f">
												<label class="label">
													<s:property value="#f.field" />
												</label>
											</s:iterator>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="user_info_title">个人简介</div>
								<div class="user_info_introduce">
									<s:property value="consultantBean.introduction" />
								</div>
							</td>
						</tr>

						<!-- <tr>
							<td colspan="2">
								<div class="user_info_title">工作经历</div>
								<div class="user_info_introduce">
									<p>第一桶金
										1998年，实用软件概念不仅培养了马化腾敏锐的软件市场感觉，也使他从中盈利不菲。马化腾是风靡一时的股霸卡的作者之一，他和朋友合作开发的股霸卡在赛格电子市场一直卖得不错。马化腾还不断为朋友的公司解决软件问题。这使他不仅在圈内小有名气，而且也有了相当的原始积累。
										但他真正意义上的第一桶金是来自股市[11] 。他最精彩的一单是将10万元炒到70万元。这为马化腾独立创业打下了基础[12] 。 创立腾讯
										1998年马化腾与他的同学张志东“合资”注册了深圳腾讯计算机系统有限公司。之后又吸纳了三位股东：曾李青、许晨晔、陈一丹。作为一家没有风险资金介入就成立的软件公司，初期的每一笔支出都让马化腾和他的同伴心惊。
										马化腾父亲马陈术等人 马化腾父亲马陈术等人 在决定做OICQ的时候，当时国内已经有了两家公司先做，产品比腾讯更有市场名气[13]
										。马化腾没有想得更多，除了因为这个产品可以和公司的主项发展业务移动局、寻呼台、无线寻呼方案和项目相互促进外，也因为当时飞华、中华网等许多公司有意向做即时通讯项目，市场显得很有发展前景。[14]
									</p>
								</div>
							</td>
						</tr> -->
						<tr>
							<td colspan="2">
								<div class="user_info_title">名片</div>
								<div class="user_info_introduce">
									<s:if test="consultantBean.cards.size">
										<ul class="gallery">
											<s:iterator value="consultantBean.cards" var="c">
												<li>
													<a href="../gwb_res/<s:property value='#c.url'/>">
														<!-- 弹出图片 -->
														<img src='../gwb_res/<s:property value="#c.url"/>' class="img-polaroid user_card" />
													</a>
												</li>
											</s:iterator>
										</ul>
									</s:if>
									<s:else>
									暂无名片
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

	<s:debug></s:debug>
</body>
</html>


