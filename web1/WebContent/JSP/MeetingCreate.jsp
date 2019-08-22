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
	    <span id="save"><input type="submit" id="save" value="保存草稿" onclick="save()"></span>
	    <span id="reset"><input type="reset" id="reset" value="重置"></span>
	</form>
	<script>
	function release()
	{
          document.meetingManageForm.action = "releaseCL.jsp";
          document.meetingManageForm.submit();
 　　}
	function save()
	{
          document.meetingManageForm.action = "saveCL.jsp";
          document.meetingManageForm.submit();
 　　}
	</script>
</body>
</html>