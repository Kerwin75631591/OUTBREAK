<!-- author：胡昱 -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="com.outbreak.util.DBConnect" %>
<%@ page import="java.sql.ResultSet" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>会议有效性评估结果</title>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/AssessmentResult.css">
</head>
<body>
<%
	//获得各个信息
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
			<th>项目</th><th>得分</th>
		</tr>
	<%
		//遍历显示信息
		if(meeting == null){
			%>
		<tr><td>null</td><td>null</td></tr>
			<%
		}else{
			%>
			<tr><td class="item_in_table">会议时间安排（包括起止时间等）</td><td class="grade_in_table"><%=meeting.getString("timeGrade")%></td></tr>
			<tr><td class="item_in_table">会议环境（包括人文环境、自然环境等）</td><td class="grade_in_table"><%=meeting.getString("environmentGrade")%></td></tr>
			<tr><td class="item_in_table">会议气氛（包括与会者参与度等）</td><td class="grade_in_table"><%=meeting.getString("atmosphereGrade")%></td></tr>
			<tr><td class="item_in_table">会议内容（包括议题是否合理等）</td><td class="grade_in_table"><%=meeting.getString("contentGrade")%></td></tr>
			<tr><td class="item_in_table">会议结果（包括是否得出正确结果、决策等）</td><td class="grade_in_table"><%=meeting.getString("resultGrade")%></td></tr>
			<%
		}
	%>
	</table>
</body>
</html>