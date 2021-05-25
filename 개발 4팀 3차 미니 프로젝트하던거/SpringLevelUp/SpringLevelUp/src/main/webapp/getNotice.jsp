<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�������� ��</title>
</head>
<body>
	<center>
		<h1>�������� ��</h1>
		<a href="logout.do">Log-out</a>
		<hr>
		
		<!-- LoginController.java���� ����� ������ ������ "userGrad"���� Grad�� ���� -->
		<c:set var="Grad" value="${userGrad }"/>
		
		<form action="updateNotice.do" method="post">
			<input name="notice_no" type="hidden" value="${notice.notice_no}" />
			<table border="1" cellpadding="0" cellspacing="0">
			
				<c:choose> <%-- if-else�� --%>
				<c:when test="${ Grad eq 'Admin'}"> <%-- �α����� ������� ������ Admin(������)�̸� / eq : �񱳰��� ��������(==) --%>
				<tr>
					<td bgcolor="orange" width="70">����</td>
					<td align="left"><input name="notice_title" type="text"
						value="${notice.notice_title }" /></td>
				</tr>
				</c:when>
				
				<c:otherwise> <%-- Admin(������)�� �ƴϸ� (������̸�) --%>
				<tr>
					<td bgcolor="orange" width="70">����</td>
					<td align="left"><input name="notice_title" type="text"
						value="${notice.notice_title }" readonly/></td> <%-- readonly : ��Ȱ��ȭ --%>
				</tr>
				</c:otherwise>
				</c:choose>
				
				<tr>
					<td bgcolor="orange">�ۼ���</td>
					<td align="left">������</td>
				</tr>
				
				<c:choose>
				<c:when test="${ Grad eq 'Admin'}">
				<tr>
					<td bgcolor="orange">����</td>
					<td align="left"><textarea name="notice_contents" cols="40" rows="10" >
						${notice.notice_contents }</textarea></td>
				</tr>
				</c:when>
				
				<c:otherwise>
				<tr>
					<td bgcolor="orange">����</td>
					<td align="left"><textarea name="notice_contents" cols="40" rows="10" readonly>
						${notice.notice_contents }</textarea></td>
				</tr>
				</c:otherwise>
				</c:choose>
				
				<tr>
					<td bgcolor="orange">�����</td>
					<td align="left"><fmt:formatDate value="${notice.notice_date }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">��ȸ��</td>
					<td align="left">${notice.notice_hits }</td>
				</tr>
				<!-- �α����� ������� ������ Admin(������)�϶�, �������� ���� ��ư�� ������ -->
				<c:if test="${ Grad eq 'Admin'}">
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="�� ����" /></td> 
				</tr>
				</c:if>
			</table>
		</form>
		<hr>
		
		<!-- �α����� ������� ������ Admin(������)�϶�, �������� ���, ���� ��ư�� ������ -->
		<c:if test="${ Grad eq 'Admin'}">
		<a href="insertNotice.jsp">�۵��</a>&nbsp;&nbsp;&nbsp; 
		<a href="deleteNotice.do?notice_no=${notice.notice_no }">�ۻ���</a>&nbsp;&nbsp;&nbsp;
		</c:if>
		<a href="getNoticeList.do">�۸��</a>
	</center>
</body>
</html>