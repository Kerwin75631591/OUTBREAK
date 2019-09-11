<!-- author������ -->

<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.outbreak.dao.*"%>
<%@ page import="com.outbreak.util.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<% 
	String path = request.getContextPath(); 

	//�����Ϣ
	String meetingid = request.getParameter("meetingid");
	String meetingName = "";
	String meetingTopic = "";
	String meetingPlace = "";
	int BeginH = 00;
	int BeginM = 00;
	int EndH = 00;
	int EndM = 00;
	String meetingData = "";
	String begintime = "";
	String endtime ="";
	String meetingContent = "";
	String Users = "";
	
	if(!(meetingid == null || "".equals(meetingid.trim())))
	{
		int mid = Integer.parseInt(meetingid);
		
		DBConnect db = new DBConnect();
		db.connect();
		ResultSet meeting = db.searchMeeting(mid);
		
		
		if(meeting != null)
		{
			meeting.next();
			meetingName = meeting.getString("name");
		    meetingTopic = meeting.getString("topic");
		    meetingPlace = meeting.getString("place");
		    meetingContent = meeting.getString("content");
		    begintime = meeting.getString("begintime");
			endtime = meeting.getString("endtime");
		    String meetingBegintime = begintime;
			String meetingEndtime =endtime;
			meetingData = begintime.substring(0, 10);
		}
		
		ResultSet people = db.searchPeople(mid);
		while(people.next())
		{
			Users += (people.getString("Uid") + "-" + people.getString("PhoneNum") + "-" + people.getString("Email") + "-");
		}
		System.out.println(Users);
		
		db.close();
	}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="GBK">
