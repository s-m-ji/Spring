package com.mimi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mimi.vo.ReplyVO;

@Service
public interface ReplyService {
	List<ReplyVO> getList(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int update(ReplyVO vo);

	ReplyVO getReply(int rno);
}
