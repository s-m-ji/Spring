package com.mimi.reply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.ReplyMapper;
import com.mimi.vo.Criteria;
import com.mimi.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	@Autowired
	ReplyMapper rMapper;
	
	
	@Test
	public void getListTest() {
		assertNotNull(rMapper);
		
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNo(1);
	 	List<ReplyVO> list = rMapper.getList(1, cri);
	 	System.out.println("---------------------------------------------------------------------------------");
	 	log.info("list : " + list);
	}
	
	@Test
	public void getTotalCnt() {
		assertNotNull(rMapper);
	 	int total = rMapper.getTotalCnt(1);
	 	System.out.println("---------------------------------------------------------------------------------");
	 	log.info("total : " + total);
	}
	
	@Test
	public void insertTest() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(2);
		vo.setReply("댓글2");
		vo.setReplyer("작성자2");
		
		int res = rMapper.insert(vo);
		System.out.println("---------------------------------------------------------------------------------");
		log.info("res : " + res);
		
		assertEquals(res, 1);
	}
	
	@Test
	public void deleteTest() {
		
		// 삭제 테스트 시에는 등록을 먼저 하고 다음에 바로 그 친구를 삭제해보는 식으로 체크해보면 
		// 없는 번호를 삭제하는 등의 오류를 줄일 수 있다.
		int res = rMapper.delete(16);
		System.out.println("---------------------------------------------------------------------------------");
		log.info("res : " + res);
		
		assertEquals(res, 1);
	}
	
	@Test
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(10);
		vo.setReply("댓글 수정");
		int res = rMapper.update(vo);
		System.out.println("---------------------------------------------------------------------------------");
		log.info("res : " + res);
		
		assertEquals(res, 1);
	}
	
}




