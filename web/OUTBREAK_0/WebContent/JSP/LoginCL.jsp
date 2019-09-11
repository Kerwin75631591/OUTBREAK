<!-- author:王明钊 -->

<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.outbreak.entity.UserBean"%>
<%@ page import="com.outbreak.dao.UserBeanCL"%>
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
    	ub.setEmail(uName);
    	ub.setPassword(pWd);
    	
    	UserBeanCL ubcl = new UserBeanCL();
    	
    	if(ubcl.Login(ub)){
    		ubcl.db.close();
    		request.getSession().setAttribute("sessionemail",ub.getEmail()); //保存邮箱
    		request.getSession().setAttribute("sessionname",ub.getName()); //保存用户名
    		request.getSession().setAttribute("sessionpwd",ub.getPassword()); //保存密码
    		response.sendRedirect(path+"/JSP/"+"MeetingManage.jsp");
    	}else{
    		ubcl.db.close();
    	}
    	
    	
    
     %>
    <script type="text/javascript">
		//document.getElementById("result").innerHTML="登录失败";
		var c=confirm("登录失败：账号不存在或者密码不匹配\n是否返回登录界面","是","否");
		if(c!=0){
			window.history.back(-1);
		}
	</script>
     
     
  </body>
  
</html>
