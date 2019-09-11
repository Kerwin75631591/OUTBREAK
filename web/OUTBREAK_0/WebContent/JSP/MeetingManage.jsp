<!-- author:��ҫ ���� -->

<%@ page language="java" contentType="text/html; 
charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.outbreak.util.*"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<title>�������</title>
<link type="text/css" rel="stylesheet"
	href="<%=path%>/CSS/MeetingManage.css">
<script>
	/*author:��ҫ
	���ܣ���ת�������������
	������void
	����ֵ��void*/
	function jumpToCreate(){
		var email=document.getElementById("email").value;
		window.open("../JSP/MeetingCreate.jsp?email="+email);
		closeWin();
	}
	/*
	author������
	���ܣ��ύ������������
	������void
	����ֵ��void
	*/
	function endMeeting(mid){
		if(confirm("�Ƿ���Ҫ�Ի�����Ч�Խ���������")){
			document.getElementById("isAssessment").value = "true";
		}else{
			document.getElementById("isAssessment").value = "false";
		}
		document.getElementById("mid").value = mid;
		document.hiddenForm.submit();
	}
</script>
</head>
<body>
	<%
		String sessionName = (String) request.getSession().getAttribute("sessionname");
		String sessionEmail = (String) request.getSession().getAttribute("sessionemail");
	%>

<div id="MngTitle">OUTBREAK �ƻ������ϵͳ</div>

	<div id="MngTopBox">
		<div id="MngWelcome">
			���ã�<span id="email"><%=request.getSession().getAttribute("sessionemail")%></span>��
		</div>
		<div id="MngJump">
			<span id="createMeeting"><input type="button"
				id="CreateMeetingBtn" value="�����»���" onclick="jumpToCreate()"></span>
			<span><input type="button" id="ManageLoginBtn" value="�˳���¼"
				onclick="window.location.href='Login.jsp';" /></span>
		</div>
	</div>

	<div id="ManageBox">
		<table>
			<tr>
				<th id="name" style="font-size: 20px; width: 300px; height:30px;">��������</th>
				<th id="date" style="font-size: 20px; width: 1400px">��������</th>
				<th id="loc" style="font-size: 20px; width: 300px">����ص�</th>
				<th id="content" style="font-size: 20px; width: 300px">��������</th>
				<th id="users" style="font-size: 20px; width: 350px">���������ȷ��</th>
				<th id="status" style="font-size: 20px; width: 300px">״̬</th>
				<th></th>
			</tr>
			<%
				MeetingBeanCL mbcl = new MeetingBeanCL();
				ResultSet meetings = mbcl.search(sessionEmail);
				if (meetings == null) {
			%>
			<tr>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
				<td style="font-size: 15px">null</td>
			</tr>
			<%
				} else {
					int counter = 1;
					while (meetings.next()) {
						int meetingid = meetings.getInt("id");
						String name = meetings.getString("name");
						String startDate = meetings.getString("begintime");
						String endDate = meetings.getString("endtime");
						String dateString = startDate + "---" + endDate;
						String loc = meetings.getString("place");
						String topic = meetings.getString("topic");
						String content = meetings.getString("content");
						int arr = meetings.getInt("ArrivalNum");
						int total = meetings.getInt("peopleNum");
						String numString = arr + "//" + total;
						int state = meetings.getInt("state");
						String stateString = null;
						boolean assessment = meetings.getBoolean("assessment");
						switch (state) {
						case 0:
							stateString = new String("δ�ύ");
							break;
						case 1:
							stateString = new String("δ���");
							break;
						case 2:
							stateString = new String("���ͨ��");
							break;
						case 3:
							stateString = new String("���ʧ��");
							break;
						case 4:
							stateString = new String("�������");
							break;
						}
			%>
			<tr>
				<td id=<%="name" + counter%> style="text-align:center;font-size: 18px;height:30px;"><%=name%></td>
				<td id=<%="date" + counter%> style="text-align:center;font-size: 18px;"><%=dateString%></td>
				<td id=<%="loc" + counter%> style="text-align:center;font-size: 18px;"><%=loc%></td>
				<td id=<%="content" + counter%> style="text-align:center;font-size: 18px;"><%=content%></td>

				<%
					String s = "InvitedPeople.jsp?meetingName=" + name + "&meetingId=" + meetingid;
							System.out.println("����"+s);
				%>
				<td id=<%="num" + counter%>  style="text-align:center;font-size: 18px;"><a href=<%=s%> title=
					"����鿴�������ߵ�״̬" target="_blank"><%=numString%></a></td>
				<td id=<%="state" + counter%> style="text-align:center;font-size: 18px;"><%=stateString%></td>
				<td><input type="button" 
				<%if (state == 3 || state == 0){%> 
				value="�޸�" onclick="window.location.href='<%=path %>/JSP/MeetingCreate.jsp?meetingid=<%=meetingid%>&meetingName=<%=name%>'"
				<%}else{%>
					<%if (state == 2){%> 
				 	 value="��������" onclick="endMeeting(<%=meetingid%>)"
				 	<%}else{
				 		if(assessment == true){%>
				 		value="�鿴���" onclick="window.location.href='<%=path %>/JSP/AssessmentResult.jsp?meetingid=<%=meetingid%>'"
				 		<%}else{%>
				 		style="display:none"
				 		<%}%>
				 	<%}%>
				 <%}%>
				 ></td>
			</tr>
			<%
				counter++;
					}
					mbcl.db.close();
				}
			%>
		</table>
	</div>
	<form name="hiddenForm" action="/OUTBREAK_0/EndMeetingServlet" style="display:none">
		<input type="text" id="mid" name="mid" style="display:none" value="">
	    <input type="text" id="isAssessment" name="isAssessment" style="display:none" value="">
	</form>
</body>
</html>
