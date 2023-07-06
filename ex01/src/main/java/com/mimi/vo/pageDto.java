package com.mimi.vo;

import lombok.Data;

@Data
public class pageDto {
	
	private Criteria cri; // 페이지 번호, 페이지당 게시물 수를 참조
	private int total; // 총 게시물 수
	
	private int startNo; // 페이지 블록의 시작번호
	private int endNo; // 페이지 블록의 끝번호
	int block = 5; // 페이지 블록 개수
	
	private int realEndNo; // 총 페이지 블록의 진정한 끝번호

	private boolean prev, next; // 이전,다음 번호 활성화 여부

	public pageDto(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// 페이지 블럭의 끝번호
		endNo = (int)Math.ceil(cri.getPageNo()/(block*1.0))*block;
		// 페이지 블럭의 시작번호
		startNo = endNo - (block-1);
		
		realEndNo = (int)Math.ceil((total*1.0)/cri.getAmount());
		
		endNo = endNo > realEndNo ? realEndNo : endNo;
		
		prev = startNo > 1 ? true : false;
		next = endNo == realEndNo ? false : true;
	}
	
	
}
