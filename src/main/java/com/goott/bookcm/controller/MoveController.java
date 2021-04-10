package com.goott.bookcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goott.bookcm.service.BoardService;


@Controller
public class MoveController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/main/index")
	public void mainMove() {
		
	}
	
	@RequestMapping(value="/board/list")
	public void listMove(Model model) {

	}
	
	@RequestMapping(value="/board/test")
	public String testMove() {
		return "redirect:/board/list/";
	}
	
	@RequestMapping(value="/board/get")
	public String getMove(@RequestParam Long bno, Model model) {
		//test
		//BoardVO bordVO = new BoardVO();
		//bordVO = boardService.getBoard(bno);
		//System.out.println("boarVO란?: "+bordVO);
		
		//실행
		model.addAttribute("boardVO",boardService.getBoard(bno));
		return "/board/get";
	}
}
