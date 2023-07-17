package com.mimi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mimi.mapper.BoardMapper;
import com.mimi.mapper.ReplyMapper;
import com.mimi.vo.Criteria;
import com.mimi.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper rMapper;
	
	@Autowired
	BoardMapper bMapper;
	
	@Override
	public List<ReplyVO> getList(@Param(value="bno")int bno, @Param(value="cri")Criteria cri) {
		return rMapper.getList(bno, cri);
	}
	
	/*
	 * Transactional
	 * 	서비스 로직에 대한 트랜잭션 처리를 지원함	
	 *  오류 발생 시 롤백 !
	 */
	@Transactional  
	@Override
	public int insert(ReplyVO vo) {
		// 댓글 입력 시 tbl_board의 replycnt 컬럼 값 1 증가
		bMapper.updateReplyCnt(vo.getBno(), 1);
		return rMapper.insert(vo);
	}

	@Transactional
	@Override
	public int delete(int rno) {
		// 댓글 입력 시 tbl_board의 replycnt 컬럼 값 1 감소
		ReplyVO vo = rMapper.getOne(rno); // 우선 조회
		bMapper.updateReplyCnt(vo.getBno(), -1); // 그리고 수정
		return rMapper.delete(rno); // 마지막으로 삭제
	}

	@Override
	public int update(ReplyVO vo) {
		return rMapper.update(vo);
	}

	@Override
	public int getTotalCnt(int bno) {
		return rMapper.getTotalCnt(bno);
	}

	
}
