<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script type="text/javascript" src="myjs/postEmail.js"></script>
<script>
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		if(pw==rpw){
			document.forms[0].submit();
		}else{
			alert("两次密码输入不一致！");
		}
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/Register.css">
<title>会议管理系统：注册</title>
</head>

<body>
<div id="content">
	<h1>OUTBREAK 多客户端云会议系统注册界面</h1>
	<div id="register_form">
		<form id="regForm" action="RegisterCL.jsp" method="POST">
			<div class="table">
				<div class="table_row">
					<div class="table_cell right_align">帐号（邮箱）：</div>
					<div class="table_cell"><input type="email" name="Email"></div>
					<div id="email_poster" class="table_cell"><input type="button" value="发送验证码" onclick="postEmail()"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">验证码：</div>
					<div class="table_cell"><input type="text" name="Check"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">密码：</div>
					<div class="table_cell"><input id="pw" type="password" name="Password"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">重复密码：</div>
					<div class="table_cell"><input id="rpw" type="password" name="RePassword"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align"><a href="Login.jsp">返回</a></div>
					<div class="table_cell center_align"><input type="button" value="注册" onclick="subForm()"></div>
				</div>
			</div>
		</form>
	</div>
</div>
</body>

</html>