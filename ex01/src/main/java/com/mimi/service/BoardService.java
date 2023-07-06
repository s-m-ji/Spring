package com.mimi.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mimi.vo.BoardVO;
import com.mimi.vo.Criteria;

@Service
public interface BoardService {
	public void getListXml(Model model, Criteria cri);
	
	public int insert(BoardVO board); 
	
	public int insertSelectKey(BoardVO board);
	
	public BoardVO getOne(int bno); 
	
	public int update(BoardVO board);
	
	public int delete(int bno);
	
	public int getTotalCnt(Criteria cri);
}
