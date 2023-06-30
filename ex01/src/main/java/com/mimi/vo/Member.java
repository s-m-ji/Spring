package com.mimi.vo;

import lombok.Data;

@Data // lombok이 getter/setter 메소드, 생성자를 자동으로 생성해줌 -> Outline view에서 확인 가능
public class Member {

	String id;
	String pw;
	String name;
}
