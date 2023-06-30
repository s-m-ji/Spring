package com.mimi.mapper;

import org.apache.ibatis.annotations.Select;

public interface SampleMapper {
	
	// 이렇게 쿼리문을 활용하는 메소드를 생성함 
	@Select("select sysdate from dual")
	String getTime(); // 메소드명 임의 지정
	String getTime2();
}
