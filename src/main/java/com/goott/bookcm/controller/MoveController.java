package com.goott.bookcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

//import lombok.extern.log4j.Log4j;


@Controller
//@Log4j
public class MoveController {
	
	@Autowired
	private BoardService boardService;
	
	//메인 홈페이지 이동
	@RequestMapping(value="/main/index")
	public void mainMove() {
		
	}
	
	//로그인 페이지 이동
	@RequestMapping(value="/main/login")
	public void mainLogin(HttpSession session, HttpServletRequest request) {
		previousURL(session, request);
	}
	
	//가입 페이지 이동
	@RequestMapping(value="/main/register")
	public void mainRegister() {

	}
	
	//전체 게시물 불러오기
	@GetMapping(value="/board/list")
	public void listMove(Criteria cri, Model model, String colList ) {
		cri.setColList(colList);
		
		//전체 게시물 or 검색한 갯수 출력
		int total = boardService.getTotalBoard_sc(cri);

		//값 출력
		List<BoardVO> list = boardService.getList_psc(cri);
		//log.info("list: "+boardService.getList_psc(cri));
		
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
	
	//게시물 등록 페이지
	@RequestMapping(value="/board/register")
	public String registerMove(Model model) {
	
		return "/board/register";
	}
	
	//게시물 등록하기
	@RequestMapping(value="/board/registers")
	public String register(@ModelAttribute("boardVO")BoardVO boardVO, RedirectAttributes rttr) {
		System.out.println("---boardVO값: "+boardVO);
		
		//생성
		boardService.createBoard(boardVO);
		
		rttr.addFlashAttribute("result", boardVO.getBno());
		System.out.println("---result값: "+ boardVO.getBno());
		
		return "redirect:/board/list";
	}
	
	
	//이전페이지 함수
	public void previousURL(HttpSession session, HttpServletRequest request) {
		//session.removeAttribute("previous");
		
		//System.out.println("already_previous: "+already_previous); 
		
		//이전url
		String previous = request.getHeader("referer");
		System.out.println("previous: "+previous);
		if(previous==null ) {
			System.out.println("성공");
			session.setAttribute("previous", "http://localhost/main/index");
			return;
		}
		
		String previous_last = previous.substring(previous.lastIndexOf("/")+1);
	     
		//확인
		System.out.println("----------------이전페이지 기록 시작----------------"); 
		System.out.println("previous: "+previous); 
		System.out.println("previous_last: "+previous_last);

		
		if(previous_last.equals("login") || previous_last.equals("register")) {
			if ( session == null || session.getAttribute("previous") == null )
			{
				System.out.println("홈페이지로 이동한다.");
				session.setAttribute("previous", "http://localhost/main/index");
			}
			else
			{
				System.out.println("이전페이지 login / register 전에있던 페이지로 가져온다.");
				String already_previous =(String) session.getAttribute("previous");
				System.out.println("already_previous : "+already_previous);
			}
		}else {
			session.setAttribute("previous", request.getHeader("referer"));
			
			String previous_session =(String) session.getAttribute("previous");
			System.out.println("previous_session : "+previous_session);
		}
		
		System.out.println("----------------이전페이지 기록 끝----------------"); 
	}
	
}
