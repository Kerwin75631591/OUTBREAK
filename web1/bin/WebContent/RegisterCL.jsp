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
	String pw=request.getParameter("Password");
	UserBean ub=new UserBean();
	if(email!=null&&pw!=null)
	{
		ub.setEmail(email);
		ub.setPassword(pw);
		UserBeanCL ubc=new UserBeanCL();
		System.out.println(ub.getEmail()+"\n"+ub.getPassword());
		if(ubc.Register(ub)){
			response.sendRedirect("Login.jsp");
		}else{
			response.sendRedirect("Register.jsp");
		}
	}
%>

</body>

</html>