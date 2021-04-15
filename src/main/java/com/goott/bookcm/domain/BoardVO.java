package com.goott.bookcm.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String colList;	//[0]공지사항 [1] 책추천  [2] 일반게시판 [3] 문의 게시판
	private String writer;
	private Timestamp regDate;
	private Timestamp editDate;
	private String editUser;
	private Long watchNumber;
	private Long thumbsNumber;
	private String board_yn;
	
	private List<ImageVO> imageList;
}
