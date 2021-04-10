package com.goott.bookcm.mapper;

import java.util.List;

import com.goott.bookcm.domain.BoardVO;

public interface BoardMapper {
	// 전체 게시물 읽어오기
	public List<BoardVO> getList();
	
	//개별 게시물 읽기
	public BoardVO getBoard(Long bno);
	
	//게시물 생성
	public int createBoard(BoardVO boardVO);

	//게시물 수정
	public int modBoard(BoardVO boardVO);
	
	//게시물 삭제
	public int delBoard(Long bno);

	//조회수 up
	public int upWatch(Long bno);
	
	//추천수 up
	public int upThumbs(Long bno);
	
	//추천 history
	public int historyThumbs(Long bno);
	
	//추천수 가져오기
	public int getThumbs(Long bno);

}
