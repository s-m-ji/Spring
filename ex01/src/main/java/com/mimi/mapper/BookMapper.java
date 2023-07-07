package com.mimi.mapper;

import java.util.List;

import com.mimi.vo.BookVO;
import com.mimi.vo.Criteria;

public interface BookMapper {
	
	public List<BookVO> getList(Criteria cri);

	public int getTotalCnt(Criteria cri);
	
	public BookVO getView(int idx);
}
