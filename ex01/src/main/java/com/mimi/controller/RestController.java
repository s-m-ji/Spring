package com.mimi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.vo.Member;

@Controller
public class RestController {
	/*
	 * JSON 데이터를 반환하는 방법
	 * 
	 * 1) 라이브러리 추가
	 * jackson-databind 라이브러리를 메이븐 리바피토리에서 검색 후 pom.xml 파일에 추가.
	 * 
	 * 2) 리턴 타입에 어노테이션 추가
	 * 메소드 선언부의 리턴타입에 ResponseBody 어노테이션을 추가.
	 * 리턴타입에 맞게 데이터를 자동으로 변환.
	 * -> 개발자 도구의 네트워크 탭에서 응답헤더를 확인해보면
	 * Content-Type이 변경된 것을 확인 가능.
	 * text/html --> application/json
	 * (https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types)
	 * 
	 * 3) 메소드의 리턴타입
	 * 	Vo, Dto
	 * 	json 타입의 데이터를 만들어서 반환하는 용도로 사용
	 * 
	 */
	
	
	@GetMapping("rest")
	// json 파싱을 위해서 객체 타입 앞에 @ResponseBody 추가
	public @ResponseBody Member rest(Member member) {
		System.out.println("---------- rest");
		return member;
	}
	
	
	/*
	 * ResponseEntity
	 * 헤더 정보를 가공하기 위한 용도로 사용
	 * 
	 * Request, Response 객체를 직접 다루지 않고
	 * 스프링 MVC에서 제공해주는 어노테이션 또는 객체를 이용함
	 */
	@GetMapping("restResponseEntity")
	public ResponseEntity<String> rest2(){
		
		// json 파싱을 위해 아래와 같이 직접 설정
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json;charset=utf-8");
		
		String msg = "{\"name\" : \"미미\"}";
		ResponseEntity<String> rs = 
				new ResponseEntity<String>(msg, header, HttpStatus.OK);
		
		return rs;
	}
}







