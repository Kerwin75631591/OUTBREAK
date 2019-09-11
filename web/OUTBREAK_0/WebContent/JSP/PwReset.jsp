<!-- author:马康耀 王明钊 -->

<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<% String path = request.getContextPath();  %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<script>
	var CHECK;
	/*
	author:马康耀
	功能：提交表单以重置密码
	参数：void
	返回值：void
	*/
	function subForm(){
		var pw=document.getElementById("pw").value;
		var rpw=document.getElementById("rpw").value;
		var email=document.getElementById("em").value;
		var check=document.getElementById("chk").value;	
		if(email.length==0){
			alert("请输入邮箱");
		}else{
			if(check.length==0){
				alert("请输入验证码");
			}else{
				if(CHECK!=check){
					alert("验证码错误！");
				}else{					
					if(pw==rpw){
						//alert("提交表单");
						document.forms[0].submit();
					}else{
						alert("两次密码输入不一致！");
					}					
				}
			}
		}
	}
	/*
	author:马康耀
	功能：生成随机码并叫相应信息提交给PwResetCheckCL.jsp
	参数：void
	返回值：void
	*/
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
	/*
	author：马康耀
	功能：对验证码加密
	参数：void
	返回值：String
	*/
	function encrypt(){
		//alert(typeof CHECK);
		var enc=CHECK;
		return enc;
	}
</script>
<link type="text/css" rel="stylesheet" href="<%= path %>/CSS/PwReset.css">
<title>重置密码</title>
</head>

<body>
   <canvas ></canvas>
   <form id="resetForm" action="PwResetCL.jsp" method="POST">
   <div id="PsRTitle">OUTBREAK 云会议管理系统</div>
   <div id="ResetBox">
      <div>
		 <span><input type="email" id="em" style="border:1px solid #999999;" placeholder="请输入邮箱" name="Email"></span>
		 <span id="email_poster"><input id="email_button" type="button" value="发送验证码" onclick="postEmail()"></span>
      </div>
	  <div><input id="pw" type="password" style="border:1px solid #999999;" placeholder="请输入密码" name="Password"></div>
	  <div><input id="rpw" type="password" style="border:1px solid #999999;" placeholder="请再次输入密码" name="RePassword"></div>
	  <div><input id="chk" type="text" style="border:1px solid #999999;" placeholder="请输入验证码" name="Check"></div>
	  <a id="ResetBack" href="Login.jsp">返回</a>
      <input id="ResetBtn" type="button" class="sendbutton" value="重 置" onclick="subForm()">
   </div>
   </form>
</body>

</html>
