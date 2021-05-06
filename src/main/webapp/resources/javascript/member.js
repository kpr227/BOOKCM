var member = (function(){
	
	// 아이디가 존재하는가? 
	function existId(id, callback, error){	
		$.ajax({
			type:"get",
			url:"/member/existId/"+id,
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				//console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function exigst
	
	
	//닉네임이 존재하는가?
	function existNick(nickName, callback, error){
		
		$.ajax({
			type:"get",
			url:"/member/existNick/"+nickName,
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				//console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function nickName
	
	//회원가입
	function regMember(memberVO, callback, error){
		
		$.ajax({
			type:"post",
			url:"/member/regMember",
			data:JSON.stringify(memberVO),
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function regMember
	
	//로그인
	function checkLogin(memberVO, callback,error){
		$.ajax({
			type:"post",
			url:"/member/checkLogin",
			data:JSON.stringify(memberVO),
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function login

	//레벨 알기
	function getGrade(loginId, callback,error){
		$.ajax({
			type:"post",
			url:"/member/getGrade",
			data:JSON.stringify(loginId),
			contentType:"application/json; charset=UTF-8",
					
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function login
	
	//로그아웃
	function logout(callback,error){
		$.ajax({
			type:"post",
			url:"/member/logout",				
			success:function(data){
				console.log(data);
				callback(data);
			},
			
			error:function(request,status,error){
       		}

		});	
	}	//end function login
	
	return{
		existId:existId,
		existNick:existNick,
		regMember:regMember,
		checkLogin:checkLogin,
		getGrade:getGrade,
		logout:logout,
		
	};
})()