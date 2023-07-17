package com.mimi.service;

import org.springframework.stereotype.Service;

import com.mimi.vo.LogVo;

@Service
public interface LogService {
	public int insert(LogVo vo);
}