<title>��������</title>
<link rel="stylesheet" href="../CSS/MeetingCreate.css" />
<script type="text/javascript">
	/*
	author������
	���ܣ���������
	������void
	����ֵ��void
	*/
	function Release(){
		
		
        var meetingName = document.getElementById("meetingName").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingTopic = document.getElementById("meetingTopic").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingData = document.getElementById("meetingData").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingPlace = document.getElementById("meetingPlace").value.replace(/(^\s*)|(\s*$)/g, '');
        var meetingContent = document.getElementById("meetingContent").value.replace(/(^\s*)|(\s*$)/g, '');
		var rowNum = document.getElementById("UserTable").rows.length;
		
		//�ж��Ƿ�����ÿһ��ļ����⣩
		if(meetingName == '' || meetingTopic == '' || meetingPlace == '' || meetingContent == '' || meetingData == '' || rowNum == 1){
			alert("��������д���������ύ�������ȱ���Ϊ�ݸ壡");
			return;
		}
		
		//ʱ���ʽ�ж�
		var meetingBeginH = document.getElementById("BeginH").value;
		var meetingBeginM = document.getElementById("BeginM").value;
		var meetingEndH = document.getElementById("EndH").value;
		var meetingEndM = document.getElementById("EndM").value;
		if (isNaN(meetingBeginH) || meetingBeginH < 0 || meetingBeginH > 24 ||
			isNaN(meetingBeginM) || meetingBeginM < 0 || meetingBeginM > 60 ||	
			isNaN(meetingEndH) || meetingEndH < 0 || meetingEndH > 24 ||
			isNaN(meetingEndM) || meetingEndM < 0 || meetingEndM > 60 || meetingBeginH > meetingEndH) {
		    alert("�ⲻ��һ����Ч��ʱ���");
		    return;
		}else{
			if(meetingBeginH == meetingEndH){
				if(meetingBeginM >= meetingEndM){
					alert("�ⲻ��һ����Ч��ʱ���");
				    return;
				}
			}
		}
		
		//ʱ���ʽת��Ϊ"hh:mm"
		document.getElementById("meetingBegintime").value = document.getElementById("BeginH").value + ":" + document.getElementById("BeginM").value;
		document.getElementById("meetingEndtime").value = document.getElementById("EndH").value + ":" + document.getElementById("EndM").value;
		
		if(!(document.getElementById("uploadFile").value == "")){
			document.meetingManageForm.enctype = "multipart/form-data" 
			document.meetingManageForm.action = "../ReleaseServlet";
	        document.meetingManageForm.submit();
		}else{
			document.meetingManageForm.action = "../ReleaseWithoutFileServlet";
	        document.meetingManageForm.submit();
		}
	}
	/*
	author������
	���ܣ����������Ϣ
	������void
	����ֵ��void
	*/
	function Save(){
		if(!(document.getElementById("uploadFile").value == "")){
			alert("����ݸ幦�ܲ�֧�ֱ����ļ������ڷ���ʱ���ϴ��ļ���");
		}
		
		var meetingBeginH = document.getElementById("BeginH").value;
		var meetingBeginM = document.getElementById("BeginM").value;
		var meetingEndH = document.getElementById("EndH").value;
		var meetingEndM = document.getElementById("EndM").value;
		
		if(!(meetingBeginH == "" || meetingBeginM == "" || meetingEndH == "" || meetingEndM == "" || document.getElementById("meetingData").value == "")){
			//ʱ���ʽ�ж�
			if (isNaN(meetingBeginH) || meetingBeginH < 0 || meetingBeginH > 24 ||
				isNaN(meetingBeginM) || meetingBeginM < 0 || meetingBeginM > 60 ||	
				isNaN(meetingEndH) || meetingEndH < 0 || meetingEndH > 24 ||
				isNaN(meetingEndM) || meetingEndM < 0 || meetingEndM > 60 || meetingBeginH > meetingEndH) {
			    alert("�ⲻ��һ����Ч��ʱ���");
			    return;
			}else{
				if(meetingBeginH == meetingEndH){
					if(meetingBeginM > meetingEndM){
						alert("�ⲻ��һ����Ч��ʱ���");
					    return;
					}
				}
			}
			//ʱ���ʽת��Ϊ"hh:mm"
			document.getElementById("meetingBegintime").value = document.getElementById("BeginH").value + ":" + document.getElementById("BeginM").value;
			document.getElementById("meetingEndtime").value = document.getElementById("EndH").value + ":" + document.getElementById("EndM").value;
		}else{
			document.getElementById("meetingBegintime").value = "";
			document.getElementById("meetingEndtime").value = "";
		}
		
        document.meetingManageForm.action = "../SaveServlet";
        document.meetingManageForm.submit();
 ����}
	/*
	author������
	���ܣ����������Ա
	������void
	����ֵ��void
	*/
	function Add(){
		var Name = document.getElementById("Name").value;
        var Phone = document.getElementById("Phone").value;
        var Email = document.getElementById("Email").value;
        
        //E-mail��֤
        var atpos = Email.indexOf("@");
        var dotpos = Email.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=Email.length){
          alert("����һ����Ч�� e-mail ��ַ");
          return false;
        }
        
        //��ϵ��ʽ��֤
        if (isNaN(Phone)){
        	alert("����һ����Ч����ϵ��ʽ");
            return false;
        }
        
        //��ӵ�Users���棬�ʿ��Դ�����servlet
		var temp = document.getElementById("Users").value;
        Users.value = temp + Name + "-" + Phone + "-" + Email + "-";

        //��ӵ�UsersTable��
		var trObj = document.createElement("tr");
		trObj.id = new Date().getTime();
		trObj.innerHTML = "<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>"
		                   +Name+"</td><td>"+Phone+"</td><td>"+Email+"</td><td><input type='button' value='ɾ��' onclick='Delete(this)'></td>";
		document.getElementById("UserTable").appendChild(trObj);
		
		//���������
		document.getElementById("Name").value = null;
        document.getElementById("Phone").value = null;
        document.getElementById("Email").value = null;
	}
	/*
	author������
	���ܣ�ɾ�������Ա
	������void
	����ֵ��void
	*/
	function Delete(obj){
		var trId = obj.parentNode.parentNode.id;
		var trObj = document.getElementById(trId);
		document.getElementById("UserTable").removeChild(trObj);
	}
	/*
	author������
	���ܣ�������Ϣ
	������void
	����ֵ��void
	*/
	function Reset(){ 
		var tb = document.getElementById("UserTable");
		var rowNum = tb.rows.length;
	    for (var i = 0; i<rowNum; ++i)
	    {
	        tb.deleteRow(1);
	        --rowNum;
	    }
	}
</script>
</head>

