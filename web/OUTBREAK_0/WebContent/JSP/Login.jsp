<!-- author:������ -->

<%@ page language="java" contentType="text/html; 
charset=GBK" pageEncoding="GBK"%>

<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);

System.out.println(basePath);%>

<!DOCTYPE html>
<html>

	<head>
	   <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
		<meta charset="GBK">
		<title>��¼ҳ��</title>
		<script>
			/*author:������
			���ܣ��ύ���Ե�¼
			������void
			����ֵ��void
			*/
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
				<span><input type="text" id="VericodeInput" style="border:1px solid #999999;" placeholder="��������֤��" name="LoginVericode"></span>
				<span id="LoginVericodePic"></span>
				<input type="button" id="LoginInput" class="logbutton ripple" data-ripple-color="white" value="��  ¼"  onclick="checkL()">
				<view style="position:relative;top:7%;margin-left:25%"><text id="JumpText" style="color:#808080;width:15%">��û���˺ţ���</text><a id="RegistText" href="./JSP/Register.jsp">ע��</a></view>
				<view style="position:relative;top:15%;left:-13%"><text id="JumpToPwReset" style="color:#808080;width:25%;margin-left:-40%">�������룿<a href="PwReset.jsp" style="margin-left:5%">������</a></text></view>
			</div>
		</form>
	</body>
</html>       