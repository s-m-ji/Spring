package com.mimi.vo;

import lombok.Data;

@Data // 이 친구가 toString까지 알아서 오버라이드 해줍니당
public class BoardVO {
	private int bno;
	private String title;
	private String id;
	private String content;
	private String writer;
	private String regdate;
	private String updatedate;
	private String rent_no;
	private String rent_date;
	private String return_exp_date;
	private int replycnt; // 댓글 수 
	
}
