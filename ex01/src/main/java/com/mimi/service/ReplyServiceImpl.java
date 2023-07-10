package com.mimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.mapper.ReplyMapper;
import com.mimi.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper rMapper;
	
	@Override
	public List<ReplyVO> getList(int bno) {
		return rMapper.getList(bno);
	}

	@Override
	public int insert(ReplyVO vo) {		
		return rMapper.insert(vo);
	}

	@Override
	public int delete(int rno) {
		return rMapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		return rMapper.update(vo);
	}

	@Override
	public ReplyVO getReply(int rno) {
		return rMapper.getReply(rno);
	}

}
