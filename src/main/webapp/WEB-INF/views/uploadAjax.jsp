<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

<style>
	.uploadResult{
		width:100%;
		background-color:gray;
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

</style>


</head>
<body>

<h1> Ajax 실행 </h1>
<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
	<input type="button" id="uploadBtn" value="입력" />
</div>
<div class="uploadResult">

</div>


<input type="button" id="test" value="입력" />

<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
var maxSize = 5242880;
var clonObj = $(".uploadDiv").clone();
var uploadResult = $(".uploadResult");

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

/*  */
function showImage(fileCallpath){
	//$("#myModal").show();
}

/* 이미지 출력 */
function showUploadFile(uploadResultArr){
	var str = "<ul>";
	
	$(uploadResultArr).each(function(i,obj){
		//alert(obj.image);
		var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
		var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
		if(!obj.image){
			str += "<li>"
				+  "	<a href='/download?fileName="+fileCallPath+"'>"
				+  "		<img src='/resources/img/file.png'>"
				+  "	</a><br/>"+ obj.fileName
				+  "<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"
				+"</li>";
		}else{
			//str += "<li>"+ obj.fileName +"&nbsp;&nbsp; </li>";
			var fileCallPath_s = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
			
			var originPath=obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			str +="<li>"
				+ "	<a href=\"javascript:showImage(\'"+originPath+"\')\">"
				+ "		<img src='/display?fileName="+fileCallPath_s+"'>"
				+ "	</a> &nbsp;&nbsp;<br/>"+obj.fileName +"&nbsp;&nbsp;"
				+  "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"
				+ "</li>";
			
		}
	});
	str += "</ul>"
	uploadResult.append(str);
}

/* x버튼 이벤트 */
$(".uploadResult").on("click", "span", function(e){
	alert("삭제하시겠습니까?");
	var targetFile = $(this).data("file");
	var type= $(this).data("type");
	var type= $(this).data("type");
	console.log(targetFile);
	console.log(type);
	
	$.ajax({
		url:'/deleteFile',
		data:{fileName:targetFile, type:type},
		dataType:'text',
		type:'POST',
		success:function(result){
			alert(result);
		}
		
	});
	
});


/*  */
$("#uploadBtn").on("click", function(e){
	alert("성공?");
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
	
	$.ajax({
		url:'/uploadAjaxAction',
		processData:false,
		contentType:false,
		data:formData,
		type:'post',
		dataType:'json',
		success:function(result){
			
			console.log(result);
			
			showUploadFile(result);
			
			$(".uploadDiv").html(clonObj.html());
			
		}
	});
});




</script>

</body>
</html>