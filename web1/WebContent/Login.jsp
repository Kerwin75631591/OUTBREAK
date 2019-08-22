<%@ page language="java" contentType="text/html; 
charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>登录页面</title>
	</head>
	<body>
	    <link rel="stylesheet" href="mycss/Login.css" />
		<canvas ></canvas>
		<div id="LoginToptitle">OUTBREAK 多客户端云会议系统</div>
		<form action="loginCl.jsp" method="post">
		<div id="LoginBox">
			<div id="LoginIDInput">
				<span id="LoginIDLabel">帐号（邮箱）：</span>
				<span id="LoginIDEnter"><input type="email" id="IDInput" name="LoginEmail"></span>
            </div>
			<div id="LoginPasswordInput">
				<span id="LoginPasswordLabel">密码：</span>
				<span id="LoginPasswordEnter"><input type="password" id="PasswordInput" name="LoginPassword"></span>
			</div>
			<div id="LoginVericodeInput">
				<span id="LoginVericodeLabel">验证码：</span>
				<span id="LoginVericodeEnter"><input type="text" id="VericodeInput" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
			</div>
			<span id="RegistWant"><input type="submit" id="RegistInput" value="注册"></span>
			<span id="LoginBtn"><input type="submit" id="LoginInput" value="登录"></span>
		</div>
		</form>
	</body>
</html>


           