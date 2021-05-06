package com.goott.bookcm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goott.bookcm.common.Criteria;
import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.domain.ImageVO;

public interface BoardService {
	// 전체 게시물 읽어오기  paging x search x colList x
	public List<BoardVO> getList();
	
	//전체 게시물 읽어오기 paging o search x colList x
	public List<BoardVO> getList_p(Criteria cri);
	
	//전체 게시물 읽어오기 paging o search o colList x
	public List<BoardVO> getList_ps(Criteria cri);
	
	//전체 게시물 읽어오기 paging o search o colList o
	public List<BoardVO> getList_psc(Criteria cri);
	
	// 전체 게시물 갯수 읽기 search x
	public int getTotalBoard();
	
	// 전체 게시물 갯수 읽기 search o colList x
	public int getTotalBoard_s(Criteria cri);
	
	// 전체 게시물 갯수 읽기 search o colList o
	public int getTotalBoard_sc(Criteria cri);
	
	//게시물 생성
	public int createBoard(BoardVO boardVO);
	
	//게시물 생성
	public Long searchNexSeq();
	
	//특정 게시물 읽기
	public BoardVO getBoard(Long bno);
	
	//특정 게시물 파일 가져오기
	public List<ImageVO> getImgaeList(Long bno);
	
	//특정 게시물 파일 수정
	public int modImage(Long bno, List<ImageVO> ImageList);
	
	//게시물 수정 
	public int modBoard(BoardVO boardVO);
	
	//게시물 삭제
	public int delBoard(Long bno);
	
	//게시물 추천
	public int upThumbs(Long bno);
	
	//추천수 History에 존재하는가
	public String existThumbs(@Param("bno")Long bno, @Param("loginId")String loginId);
	
	
}
