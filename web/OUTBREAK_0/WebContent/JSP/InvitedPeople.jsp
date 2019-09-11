<!-- author:马康耀 王明钊 -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.UserBeanCL" %>
<%@ page import="com.outbreak.util.DBConnect" %>
<%@ page import="java.sql.ResultSet" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>受邀名单</title>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/InvitedPeople.css">
</head>
<body>
<%
	//从服务器获得各个信息
	String all=request.getQueryString();
	String query=java.net.URLDecoder.decode(all,"GBK");
	System.out.println(query);
	String[] qs=query.split("&");
	String meetingID[]=qs[1].split("=");
	String meetingNAME[]=qs[0].split("=");
	String meetingName=meetingNAME[1];
	int MID=Integer.parseInt(meetingID[1]);
	/*
	String meetingid=request.getParameter("meetingId");
	int MID=Integer.parseInt(meetingid);
	String meetingName=request.getParameter("meetingName");
	*/
	//System.out.println("接收"+meetingName);
	DBConnect db=new DBConnect();
	db.connect();
	ResultSet inviteds=db.searchPeople(MID);
%>
<header><h1><%=meetingName %></h1></header>
<table>
	<tr>
		<th>姓名</th><th>状态</th>
	</tr>
<%
	//遍历显示受邀名单
	if(inviteds==null){
		%>
	<tr><td>null</td><td>null</td></tr>
		<%
	}else{
		while(inviteds.next()){
			String uid=inviteds.getString("uid");
			int status=inviteds.getInt("TOF");
			String statusString;
			if(status==0){
				statusString="未确定参加";
			}else{
				statusString="确定参加";
			}
			%>
	<tr><td class="name_in_table"><%=uid %></td><td class="status_in_table"><%=statusString %></td></tr>
			<%
		}
	}
%>
</table>
</body>
</html>