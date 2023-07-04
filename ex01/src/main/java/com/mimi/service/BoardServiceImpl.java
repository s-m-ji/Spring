package com.mimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.mapper.BoardMapper;
import com.mimi.vo.BoardVO;

/*
 * 각 계층 간의 연결은 인터페이스를 활용하여 느슨한 결합을 한다. 
 * -> 내부 의존성 / 결합도를 낮춰서 오류 가능성을 낮추는 것.
 *  - 느슨한 결합 : 하나의 컴포넌트의 변경이 다른 컴포넌트들의 변경을 요구하는 위험을 줄이는 것은 목적으로 하는 시스템에서
 *   	컴포넌트 간의 내부 의존성을 줄이는 것을 추구하는 디자인 목표
 *   
 * 	 ▶ Service
 * 		계층 구조상 비즈니스 영역을 담당한느 객체임을 표시
 *   
 * 	 ▶  root-contect.xml
 *   component-scan 속성에 패키지를 등록.
 * 
 * Service를 Interface로 생성하는 이유
 * 
 *  1) 내부 로직의 분리
 *  	인터페이스를 사용하여 내부 로직의 변경/수정 시 유연하게 대처 가능
 *  2) 구현체의 전환이 용이함
 *  	구현체의 변경, 교체가 용이
 *  3) 테스트 용이함
 *  	단위 테스트 시 테스트용 구현체 이용하여 테스트를 수행
 */


@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper bMapper;

	@Override
	public List<BoardVO> getListXml() {		
		return bMapper.getListXml();
	}

	@Override
	public int insert(BoardVO board) {		
		return bMapper.insert(board);
	}

	@Override
	public int insertSelectKey(BoardVO board) {
		return bMapper.insertSelectKey(board);
	}

	@Override
	public BoardVO getOne(int bno) {		
		return bMapper.getOne(bno);
	}

	@Override
	public int update(BoardVO board) {		
		return bMapper.update(board);
	}

	@Override
	public int delete(int bno) {
		return bMapper.delete(bno);
	}

	@Override
	public int getTotalCnt() {
		return bMapper.getTotalCnt();
	}

}
