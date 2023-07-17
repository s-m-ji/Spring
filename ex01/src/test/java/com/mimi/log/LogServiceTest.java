package com.mimi.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.LogMapper;
import com.mimi.service.LogService;
import com.mimi.vo.LogVo;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LogServiceTest {

	@Autowired
	LogService lService;
	
	@Test
	public void insert() {
		
		assertNotNull(lService);
		
		LogVo vo = new LogVo();
		
		vo.setClassname("classname2");
		vo.setMethodname("methodname2");
		vo.setParams("params2");
		vo.setErrmsg("errmsg2");
		vo.setRegdate("2023/07/17");
		
		int res = lService.insert(vo);
		
		log.info("******************** vo : " + vo);
		
		assertEquals(1, res);
		
	}
	
}











