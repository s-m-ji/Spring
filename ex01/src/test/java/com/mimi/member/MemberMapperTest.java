package com.mimi.member;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.MemberMapper;
import com.mimi.vo.Member;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTest {

	@Autowired
	MemberMapper mMapper;
	
	@Test
	public void test(){
		assertNotNull(mMapper);
		log.info("-------------------------------------------------------------");
		
		Member m = new Member();
		m.setId("test");
		m.setPass("1234");
		
		m = mMapper.login(m);
		assertNotNull(m);
	}
}