<body>
	<canvas></canvas>
	<div id="CrtTitle">OUTBREAK �ƻ������ϵͳ</div>
	<div id="CrtTopBox">
	   <div id="CreateWelcome">���ã�<%=request.getSession().getAttribute("sessionemail") %>��</div>
	   <div id="CreateJump">
	      <span><input type="button" id="CreateMeetingManageBtn" 
	            value="�� �� �� ��" onclick="window.location.href='MeetingManage.jsp';"/></span>
	      <span><input type="button" id="CreateLoginBtn" 
	            value="�� �� �� ¼" onclick="window.location.href='Login.jsp';"/></span>
	   </div>
	</div>
	
	<form action="" id="meetingManageForm" name="meetingManageForm" method="post">
	<div id="CrtLeftBox">
	   <table width="100%" border=0 cellspacing="10" style="margin-top: 10px; margin-left: 20px border-width:0 0 0 0">
		  <tr>
		     <td style="font-size: 20px">�������ƣ�</td>
		     <td><input type="text" id="meetingName" name="meetingName" value="<%=meetingName%>"
		         style="width: 500px; height: 30px; font-size: 30px;"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">�������⣺</td>
			 <td><input id="meetingTopic" name="meetingTopic" value="<%=meetingTopic%>"
			 type="text"style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">�������ڣ�</td>
			 <td><input id="meetingData" name="meetingData" value="<%=meetingData%>" type="date" style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">����ʱ�䣺</td>
			 <td>
			    <table id="TimeTable">
			       <tr>
			          <td style="font-size: 20px;"><input id="BeginH" type="text" value="<%=BeginH%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 50px;text-align:center;">:</td>
			          <td style="font-size: 20px;"><input id="BeginM" type="text" value="<%=BeginM%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 80px;text-align:center;">��</td>
			          <td style="font-size: 20px;"><input id="EndH" type="text" value="<%=EndH%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			          <td style="font:bold;font-size: 20px; width: 50px;text-align:center;">:</td>
			          <td style="font-size: 20px;"><input id="EndM" type="text" value="<%=EndM%>"
			                                       style="width: 70px;height:30px; font-size: 30px;text-align:center;"></td>
			       </tr>
			    </table>
			 </td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">����ص㣺</td>
			 <td><input id="meetingPlace" name="meetingPlace" value="<%=meetingPlace%>" type="text" style="width: 500px; height: 30px; font-size: 30px"></td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">�������ݣ�</td>
			 <td><textarea id="meetingContent" name="meetingContent" style="line-height: 30px; width: 500px; height: 350px; font-size: 30px"><%=meetingContent%></textarea></td>
		  </tr>
		  <tr>
			 <td style="font-size: 20px">�ϴ����ϣ�</td>
			 <td><input id="uploadFile" name="uploadFile" type="file" style="width: 500px; height: 30px; font-size: 20px"></td>
		  </tr>
	   </table>
	</div>
	<div id="CrtRightBox">
	<div id="Box">
	   <table id="UserTable" name="UserTable">
	      <tr>
		     <td style="font-size: 20px; margin-left: -400px; width:100px;">�����Ա��</td>
			 <td style="font-size: 20px; width:150px;height:40px;">��Ա����</td>
			 <td style="font-size: 20px; width:150px;height:40px;">��ϵ��ʽ</td>
			 <td style="font-size: 20px; width:250px;height:40px;">�����ַ</td>
			 <td style="font-size: 20px;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  </tr>
		   <%
		  	String[] guests = Users.split("-");
		  	for(int i = 0; i<guests.length-2; i += 3)
			{
		  %>
		  	<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><%=guests[i]%></td>
				<td><%=guests[i+1]%></td>
				<td><%=guests[i+2]%></td>
				<td><input type="button" value="ɾ��" onclick="Delete(this)"></td>
			</tr>
		  <%
			}
		  %>
	   </table>
	</div>
	   <table id="InputTable">
	      <tr>
		     <td style="font-size: 20px; width:100px;">������Ϣ��</td>
			 <td style="font-size: 20px; width:150px;height:40px;">��Ա����</td>
			 <td style="font-size: 20px; width:150px;height:40px;">��ϵ��ʽ</td>
			 <td style="font-size: 20px; width:250px;height:40px;">�����ַ</td>
		  </tr>
		  <tr>
		     <td style="font-size: 20px">&nbsp;</td>
			 <td><input type="text" id="Name" style="width:150px;height:40px; font-size: 20px"></td>
			 <td><input type="text" id="Phone" style="width:150px;height:40px; font-size: 20px"></td>
			 <td><input type="email" id="Email" style="width:250px;height:40px; font-size: 20px"></td>
			 <td><span><input type="button" id="CreateAdd" value="��  ��" onclick="Add()"></span></td>
		  </tr>
	   </table>
	</div>
	<div id="CrtBottomBox">
	   <input type="button" id="CreateRelease" value="��  ��" onclick="Release()"> 
	   <input type="button" id="CreateSave" value="����ݸ�" onclick="Save()"> 
	   <input type="reset" id="CreateReset" value="��  ��" onclick="Reset()">
	   <input type="text" id="Users" name="Users" style="display:none" value="<%=Users%>">
	   <input type="text" id="meetingBegintime" name="meetingBegintime" style="display:none" value="<%=begintime%>">
  	 	<input type="text" id="meetingEndtime" name="meetingEndtime" style="display:none" value="<%=endtime%>">

	</div>
	
	</form>

</body>
</html>