<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script>
	var CHECK;
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		var email=document.getElementById("em").value;
		var check=document.getElementById("chk").value;	
		if(email.length==0){
			alert("����������");
		}else{
			if(check.length==0){
				alert("��������֤��");
			}else{
				if(CHECK!=check){
					alert("��֤�����");
				}else{					
					if(pw==rpw){
						//alert("�ύ��");
						document.forms[0].submit();
					}else{
						alert("�����������벻һ�£�");
					}					
				}
			}
		}
	}
	function postEmail(){
		while(true){
			CHECK=Math.floor(Math.random()*1000000);
			if(CHECK>99999)
				break;
		}
		var email=document.getElementById("em").value;
		//document.getElementById("show_check").innerHTML=CHECK;
		document.getElementById("email_button").disabled=true;
		var e=encrypt();
		//alert(e);
		window.open("PwResetCheckCL.jsp?Email="+email+"&check="+e);
	}
	function encrypt(){
		//alert(typeof CHECK);
		var enc=CHECK;
		return enc;
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/PwReset.css">
<title>��������</title>
</head>

<body>
   <canvas ></canvas>
   <form id="resetForm" action="PwResetCL.jsp" method="POST">
   <div id="PsRTitle">OUTBREAK �ƻ������ϵͳ</div>
   <div id="ResetBox">
      <div>
		 <span><input type="email" id="em" style="border:1px solid #999999;" placeholder="����������" name="Email"></span>
		 <span id="email_poster"><input id="email_button" type="button" value="������֤��" onclick="postEmail()"></span>
      </div>
	  <div><input id="pw" type="password" style="border:1px solid #999999;" placeholder="����������" name="Password"></div>
	  <div><input id="rpw" type="password" style="border:1px solid #999999;" placeholder="���ٴ���������" name="RePassword"></div>
	  <div><input id="chk" type="text" style="border:1px solid #999999;" placeholder="��������֤��" name="Check"></div>
	  <a id="ResetBack" href="Login.jsp">����</a>
      <input id="ResetBtn" type="button" class="sendbutton" value="�� ��" onclick="subForm()">
   </div>
   </form>
</body>

</html>
