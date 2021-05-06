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

<title> BookCM-Register </title>

<!-- Custom fonts for this template-->
<%-- <link
	href="${contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css"> --%>
<!-- <link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet"> -->

<!-- Bootstrap core JavaScript-->
<%-- <script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${contextPath}/resources/js/sb-admin-2.min.js"></script> --%>

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
		<div class="card-body col-mg-12 startChat">
			<div class="p-5 col-md-6 offset-md-3">
				<div class="text-center">
					<h1 class="h4 text-gray-900 mb-3">회원가입</h1>
				</div>
				<form class="regMemberForm">
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="id" name="id" placeholder="아이디">
						<!-- 중복관리 -->
						<p/><div class="idChat" style="text-align:center;"></div>
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user" autocomplete = "new-password" id="password" name="password" placeholder="비밀번호">
					</div>	
					<div class="form-group">
						<input type="password" class="form-control form-control-user"  autocomplete = "new-password" id="password_c" name="password_c" placeholder="비밀번호 확인">
						<p/><div class="pwChat" style="text-align:center;"></div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="name" name="name" placeholder="이름"><p/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="nickName" name="nickName" placeholder="닉네임">
						<p/><div class="nameChat" style="text-align:center;"></div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="email" name="email" placeholder="이메일">
						<p/><div class="emailChat" style="text-align:center;"></div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control form-control-user" id="phone" name="phone" placeholder="핸드폰">
						<p/><div class="phoneChat" style="text-align:center;"></div>
					</div>
					<input type="button" class="btn btn-primary btn-user btn-block" id="regBtn" value="가입하기"> 
				</form>
				<hr>
				<div class="text-center"> <a class="small" href="forgot-password.html">비밀번호 찾기</a> </div>
				<div class="text-center"> <a class="small" href="${contextPath}/main/login">로그인 하러 가기</a> </div>
			</div>
		</div>
	</div>
</div>

<!-- modal 시작 -->
<div class="modal" id="myModal"tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title header"></h5>
				<button type="button" class="close closer" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
	      
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary closer" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
<!-- modal 끝 -->
</body>


<script src="${contextPath}/resources/javascript/member.js?v=<%=System.currentTimeMillis() %>"></script>
<script>
var result1 = false;	//아이디
var result2 = false;	//비밀번호
var result3 = false;	//닉네임
var result4 = false;	//이메일
var result5 = false;	//핸드폰
//비밀번호가 변경될때 실행
$("#password").on("change keyup paste", function() {
	checkPassword();
});
$("#password_c").on("change keyup paste", function() {
	checkPassword();
});
//아이디 변경될때 실행
$("#id").on("change keyup paste", function() {
	checkId();
});
//닉네임 변경될때 실행
$("#nickName").on("change keyup paste", function() {
	checkNick();
});
//이메일 변경될때 실행
$("#email").on("change keyup paste", function() {
	checkEmail();
});
//핸드폰 변경될때 실행
$("#phone").on("change keyup paste", function() {
	checkPhone();
});

//아이디 존재여부 판단
function checkId(){
	var id_value = ($("#id").val());
	var regExpId = /^[0-9a-z]+$/;	//영어와 숫자만 가능
	
	result1 = false;
	
	if(regExpId.test(id_value)){
		member.existId(
			id_value,
			function(data){
				if(data == "success"){
					result1 = true;
	 				$(".idChat").css("color","blue");
					$(".idChat").html("사용 가능한 아이디입니다.");
				}else{
					result1 = false;
	 				$(".idChat").css("color","red");
					$(".idChat").html("사용 불가능한 아이디 입니다.");
				}
	});
		
	}else{
		$(".idChat").css("color","red");
		$(".idChat").html("사용 불가능한 아이디 입니다.");
	}
	
}

