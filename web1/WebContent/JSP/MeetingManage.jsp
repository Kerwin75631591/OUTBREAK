<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>�������</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/CSS/MeetingManage.css">
<script>
	function jumpToCreate(){
		var email=document.getElementById("email").value;
		window.open("MeetingCreate.jsp?email="+email);
	}
</script>
</head>
<body>
<%
	String sessionName=(String)request.getSession().getAttribute("sessionname");
	String sessionEmail=(String)request.getSession().getAttribute("sessionemail");
%>
	<header>
	<span id="title">OUTBREAK ��ͻ����ƻ���ϵͳ</span>
	<span id="createMeeting"><input type="button" value="�����»���" onclick="jumpToCreate()"></span>
	</header>
	<div id="back">
		���ã�<span id="email"><%=sessionEmail %></span>��&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="Login.jsp">�˳���½</a>
	</div>
		
	<table>
		<tr>
			<th id="name">��������</th>
			<th id="date">��������</th>
			<th id="loc">����ص�</th>
			<th id="content">��������</th>
			<th id="users">���������ȷ��</th>
			<th id="status">״̬</th><th></th>
		</tr>
	<% 
		MeetingBeanCL mbcl=new MeetingBeanCL();
		ResultSet meetings=mbcl.search(sessionEmail);
		if(meetings==null){
	%>
		<tr>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td>null</td>
			<td><input type="button" value="�޸�"></td>
		</tr>
	<%}else{ 
		int counter=1;
		while(meetings.next()){
			String name=meetings.getString(4);
			Date date=(Date)meetings.getObject(2);
			String loc=meetings.getString(3);
			String cont=meetings.getString(5);
			int arr=meetings.getInt(9);
			int total=meetings.getInt(8);
			String num=arr+"//"+total;
			int state=meetings.getInt(7);
	%>
		<tr>
			<td id=<%="name"+counter %>><%=name %></td>
			<td id=<%="date"+counter %>><%=date %></td>
			<td id=<%="loc"+counter %>><%=loc %></td>
			<td id=<%="content"+counter %>><%=cont %></td>
			<td id=<%="num"+counter %>><%=num %></td>
			<td id=<%="state"+counter %>><%=state %></td>
			<td><input type="button" value="�޸�" onclick="submit(<%=counter%>)"></td>
		</tr>
	<%
			counter++;
		}
	} 
	%>
	</table>
	
</body>
</html>