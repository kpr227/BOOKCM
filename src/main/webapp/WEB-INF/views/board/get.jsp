<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../include/header.jsp" %>

<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">상세보기</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<div class="form-group">
					<div class="userBind">
						<div class="col-3">글 번호</div>
						<div class="col-2 offset-md-1">카테고리</div>
						<div class="col-2 offset-md-1">작성자</div>
						<div class="col-1 offset-md-1">추천수</div>
						<div class="col-1">조회수</div>
					</div>
					<div class="userBind">
						<input class="data-non form-control col-3" id="bno" name="bno" value="${boardVO.bno}" disabled>
						
						<select class="data-mod form-control col-2 offset-md-1" id="colList" name="colList" disabled>
							<option value="1" <c:if test="${boardVO.colList == '1'}">selected</c:if> >공지사항</option>
							<option value="2" <c:if test="${boardVO.colList == '2'}">selected</c:if> >책 추천</option>
							<option value="3" <c:if test="${boardVO.colList == '3'}">selected</c:if> >일반</option>
							<option value="4" <c:if test="${boardVO.colList == '4'}">selected</c:if> >문의사항</option>
						</select>
						<input type="hidden" id="colList_origin" name="colList_origin" value="${boardVO.colList}">
						<input class="data-non form-control col-2 offset-md-1" id="writer" name="writer" value="${boardVO.writer}" disabled>  
						<input class="data-non form-control col-1 offset-md-1" id="thumbsNumber" name="thumbsNumber" value="${boardVO.thumbsNumber}" disabled> 
						<input class="data-non form-control col-1" id="watchNumber" name="watchNumber" value="${boardVO.watchNumber}" disabled> 
					</div>	
					<%-- <input type="hidden" id="title" name="title" value="${board.title}"> --%>
				</div>
				<div class="form-group">
					<label>글 제목</label> 
					<input class="data-mod form-control" id="title"  name="title" value="${boardVO.title}" disabled>
					<input type="hidden" id="title_origin"  name="title_origin" value="${boardVO.title}" disabled>
				</div>
				<div class="form-group">
					<label>글 내용</label> 
					<textarea class="data-mod form-control" rows="5" id="content_" name="content_" disabled>${boardVO.content}</textarea>
					<input type="hidden" id="content_origin" name="content_origin" value="${boardVO.content}"><p/>
					<div style="margin:auto; text-align:center;">
						<img src="${contextPath}/resources/img/nonThumbs.jpg" id="thumbs" alt="recommend" width="10%" height="10%">
					</div>
					
				</div><hr/>
				<div class="form-group">
					<div class="userBind">
						<div class="col-6">작성 날짜</div>
						<c:if test="${boardVO.regDate != boardVO.editDate}">
							<div class="col-6">수정 날짜</div>
						</c:if>
						
					</div>
					<div class="userBind">
						<input class="data-non form-control col-5" id="regDate" name="regDate" value="${boardVO.regDate}" disabled>
						<c:if test="${boardVO.regDate != boardVO.editDate}"> 
							<input class="data-non form-control col-6 offset-md-1" id="editDate" name="editDate" value="${boardVO.editDate}" disabled>
						</c:if> 
					</div>
				</div><hr/>
				<div class="checkBtn-select"><!-- 버튼 -->
					<input type="button" id="btn_modify" class="btn btn-info" value="수정"> 
					<input type="button" id="btn_list" class="btn btn-secondary" value="목록"> 
				</div>
				<div class="checkBtn-confirm"><!-- 버튼 -->
					<input type="button" id="btn_confirm" class="btn btn-success" value="확인"> 
					<input type="button" id="btn_cancel" class="btn btn-warning" value="취소"> 
					<input type="button" id="btn_delete" class="btn btn-danger" value="삭제"> 
				</div>
			</div>
		</div>
		<!-- 모달창 -->
		<!-- openForm -->
		<form id="getForm" action="" method="post">
		</form>
	</div>
</div>


<a class="scroll-to-top rounded" href="#page-top"> <i
	class="fas fa-angle-up"></i>
</a>
<script src="${contextPath}/resources/javascript/board.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="${contextPath}/resources/javascript/time.js?v=<%=System.currentTimeMillis() %>"></script>
<script>

//bno
var bno_value = ${boardVO.bno};
//form
var getForm = $("#getForm");

//수정버튼 선택
$("#btn_modify").on("click", function(){
	$(".checkBtn-select").css("display","none");
	$(".checkBtn-confirm").css("display","block");
	
	$(".data-mod").attr("disabled",false);
});

//확인버튼 선택
$("#btn_confirm").on("click", function(){	

	
	var boardVO = {
		bno : bno_value,
		title : $("#title").val(),
		content : $("#content_").val(),
		colList : $("#colList").val(),
	}
	
	//ajax사용
	board.modBoard(
		boardVO,
		function(data){
			alert("수정이 완료되었습니다.");
			//수정된 값 넣기
			$("#colList_origin").val($("#colList").val());
			$("#title_origin").val($("#title").val());
			$("#content_origin").val($("#content_").val());
		}
	);
	
	//버튼 
	$(".checkBtn-select").css("display","block");
	$(".checkBtn-confirm").css("display","none");
	
	//input: disabled -변경
	$(".data-mod").attr("disabled",true);
});

//취소버튼 확인 
$("#btn_cancel").on("click", function(){
	//origin값 넣기
	$("#colList").val($("#colList_origin").val());
	$("#title").val($("#title_origin").val());
	$("#content_").val($("#content_origin").val());
	
	//버튼
	$(".checkBtn-select").css("display","block");
	$(".checkBtn-confirm").css("display","none");
	
	//input: disabled -변경
	$(".data-mod").attr("disabled",true);
});

//목록 이동
$("#btn_list").on("click", function(){
	getForm.attr("action","/board/list").submit();
});

//추천 
$("#thumbs").on("click", function(){
	var confirmflag = confirm("추천하시겠습니까?");
	if (confirmflag) {
		//ajax사용
		board.upThumbs(bno_value, function(data) {
			$("#thumbsNumber").val(data);
			alert("추천 되었습니다.");
		});
		
	} else {}
		
});
</script>

</body>
</html>