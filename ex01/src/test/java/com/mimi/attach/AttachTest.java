package com.mimi.attach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mimi.mapper.AttachMapper;
import com.mimi.service.AttachService;
import com.mimi.vo.AttachVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AttachTest {

	@Autowired
	AttachMapper aMapper;
	
	@Autowired
	AttachService aService;
	
	@Test
	public void insertMapper() {
		assertNotNull(aMapper);
		
		AttachVO att = new AttachVO();
		att.setBno(3);
		att.setUuid("test_uuid");
		att.setFileName("test_fileName");
		att.setUploadPath("test_uploadPath");
		att.setFiletype("i");
		
		int res = aMapper.insert(att);
		
		assertEquals(1, res);
	}
	
	@Test
	public void getListMapper() {
		List<AttachVO> list = aMapper.getList(3);
		log.info("list : " + list);
	}
	
	@Test
	public void deleteMapper() {
		AttachVO att = new AttachVO();
		att.setBno(6);
		att.setUuid("test_uuid");
		att.setFileName("test_fileName");
		att.setUploadPath("test_uploadPath");
		att.setFiletype("i");
		aMapper.insert(att);
		// ㄴ> DB 손실 방지를 위해서 생성 후 바로 삭제할 수 있도록 테스트함
		
		int res = aMapper.delete(att);
		assertEquals(1, res);
	}
	
	@Test
	public void insertService() {
		assertNotNull(aService);
		
		AttachVO att = new AttachVO();
		att.setBno(3);
		att.setUuid("test2_uuid");
		att.setFileName("test2_fileName");
		att.setUploadPath("test2_uploadPath");
		att.setFiletype("i");
		
		int res = aService.insert(att);
		
		assertEquals(1, res);
		log.info("att : " + att);
	}
	
	@Test
	public void getListService() {
		List<AttachVO> list = aService.getList(2);
		log.info("list : " + list);
	}
	
	@Test
	public void deleteService() {
		AttachVO att = new AttachVO();
		att.setBno(6);
		att.setUuid("test_uuid");
		att.setFileName("test_fileName");
		att.setUploadPath("test_uploadPath");
		att.setFiletype("i");
		aService.insert(att);
		// ㄴ> DB 손실 방지를 위해서 생성 후 바로 삭제할 수 있도록 테스트함
		
		int res = aService.delete(att);
		assertEquals(1, res);
	}
	
	
	
}
