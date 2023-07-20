package com.mimi.mapper;

import java.util.List;

import com.mimi.vo.AttachVO;

public interface AttachMapper {

	public List<AttachVO> getList(int bno);
	
	public int insert(AttachVO att);
	
	public int delete(AttachVO att);
	
}
