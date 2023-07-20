package com.mimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.mapper.AttachMapper;
import com.mimi.vo.AttachVO;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	AttachMapper aMapper;

	@Override
	public List<AttachVO> getList(int bno) {
		return aMapper.getList(bno);
	}

	@Override
	public int insert(AttachVO att) {
		return aMapper.insert(att);
	}

	@Override
	public int delete(AttachVO att) {
		// 파일을 진짜 삭제
		// 파일을 DB에서도 삭제
		
		
		return aMapper.delete(att);
	}
}
