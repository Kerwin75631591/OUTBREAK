<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html" charset="GBK">
<link type="text/css" rel="stylesheet" href="mycss/Register.css">
<title>�������ϵͳ��ע��</title>
</head>

<body>
	<div id="content">
		<h1>OUTBREAK ��ͻ����ƻ���ϵͳ</h1>
		<div id="register_form">
			<form id="regForm" action="RegisterCL.jsp" method="POST">
				<div class="table">
					<div class="table_row">
						<div class="table_cell right_align">�ʺţ����䣩��</div>
						<div class="table_cell">
							<input type="email" name="Email">
						</div>
						<div class="table_cell">
							<input type="button" value="������֤��">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">��֤�룺</div>
						<div class="table_cell">
							<input type="text" name="Check">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">���룺</div>
						<div class="table_cell">
							<input type="password" name="Password">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell right_align">�ظ����룺</div>
						<div class="table_cell">
							<input type="password" name="RePassword">
						</div>
					</div>
					<div class="table_row">
						<div class="table_cell"></div>
						<div class="table_cell center_align">
							<input type="submit" value="ע��">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>