<!-- author:��ҫ ������ -->

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
<title>��������</title>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/InvitedPeople.css">
</head>
<body>
<%
	//�ӷ�������ø�����Ϣ
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
	//System.out.println("����"+meetingName);
	DBConnect db=new DBConnect();
	db.connect();
	ResultSet inviteds=db.searchPeople(MID);
%>
<header><h1><%=meetingName %></h1></header>
<table>
	<tr>
		<th>����</th><th>״̬</th>
	</tr>
<%
	//������ʾ��������
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
				statusString="δȷ���μ�";
			}else{
				statusString="ȷ���μ�";
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