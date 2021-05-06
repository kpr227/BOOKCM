<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../include/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3 userBind">
			<h6 class="m-0 font-weight-bold text-primary">책추천 게시판</h6>

		</div>
		<div class="card-body">
			<div id="search">
				<form id="searchForm" action="/board/list" method="get">
					<div class="userBind" style="float:right;">
						<select class="form-control col-5" name="type">
							<option value="" ${pageMaker.cri.type == null ? 'selected':''}>-----</option>
							<option value="T" ${pageMaker.cri.type == 'T' ? 'selected':''}>제목</option>
							<option value="W" ${pageMaker.cri.type == 'C' ? 'selected':''}>내용</option>
							<option value="C" ${pageMaker.cri.type == 'W' ? 'selected':''}>작성자</option>
							<option value="TC" ${pageMaker.cri.type == 'TC' ? 'selected':''}>제목 or 내용</option>
							<option value="TWC" ${pageMaker.cri.type == 'TCW' ? 'selected':''}>제목 or 내용 or 작성자</option>
						</select>&nbsp;&nbsp;
						<input type="text" class="form-control col-5" name="keyword" value="${pageMaker.cri.keyword }"/>&nbsp;
						<button class="btn btn-info">검색 </button>
					</div><p style="clear:right;" />
					<div class="hiddenValue">
						<input type="hidden" name="colList" value="${pageMaker.cri.colList}">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
					</div>
				</form>
			</div>
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable"
					style="text-align:center; width:100%; cellspacing:0;">
					<tr>
						<td>번호</td>
						<td>카테고리</td>
						<td width="30%">제목</td>
						<td>작성자</td>
						<td>조회수</td>
						<td>추천수</td>
						<td>작성일</td>
					</tr>
					<c:forEach items="${list}" var="boardVO">
					<tr>
						<td>${ boardVO.bno }</td>
						<td>
							<c:choose>
								<c:when test="${boardVO.colList == '0'}">공지</c:when>
								<c:when test="${boardVO.colList == '1'}">추천</c:when>
								<c:when test="${boardVO.colList == '2'}">일반</c:when>
								<c:when test="${boardVO.colList == '3'}">문의</c:when>
								<c:otherwise>non</c:otherwise>
							</c:choose>
						</td>
						<td><a class='move' href="${boardVO.bno}">${boardVO.title}</a></td>
						<td>${ boardVO.writer }</td>
						<td>${ boardVO.watchNumber }</td>
						<td>${ boardVO.thumbsNumber }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.regDate}" /></td>
					</tr>
					</c:forEach>
				</table>
				
				<!-- paging 처리 시작 -->
				<div class="dataTables_paginate paging_simple_numbers pull-right" id="dataTable_paginate">
				<input type="button" class="btn btn-primary" id="register" name="register" value="글쓰기" >
					<ul class="pagination" style="float:right">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button page-item previous">
								<a href="${ pageMaker.startPage -1 }" aria-controls="dataTable" 
									data-dt-idx="${pageMaker.startPage -1}" tabindex="${pageMaker.startPage -1}" 
										class="page-link" >이전</a>
							</li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
							<li class="paginate_button page-item ${pageMaker.cri.pageNum == num ? 'active' :''} ">
								<a href="${num}" aria-controls="dataTable" data-dt-idx="${num}" tabindex="${num}" class="page-link" >${num}</a>
							</li>
						</c:forEach>
						
						<c:if test="${pageMaker.next}">
							<li class="paginate_button page-item next">
								<a href="${ pageMaker.endPage +1 }" aria-controls="dataTable" 
									data-dt-idx="${pageMaker.endPage +1}" tabindex="${pageMaker.endPage +1}" class="page-link">다음</a>
							</li>
						</c:if>
					</ul>
				</div>
				<!-- paging 처리 끝 -->
				
			</div><!-- ./table-responsive -->
			
			<!-- modal 시작 -->
			<div class="modal" id="myModal"tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Modal title</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      
			      </div>
			      <div class="modal-footer">
			      	<button type="button" class="btn btn-primary close" data-dismiss="modal">닫기</button>
			      </div>
			    </div>
			  </div>
			</div>
			<!-- modal 끝 -->
			
			<!-- 페이징 번호 클릭시 전송되는 pageNum 과 amount의 값 -->
			<form id="actionForm" action="/board/list" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="type" value="${pageMaker.cri.type}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				<input type="hidden" name="colList" value="${pageMaker.cri.colList}">
			</form>
			
		</div><!-- ./card-body -->
	</div><!-- ./card shadow mb-4 -->
</div><!-- ./container-fluid -->

<!-- header.jsp -->
</div></div></div>

<!-- My Account -->
<script src="${contextPath}/resources/javascript/board.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="${contextPath}/resources/javascript/time.js?v=<%=System.currentTimeMillis() %>"></script>
<script>
var result = "${result}";
checkModal(result);

window.addEventListener('popstate',function(event){
	history.pushState(null,null,location.href);
});

/* 뒤로가기 막기 */
$(document).ready(function() {
	$(".sidebar-nav")
		.attr("class","sidebar-nav navbar-collapse collapse")
		.attr("aria-expanded","false")
		.attr("style","height:1px");
		
	/* histoory */
	//history.replaceState({},null,null);
	history.pushState(null,null,location.href);
	
});	//END $(document).ready

/* 게시물 등록시 modal창 생성 */
function checkModal(result){
	
	if(result == ''){
		return;
	}else if(parseInt(result) > 0){
		$(".modal-body").html("");
		$(".modal-body").html("게시물 "+result+"번이 등록되었습니다.");
	}
	
	$("#myModal").show();
}


/* 모달 창 닫기  */
$("#myModal").on("click", ".close", function(){
	$("#myModal").hide();
});

/* 검색 이벤트 */
$("#searchForm button").on("click", function(e){
	if(!$("#searchForm").find("option:selected").val()){
		alert("검색종류를 선택하세요");
		return false;
	}
	
	if(!$("#searchForm").find("input[name='keyword']").val()){
		alert("키워드를 입력하세요");
		return false;
	}
	
	$("#searchForm").find("input[name='pageNum']").val("1");
	e.preventDefault();
	
	searchForm.submit();
	
});

/* 하단 페이징 버튼 클릭 이벤트 */
$(".paginate_button a").on("click",function(e){
	e.preventDefault();
	$("#actionForm").find("input[name='pageNum']").val($(this).attr("href"));
	$("#actionForm").submit();
});

/* 글쓰기 이벤트 */
$("#register").on("click",function(e){
	$("#actionForm").attr("action","/board/register");
	$("#actionForm").submit();
});


/* 게시물 눌렀을 때 이벤트 */
//$(".boardChat").on("click",".move",function(e){
$(".move").on("click",function(e){
	alert("성공");
	e.preventDefault();
	//alert("페이징 번호 클릭함")
	$("#actionForm").append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
	$("#actionForm").attr("action","/board/get");
	$("#actionForm").submit();
});

/*
function boardChat(data){
	var str ="";
	var colList="";
	
	for(var i=0; i<data.boardVO.length; i++){
		if(data.boardVO[i].colList == 0){colList="공지사항";}
		else if(data.boardVO[i].colList == 1){colList="추천";}
		else if(data.boardVO[i].colList == 2){colList="나눔";}
		else if(data.boardVO[i].colList == 3){colList="일반";}
		else if(data.boardVO[i].colList == 4){colList="문의";}
		else{colList="error";}
		
		str	+="<tr>" 
			 +	"<td>"+data.boardVO[i].bno+"</td>"
			 +	"<td>"+colList+"</td>"
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
*/
</script>
</body>
</html>