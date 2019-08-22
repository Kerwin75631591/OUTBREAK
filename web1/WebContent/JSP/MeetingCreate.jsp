<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>meetingCreate</title>
</head>
<body>
	<script type="text/javascript">
	function Release(){
		document.meetingManageForm.action = "releaseCL.jsp";
		document.meetingManageForm.submit();
 　　}
	function Save(){
        document.meetingManageForm.action = "saveCL.jsp";
        document.meetingManageForm.submit();
 　　}
	</script>
	
	<a href="Login.jsp">退出登陆</a>
	
	<form action="" name="meetingManageForm" method="post">
		<span id="release"><input type="button" id="release" value="发布" onclick="Release()"/></span>
	    <span id="save"><input type="button" id="save" value="保存草稿" onclick="Save()"/></span>
	    <span id="reset"><input type="reset" id="reset" value="重置"/></span>
	</form>

</body>
</html>