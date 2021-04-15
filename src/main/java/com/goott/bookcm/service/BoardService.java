package com.goott.bookcm.service;

import java.util.List;

import com.goott.bookcm.common.Criteria;
import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.domain.ImageVO;

public interface BoardService {
	// 전체 게시물 읽어오기  paging x search x
	public List<BoardVO> getList();
	
	//전체 게시물 읽어오기 paging o search x
	public List<BoardVO> getList_p(Criteria cri);
	
	//전체 게시물 읽어오기 paging o search o
	public List<BoardVO> getList_ps(Criteria cri);
	
	// 전체 게시물 갯수 읽기 search x
	public int getTotalBoard();
	
	// 전체 게시물 갯수 읽기 search o
	public int getTotalBoard_s(Criteria cri);
	
	//게시물 생성
	public int createBoard(BoardVO boardVO);
	
	//게시물 생성
	public Long searchNexSeq();
	
	//특정 게시물 읽기
	public BoardVO getBoard(Long bno);
	
	//특정 게시물 파일 가져오기
	public List<ImageVO> getImgaeList(Long bno);
	
	//특정 게시물 파일 전체 삭제
	public boolean deleteAll(Long bno);
	
	//게시물 수정 
	public int modBoard(BoardVO boardVO);
	
	//게시물 삭제
	public int delBoard(Long bno);
	
	//게시물 추천
	public int upThumbs(Long bno);
	

	
}
