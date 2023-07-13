package com.mimi.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private int rno;
	private int bno;
	private int seq; // 댓글 순번처리용
	private int rn; // 페이지네이션 ROWNUM rn 확인용
	private String reply;
	private String replyer;
	private String replydate;
	private String updatedate;
	
}
