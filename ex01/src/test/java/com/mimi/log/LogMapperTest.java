package com.mimi.log;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.LogMapper;
import com.mimi.vo.LogVo;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LogMapperTest {

	@Autowired
	LogMapper lMapper;
	
	@Test
	public void insert() {
		
		assertNotNull(lMapper);
		
		LogVo vo = new LogVo();
		
		vo.setClassname("classname");
		vo.setMethodname("methodname");
		vo.setParams("params");
		vo.setErrmsg("errmsg");
		vo.setRegdate("2023/07/17");
		
		int res = lMapper.insert(vo);
		
		log.info("******************** vo : " + vo);
		
		assertEquals(1, res);
		
	}
	
}











