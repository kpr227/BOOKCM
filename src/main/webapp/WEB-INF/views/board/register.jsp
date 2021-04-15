<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3 userBind">
			<h6 class="m-0 font-weight-bold text-primary">게시물 작성</h6>
		</div><!-- ./m-0 -->
		<div class="card-body">
			<div class="table-responsive">
				<form role="form" id="registerForm" action="/board/registers" method="post">
					<div class="userBind" style="text-align:center; float:center">
						카테고리 선택:  &nbsp;
						<select id="colList" name="colList" class="form-control col-4">
							<option value="0">공지사항</option>
							<option value="1">책 추천</option>
							<option value="2">일반게시판</option>
							<option value="3">문의사항</option>
						</select>
						<hr/>
					</div>
					<div class="registerChat">
					
					</div>
					<div class="ImgaeChat">
						<div class="form-group">
	    					<label for="exampleFormControlFile1">파일 첨부</label>
	    					<input type="file" class="form-control-file" id="uploadFile" name="uploadFile" multiple>
	  					</div>
	  					
	  					<div class="form-group uploadResult">

						</div>

					</div>
					<div class="checkBtn-confirm">
						<input type="button" class="btn btn-success" id="register_btn" value="확인">
						<input type="button" class="btn btn-secondary" id="cancel_btn" value="취소">
					</div>
				</form>
			</div>
		</div>
	</div><!-- ./card -->
</div><!-- ./container-fluid -->

<!-- header.jsp -->
</div></div></div>

<script src="${contextPath}/resources/javascript/board.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="${contextPath}/resources/javascript/image.js?v=<%=System.currentTimeMillis() %>"></script>
<script>

var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
var maxSize = 5242880;
var clonObj = $(".uploadDiv").clone();
var uploadResult = $(".uploadResult");

/* 카테고리 선택 */
$("#colList").on("change", function(){
	var result = $("#colList").val();
	alert(result);
	if(result == "0"){
		
	}else if(result == "1"){
		
	}else if(result == "2"){
		general();
		$(".checkBtn-confirm").css("display","block");
	}else if(result == "3"){
		
	}
});

/* 일반 게시판  */
function general(){
	var str="";
	
	str +="<div class='form-group'>"
		+ "	<label>글 제목</label> "
		+ "	<input class='form-control' id='title'  name='title' >"
		+ "</div>"
		+ "<div class='form-group'>"
		+ "	<label>글 내용</label> "
		+ "	<textarea class='form-control' rows='7' id='content' name='content'></textarea>"
		+ "</div><hr/>"
		+ "<div class='form-group'>"
		+ "	<label>작성자</label> "
		+ "	<input class='form-control' id='writer'  name='writer' value='member0' readOnly>"
		+ "</div> <hr/>";
	
	$(".registerChat").html("");
	$(".registerChat").html(str);
}

/* 확인 버튼 */
$("#register_btn").on("click", function(e){
	e.preventDefault();	
	
	var formObj = $("form[role='form']");
	var str = "";
	
	$(".uploadResult ul li").each(function(i, obj){
		
		
		var jobj = $(obj);
		var typeName = "";
		
		if(jobj.data("type") == true){
			typeName = "image"
		}else{
			typeName = "file"
		}
		
		//alert(typeName);
		
		str +="<input type='hidden' name='imageList["+i+"].fileName'	value='"+jobj.data("filename")+"'>"
			+ "<input type='hidden' name='imageList["+i+"].uuid' 		value='"+jobj.data("uuid")+"'>"
			+ "<input type='hidden' name='imageList["+i+"].uploadPath'	value='"+jobj.data("path")+"'>"
			+ "<input type='hidden' name='imageList["+i+"].fileType'	value='"+typeName+"	'>";

	});

	formObj.append(str).submit();
	
	$("#registerForm").submit();
});

/* 파일 선택 리스트 */
function checkExtension(fileName, fileSize){
	if(fileSize >= maxSize){
		alert("파일 사이즈 초과");
		return false;
	}
	
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 업슷비다.");
		return false;
	}
	return true;
}

/* 파일 변경 이벤트  */
$("input[type='file']").change(function(e){
	var formData = new FormData();
	
	var inputFile = $("input[name='uploadFile']");
	
	var files = inputFile[0].files;
	
	console.log(files);
	
	for(var i=0; i<files.length; i++){
		if(!checkExtension(files[i].name, files[i].size)){
			return false;
		}
		
		formData.append("uploadFile", files[i]);
	}
	
	image.regImage(
		formData, 		
		function(result){		
			showUploadFile(result);
			$(".uploadDiv").html(clonObj.html());
		}
	);
	
});

/* 썸네일 올리기 */
function showUploadFile(uploadResultArr){
	if(!uploadResultArr || uploadResultArr.length == 0) {return};
	
	
	alert("성공");
	var uploadResult = $(".uploadResult");
	
	var str = "<ul>";
	
	$(uploadResultArr).each(function(i,obj){
		var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
		var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
		
		if(!obj.image){
			str +="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'"
				+ "		data-fileName='"+obj.fileName+"' data-type='"+obj.image+"'>"
				+ "	<img src='/resources/img/file.png'><br/>"+ obj.fileName
				+ "	<button type='button' class='btn btn-danger btn-circle' "
				+ "			data-file=\'"+fileCallPath+"\' data-type='file'>"
				+ "		<i class='fa fa-times'></i>"
				+ "	</button>"
				+"</li>";
		}else{
			//str += "<li>"+ obj.fileName +"&nbsp;&nbsp; </li>";
			var fileCallPath_s = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
			
			var originPath=obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			str +="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'"
				+ "		data-fileName='"+obj.fileName+"' data-type='"+obj.image+"'>"
				+ "	<img src='/display?fileName="+fileCallPath_s+"'><br/>"+obj.fileName +"&nbsp;&nbsp;"
				+ "	<button type='button' class='btn btn-danger btn-circle'"
				+ "			data-file=\'"+fileCallPath+"\' data-type='image'>"
				+ "		<i class='fa fa-times'></i>"
				+ "	</button>"
				+ "</li>";
			
		}
	});
	str += "</ul>"
	uploadResult.append(str);
}


/* x버튼 이벤트 */
$(".uploadResult").on("click", "button", function(e){
	alert("삭제하시겠습니까?");
	var targetFile = $(this).data("file");
	var type= $(this).data("type");
	var targetLi= $(this).closest("li");

	//console.log(targetFile);
	//console.log(type);
	console.log(targetLi);
	
	image.delImage(
		targetFile,
		type,
		function(result){
			alert(result);
			alert(targetLi);
			targetLi.remove();
		}	
	);
	
});


</script>
</body>
</html> 