package com.goott.bookcm.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	Long bno;
	String title;
	String content;
	String colList;	//[0]공지사항 [1] 책추천 [2] 책 나눔 [3] 일반게시판 [4] 문의 게시판
	String writer;
	Timestamp regDate;
	Timestamp editDate;
	String editUser;
	Long watchNumber;
	Long thumbsNumber;
	String board_yn;
}
