<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>

<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);

System.out.println(basePath);%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="GBK">
		<title>��¼ҳ��</title>
		<script>
			function checkL() {
				if(document.getElementById("VericodeInput").length == 0 || document.getElementById("VericodeInput").value != "7364"){
					alert("��֤�����");
					return;
				}
				var em = document.getElementById("IDInput").value;
				var pw = document.getElementById("PasswordInput").value;
				if(em.length == 0) {
					alert("�������˺ţ�");
				} else if(pw.length == 0) {
					alert("���������룡");
				} else {
					document.forms[0].submit();
				}
			}
			
		</script>
		<link rel="stylesheet" href="<%=path %>/CSS/Login.css" >
	</head>
	<body>
		<canvas ></canvas>
		<form action="<%=path %>/JSP/LoginCL.jsp" method="post">
        <div id="LoginSTitle">OUTBREAK</div>
        <div id="LoginXTitle">�ƻ������ϵͳ</div>
		<div id="LoginBox">
		   <span><input type="email" id="IDInput"  style="border:1px solid #999999;" placeholder="����������" name="LoginEmail"></span>
		   <span><input type="password" id="PasswordInput" style="border:1px solid #999999;" placeholder="����������" name="LoginPassword"></span>
			 <div>
				<span><input type="text" id="VericodeInput" style="border:1px solid #999999;" placeholder="��������֤��" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
			 </div>
			<input type="button" id="LoginInput" class="logbutton ripple" data-ripple-color="white" value="��  ¼"  onclick="checkL()">
			<div style="position:relative;top:7%;margin-left:25%"><text id="JumpText" style="color:#808080;width:15%">��û���˺ţ���</text><a id="RegistText" href="../JSP/Register.jsp">ע��</a></div>
			<div style="position:relative;top:15%;left:62%"><text id="JumpToPwReset" style="color:#808080;width:25%;margin-left:-40%">�������룿<a href="PwReset.jsp" style="margin-left:5%">������</a></text></div>
		</div>
		</form>
	</body>
</html>       