//console.log("board.js실행");

var board = (function(){
	
	/* list 가져오기 */
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

		});	//end ajax
	}	//end function getCustLogin

	
	
	
	return{
		getList:getList,
	};
})();