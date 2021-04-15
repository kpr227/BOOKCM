package com.goott.bookcm.mapper;

import java.util.List;

import com.goott.bookcm.common.Criteria;
import com.goott.bookcm.domain.BoardVO;

public interface BoardMapper {
	// 전체 게시물 읽어오기 paging x search x
	public List<BoardVO> getList();
	
	// 전체 게시물 읽어오기 paging o search x
	public List<BoardVO> getList_p(Criteria cri);
	
	// 전체 게시물 읽어오기 paging o search o
	public List<BoardVO> getList_ps(Criteria cri);
	
	// 전체 게시물 갯수 읽기
	public int getTotalBoard();
	
	// 전체 게시물 갯수 읽기
	public int getTotalBoard_s(Criteria cri);
	
	//개별 게시물 읽기
	public BoardVO getBoard(Long bno);
	
	//게시물 생성
	public int createBoard(BoardVO boardVO);
	
	//seq_bno의 다음값 찾기
	public Long searchNexSeq();

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
