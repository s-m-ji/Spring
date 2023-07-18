package com.mimi.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.service.BoardService;
import com.mimi.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
	@Autowired
	BoardService bService;

	/*
	@Test
	public void getListXml() {
		List<BoardVO> list = bService.getListXml();
		
		System.out.println("======================================================================");
		list.forEach(board -> {
			log.info(board);
		});
	}
	*/
}
