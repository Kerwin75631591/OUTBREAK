<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<link type="text/css" rel="stylesheet" href="mycss/Register.css">
<title>会议管理系统：注册</title>
</head>

<body>
	<div id="content">
		<h1>OUTBREAK 多客户端云会议系统</h1>
		<div id="register_form">
			<form id="regForm" action="RegisterCL.jsp" method="POST">
				<div class="table">
					<div class="table_row">
						<div class="table_cell right_align">帐号（邮箱）：</div>
						<div class="table_cell">
							<input type="email" name="Email">
						</div>
						<div class="table_cell">
							<input type="button" value="发送验证码">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">验证码：</div>
						<div class="table_cell">
							<input type="text" name="Check">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">密码：</div>
						<div class="table_cell">
							<input type="password" name="Password">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">重复密码：</div>
						<div class="table_cell">
							<input type="password" name="RePassword">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell"></div>
						<div class="table_cell center_align">
							<input type="submit" value="注册">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>