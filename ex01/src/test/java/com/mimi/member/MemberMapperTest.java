package com.mimi.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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
	
	@Test
	public void test2(){
		log.info("-------------------------------------------------------------");
		
		Member m = new Member();
		m.setId("test11");
		m.setPass("1234");
		m.setName("사용자11");
		
		int res = mMapper.register(m);
		
		assertEquals(1, res);
	}
	
	@Test
	public void test3(){
		log.info("-------------------------------------------------------------");
		
		Member m = new Member();
		m.setId("test");
		
		int res = mMapper.idCheck(m);
		
		assertEquals(1, res);
	}
	
	@Test
	public void test4(){
		log.info("-------------------------------------------------------------");
		List<String> list = mMapper.getMemberRole("admin");
		
		System.out.println("****************** list : " + list);
		System.out.println("****************** 관리자 권한 : " + list.contains("admin_role"));
	}
}
