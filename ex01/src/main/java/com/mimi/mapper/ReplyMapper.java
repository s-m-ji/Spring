package com.mimi.mapper;

import java.util.List;

import com.mimi.vo.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> getList(int bno);

	public int insert(ReplyVO vo);

	public int delete(int rno);

	public int update(ReplyVO vo);

	public ReplyVO getReply(int rno);

}
