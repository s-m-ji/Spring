package com.mimi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mimi.vo.BoardVO;
import com.mimi.vo.Criteria;

public interface BoardMapper {
	
	// implements는 MyBatis가 알아서 구현해주니까 우리는 인터페이스만 생성하면 된다.
	@Select("SELECT * FROM tbl_board")
	public List<BoardVO> getList(); // List 타입인 이유 : 쿼리 결과를 조회

	public List<BoardVO> getListXml(Criteria cri);
	
	public int insert(BoardVO board); // int 타입인 이유 : 몇 건이 처리되었는지 조회 
	
	public int insertSelectKey(BoardVO board);
	
	public BoardVO getOne(int bno); 
	
	public int update(BoardVO board);
	
	public int delete(int bno);
	
	public int getTotalCnt(Criteria cri);

	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
	
}
