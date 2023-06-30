package com.mimi.ex;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.SampleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisTest {
	
	@Autowired // root-context.xml 안에 설정된 내용을 보고 스캔해옴, 그리고 스프링이 알아서 만들어줌
	SampleMapper smplMapper;
	
	@Test
	public void test() {
		System.out.println("********** [smplMapper] : " + smplMapper);
		
		String time = smplMapper.getTime();
		System.out.println("********** [time] : " + time);
		assertNotNull(time);
		
		String time2 = smplMapper.getTime2();
		System.out.println("********** [time2] : " + time2);
		assertNotNull(time2);
	}
}

