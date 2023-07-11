package com.mimi.reply;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.ReplyMapper;
import com.mimi.service.ReplyService;
import com.mimi.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest {
	
	@Autowired
	ReplyService rService;
	
	@Test
	public void test() {
		assertNotNull(rService);
		/* 
		List<ReplyVO> list = rService.getList(1);
	 	System.out.println("---------------------------------------------------------------------------------");
	 	log.info("list : " + list);
		 */
}
}




