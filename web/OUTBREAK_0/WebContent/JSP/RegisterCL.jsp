<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="javax.swing.JOptionPane" %>
<%@ page import="javax.script.ScriptEngine" %>
<%@ page import="javax.script.ScriptEngineManager" %>
<%@ page import="com.outbreak.entity.UserBean" %>
<%@ page import="com.outbreak.dao.UserBeanCL" %>
<% String path = request.getContextPath(); %>
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
	request.setCharacterEncoding("GBK");
	response.setContentType("text/html;charset=GBK");
	String email=request.getParameter("Email");
	String pw=request.getParameter("Password");
	String name=request.getParameter("Name");
	UserBean ub=new UserBean();
	Boolean success=false;
	System.out.println(email+"\n"+name+"\n"+pw);
	if(email!=null&&pw!=null)
	{
		ub.setEmail(email);
		ub.setPassword(pw);
		ub.setName(name);
		UserBeanCL ubc=new UserBeanCL();
		
		System.out.println(ub.getEmail()+"\n"+ub.getPassword());
		success=ubc.Register(ub);
		if(success){
			ubc.db.close();
			request.getSession().setAttribute("sessionemail",ub.getEmail()); //��������
    		request.getSession().setAttribute("sessionname",ub.getName()); //�����û���
    		request.getSession().setAttribute("sessionpwd",ub.getPassword()); //��������
			response.sendRedirect(path+"/JSP/"+"MeetingManage.jsp");
		}else{
			//response.sendRedirect("Register.jsp");
		}
	}
%>
<script type="text/javascript">
	//document.getElementById("result").innerHTML="��¼ʧ��";
	alert("ע��ʧ�ܣ���������ע��\n");
	window.history.back(-1);
</script>
</body>

</html>