package com.mimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.mapper.LogMapper;
import com.mimi.vo.LogVo;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper lMapper;
	
	@Override
	public int insert(LogVo vo) {
		return lMapper.insert(vo);
	}

}
