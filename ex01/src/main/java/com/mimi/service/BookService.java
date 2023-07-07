package com.mimi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mimi.vo.BookVO;
import com.mimi.vo.Criteria;

@Service
public interface BookService {

	public List<BookVO> getList(Model model, Criteria cri);

	// public BookVO getView(int idx);

	BookVO getView(int idx, Model model);
}
