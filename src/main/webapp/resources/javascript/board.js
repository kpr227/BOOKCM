//console.log("board.js실행");

var board = (function(){
	
	// list 가져오기
	function getList(page, callback, error){	
		$.ajax({
			type:"get",
			url:"/board/list",
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				console.log(data);
				//alert(data);
				callback(data);
				//alert(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function getList
	
	
	// board 수정
	function modBoard(boardVO, callback, error){	
		$.ajax({
			type:"post",
			url:"/board/modify",
			data:JSON.stringify(boardVO),	//자바스크립트 객체를 JSON형식의 문자열로 반환한다.
			contentType:"application/json; charset=UTF-8",
			
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(xhr,status,err){
				console.log(err);
					console.log("modBoard ERROR");
			}
		});	
	}	//end function getList
	
	// board 추천
	function upThumbs(bno, callback, error){	
		$.ajax({
			type:"post",
			url:"/board/upThumbs",
			data:JSON.stringify(bno),	//자바스크립트 객체를 JSON형식의 문자열로 반환한다.
			contentType:"application/json; charset=UTF-8",
			
			success:function(data){
				callback(data);
			},
			
			error:function(xhr,status,err){
				console.log("upThumbs ERROR");
			}
		});	
	}	//end function upThumbs
	
	return{
		getList:getList,
		modBoard:modBoard,
		upThumbs:upThumbs,
	};
})();