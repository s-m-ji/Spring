package com.mimi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.vo.BoardVO;
import com.mimi.vo.Criteria;

@Service
public interface BoardService {
	public void getListXml(Model model, Criteria cri);
	
	public int insert(BoardVO board); 
	
	public int insertSelectKey(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public BoardVO getOne(int bno); 
	
	public int update(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public int delete(int bno, Criteria cri);
	
	public int getTotalCnt(Criteria cri);
	
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
