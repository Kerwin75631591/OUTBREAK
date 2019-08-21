<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.login.dao.UserBean" %>
<%@ page import="com.login.dao.UserBeanCL" %>
    
<!DOCTYPE html>
<html>

<head>
</head>

<body>
<%
	String email=request.getParameter("Email");
	String check=request.getParameter("Check");
	String pw=request.getParameter("Password");
	String rpw=request.getParameter("RePassword");
	UserBean ub=new UserBean();
	ub.setEmail(email);
	ub.setPassword(pw);
	UserBeanCL ubc=new UserBeanCL();
	if(ubc.Register(ub)){
		response.sendRedirect("Login.jsp");
	}else{
		response.sendRedirect("Register.jsp");
	}
%>

</body>

</html>