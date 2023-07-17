package com.mimi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mimi.vo.Criteria;
import com.mimi.vo.ReplyVO;

public interface ReplyMapper {
	/**
	 * 매개변수가 2개 이상 전달되는 경우 
	 * 	여러개 모두 인식하지 못하고 앞의 1개만 전달되기때문에 
	 * 	=> Param 어노테이션을 사용
	 * @param bno
	 * @param cri
	 * @return
	 */
	
	public List<ReplyVO> getList(@Param(value="bno")int bno, @Param(value="cri")Criteria cri);

	public int insert(ReplyVO vo);

	public int delete(int rno);

	public int update(ReplyVO vo);

	public int getTotalCnt(int bno);

	public ReplyVO getOne(int rno);

}
