package com.goott.bookcm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goott.bookcm.common.Criteria;
import com.goott.bookcm.domain.BoardVO;
import com.goott.bookcm.domain.ImageVO;
import com.goott.bookcm.mapper.BoardMapper;
import com.goott.bookcm.mapper.ImageMapper;

@Service
public class BoardServiceImple implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	// 전체 게시물 읽어오기  paging x search x colList x
	@Override
	public List<BoardVO> getList() {
		return boardMapper.getList();
	}

	//전체 게시물 읽어오기 paging o search x colList x
	@Override
	public List<BoardVO> getList_p(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getList_p(cri);
	}
	
	//전체 게시물 읽어오기 paging o search o colList x
	@Override
	public List<BoardVO> getList_ps(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getList_ps(cri);
	}
	
	//전체 게시물 읽어오기 paging o search o colList o
	@Override
	public List<BoardVO> getList_psc(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getList_psc(cri);
	}
	
	//전체 게시물 갯수 읽기
	@Override
	public int getTotalBoard() {
		// TODO Auto-generated method stub
		return boardMapper.getTotalBoard();
	}
	
	//전체 게시물 갯수 읽기 paging o search o 
	@Override
	public int getTotalBoard_s(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalBoard_s(cri);
	}
	
	//전체 게시물 갯수 읽기 paging o search o colList o
	@Override
	public int getTotalBoard_sc(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalBoard_sc(cri);
	}
	
	//게시물 생성
	@Override
	public int createBoard(BoardVO boardVO) {
		boardMapper.createBoard(boardVO);
		Long bno = boardMapper.searchNexSeq();
		boardVO.setBno(bno);
		
		if(boardVO.getImageList() == null || boardVO.getImageList().size() <=0) {
			return 0;
		}
		
		boardVO.getImageList().forEach(image ->{
			image.setBno(bno);
			imageMapper.regImage(image);
		});
		
		return 1;
	}
	
	//게시물 생성
	@Override
	public Long searchNexSeq() {
		return boardMapper.searchNexSeq();
	}
	

	//특정 게시물 읽기
	@Override
	public BoardVO getBoard(Long bno) {
		//조회수 올리기
		boardMapper.upWatch(bno);
		//게시물 올리기
		return boardMapper.getBoard(bno);
	}
	
	//특정 게시물 이미지 출력
	@Override
	public List<ImageVO> getImgaeList(Long bno) {
		// TODO Auto-generated method stub
		return imageMapper.getImageBno(bno);
	}
	
	//특정 게시물 파일 수정
	@Override
	public int modImage(Long bno, List<ImageVO> ImageList) {
		imageMapper.delAllImgae(bno);
		
		ImageList.forEach(image ->{
			image.setBno(bno);
			image.setUuid(image.getUuid());
			image.setUploadPath(image.getUploadPath());
			image.setFileName(image.getFileName());
			image.setFileType(image.getFileType());
			
			imageMapper.regImage(image);
		});

		return 1;
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

	//추천수 History에 존재하는가
	@Override
	public String existThumbs(@Param("bno")Long bno, @Param("loginId")String loginId) {
		boardMapper.existThumbs(bno, loginId);
		return null;
	}




	

}
