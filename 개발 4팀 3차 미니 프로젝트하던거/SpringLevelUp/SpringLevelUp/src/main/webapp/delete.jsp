<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ��Ż��</title>
</head>
<body>
	<center>
		<h1>ȸ��Ż��</h1>
		<hr>
		<form action="delete.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange">���̵�</td>
					<td><input type="text" name="id" value="${user.id}"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">��й�ȣ</td>
					<td><input type="pw" name="pw" value="${user.pw}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="ȸ��Ż��"/>
					<input type="button" value="���" onclick="location.href='getBoardList.do'" />
				</tr>
			</table>
		</form>
		<hr>
	</center>
</body>
</html>