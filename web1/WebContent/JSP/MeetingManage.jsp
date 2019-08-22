<%@ page language="java" contentType="text/html; 
charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
	function Release(){
		document.meetingManageForm.action = "releaseCL.jsp";
		document.meetingManageForm.submit();
 　　}
	</script>
	
	您好，<%=request.getSession().getAttribute("sessionname") %>！
	<a href="MeetingCreate.jsp">发布新的会议</a>
	<a href="Login.jsp">退出登陆</a>
	
	<br><hr>
	
</body>
</html>