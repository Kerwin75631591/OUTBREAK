<%@ page language="java" contentType="text/html; 
charset=UTF-8" pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%
	String path = request.getContextPath();
	System.out.print(path);
%>
=======
<% String path = request.getContextPath();  %>

>>>>>>> Jialei
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>登录页面</title>
<<<<<<< HEAD
		<link rel="stylesheet" href="<%=path%>/CSS/Login.css" >
=======
		<link rel="stylesheet" href="<%= path %>/CSS/Login.css" >
>>>>>>> Jialei
	</head>
	<body>
		<canvas ></canvas>
		<div id="LoginToptitle">OUTBREAK 多客户端云会议系统</div>
		<form action="LoginCL.jsp" method="post">
		<div id="LoginBox">
			<div>
				<span id="LoginIDLabel">帐号：</span>
				<span><input type="email" id="IDInput" name="LoginEmail"></span>
            </div>
			<div>
				<span id="LoginPasswordLabel">密码：</span>
				<span><input type="password" id="PasswordInput" name="LoginPassword"></span>
			</div>
			<div>
				<span id="LoginVericodeLabel">验证码：</span>
				<span><input type="text" id="VericodeInput" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
			</div>
<<<<<<< HEAD
			<span><input type="button" id="RegistWant" value="注册" onclick="window.location.href='<%=path%>/JSP/Register.jsp'"/></span>
=======
			<span><input type="button" id="RegistWant" value="注册" onclick="window.location.href='<%= path %>/JSP/Register.jsp';"/></span>
>>>>>>> Jialei
			<span><input type="submit" id="LoginInput" value="登录"></span>
		</div>
		</form>
	</body>
</html>       