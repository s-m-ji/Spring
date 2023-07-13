package com.mimi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mimi.vo.ReplyVO;
import com.mimi.vo.pageDto;

public class CommonRestController {
	
	// 아래의 메세지 처리를 위해서 값을 상수로 등록해둠
	private final String REST_WRITE = "등록";
	private final String REST_EDIT = "수정";
	private final String REST_DELETE = "삭제";
	private final String REST_SELECT = "조회";
	
	/**
	 * 입력(등록), 수정, 삭제 처리는 int 값을 반환하므로
	 * 결과를 받아서 Map을 생성 후 반환합니당 
	 * @return
	 */
	// map을 생성 후 result, msg를 셋팅
	public Map<String, Object> responseMap(int res, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(res > 0) {
			map.put("result", "success");
			map.put("msg", msg + " 되었습니다.");
		} else {
			map.put("result", "fail");
			map.put("msg", msg + "중 예외가 발생하였습니다.");
		}
		
		return map;
	}
	
	public Map<String, Object> responseWriteMap(int res){
		return responseMap(res, REST_WRITE);
	}
	
	public Map<String, Object> responseEditMap(int res){
		return responseMap(res, REST_EDIT);
	}
	
	public Map<String, Object> responseDeleteMap(int res){
	    return responseMap(res, REST_DELETE);
	}

	public Map<String, Object> responseListMap(List<?> list, pageDto pDto){
		// List<?> 이렇게 물음표로 넣어두면 어떤 타입이 들어오든지 모두 처리 가능
		int res = list != null ? 1 : 0 ;
		
		Map<String, Object> map = responseMap(res, REST_SELECT);
		map.put("list", list);
		map.put("pDto", pDto);

		return map;
	}
}
