<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="context"><%=request.getContextPath()%></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="message.user.login.title" text="로그인"/></title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap.min.css" rel="stylesheet">

</head>
<body>



	<center>
		<h1><spring:message code="message.user.login.title" text="로그인"/></h1>
		<a href="login.do?lang=en">
		<spring:message code="message.user.login.language.en" text="Eng"/></a>&nbsp;&nbsp;
		<a href="login.do?lang=ko">
		<spring:message code="message.user.login.language.ko" text="한국어"/></a>
	</center>
	
		<hr>
			<form action="login.do" method="post" class="form-horizontal">
  			<div class="form-group">
    			<label for="inputId" class="col-sm-2 control-label"><spring:message code="message.user.login.id" text="아이디" var="${user.id}"/></label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="inputId" name="id" placeholder="ID" autofocus>
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="inputPassword" class="col-sm-2 control-label"><spring:message code="message.user.login.password" text="비밀번호" var="${user.pw}"/></label>
    			<div class="col-sm-5">
      			<input type="password" class="form-control" id="inputPassword" name="pw" placeholder="Password">
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-primary"><spring:message code="message.user.login.loginBtn" text="로그인"/></button>
      			<input type="button" class="btn btn-primary" value="회원가입" onclick="location.href='signUp.jsp'" text="가입하기"/>
    			</div>
  			</div>
			</form>
		<hr>
</body>
</html>