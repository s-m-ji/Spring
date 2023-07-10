package com.mimi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mimi.service.ReplyService;
import com.mimi.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

/**
 * 	▶ ▶ ▶  @RestController
 * 		Controller가 REST 방식을 처리하기 위한 것임을 명시
 * 		경로의 일부분을 파라미터로 사용
 * 		
 */

@RestController // rest 방식을 지원하는 컨트롤러라고 명시 *@Controller는 기본 jsp 파일을 반환하도록 설정되어있다.
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService rService;
	
	@GetMapping("/test") // 1.경로 입력 받아서
	public String test() {
		return "test"; // 2. 문자열을 출력 : @RestController 이렇게 설정해두면 그대로 반환값을 보여준다
	}
	
	/*
	 *        ▶ ▶ ▶  @PathVariable
	 *     URL 경로에 있는 값을 파라미터로 추출하려고 할 때 사용
	 */
	// ==================== 댓글 리스트 조회 ==================== 
	@GetMapping("/reply/list/{bno}")
	public List<ReplyVO> getList(@PathVariable("bno") int bno){
		System.out.println("---------------------------------------------------------------------------------");
		log.info("bno" + bno);
		return rService.getList(bno);
	}
	
	// ==================== 댓글 수정 조회 ==================== 
	@GetMapping("/reply/reply/{rno}")
	public ReplyVO getReply(@PathVariable("rno") int rno){
		System.out.println("---------------------------------------------------------------------------------");
		log.info("rno" + rno);
		return rService.getReply(rno);
	}
	
	// ==================== 댓글 등록 처리 ==================== 
		@PostMapping("/reply/update")
		public Map<String, Object> update(@RequestBody ReplyVO vo){ // JSON 객체를 가져오기 위해 @RequestBody 사용 * 문자열을 내가 원하는 객체에 알맞게 셋팅해준다.
			System.out.println("---------------------------------------------------------------------------------");
			log.info("수정 : " + vo);
			
			int res = rService.update(vo);		
			Map<String, Object> map = new HashMap<String, Object>();
			if(res>0) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
				map.put("message", "댓글 수정 등록 중 예외 사항 발생");
			}
			
			return map;
		}
	
	/*
	 *      ▶ ▶ ▶ @RequestBody
	 * 		JSON 데이터를 원하는 타입으로 바인딩 처리
	 */
	// ==================== 댓글 등록 처리 ==================== 
	@PostMapping("/reply/insert")
	public Map<String, Object> insert(@RequestBody ReplyVO vo){ // JSON 객체를 가져오기 위해 @RequestBody 사용 * 문자열을 내가 원하는 객체에 알맞게 셋팅해준다.
		System.out.println("---------------------------------------------------------------------------------");
		log.info("등록 : " + vo);
		
		int res = rService.insert(vo);		
		Map<String, Object> map = new HashMap<String, Object>();
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
			map.put("message", "댓글 등록 중 예외 사항 발생");
		}
		
		return map;
	}
	
	// ==================== 댓글 삭제 처리 ==================== 
	@GetMapping("/reply/delete/{rno}")
	public Map<String, Object> delete(@PathVariable("rno") int rno){
		System.out.println("---------------------------------------------------------------------------------");
		log.info("rno" + rno);
		
		int res = rService.delete(rno);
		Map<String, Object> map = new HashMap<String, Object>();
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
			map.put("message", "댓글 삭제 중 예외 사항 발생");
		}
		
		return map;
	}
	
	
}










