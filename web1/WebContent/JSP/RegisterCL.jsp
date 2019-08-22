<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="javax.swing.JOptionPane" %>
<%@ page import="javax.script.ScriptEngine" %>
<%@ page import="javax.script.ScriptEngineManager" %>
<%@ page import="com.outbreak.entity.UserBean" %>
<%@ page import="com.outbreak.dao.UserBeanCL" %>
    
<!DOCTYPE html>
<html>

<head>

<style>
#content{
	margin-top:15%;
	text-align:center;
}
#result{
	font-size:2.0em;
}
</style>

</head>

<body>
<%
	String email=request.getParameter("Email");
	String pw=request.getParameter("Password");
	UserBean ub=new UserBean();
	Boolean success=false;
	if(email!=null&&pw!=null)
	{
		ub.setEmail(email);
		ub.setPassword(pw);
		UserBeanCL ubc=new UserBeanCL();
		System.out.println(ub.getEmail()+"\n"+ub.getPassword());
		success=ubc.Register(ub);
		if(success){
			response.sendRedirect("Login.jsp");
		}else{
			//response.sendRedirect("Register.jsp");
		}
	}
%>
<script type="text/javascript">
	//document.getElementById("result").innerHTML="µÇÂ¼Ê§°Ü";
	var c=confirm("×¢²áÊ§°Ü\nÊÇ·ñ·µ»Ø×¢²á½çÃæ","ÊÇ","·ñ");
	if(c!=0){
		window.history.back(-1);
	}
</script>
</body>

</html>