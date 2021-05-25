<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지사항 상세</title>
</head>
<body>
	<center>
		<h1>공지사항 상세</h1>
		<a href="logout.do">Log-out</a>
		<hr>
		
		<!-- LoginController.java에서 사용자 권한을 저장한 "userGrad"값을 Grad에 저장 -->
		<c:set var="Grad" value="${userGrad }"/>
		
		<form action="updateNotice.do" method="post">
			<input name="notice_no" type="hidden" value="${notice.notice_no}" />
			<table border="1" cellpadding="0" cellspacing="0">
			
				<c:choose> <%-- if-else문 --%>
				<c:when test="${ Grad eq 'Admin'}"> <%-- 로그인한 사용자의 권한이 Admin(관리자)이면 / eq : 비교값이 동일한지(==) --%>
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input name="notice_title" type="text"
						value="${notice.notice_title }" /></td>
				</tr>
				</c:when>
				
				<c:otherwise> <%-- Admin(관리자)가 아니면 (사용자이면) --%>
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td align="left"><input name="notice_title" type="text"
						value="${notice.notice_title }" readonly/></td> <%-- readonly : 비활성화 --%>
				</tr>
				</c:otherwise>
				</c:choose>
				
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left">관리자</td>
				</tr>
				
				<c:choose>
				<c:when test="${ Grad eq 'Admin'}">
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left"><textarea name="notice_contents" cols="40" rows="10" >
						${notice.notice_contents }</textarea></td>
				</tr>
				</c:when>
				
				<c:otherwise>
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left"><textarea name="notice_contents" cols="40" rows="10" readonly>
						${notice.notice_contents }</textarea></td>
				</tr>
				</c:otherwise>
				</c:choose>
				
				<tr>
					<td bgcolor="orange">등록일</td>
					<td align="left"><fmt:formatDate value="${notice.notice_date }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td align="left">${notice.notice_hits }</td>
				</tr>
				<!-- 로그인한 사용자의 권한이 Admin(관리자)일때, 공지사항 수정 버튼이 보여짐 -->
				<c:if test="${ Grad eq 'Admin'}">
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="글 수정" /></td> 
				</tr>
				</c:if>
			</table>
		</form>
		<hr>
		
		<!-- 로그인한 사용자의 권한이 Admin(관리자)일때, 공지사항 등록, 삭제 버튼이 보여짐 -->
		<c:if test="${ Grad eq 'Admin'}">
		<a href="insertNotice.jsp">글등록</a>&nbsp;&nbsp;&nbsp; 
		<a href="deleteNotice.do?notice_no=${notice.notice_no }">글삭제</a>&nbsp;&nbsp;&nbsp;
		</c:if>
		<a href="getNoticeList.do">글목록</a>
	</center>
</body>
</html>