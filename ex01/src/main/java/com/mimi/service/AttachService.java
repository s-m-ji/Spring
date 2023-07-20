package com.mimi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mimi.vo.AttachVO;

@Service
public interface AttachService {
	public List<AttachVO> getList(int bno);

	public int insert(AttachVO att);
	
	public int delete(AttachVO att);
}
