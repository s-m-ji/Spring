package com.mimi.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.AttachMapper;
import com.mimi.mapper.BoardMapper;
import com.mimi.vo.AttachVO;
import com.mimi.vo.BoardVO;

import lombok.extern.log4j.Log4j;


//스프링 실행
@RunWith(SpringJUnit4ClassRunner.class)
//설정파일 경로안내
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class boardTest {
	
	@Autowired // 주입을 위한 어노테이션
	BoardMapper bMapper;
	
	@Autowired
	AttachMapper aMapper;
	
	@Test
	public void getList() {
		
		assertNotNull(bMapper);
		//System.out.println("********** Test - getList 실행");
		List<BoardVO> list = bMapper.getList();
		list.forEach(board -> {
			System.out.println("**********************************************************");
			log.info("********* getBno : " + board.getBno());
			log.info("********* getTitle : " + board.getTitle());
			log.info("********* getContent : " + board.getContent());
		});
	}
	
	/*
	@Test
	public void getListXml() {
		List<BoardVO> list = bMapper.getListXml();
		list.forEach(board -> {
			System.out.println("**********************************************************");
			log.info("********* getBno : " + board.getBno());
			log.info("********* getTitle : " + board.getTitle());
			log.info("********* getContent : " + board.getContent());
		});
	}
	*/
	
	@Test
	public void insert() {
		// insert(BoardVO board) 라고 하면  XML에 id="insert"랑 매치를 못하는둣..
		BoardVO board = new BoardVO();
		board.setTitle("title 101");
		board.setContent("content 101");
		board.setWriter("writer 101");
		
		
		AttachVO att = new AttachVO();
		att.setBno(3);
		att.setUuid("test_uuid");
		att.setFileName("test_fileName");
		att.setUploadPath("test_uploadPath");
		att.setFiletype("i");
		
		aMapper.insert(att);
		
		int res = bMapper.insert(board);
		assertEquals(res, 1);
	}
	
	
	@Test
	public void insertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("title 105");
		board.setContent("content 105");
		board.setWriter("writer 105");
		
		int res = bMapper.insertSelectKey(board);
		System.out.println("**********************************************************");
		log.info("********** bno : " + board.getBno());
		assertEquals(res, 1);
	}
	
	
	@Test
	public void getOne() {
		BoardVO board = bMapper.getOne(100);
		System.out.println("****************************************************************************");
		log.info("********* getBno : " + board.getBno());
		log.info("********* getTitle : " + board.getTitle());
		log.info("********* getContent : " + board.getContent());
	}
	
	
	@Test
	public void update() {
		int bno = 102;
		BoardVO board = new BoardVO();
		board.setBno(bno);
		board.setTitle("title 102");
		board.setContent("content 102");
		board.setWriter("writer 102");
		
		int res = bMapper.update(board);
		
		BoardVO getB = bMapper.getOne(bno);
		// 이렇게 수정된 텍스트가 일치하는지도 테스트해볼 수 있음.
		assertEquals("title 102", getB.getTitle());
		
		System.out.println("**********************************************************");
		log.info("********* getBno : " + board.getBno());
		assertEquals(res, 1);
	}
	
	
	@Test
	public void delete() {
		String[] numbers = {"103", "102"};        
		
        for (int i = 0; i < numbers.length; i++) {
        	int value = Integer.parseInt(numbers[i].trim());

			int res = bMapper.delete(value);
			System.out.println("**********************************************************");
			assertEquals(res, 1);
        }
		
	}

	/*
	@Test
	public void getTotalCnt() {
		int res = bMapper.getTotalCnt();
		System.out.println("**********************************************************");
		log.info("********* res : " + res);
		assertEquals(res, 101);
	}
	*/
	
	@Test
	public void updateReplyCnt() {
		int res = bMapper.updateReplyCnt(2, 1);
		assertEquals(1, res);
	}
	
}












