<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../include/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">책추천 게시판</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable"
					text-align="center" width="100%" cellspacing="0">
					<tr>
						<td>번호</td>
						<td>카테고리</td>
						<td width="30%">제목</td>
						<td>작성자</td>
						<td>조회수</td>
						<td>추천수</td>
						<td>작성일</td>
					</tr>
					<tbody class="boardChat">
					</tbody>
				</table>
			</div><!-- ./table-responsive -->
			
			<!-- 페이징 번호 클릭시 전송되는 pageNum 과 amount의 값 -->
			<form id="actionForm" action="/board/list" method="get">
			</form>
			
		</div><!-- ./card-body -->
	</div><!-- ./card shadow mb-4 -->
</div><!-- ./container-fluid -->
<!-- My Account -->
<script src="${contextPath}/resources/javascript/board.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="${contextPath}/resources/javascript/time.js?v=<%=System.currentTimeMillis() %>"></script>
<script>
/* 페이지 이동시 자동 list 이동 */
$(document).ready(function() {
	var page=1;
	board.getList(
		page,
		function(data){
			boardChat(data);
		}
	);
});


/* 게시물 눌렀을 때 이벤트 */
$(".boardChat").on("click",".move",function(e){
	alert("성공");
	e.preventDefault();
	//alert("페이징 번호 클릭함")
	$("#actionForm").append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
	$("#actionForm").attr("action","/board/get");
	$("#actionForm").submit();
});


function boardChat(data){
	var str ="";
	var colList="";
	
	for(var i=0; i<data.boardVO.length; i++){
		if(data.boardVO[i].colList == 1){colList="공지사항";}
		else if(data.boardVO[i].colList == 2){colList="추천";}
		else if(data.boardVO[i].colList == 3){colList="나눔";}
		else if(data.boardVO[i].colList == 4){colList="일반";}
		else if(data.boardVO[i].colList == 5){colList="문의";}
		else{colList="error";}
		
		str	+="<tr>" 
			 +	"<td>"+data.boardVO[i].bno+"</td>"
			 +	"<td>"+colList+"</td>"
/* 			 +	"<td>"+data.boardVO[i].title+"</td>" */
			 +	"<td><a class='move' href="+data.boardVO[i].bno+">"+data.boardVO[i].title+"</a></td>"
			 
			 +	"<td>"+data.boardVO[i].writer+"</td>"
			 +	"<td>"+data.boardVO[i].watchNumber+"</td>"
			 +	"<td>"+data.boardVO[i].thumbsNumber+"</td>"
			 +	"<td>"+time.displayTime(data.boardVO[i].regDate)+"</td>"
			 +"</tr>";
			 
			 
	}
	//alert(str);
	$(".boardChat").html("");
	$(".boardChat").html(str);
	
}
</script>
</body>
</html>