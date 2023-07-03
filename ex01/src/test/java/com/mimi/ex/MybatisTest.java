package com.mimi.ex;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.SampleMapper;

// 스프링 실행
@RunWith(SpringJUnit4ClassRunner.class)
// 설정파일 경로안내
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisTest {
	
	@Autowired // root-context.xml 안에 설정된 내용을 보고 스캔해옴, 그리고 스프링이 알아서 만들어줌
	SampleMapper smplMapper;
	
	@Test
	public void test() {
		// 테스트 할 때는 우선 null 여부를 체크하는게 좋음
		assertNotNull(smplMapper);
		System.out.println("********** [smplMapper] : " + smplMapper);
		
		String time = smplMapper.getTime();
		System.out.println("********** [time] : " + time);
		assertNotNull(time);
		
		String time2 = smplMapper.getTime2();
		System.out.println("********** [time2] : " + time2);
		assertNotNull(time2);
	}
}

