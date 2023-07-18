package com.mimi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

// @ControllerAdvice // 예외처리용 어노테이션 // ※※※ RestCommonExceptionAdvice 테스트 해보느라 잠시 어노테이션 주석 처리함
@Log4j // 로그 남김용 어노테이션
public class CommonExceptionAdvice {

	/*
	 * root-context에 component-scan을 패키지에 등록해야하므로 Bean으로 등록.
	 *   
	 *   ▶▶▶ @  ControllerAdvice
	 *   컨트롤러에 대한 예외를 처리하는 객체임을 명시
	 *   컨트롤러가 실행 중 발생되는 오류이므로 500오류가 발생하는 경우 실행됨
	 *   
	 *   ▶▶▶ @ ExceptionHandler
	 *   Controller, RestController가 적용된 Bean 내에서 발생하는 예외를
	 *   하나의 메소드에서 처리해주는 기능을 한다
	 *  
	 * 
	 */
	@ExceptionHandler(Exception.class) // 어떤 오류인지 명시 : 여기서는 최상위 익셉션을 기입했음
	// 근데 여기서는 500에러밖에 처리를 못한다구..
	public String except(Exception ex, Model model) {
		
		log.debug("---------- 로그 테스트 debug");
		log.error("---------- 로그 테스트 error");
		System.out.println("----------------------------------------------------------------------" );
		System.out.println("---------- Exception " + ex.getMessage());
		ex.printStackTrace();
		model.addAttribute("exception", ex);
		
		return "/error/err500"; // jsp 페이지 연결
	}

	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex , Model model) {
		
		model.addAttribute("exception", ex);
		return "/error/err404"; // jsp 페이지 연결
	}
	
}

