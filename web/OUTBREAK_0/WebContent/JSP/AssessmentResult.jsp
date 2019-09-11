<!-- author������ -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.outbreak.util.DBConnect" %>
<%@ page import="java.sql.ResultSet" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>������Ч���������</title>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/AssessmentResult.css">
</head>
<body>
<%
	//��ø�����Ϣ
	String all=request.getQueryString();
	String query=java.net.URLDecoder.decode(all,"GBK");
	System.out.println(query);
	String[] qs=query.split("&");
	String meetingID[]=qs[1].split("=");
	String meetingNAME[]=qs[0].split("=");
	String meetingName=meetingNAME[1];
	int MID=Integer.parseInt(meetingID[1]);
	DBConnect db=new DBConnect();
	db.connect();
	ResultSet meeting=db.searchMeeting(MID);
%>
	<header><h1><%=meetingName %></h1></header>
	<table>
		<tr>
			<th>��Ŀ</th><th>�÷�</th>
		</tr>
	<%
		//������ʾ��Ϣ
		if(meeting == null){
			%>
		<tr><td>null</td><td>null</td></tr>
			<%
		}else{
			%>
			<tr><td class="item_in_table">����ʱ�䰲�ţ�������ֹʱ��ȣ�</td><td class="grade_in_table"><%=meeting.getString("timeGrade")%></td></tr>
			<tr><td class="item_in_table">���黷�����������Ļ�������Ȼ�����ȣ�</td><td class="grade_in_table"><%=meeting.getString("environmentGrade")%></td></tr>
			<tr><td class="item_in_table">�������գ���������߲���ȵȣ�</td><td class="grade_in_table"><%=meeting.getString("atmosphereGrade")%></td></tr>
			<tr><td class="item_in_table">�������ݣ����������Ƿ����ȣ�</td><td class="grade_in_table"><%=meeting.getString("contentGrade")%></td></tr>
			<tr><td class="item_in_table">�������������Ƿ�ó���ȷ��������ߵȣ�</td><td class="grade_in_table"><%=meeting.getString("resultGrade")%></td></tr>
			<%
		}
	%>
	</table>
</body>
</html>