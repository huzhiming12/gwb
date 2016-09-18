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
<title>顾问注册</title>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="admin/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="admin/stylesheets/theme.css">
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

#my-form {
	width: 85%;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 1em;
	border-radius: 3px;
	box-shadow: 0 0 1px rgba(0, 0, 0, .2);
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
	<br>
	<form id="my-form" method="post" action="Consultant_registerResult">
		<div style="width: 100%; text-align: center">
			<h3>顾问注册</h3>
			<table style="margin: auto">

				<tr>
					<td style="width: 25%;" height="34" align="center">
						<label class="control-label" for="inputPassword">真实姓名:</label>
					</td>
					<td colspan="2" align="center">
						<input name="consultant.name" style="width: 90%;" id="name" type="text" placeholder="请输入姓名" />
					</td>
				</tr>
				<tr id="nameWarning" style="display: none;">
					<td colspan="2" align="center" style="color: red">真实姓名不能为空</td>
				</tr>



				<tr>
					<td style="width: 25%;" height="34" align="center">
						<label class="control-label" for="inputPassword">企业职务:</label>
					</td>
					<td colspan="2" align="center">
						<input name="consultant.position" style="width: 90%;" id="position" type="text"
							placeholder="请输入所在企业职务" />
					</td>
				</tr>
				<tr id="positionWarning" style="display: none;">
					<td colspan="2" align="center" style="color: red">企业职务不能为空</td>
				</tr>


				<tr>
					<td style="width: 25%;" height="34" align="center">
						<label class="control-label" for="inputPassword">所在城市:</label>
					</td>
					<td colspan="2" align="center">
						<select name="consultant.city.id" style="width: 90%;" id="citySelector">
							<s:iterator value="cityBeans" var="c">
								<option value="<s:property value="#c.id"/>"><s:property value="#c.name" /></option>
							</s:iterator>
						</select>
					</td>
				</tr>


				<tr>
					<td style="width: 25%;" height="34" align="center">
						<label class="control-label" for="inputPassword">擅长领域:</label>
					</td>
					<td colspan="2" align="center">
						<select name="field" style="width: 90%;" id="fieldSelector" multiple="multiple" size="5">
							<s:iterator value="fieldBeans" var="f">
								<option value="<s:property value="#f.id"/>"><s:property value="#f.title" /></option>
							</s:iterator>
						</select>
					</td>
				<tr id="fieldWarning" style="display: none;">
					<td colspan="2" align="center" style="color: red">只能选择1-5擅长领域</td>
				</tr>


				<tr>
					<td colspan="4">
						<hr>
					</td>
				</tr>
				<tr>
					<td style="width: 25%;" height="34" align="center">
						<label class="control-label" for="inputPassword">手&nbsp;机&nbsp;号:</label>
					</td>
					<td colspan="2" align="center">
						<input style="width: 90%;" id="phone" type="text" placeholder="请输入手机号" />
						<input type="hidden" name="consultant.mobilePhone" id="realPhone">
					</td>
				</tr>
				<tr id="phoneWarning" style="display: none;">
					<td id="phoneText" colspan="2" align="center" style="color: red">手机号码已注册</td>
				</tr>

				<tr>
					<td height="34" align="center">
						<label class="control-label" for="inputPassword">验&nbsp;证&nbsp;码:</label>
					</td>
					<td align="center">
						<input style="width: 46%;" id="code" type="text" id="text" placeholder="请输入验证码" />
						<button id="btn_code" type="button" style="margin-bottom: 10px"
							class="btn btn-warning btn-small">获取验证码</button>
					</td>
				</tr>
				<tr id="codeWarning" style="display: none;">
					<td colspan="2" id="codeText" align="center" style="color: red">验证码不能为空</td>
				</tr>


				<tr>
					<td height="34" align="center">
						<label class="control-label" for="inputPassword">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
					</td>
					<td colspan="2" align="center">
						<input name="consultant.password" style="width: 90%;" id="password" type="password"
							placeholder="请输入密码" />
					</td>
				</tr>
				<tr id="passwordWarning" style="display: none;">
					<td colspan="2" align="center" style="color: red">密码不能为空且不少于6位数</td>
				</tr>


				<tr>
					<td height="37" align="center">
						<label class="control-label" for="inputPassword">确认密码:</label>
					</td>
					<td colspan="2" align="center">
						<input style="width: 90%;" id="repeat" type="password" placeholder="请确认密码" />
					</td>
				</tr>
				<tr id="repeatWarning" style="display: none;">
					<td colspan="2" align="center" style="color: red">密码前后不一致</td>
				</tr>

				<tr>
					<td></td>
					<td height="37" colspan="2" align="center">
						<button id="registerBtn" class="btn btn-success btn-block" type="button">注&nbsp;&nbsp;册</button>
					</td>
				</tr>
			</table>
		</div>
	</form>

	<br>
	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
		var code;
		$(document).ready(function() {

			//点击获取验证码
			$("#btn_code").click(function() {
				var phone = $("#phone").val();
				if (phone == "") {
					$("#phoneText").text("手机号码不能为空");
					$("#phoneWarning").show();
					return;
				} else if (!reg.test(phone)) {
					$("#phoneText").text("手机号码格式不正确");
					$("#phoneWarning").show();
					return;
				} else
					$("#phoneWarning").hide();
				isPhoneUsed();
			});

			//检验手机号码是否使用
			function isPhoneUsed() {
				$.ajax({
					type : "post",
					url : "Consultant_phoneCheck",
					dataType : "json",
					data : {
						"consultant.mobilePhone" : $("#phone").val(),
					},
					success : function(result) {
						res = result.res;
						if (res) {
							$("#phoneText").text("手机号码已经使用");
							$("#phoneWarning").show();
							return;
						} else
							getCode();
					}
				});
			}

			//获取验证码
			function getCode() {
				$("#btn_code").attr('disabled', "true");
				var time = 60;
				var change = setInterval(changeText, 1000);
				function changeText() {
					$("#btn_code").text("重新获取(" + time + ")");
					if (time == 0) {
						clearInterval(change);
						$("#btn_code").text("获取验证码")
						$('#btn_code').removeAttr("disabled"); //移除disabled属性 
					}
					time--;
				}
				//请求验证码
				$.ajax({
					type : "post",
					url : "Tool_getVerificationCode",
					dataType : "json",
					data : {
						mobilePhone : $("#phone").val(),
					},
					success : function(result) {
						code = result.res;
						$("#phone").attr("disabled", "disabled");
						$("#realPhone").val($("#phone").val());
						//alert(code);
					}
				});
			}

			$("#registerBtn").click(function() {
				var flag = true;
				//姓名检验
				if ($("#name").val() == "") {
					$("#nameWarning").show();
					flag = false;
				} else
					$("#nameWarning").hide();

				//职位检验
				if ($("#position").val() == "") {
					$("#positionWarning").show();
					flag = false;
				} else
					$("#positionWarning").hide();

				//领域检验
				var size = $("#fieldSelector option:selected").size();
				if (size > 5 || size < 1) {
					$("#fieldWarning").show();
					flag = false;
				} else
					$("#fieldWarning").hide();

				//验证码检验
				if ($("#code").val() == "") {
					$("#codeText").text("验证码不能为空");
					$("#codeWarning").show();
					flag = false;
				} else if ($("#code").val() != code) {
					$("#codeText").text("验证码输入错误");
					$("#codeWarning").show();
					flag = false;
				} else
					$("#codeWarning").hide();

				//密码检验
				var password = $("#password").val();
				if (password == "" || password.length < 6) {
					$("#passwordWarning").show();
					flag = false;
				} else
					$("#passwordWarning").hide();

				//密码确认检验
				if ($("#password").val() != $("#repeat").val()) {
					$("#repeatWarning").show();
					flag = false;
				} else
					$("#repeatWarning").hide();

				//检验通过 发送请求
				if (flag) {
					$("#my-form").submit();
				}

			});

		});
	</script>



</body>
</html>


