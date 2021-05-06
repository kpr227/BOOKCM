<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="kr">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Book community</title>
	<!-- Custom styles for this template-->
	<link href="${contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
	
	<!-- Bootstrap core JavaScript--><!-- 페이지 다운 -->
	<script src="${contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script	src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	
	<style type="text/css">
		.userBind {
			display: flex;
			/* margin: auto; */
		}
		.checkBtn-confirm{
			display: none;
		}
		
		.data-dis{
			display: none;
		}
		
		.uploadResult{
			width:100%;
			background-color:#8181F7;
		}
		.uploadResult ul{
			display : flex;
			flex-flow : row;
			justify-content : center;
			align-items : center;
		}
		.uploadResult ul li{
			list-style : none;
			padding 10px;
		}
		.uploadResult ul li img{
			width: 200px;
			hieght: 200px;
		}
		
		.last_li{
			margin-left:auto;
			display: inline;
		}
		
		@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
		
		.font{
			margin:1em auto;
			text-align:center;
			font-family: 'Do Hyeon', sans-serif;
			font-size : 1.5em;
		}
	</style>

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
	
				<!-- Topbar -->
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<ul class="navbar-nav"> <!-- ml-auto -->
					
						<!-- Topbar hompage Icon -->
						<li class="nav-item dropdown no-arrow ">
							<a href="${contextPath}/main/index" >
								<img  src="${contextPath}/resources/img/mainIcon.jpg" height="60" width="120" >
							</a>
						</li>
						
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1">
							<div class="topbar-divider d-none d-lg-block"></div>
						</li>
						
						<!-- Topbar Navbar -->
						<li class="nav-item dropdown no-arrow mx-1">
							<a href="${contextPath}/board/list?colList=0">
								<div class="font" id="notice" >공지사항</div>
							</a>
						</li>
						
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1">
							<div class="topbar-divider d-none d-lg-block"></div>
						</li>
						
						<!-- Topbar Navbar -->
						<li class="nav-item dropdown no-arrow mx-1">
							<a href="${contextPath}/board/list?colList=1">
								<div class="font" id="bookRecom" >책 추천</div>
							</a>
						</li>
						
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1">
							<div class="topbar-divider d-none d-lg-block"></div>
						</li>
						
						<!-- Topbar Navbar -->
						<li class="nav-item dropdown no-arrow mx-1">
							<a href="${contextPath}/board/list?colList=2">
								<div class="font" id="community" >커뮤니티</div>
							</a>
						</li>
						
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1">
							<div class="topbar-divider d-none d-lg-block"></div>
						</li>
						
						<!-- Topbar Navbar -->
						<li class="nav-item dropdown no-arrow mx-1">
							<a href="${contextPath}/board/list?colList=3">
								<div class="font" id="enquiry">문의사항</div>
							</a>
						</li>
						
						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1">
							<div class="topbar-divider d-none d-lg-block"></div>
						</li>
						
					</ul>
						<ul class="navbar-nav ml-auto">
						<c:choose>

							<c:when test="${empty loginId || loginId eq null}"> 
								<li class="nav-item dropdown no-arrow">
								<a class="btn btn-success" href="${contextPath}/main/login"> 로그인
									<!-- <input type="button" class="btn success" id="register" name="login" value="로그인" > -->
								</a></li>
							</c:when>
							<c:otherwise>
							
								<!-- Nav Item - User Information -->
								<li class="nav-item dropdown no-arrow">
									<a class="nav-link dropdown-toggle" href="" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
										<span class="mr-2 d-none d-lg-inline text-gray-600 small" >${loginId}</span>
										<span class="imgfile"></span>
										<%-- <img class="img-profile rounded-circle"	src="${contextPath}/resources/img/undraw_profile.svg"> --%>
									</a>
									<!-- Dropdown - User Information -->
									<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"	aria-labelledby="userDropdown">
										<a class="dropdown-item" href=""> 
											<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 내 정보
										</a> 
										<a class="dropdown-item logout" href="javascript:logout()"> 
											<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 로그아웃
										</a> 
		<!-- 								
										<a class="dropdown-item" href="#"> 
											<i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Activity Log
										</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> 
											<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Logout
										</a> -->
									</div>
								</li>
							
							</c:otherwise>
						
						</c:choose>

						
					</ul>
				</nav>
				<!-- End of Topbar -->
<!-- </div></div></div></body></html> -->

<script src="${contextPath}/resources/javascript/member.js?v=<%=System.currentTimeMillis() %>"></script>
<script>
var loginId='${loginId}';

if(loginId != ''){
	member.getGrade(
		loginId,
		function(data){
			var str ="";
			str = "<img class='img-profile rounded-circle'	src='${contextPath}/resources/img/level_"+data+".jpg' width='10'>"
			$(".imgfile").html("");
			$(".imgfile").html(str);
	});
}

function logout() {
	member.logout(
		function(data){
		alert(data);
		window.location.reload();
	});
	
}



</script>