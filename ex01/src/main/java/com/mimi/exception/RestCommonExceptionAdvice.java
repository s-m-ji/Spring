package com.mimi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@RestControllerAdvice // Rest 예외처리용 어노테이션	
@Log4j // 로그 남김용 어노테이션
public class RestCommonExceptionAdvice {

	@ExceptionHandler(Exception.class) 
	public Map<String, Object> except(Exception ex) {
		
		System.out.println("----------------------------------------------------------------------" );
		log.info("---------- Exception ....." + ex.getMessage());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "fail");
		map.put("message", ex.getMessage());
		return map;
	}

	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex , Model model) {
		
		model.addAttribute("exception", ex);
		return "/error/err404"; // jsp 페이지 연결
	}
	
}

