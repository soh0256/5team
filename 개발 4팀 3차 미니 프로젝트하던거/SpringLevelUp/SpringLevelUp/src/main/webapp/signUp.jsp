<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="dataTables.bootstrap.css" rel="stylesheet">
<script src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">

	function fn_save(){
		if(!fn_validation()) return;

		$("#phone_num").val($("#phone0").val() + "-" + $("#phone1").val() + "-" + $("#phone2").val());
		$("#email").val($("mailID").val() +"@"+ $("#mailAD").val());
		$("#birth").val($("birthY").val() + $("#birthM").val() + $("#birthD").val());
		$("#joinFrm").submit();
	}
</script>


</head>
<body>
	<center>
		<h1>ȸ������</h1>
	</center>
		<hr>

  		<form action="signUp.do" method="post" class="form-horizontal" id="joinFrm">
  			<div class="form-group">
    			<label for="inputId" class="col-sm-2 control-label">���̵�</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="id" name="id" value="${param.id}" placeholder="ID" autofocus>
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="inputPassword" class="col-sm-2 control-label">��й�ȣ</label>
    			<div class="col-sm-5">
      			<input type="password" class="form-control" id="pw" name="pw" placeholder="Password" value="${param.pw}">
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<label for="inputEmail" class="col-sm-2 control-label">�̸���</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="mailID" name="mailID" placeholder="���̵�" onkeydown="return fn_showKeyCode(event)"/ maxlength="10">
      			@
      			<input type="text" class="form-control" id="mailAd" name="mailAD" placeholder="���ϻ���Ʈ.com" onkeydown="return fn_showKeyCode(event)" maxlength="17"/>
    			</div>
    			<input type="hidden" id="email" name="email"></div>
  			</div>
  			
  			<div class="form-group">
    			<label for="inputName" class="col-sm-2 control-label">�̸�</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${param.name}">
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="birth" class="col-sm-2 control-label">�������</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="birthY" name="birth" required="required"  maxlength="4">
      			<input type="text" class="form-control" id="birthM" name="birth" required="required"  maxlength="2">
      			<input type="text" class="form-control" id="birthD" name="birth" required="required"  maxlength="2">
    			</div>
    			<input type="hidden" id="birth" name="birth">
  			</div>
  			
  			<div class="form-group">
    			<label for="inputPhone" class="col-sm-2 control-label">�ڵ�����ȣ</label>
    			<div class="col-sm-5">
	    			<select class="form-control" id="phone0" name="phoneCd" required="required">
								<option value="1">010</option>
								<option value="4">070</option>
								<option value="2">02</option>
								<option value="5">032</option>
								<option value="6">031</option>
			     	</select>
	    			<input class="form-control" type="text" id="phone1" maxlength="4" required="required" onkeydown="return fn_showKeyCode(event)"/>
	    			<input class="form-control" type="text" id="phone2" maxlength="4" required="required" onkeydown="return fn_showKeyCode(event)"/>
	     		</div>
				<input type="hidden" id="phone_num" name="phone_num"></div>
    		</div>
    			
  			<div class="form-group">
    			<label for="inputAddress" class="col-sm-2 control-label">�⺻�ּ�</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="post_num" name="post_num" value="${param.post_num}" placeholder="�˻��� �̿����ּ���">
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<label for="inputAddress" class="col-sm-2 control-label">���ּ�</label>
    			<div class="col-sm-5">
      			<input type="text" class="form-control" id="address" name="address" value="${param.address}" placeholder="������ �ּ� �Է����ּ���">
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<label for="inputAddress" class="col-sm-2 control-label">����</label>
    			<div class="col-sm-5">
      			<select name="grade">
  							<option value="User">�����</option>
 							<option value="Admin">������</option>
				</select>
    			</div>
  			</div>
  			
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-primary">ȸ������</button>
      			<input type="button" class="btn btn-primary" value="���" onclick="location.href='login.do'" />
    			</div>
  			</div>
			</form>
		<hr>
</body>
</html>