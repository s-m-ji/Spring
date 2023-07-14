package com.mimi.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.service.MemberService;
import com.mimi.vo.Member;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTest {

	@Autowired
	MemberService mService;
	
	@Test
	public void test(){
		assertNotNull(mService);
		log.info("-------------------------------------------------------------");
		
		Member m = new Member();
		m.setId("test14");
		m.setPass("1234");
		m.setName("사용자14");
		
		int res = mService.register(m);
		System.out.println("암호화 된 비번 : " + m.getPass());
		assertEquals(1, res);
	}
	
	
}
