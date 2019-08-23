<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.login.dao.UserBean"%>
<%@ page import="com.login.dao.UserBeanCL"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginCL.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	//接受用户名和密码
    	String uName=request.getParameter("LoginEmail");
    	String pWd=request.getParameter("LoginPassword");
    	
    	System.out.println(uName+"==="+pWd);
    	
    	UserBean ub = new UserBean();
    	ub.setName(uName);
    	ub.setPassword(pWd);
    	
    	UserBeanCL ubcl = new UserBeanCL();
    	
    	//response.sendRedirect("Register.jsp");
    	
    	if(ubcl.Login(ub)){
    		
    		ubcl.db.close();
    		response.sendRedirect("MeetingManage.jsp");
    	}else{
    		ubcl.db.close();
    		response.sendRedirect("Login.jsp");
    	}
    
     %>
  </body>
</html>
