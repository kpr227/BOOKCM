package com.goott.bookcm.controller;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goott.bookcm.service.BoardService;

@Controller
public class MoveController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/main/index")
	public void mainMove() {
		
	}
	
	@RequestMapping(value="/board/list")
	public void listMove() {
		
	}
	
	@RequestMapping(value="/board/test")
	public String testMove() {
		return "redirect:/board/list/";
	}
	
	@RequestMapping(value="/board/get")
	public String getMove(Long bno, Model model) {
		boardService.getBoard(bno);
		return "/board/get";
	}
}
