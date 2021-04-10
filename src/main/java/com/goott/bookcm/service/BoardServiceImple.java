package com.goott.bookcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.mapper.BoardMapper;

@Service
public class BoardServiceImple implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	// 전체 게시물 읽어오기
	@Override
	public List<BoardVO> getList() {
		return boardMapper.getList();
	}

	//게시물 생성
	@Override
	public int createBoard(BoardVO boardVO) {
		return boardMapper.createBoard(boardVO);
	}

	//개별 게시물 읽기
	@Override
	public BoardVO getBoard(Long bno) {
		//조회수 올리기
		boardMapper.upWatch(bno);
		//게시물 올리기
		return boardMapper.getBoard(bno);
	}

	//게시물 수정
	@Override
	public int modBoard(BoardVO boardVO) {
		return boardMapper.modBoard(boardVO);
	}

	//게시물 삭제
	@Override
	public int delBoard(Long bno) {
		return boardMapper.delBoard(bno);
	}

	//게시물 추천
	@Override
	public int upThumbs(Long bno) {
		int result ; 
		
		if( boardMapper.upThumbs(bno) ==1 && boardMapper.historyThumbs(bno)==1 ) {
			result = boardMapper.getThumbs(bno);
		}else{
			result = 0;
		}
		
		return result;
	}


}
