<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>会议创建页面</title>
	<link rel="stylesheet" href="../CSS/MeetingCreate.css" />
	<script type="text/javascript">
	function Release(){
		document.meetingManageForm.action = "ReleaseServlet";
		document.meetingManageForm.submit();
 　　}
	function Save(){
        document.meetingManageForm.action = "SaveServlet";
        document.meetingManageForm.submit();
 　　}
	function Add(){
        var Name = document.getElementById("Name").value;
        var Phone = document.getElementById("Phone").value;
        var Email = document.getElementById("Email").value;
		var trObj = document.createElement("tr");
		trObj.id = new Date().getTime();
		trObj.innerHTML = "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"+Name+"</td><td>"+Phone+"</td><td>"+Email+"</td><td><input type='button' value='删除' onclick='Delete(this)'></td>";
		document.getElementById("UserTable").appendChild(trObj);
 　　}
	function Delete(obj){
		var trId = obj.parentNode.parentNode.id;
		var trObj = document.getElementById(trId);
		document.getElementById("UserTable").removeChild(trObj);
	}
	</script>
</head>
<body>
	<canvas ></canvas>
		<div id="CreateToptitle">OUTBREAK 多客户端云会议系统</div>
		<div id="CreateWelcome">您好，<%=request.getSession().getAttribute("sessionname") %>！</div>
		<div id="CreateJump">
		   <a id="CreateMeetingManageL" href="MeetingManage.jsp">管理会议</a>
		   <span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span>
		   <a id="CreateLoginL" href="Login.jsp">退出登陆</a>
		</div>
		<form action="" name="meetingManageForm" method="post">
		<table id="CreateTable">
		   <td>
		      <table width="100%" border="0" cellspacing="5" style="margin-top:40px;margin-left:20px">
		         <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">会议名称：</td>
                    <td><input type="text" style="width:500px;height:30px;font-size:30px;border: 1px solid #E2E3E5;"></td>
                 </tr>
                 <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">会议主题：</td>
                    <td><input type="text" style="width:500px;height:30px;font-size:30px"></td>
                 </tr>
                 <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">日期：</td>
                    <td><input type="date" style="width:500px;height:30px;font-size:30px"></td>
                 </tr>
                 <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">会议地点：</td>
                    <td><input type="text" style="width:500px;height:30px;font-size:30px"></td>
                 </tr>
                 <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">会议内容：</td>
                    <td><textarea style="line-height: 30px;width:500px;height:400px;font-size:30px"></textarea></td>
                 </tr>
                 <tr>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">上传资料：</td>
                    <td><input type="file" style="width:500px;height:30px;font-size:20px"></td>
                 </tr> 
		      </table>
		   </td>
		   <td>
		      <table id="UserTable" style="margin-top:0px;">
		         <tr>
		            <td style="border: 1px solid #E2E3E5;font-size:20px;margin-left:-400px">与会人员：</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">人员姓名</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">联系方式</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">邮箱地址</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		         </tr>
		         </table>
		         
		         <table id="InputTable">
		         <tr>
		            <td style="border: 1px solid #E2E3E5;font-size:20px">输入信息：</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">人员姓名</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">联系方式</td>
                    <td style="border: 1px solid #E2E3E5;font-size:20px">邮箱地址</td>
		         </tr>
		         <tr>
		            <td style="border: 1px solid #E2E3E5;font-size:20px">&nbsp;</td>
		            <td><input type="text" id="Name" style="height:20px;font-size:20px"></td>
		            <td><input type="text" id="Phone" style="height:20px;font-size:20px"></td>
		            <td><input type="email" id="Email" style="height:20px;font-size:20px"></td>
		            <td><span><input type="button" id="CreateAdd" value="增  加" onclick="Add()"></span></td>
		         </tr>
		         </table>
		   </td>
		</table>
		<div id="CreateSubmitBox">
		</div>
		</form>

</body>
</html>