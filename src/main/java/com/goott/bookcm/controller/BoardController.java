package com.goott.bookcm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goott.bookcm.domain.MemberVO;
import com.goott.bookcm.service.MemberService;


@RestController
@RequestMapping(value="/board/*")
public class BoardController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/test")
	public void main() {
		MemberVO memVO = new MemberVO();
		memVO = memberService.test();
		System.out.println("memverVO:" +memVO);
	}
	
	
}