//비밀번호 일치 여부판단
function checkPassword(){
	result2 = false;
	var regExpPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$/;	//최소4자 문자 및 숫자 1개씩을 들어가야함
	//console.log($("#password").val());
	//console.log($("#password_c").val());
	if(regExpPassword.test($("#password").val())){
		if($("#password").val() == $("#password_c").val()){
			result2 = true;
			$(".pwChat").css("color","blue");
			$(".pwChat").html("비밀번호가 일치합니다.");
		}else{
			result2 = false;
			$(".pwChat").css("color","red");
			$(".pwChat").html("비밀번호가 일치하지않습니다.");
		}
	}else{
		result2 = false;
		$(".pwChat").css("color","red");
		$(".pwChat").html("형식에 일치하지않는 비밀번호입니다.");
	}
	
}

//닉네임 존재여부 판단
function checkNick(){
	var nickName_value = ($("#nickName").val());
	result3 = false;
	
	member.existNick(
		nickName_value,
		function(data){
			if(data == "success"){
				result3 = true;
 				$(".nameChat").css("color","blue");
				$(".nameChat").html("사용 가능한 별명입니다.");
			}else{
				result3 = false;
 				$(".nameChat").css("color","red");
				$(".nameChat").html("사용이 불가능한 별명입니다.");
			}
		}
			
	)
}

//이메일 유효성 검사 판단
function checkEmail(){
	var email_value = ($("#email").val());
	//var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var regExpEmail = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}$/;
	result4 = false;
	
	if(regExpEmail.test(email_value)){
		result4 = true;
		$(".emailChat").css("color","blue");
		$(".emailChat").html("형식에 알맞는 이메일입니다.");
	}else{
		result4 = false;
		$(".emailChat").css("color","red");
		$(".emailChat").html("형식에 맞지않는 이메일입니다.");
	}
	
}

//핸드폰 유효성 검사 판단
function checkPhone(){
	var phone_value = ($("#phone").val());
	//var regExp = /^[0-9]*$/;
	var regExp = /^[0-9]{11}$/;
	result5 = false;
	
	if(regExp.test(phone_value)){
		result5 = true;
		$(".phoneChat").css("color","blue");
		$(".phoneChat").html("형식에 알맞는 번호입니다.");
	}else{
		result5 = false;
		$(".phoneChat").css("color","red");
		$(".phoneChat").html("형식에 맞지않는 번호입니다.");
	}
	
}

//유효성검사
function checkValue(){
	if(result1 && result2){
		if(result3 && result4 && result5){
			return 1;
			
		}else{
			$(".header").html("회원가입 경고");
			$(".modal-body").html("형식에 알맞이 않은 항목이 있습니다.");
			$("#myModal").show();
			return 0;
		}
		
	}else{
		$(".header").html("회원가입 경고");
		$(".modal-body").html("아이디와 비밀번호를 확인하세요");
		$("#myModal").show();
		return 0;
	}
}

//모달 닫기
$(".closer").on("click", function(){
	$("#myModal").hide();
});


//가입하기
$("#regBtn").on("click", function(e){
	e.preventDefault();
	
	var memberVO={
			id:$("#id").val(),
			password:$("#password").val(),
			name:$("#name").val(),
			nickName:$("#nickName").val(),
			email:$("#email").val(),
			phone:$("#phone").val(),
	}
	
	console.log(memberVO);
	
	if(checkValue() == 1){
		member.regMember(
			memberVO,
			function(data){
				if(data == 1 || data == "1"){
					return 1;
				}else{
					return 0;
				}
		});
	var str ="";
	str +="<div class='text-center'> <p/><p/><img src='/resources/img/regMember.jpg'> </div>"
		+ "<hr/>"
		+ "<div class='text-center'> <a class='small' href='${contextPath}/main/login'>로그인 하러 가기</a> </div>";
		
		alert("성공");
		$(".startChat").html("");
		$(".startChat").html(str);
	}else{
		alert("실패");
	}
	
});
</script>

</html>