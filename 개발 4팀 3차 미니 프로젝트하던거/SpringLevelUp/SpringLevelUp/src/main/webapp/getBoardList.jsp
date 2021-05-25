<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>�� ���</title>
</head>
<body>

<!-- �׺���̼�(nav) ��Ʈ�ѿ� ����ϴ� ��Ӵٿ�. -->
<div style="margin:20px;">
	<nav id="navbar-example" class="navbar navbar-default navbar-static">
		<div class="container-fluid">
		<!-- �׺���̼�(nav)�� �⺻ �������� ������� ��, �޴� ��ư�� ���´�. -->
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- Ÿ��Ʋ -->
			<a class="navbar-brand" >Title</a>
		</div>
		<!-- �޴� ���� -->
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<!-- �Խñ� ������� �� -->
				<li>
					<a href="getNoticeList.do" class="dropdown-toggle" >
						��������
					</a>
				</li>
			</ul>
			<!-- �޴��� ������ ���ķ� ���� ���� -->
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						ȸ������
						<!-- �Ʒ� ȭ��ǥ -->
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="modify.do" >ȸ�� ���� ����</a></li>
						<li><a href="logout.do" >Log-out</a></li>
						<li><a href="delete.do" >ȸ�� Ż��</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	</nav>
</div>
	<hr>
		
	<center>
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>${user.id}<spring:message code="message.board.list.welcomeMsg"/><br/></h3>
		
		<!-- �˻� ���� -->
		<form class="form-inline" action="getBoardList.do" method="post">
  			<div class="form-group">
    			<label for="exampleInputName2">
    			<select name="searchCondition">
					<c:forEach items="${conditionMap }" var="option">
						<option value="${option.value }">${option.key }
					</c:forEach>							
				</select> </label> &nbsp;&nbsp;
    			<input type="text" class="form-control" id="exampleInputName2" name="searchKeyword">
  			</div>
  			<button type="submit" class="btn btn-default" ><spring:message code="message.board.list.search.condition.btn"/></button>
		</form><br/>
		<!-- �˻� ���� -->
		<table border="1" cellpadding="0" cellspacing="0" width="700" class="table table-striped">
			<tr>
				<th bgcolor="#d2d2d2" width="100">
				<spring:message	code="message.board.list.table.head.seq" /></th>
				<th bgcolor="#d2d2d2" width="200">
				<spring:message code="message.board.list.table.head.title" /></th>
				<th bgcolor="#d2d2d2" width="150">
				<spring:message code="message.board.list.table.head.writer" /></th>
				<th bgcolor="#d2d2d2" width="150">
				<spring:message code="message.board.list.table.head.regDate" /></th>
				<th bgcolor="#d2d2d2" width="100">
				<spring:message code="message.board.list.table.head.cnt" /></th>
			</tr>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.board_no }</td>
					<td align="left"><a href="getBoard.do?board_no=${board.board_no }">
							${board.board_title }</a></td>
					<td>${board.id }</td>
					<td><fmt:formatDate value="${board.board_date }" pattern="yyyy-MM-dd"/></td>
					<td>${board.board_hits }</td>
				</tr>
			</c:forEach>
		</table>
		<br> <a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</center>
</body>
</html>