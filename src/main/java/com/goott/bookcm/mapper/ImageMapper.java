package com.goott.bookcm.mapper;

import java.util.List;

import com.goott.bookcm.domain.ImageVO;

public interface ImageMapper {
	//bno에 등록된 이미지 찾기
	public List<ImageVO> getImageBno(Long bno);
	
	//bno에 이미지 등록
	public int regImage(ImageVO imageVO);
	
	//bno에 등록된 사진 지우기
	public int delImgae(String uuid);
	
	//bno에 등록된 사진 지우기
	public int delAllImgae(Long bno);
}
