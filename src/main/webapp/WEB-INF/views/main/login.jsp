<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title> Login </title>

<!-- Custom styles for this template-->
<link href="${contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet"> 

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body class="bg-gradient-primary">
<div class="container">
	<div class="card o-hidden border-0 shadow-lg my-5 mx-3">
		<div class="card-body col-mg-12">
			<div class="p-5 col-md-6 offset-md-3">
				<div class="text-center">
					<h1 class="h4 text-gray-900 mb-3">회원가입</h1>
				</div>
				<form class="user">
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="id" name="id" placeholder="아이디">
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user" autocomplete = "new-password" id="password" name="password" placeholder="비밀번호">
					</div>	
					<input type="button" class="btn btn-primary btn-user btn-block" id="loginBtn" value="로그인" >
					
				</form>
				<hr>
				<div class="text-center"> <a class="small" href="forgot-password.html">비밀번호 찾기</a> </div>
				<div class="text-center"> <a class="small" href="${contextPath}/main/register">회원가입 </a> </div>
			</div>
		</div>
	</div>
</div>

<!-- 모달 시작 -->
<div class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- 모달 끝 -->

</body>

<script src="${contextPath}/resources/javascript/member.js?v=<%=System.currentTimeMillis() %>"></script>
<script>
loginId="";

$("#loginBtn").on("click", function(){
	alert("성공?");
	var memberVO = {
		id : $("#id").val(),
		password : $("#password").val()
	};
	console.log($("#id").val());
	console.log($("#password").val());
	
	
	member.checkLogin(
		memberVO, 		
		function(result){			
			if(result=="success"){
				loginId="${loginId}";
				var previous_url="${previous}";
				<% session.removeAttribute("previous"); %>
				//var previous_list="${previous}";
				//alert(previous_list);
				location.href=previous_url;
			}
	});
	
	
});








</script>
</html>