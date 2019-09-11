<!-- author:Âí¿µÒ« -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.outbreak.util.DBConnect"%>
<%@ page import="com.outbreak.dao.UserBeanCL"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("GBK");
	response.setContentType("text/html;charset=GBK");
	String Email=request.getParameter("Email");
	String pw=request.getParameter("Password");
	UserBeanCL ubcl=new UserBeanCL();
	try{
		ubcl.db.updateUserpassword(Email,pw);
	}catch(SQLException e){
		e.printStackTrace();
	}
%>
</body>
</html>