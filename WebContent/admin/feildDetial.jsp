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

<link rel="stylesheet" type="text/css"
	href="admin/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="admin/lib/bootstrap/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css"
	href="admin/stylesheets/theme.css">
<link rel="stylesheet"
	href="admin/lib/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="admin/stylesheets/mystyle.css">
<script language="JavaScript" src="admin/lib/bootstrap/js/mydate.js"></script>
<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<script src="admin/lib/bootstrap/js/bootstrap.js"></script>

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
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">


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
				<h1 class="page-title">JAVA领域详情</h1>
				<hr>
					<div class="row-fluid">
		<div class="span12">
			<div class="carousel slide" id="carousel-704627">
				<div class="carousel-inner">
					<div class="item active">
						<img alt="" src="admin/images/1.jpg" />
						<div class="carousel-caption">
							<h4>
								JAVA
							</h4>
							<p>
								Java最初被称为Oak，是1991年为消费类电子产品的嵌入式芯片而设计的。
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="admin/images/2.jpg" />
						<div class="carousel-caption">
							<h4>
								Sun Microsystems
							</h4>
							<p>
								公司推出的Java面向对象程序设计语言（以下简称Java语言）和Java平台的总称。
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="admin/images/3.jpg" />
						<div class="carousel-caption">
							<h4>
								Java applet
							</h4>
							<p>
								跨平台、动态Web、Internet计算显示了Java的魅力。
							</p>
						</div>
					</div>
				</div> <a data-slide="prev" href="#carousel-704627" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-704627" class="right carousel-control">›</a>
			</div>
		</div>
	</div>
				<div class="user_info_title">
					<strong>领域简介</strong>
				</div>
				<br>
				<div class="row-fluid">
					<div class="span12">
						<p class="lead">
							<em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JAVA</em>是由<strong>Sun Microsystems</strong>公司推出的Java面向对象程序设计语言（以下简称Java语言）和Java平台的总称。由James Gosling和同事们共同研发
							，并在1995年正式推出。Java最初被称为Oak，是1991年为消费类电子产品的嵌入式芯片而设计的。1995年更名为Java，并重新设计用于开发Internet应用程序。用Java实现的HotJava浏览器（支持
							Java applet）显示了Java的魅力：跨平台、动态Web、Internet计算。从此，Java被广泛接受并推动了Web的迅速发展，常用的浏览器均支持Javaapplet。另一方面，Java技术也不断更新。Java
							自面世后就非常流行，发展迅速，对C++语言形成有力冲击。在全球云计算和移动互联网的产业环境下，Java更具备了显著优势和广阔前景。2010年Oracle公司收购Sun Microsystems。
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>


