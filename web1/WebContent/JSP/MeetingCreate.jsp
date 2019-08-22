<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="meetingManageForm" method="post">
		<span id="release"><input type="submit" id="release" value="发布" onclick="release()"></span>
	
	</form>
	<script>
	function release()
	{
      　　document.meetingManageForm.action = "";
 　　}
	</script>
</body>
</html>