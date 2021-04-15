package com.goott.bookcm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goott.bookcm.common.Criteria;
import com.goott.bookcm.common.PageDTO;
import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.service.BoardService;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class MoveController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/main/index")
	public void mainMove() {
		
	}
	
	//전체 게시물 불러오기
	@GetMapping(value="/board/list")
	public void listMove(Criteria cri, Model model) {
		//전체 게시물 or 검색한 갯수 출력
		int total = boardService.getTotalBoard_s(cri);
		//값 출력
		List<BoardVO> list = boardService.getList_ps(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	//특정 게시물 불러오기
	@GetMapping(value="/board/get")
	public String getMove(@RequestParam Long bno, @ModelAttribute("cri")Criteria cri, Model model) {	
		//실행
		model.addAttribute("boardVO",boardService.getBoard(bno));
		return "/board/get";
	}
	
	@RequestMapping(value="/board/register")
	public String registerMove(Model model) {
	
		return "/board/register";
	}
	
	@RequestMapping(value="/board/registers")
	public String register(@ModelAttribute("boardVO")BoardVO boardVO, RedirectAttributes rttr) {
		System.out.println("---boardVO값: "+boardVO);
		
		//파일 존재 여부
		//if(boardVO.getImageList() != null) {
		//	boardVO.getImageList().forEach(image -> log.info(image));
		//}
		
		//생성
		boardService.createBoard(boardVO);
		
		//시퀀스 찾기
		
		//int result = boardService.searchNexSeq();
		rttr.addFlashAttribute("result", boardVO.getBno());
		System.out.println("---result값: "+ boardVO.getBno());
		
		return "redirect:/board/list";
	}
	
	
}
