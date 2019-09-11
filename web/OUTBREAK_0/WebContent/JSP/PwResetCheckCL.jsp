<!-- author:Âí¿µÒ« -->

<%@ page language="java" contentType="text/html; charset=IGBK"
    pageEncoding="GBK"%>
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