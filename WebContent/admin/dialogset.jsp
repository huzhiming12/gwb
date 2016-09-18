<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<script type="text/javascript">
	function disp_prompt()
	{
		var name = prompt("请输入您的名字", "Bill Gates")
		if (name != null && name != "")
		{
			document.write("你好！" + name + " 今天过得怎么样？")
		}
	}
</script>
</head>
<body>
	<input type="button" onclick="disp_prompt()" value="显示提示框" />
</body>

</html>
