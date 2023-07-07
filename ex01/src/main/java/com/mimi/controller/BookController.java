package com.mimi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mimi.service.BookService;
import com.mimi.vo.BookVO;
import com.mimi.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book/*")
@Log4j
public class BookController {
	
	@Autowired
	BookService bService;
	
	/*
	 * 도서 목록 조회
	 */
	@GetMapping("list")
	public void getList(Model model, Criteria cri) {
		// int pageNo에 아무런 값이 없으면 '' 빈 문자열이 나오기때문에 유의
		
		log.info("*********************************************");
		log.info("********** @GetMapping(\"list\") 실행");
		log.info("cri : " + cri);
		
		bService.getList(model, cri);
	}
	
	/*
	 * 도서 상세 조회
	 */
	@GetMapping("view")
	public void getView(Model model, BookVO book ) {
		// int pageNo에 아무런 값이 없으면 '' 빈 문자열이 나오기때문에 유의
		
		log.info("*********************************************");
		log.info("********** @GetMapping(\"view\") 실행");
		log.info("book.getIdx() : " + book.getIdx());
		
		bService.getView(book.getIdx(), model);
		
	}
	
	/*
	 * 도서 상세 조회
	 */
	@GetMapping("write")
	public void write() {
		
	}
}
