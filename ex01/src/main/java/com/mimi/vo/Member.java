package com.mimi.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Lombok 라이브러리
 * getter/setter, equals. toString 등 메서드를 자동 생성해줌
 * 
 *  @ Data
 *  IDE(이클립스, STS 등등) 설치 후 lombok 라이브러리를 추가하여 사용 가능
 *  IDE가 설치되어있지 않으면 어노테이션을 추가해도 메소드가 생성되지 않을 수 있음
 *  
 *  Outline View를 통해 메소드가 생성되었는지 확인 !
 */
import lombok.Data;

@Data // lombok이 getter/setter 메소드, 생성자를 자동으로 생성해줌 -> Outline view에서 확인 가능
public class Member {

	private String id;
	private String pass;
	private String name;
	private int age;
	private List<String> role; // 사용자 권한
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}













