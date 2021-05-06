package com.goott.bookcm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goott.bookcm.domain.MemberVO;
import com.goott.bookcm.service.MemberService;

@RestController
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//아이디 존재여부
	@GetMapping(value = "/existId/{id}", 
		consumes = {"application/json; charset=UTF-8" }, // 수신 하고자하는 데이터 포맷을 정의한다.
		produces = {"text/plain; charset=utf-8"})	// 출력하고자 하는 데이터 포맷을 정의한다.
	public ResponseEntity<String> existId (@PathVariable String id) {
		//System.out.println("------existId: "+id);
		
		String existId_value = memberService.existId(id);
		String msg;
		
		if(existId_value.equals("TRUE")){
			msg ="아이디가 존재합니다.";
		}else {
			msg ="success";
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	//닉네임 존재 여부
	@GetMapping(value = "/existNick/{nickName}", 
			consumes = {"application/json; charset=UTF-8" }, // 수신 하고자하는 데이터 포맷을 정의한다.
			produces = {"text/plain; charset=utf-8"})	// 출력하고자 하는 데이터 포맷을 정의한다.
		public ResponseEntity<String> existNick (@PathVariable String nickName) {
			//System.out.println("------existId: "+id);
			
			String existNick_value = memberService.existNick(nickName);
			String msg;
			
			if(existNick_value.equals("TRUE")){
				msg ="닉네임이 존재합니다.";
			}else {
				msg ="success";
			}
			
			return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	//로그인 체크
	@PostMapping(value = "/checkLogin", 
		consumes = {"application/json; charset=UTF-8"}, // 수신 하고자하는 데이터 포맷을 정의한다.
		produces = {"text/plain; charset=utf-8"})	// 출력하고자 하는 데이터 포맷을 정의한다.
	public ResponseEntity<String> checkLogin(HttpSession session, @RequestBody MemberVO memberVO) {
		
		System.out.println("------checkLogin: "+memberVO);
		
		int checkLogin_value = memberService.checkLogin(session, memberVO);
		String msg;
		
		if(checkLogin_value==0){
			msg ="아이디 혹은 비밀번호가 존재하지 않습니다.";
		}else {
			msg ="success";
		}
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	//회원가입
	@PostMapping(value = "/regMember", 
		consumes = { "application/json; charset=UTF-8" }, // 수신 하고자하는 데이터 포맷을 정의한다.
		produces = { "application/json; charset=utf-8" }) // 출력하고자 하는 데이터 포맷을 정의한다.
	public ResponseEntity<Integer> regMember(@RequestBody MemberVO memberVO) {
		
		System.out.println("------regMember: "+memberVO);
	 
		return memberService.regMember(memberVO) == 1
				? new ResponseEntity<>(1, HttpStatus.OK)
				: new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	//회원정보 등급 가져오기
	@PostMapping(value = "/getGrade", 
		produces = { "application/json; charset=utf-8" }) // 출력하고자 하는 데이터 포맷을 정의한다.
	public ResponseEntity<String> getGrade(@RequestBody String loginId) {
		
		//System.out.println("------getGrade: "+loginId);
		//System.out.println("level: "+memberService.getGrade(loginId));
		
		
		return new ResponseEntity<>(memberService.getGrade(loginId), HttpStatus.OK);
				
	}
	
	//로그아웃
	@PostMapping(value = "/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("loginId");				
	}

}
