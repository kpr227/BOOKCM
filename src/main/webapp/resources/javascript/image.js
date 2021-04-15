//console.log("board.js실행");

var image = (function(){
	
	// list 가져오기
	function regImage(formData, callback, error){	
		$.ajax({
			url:'/uploadAjaxAction',
			processData:false,
			contentType:false,
			data:formData,
			type:'post',
			dataType:'json',
			
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(xhr,status,err){
				console.log(err);
					console.log("modBoard ERROR");
			}
			
			
		});
	}
	
	// 이미지 삭제
	function delImage(targetFile, type, callback, error){	
		$.ajax({
			url:'/deleteFile',
			data:{fileName:targetFile, type:type},
			dataType:'text',
			type:'POST',
			
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(xhr,status,err){
				console.log(err);
					console.log("modBoard ERROR");
			}
			
			
		});
	}
	
	// 특정게시물의 이미지 가져오기
	function getImage(bno, callback, error){	
		$.ajax({
			url:'/board/findImageBno',
			data:JSON.stringify(bno),	//자바스크립트 객체를 JSON형식의 문자열로 반환한다.
			contentType:"application/json; charset=UTF-8",
			type:'POST',
			
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(xhr,status,err){
				console.log(err);
					console.log("getImage ERROR");
			}
			
			
		});
	}
	
	
	
	return{
		regImage:regImage,
		delImage:delImage,
		getImage:getImage,
	};
	
	
	
})();