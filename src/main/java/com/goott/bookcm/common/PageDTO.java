package com.goott.bookcm.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;	//현재페이지 번호, 행개수
	private int endPage;	//화면에 표시되는 끝 페이지 번호
	private boolean prev, next;	//이전, 다음 버튼 표시 여부 (true:표시됨, false:표시안됨)
	
	private int total;
	private Criteria cri;
	
	private int pagingNomCnt;	//화면 하단에 출력할 페이지 번호 개수
	private int realEnd;

	public PageDTO() {}

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.pagingNomCnt = 5;	//상태바의 갯수
		
		this.endPage= (int)(Math.ceil(cri.getPageNum()/(this.pagingNomCnt*1.0))*this.pagingNomCnt);
		this.startPage=this.endPage-(this.pagingNomCnt-1);	

		realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));

		
		//계산된 끝 페이징 번호가 실제 마지막 페이지 번호보다 크면, endPage 값을 realEnd로 대체
		if(realEnd < this.endPage) { this.endPage = realEnd; }
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}
}

