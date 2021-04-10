package com.goott.bookcm.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/* 전제 게시물 출력 */
	@GetMapping(value="/list",
		consumes = {"application/json; charset=UTF-8"}, 
		produces = {"application/json; charset=utf-8"})
		//produces = {"text/plain; charset=utf-8"})
	public ResponseEntity<Map<String,Object>> getList(){
		log.info("------getList Controller");
		
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
	
	@PostMapping(value="/modify",
		consumes = {"application/json; charset=UTF-8"}, 
		produces = {"text/plain; charset=utf-8"})
	public ResponseEntity<String> modBoard(@RequestBody BoardVO boardVO){
		log.info("------modBoard Controller");
		
		// -- test
		System.out.println("boardVO: "+boardVO);

		return boardService.modBoard(boardVO)==1
				? new ResponseEntity<>("modify-success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/* 특정 게시물 추천 */
	@PostMapping(value="/upThumbs",
		consumes = {"application/json; charset=UTF-8"}, 
		produces = {"application/json; charset=UTF-8"})
	public ResponseEntity<Object> upThumbs(@RequestBody Long bno){
		log.info("------upThumbs Controller");
		// -- test
		System.out.println("bno: "+bno);

		//--- Map mapping
		int result = boardService.upThumbs(bno);
		System.out.println("-----게시물의 추천수: "+result);
		return result == 0
				? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
}
