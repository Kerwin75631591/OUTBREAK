<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.outbreak.dao.EmailPosterCL" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String email=request.getParameter("Email");
	String check=request.getParameter("check");
	EmailPosterCL.sendPwResetCheck(email, Integer.parseInt(check));
%>
</body>
</html>