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
public class BookMapperTest {
	
	@Autowired
	BookMapper bMapper; 
	
	@Test
	public void getList() {
		assertNotNull(bMapper);
		
		Criteria cri = new Criteria();
		cri.setSField("title");
		cri.setSWord("100");
		List<BookVO> list = bMapper.getList(cri);
		log.info("===========================================================");
		log.info("========== list : " + list);
		list.forEach(book -> {
			log.info(book);
		});
	}
	
	@Test
	public void getTotalCnt() {
		int res = bMapper.getTotalCnt(new Criteria());
		log.info("===========================================================");
		log.info("========== res : " + res);
	}
	
	@Test
	public void getView() {
		BookVO book = bMapper.getView(100);
		log.info("===========================================================");
		log.info("========== book : " + book);
	}
	
}
