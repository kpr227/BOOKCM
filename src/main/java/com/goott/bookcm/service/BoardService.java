package com.goott.bookcm.service;

import java.util.List;

import com.goott.bookcm.domain.BoardVO;

public interface BoardService {
	// 전체 게시물 읽어오기
	public List<BoardVO> getList();
	
	//게시물 생성
	public int createBoard(BoardVO boardVO);
	
	//개별 게시물 읽기
	public BoardVO getBoard(Long bno);
	
	//게시물 수정
	public int modBoard(BoardVO boardVO);
	
	//게시물 삭제
	public int delBoard(Long bno);
		
	
}
