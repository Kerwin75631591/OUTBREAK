<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.MeetingBeanCL" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>会议管理</title>
<link type="text/css" rel="stylesheet" href="<%=path %>/CSS/MeetingManage.css">
</head>
<body>
<%
	String sessionName=(String)request.getSession().getAttribute("sessionname");
	String sessionEmail=(String)request.getSession().getAttribute("sessionemail");
%>
	<header>
	<span id="title">OUTBREAK 多客户端云会议系统</span>
	<span id="wel">您好，<%=sessionName %>！
	<a href="MeetingCreate.jsp">发布新的会议</a></span>
	</header>
	<div id="back">
		<a href="Login.jsp">退出登陆</a>
	</div>
		
	<table>
		<tr>
			<th id="name">会议名称</th>
			<th id="date">会议日期</th>
			<th id="loc">会议地点</th>
			<th id="content">会议内容</th>
			<th id="users">与会者名单确认</th>
			<th id="status">状态</th><th></th>
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
			<td><input type="button" value="修改"></td>
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
			<td><input type="button" value="修改" onclick="submit(<%=counter%>)"></td>
		</tr>
	<%
			counter++;
		}
	} 
	%>
	</table>
	
</body>
</html>