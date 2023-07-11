package com.mimi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.mimi.vo.Criteria;
import com.mimi.vo.ReplyVO;

@Service
public interface ReplyService {
	List<ReplyVO> getList(@Param(value="bno")int bno, @Param(value="cri")Criteria cri);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int update(ReplyVO vo);

	public int getTotalCnt(int bno);
}
