<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script type="text/javascript" src="myjs/postEmail.js"></script>
<script>
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		if(pw==rpw){
			document.forms[0].submit();
		}else{
			alert("�����������벻һ�£�");
		}
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/Register.css">
<title>�������ϵͳ��ע��</title>
</head>

<body>
<div id="content">
	<h1>OUTBREAK ��ͻ����ƻ���ϵͳע�����</h1>
	<div id="register_form">
		<form id="regForm" action="RegisterCL.jsp" method="POST">
			<div class="table">
				<div class="table_row">
					<div class="table_cell right_align">�ʺţ����䣩��</div>
					<div class="table_cell"><input type="email" name="Email"></div>
					<div id="email_poster" class="table_cell"><input type="button" value="������֤��" onclick="postEmail()"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">��֤�룺</div>
					<div class="table_cell"><input type="text" name="Check"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">���룺</div>
					<div class="table_cell"><input id="pw" type="password" name="Password"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align">�ظ����룺</div>
					<div class="table_cell"><input id="rpw" type="password" name="RePassword"></div>
				</div>
				<div class="table_row">
					<div class="table_cell right_align"><a href="Login.jsp">����</a></div>
					<div class="table_cell center_align"><input type="button" value="ע��" onclick="subForm()"></div>
				</div>
			</div>
		</form>
	</div>
</div>
</body>

</html>