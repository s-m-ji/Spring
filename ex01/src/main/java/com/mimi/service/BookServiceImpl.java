package com.mimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mimi.mapper.BookMapper;
import com.mimi.vo.BookVO;
import com.mimi.vo.Criteria;
import com.mimi.vo.pageDto;

@Service
// class는 추상메소드를 가질 수 없기 때문에 implements를 제대로 하지 않으면 오류가 나는 것
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bMapper; // TODO ??? 여기서 private을 꼭 써야하는지?
	
	@Override
	public List<BookVO> getList(Model model, Criteria cri) {
		
		/*
		 * 1) 리스트 조회
		 * 2) 총 건수 조회
		 * 3) pageDto 생성(페이지네이션 블록)
		 */
		
		List<BookVO> list = bMapper.getList(cri);
		int totalCnt = bMapper.getTotalCnt(cri);
		pageDto pageDto = new pageDto(cri, totalCnt);
		
		model.addAttribute("list", list);
		model.addAttribute("pDto", pageDto);
		
		//return list; -> model에 저장해서 넘길거라 리턴하지 않음
		return null;
		
	}

	@Override
	public BookVO getView(int idx, Model model) {
		BookVO book = bMapper.getView(idx);
		model.addAttribute("book", book);
		return book;
	}

	@Override
	public void write(int idx, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int writePost(BookVO book) {
		return 0;
		
	}

}
