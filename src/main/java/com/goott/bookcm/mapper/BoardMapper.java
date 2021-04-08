package com.goott.bookcm.mapper;

import java.util.List;

import com.goott.bookcm.domain.BoardVO;

public interface BoardMapper {
	// 전체 게시물 읽어오기
	public List<BoardVO> getList();
	
	//게시물 생성
	public int createBoard();
	
	//개별 게시물 읽기
	public BoardVO getBoard();
	
	//게시물 수정
	public int modBoard();
	
	//게시물 삭제
	public int delBoard();
	

}
