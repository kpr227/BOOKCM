<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../include/header.jsp" %>

<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">책추천 게시판</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<div class="form-group">
					<div class="userBind">
						<div class="col-4">글 번호</div>
						<div class="col-4 offset-md-1">작성자</div>
						<div class="col-1 offset-md-1">추천수</div>
						<div class="col-1">조회수</div>
					</div>
					<div class="userBind">
						<input class="form-control col-4" name="title" value="${board.title}" disabled>
						<input class="form-control col-4 offset-md-1" name="writer" value="${board.content}" disabled>  
						<input class="form-control col-1 offset-md-1" name="title" value="${board.title}" disabled> 
						<input class="form-control col-1" name="title" value="${board.title}" disabled> 
					</div>	
					<%-- <input type="hidden" id="title" name="title" value="${board.title}"> --%>
				</div>
				<div class="form-group">
					<label>글 제목</label> 
					<input class="form-control" name="title" value="${board.title}" disabled> 
				</div>
				<div class="form-group">
					<label>글 내용</label> 
					<input class="form-control" name="content" value="${board.content}" disabled> 
				</div>
				<div class="form-group">
					<label>작성자</label> 
					<input class="form-control" name="writer" value="${board.content}" disabled> 
				</div><hr/>
				<div class="form-group">
					<div class="userBind">
						<div class="col-6">작성 날짜</div>
						<div class="col-6">수정 날짜</div>
					</div>
					<div class="userBind">
						<input class="form-control col-5" name="content" value="${board.content}" disabled> 
						<input class="form-control col-6 offset-md-1" name="content" value="${board.content}" disabled> 
					</div>
				</div>
					
			</div>
		</div>
	</div>
</div>


<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>


</body>
</html>