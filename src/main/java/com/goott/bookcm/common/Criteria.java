package com.goott.bookcm.common;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;	//현재 페이지 번호
	private int amount;		//페이지당 출력할 레코드 수
	
	private String type;	//검색범위(searchScope c:content t:title w:writer)
	private String keyword;
	
	private String colList;	//[0]공지사항 [1] 책추천  [2] 일반게시판 [3] 문의 게시판
	
	public Criteria() {
		this(1,5);			//게시물 번호를 입력
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//type필드에 ["T", "C", "W"] 배열로 반환하는 메소드
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	//웹페이지에서 매번 파라미터를 유지하는 일이 번거롭고 힘들어서 사용함
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.getAmount())
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
	
}
