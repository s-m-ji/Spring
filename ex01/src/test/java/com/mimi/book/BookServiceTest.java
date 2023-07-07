package com.mimi.book;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.mimi.mapper.BookMapper;
import com.mimi.service.BookService;
import com.mimi.vo.BookVO;
import com.mimi.vo.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookServiceTest {
	
	@Autowired
	BookService bService;
	
	@Test
	public void getList() {
		assertNotNull(bService);
		
		List<BookVO> list = bService.getList(null, new Criteria());
		log.info("===========================================================");
		log.info("========== list : " + list);
		list.forEach(book -> {
			log.info(book);
		});
	}
	
	@Test
	public void getView() {
		BookVO book = bService.getView(100, null);
		log.info("===========================================================");
		log.info("========== book : " + book);
	}
}
