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

<script src="admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

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
				<script type="text/javascript" src="admin/lib/jqplot/jquery.jqplot.min.js"></script>
				<script type="text/javascript" charset="utf-8" src="admin/javascripts/graphDemo.js"></script>

				<div class="stats">
					<p class="stat">
						<span class="number">53</span>tickets
					</p>
					<p class="stat">
						<span class="number">27</span>tasks
					</p>
					<p class="stat">
						<span class="number">15</span>waiting
					</p>
				</div>
				<h1 class="page-title">Dashboard</h1>

				<div class="row-fluid">
					<div class="block">
						<p class="block-heading" data-toggle="collapse" data-target="#chart-container">Performance
							Chart</p>
						<div id="chart-container" class="block-body collapse in">
							<div id="line-chart"></div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse" data-target="#tablewidget">Users</div>
						<div id="tablewidget" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Username</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Mark</td>
										<td>Tompson</td>
										<td>the_mark7</td>
									</tr>
									<tr>
										<td>Ashley</td>
										<td>Jacobs</td>
										<td>ash11927</td>
									</tr>
									<tr>
										<td>Audrey</td>
										<td>Ann</td>
										<td>audann84</td>
									</tr>
									<tr>
										<td>John</td>
										<td>Robinson</td>
										<td>jr5527</td>
									</tr>
									<tr>
										<td>Aaron</td>
										<td>Butler</td>
										<td>aaron_butler</td>
									</tr>
									<tr>
										<td>Chris</td>
										<td>Albert</td>
										<td>cab79</td>
									</tr>
								</tbody>
							</table>
							<p>
								<a href="users.html">More...</a>
							</p>
						</div>
					</div>
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse" data-target="#widget1container">Collapsible</div>
						<div id="widget1container" class="block-body collapse in">
							<h2>Using Ruby?</h2>
							<p>
								This template was developed with
								<a href="http://middlemanapp.com/" target="_blank">Middleman</a>
								and includes .erb layouts and views.
							</p>
							<p>All of the views you see here (sign in, sign up, users, etc) are already split up so
								you don't have to waste your time doing it yourself!</p>
							<p>The layout.erb file includes the header, footer, and side navigation and all of the
								views are broken out into their own files.</p>
							<p>If you aren't using Ruby, there is also a set of plain HTML files for each page, just
								like you would expect.</p>
						</div>
					</div>
				</div>
				<div class="copyrights">
					Collect from
					<a href="http://www.cssmoban.com/" title="ç½ç«æ¨¡æ¿">ç½ç«æ¨¡æ¿</a>
				</div>
				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading" data-toggle="collapse" data-target="#widget2container">
							History<span class="label label-warning">+10</span>
						</div>
						<div id="widget2container" class="block-body collapse in">
							<table class="table">
								<tbody>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Mark Otto
											</p>
										</td>
										<td>
											<p>Amount: $1,247</p>
										</td>
										<td>
											<p>Date: 7/19/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Audrey Ann
											</p>
										</td>
										<td>
											<p>Amount: $2,793</p>
										</td>
										<td>
											<p>Date: 7/12/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Mark Tompson
											</p>
										</td>
										<td>
											<p>Amount: $2,349</p>
										</td>
										<td>
											<p>Date: 3/10/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p>
												<i class="icon-user"></i> Ashley Jacobs
											</p>
										</td>
										<td>
											<p>Amount: $1,192</p>
										</td>
										<td>
											<p>Date: 1/19/2012</p> <a href="#">View Transaction</a>
										</td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
					<div class="block span6">
						<p class="block-heading">Not Collapsible</p>
						<div class="block-body">
							<h2>Tip of the Day</h2>
							<p>Fava bean jÃ­cama seakale beetroot courgette shallot amaranth pea garbanzo carrot
								radicchio peanut leek pea sprouts arugula brussels sprout green bean. Spring onion broccoli
								chicory shallot winter purslane pumpkin gumbo cabbage squash beet greens lettuce celery.
								Gram zucchini swiss chard mustard burdock radish brussels sprout groundnut. Asparagus
								horseradish beet greens broccoli brussels sprout bitterleaf groundnut cress sweet pepper
								leek bok choy shallot celtuce scallion chickpea radish pea sprouts.</p>
							<p>
								<a class="btn btn-primary btn-large">Learn more &raquo;</a>
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>


	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="admin/lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>


