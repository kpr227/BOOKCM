package com.goott.bookcm.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.domain.MemberVO;
import com.goott.bookcm.service.BoardService;
import com.goott.bookcm.service.MemberService;

import lombok.extern.log4j.Log4j;


@Log4j
@RestController
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/test1")
	public void main() {
		MemberVO memVO = new MemberVO();
		memVO = memberService.test();
		System.out.println("memverVO:" +memVO);
	}
	
	
	@GetMapping(value="/list",
		consumes = {"application/json; charset=UTF-8"}, 
		produces = {"application/json; charset=utf-8"})
		//produces = {"text/plain; charset=utf-8"})
	public ResponseEntity<Map<String,Object>> getList(){
		log.info("------BoardController");
		
		// -- test
		//BoardVO boardVO = new BoardVO();
		List<BoardVO> boardList= new ArrayList<BoardVO>(); 
		boardList=boardService.getList();
		System.out.println("--boardVO: "+boardList);
		
		//--- Map mapping
		Map<String,Object> ListMap = new HashMap<String,Object>();
		ListMap.put("boardVO", boardService.getList());
		
		return new ResponseEntity<>(ListMap,HttpStatus.OK);	
	}
	
}
