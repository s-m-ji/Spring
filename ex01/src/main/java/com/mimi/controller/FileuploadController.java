package com.mimi.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileuploadController {
	/*
	 * 메소드의 리턴 타입
	 * 
	 * String
	 * /WEB-INF/views/반환값.jsp 응답 페이지 주소
	 * servlet-context.xml에 정의되어있음
	 * 
	 * 
	 * void
	 *  요청 주소와 동일한 이름의 jsp를 반환
	 */
	
	// @GetMapping("/fileupload")
	@GetMapping("/file/fileupload")
	private void fileupload() {
		
	}

	/**
	 * 파일 업로드용 라이브러리 추가
	 * commons-fileupload
	 * 
	 * cos.jar와 달리 파일을 저장하는 로직이 추가되어야 함
	 * 
	 *  1) 라이브러리 추가
	 *  2) multipartResolver 빈 등록
	 *  3) 메소드의 매개변수로 MultipartFile 이용
	 */
	@PostMapping("/file/fileupload")
	public void fileuploadAction(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			System.out.println("--------- oName : " + file.getOriginalFilename());
			System.out.println("--------- name : " + file.getName());
			System.out.println("--------- size : " + file.getSize());
		});
	}
	
}















