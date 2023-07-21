package com.mimi.service;

import java.util.List;

import javax.servlet.jsp.tagext.PageData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.mapper.BoardMapper;
import com.mimi.mapper.ReplyMapper;
import com.mimi.vo.AttachVO;
import com.mimi.vo.BoardVO;
import com.mimi.vo.Criteria;
import com.mimi.vo.ReplyVO;
import com.mimi.vo.pageDto;

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
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper bMapper;
	
	@Autowired
	private AttachService aService;
	
	@Autowired
	private ReplyMapper rMapper;

	@Override
	public void getListXml(Model model, Criteria cri) {
		/*
		 * 1) 리스트 조회 - 검색어, 페이지 정보 
		 * 2) 총 건수 조회
		 * 3) pageDto 객체 생성
		 * 
		 *  파라미터 자동 수집 : 기본생성자를 이용해서 객체를 생성 ! -> setter 메소드를 이용해서 셋팅
		 */
		List<BoardVO> list = bMapper.getListXml(cri);
		int totalCnt = bMapper.getTotalCnt(cri);
		pageDto pageDto = new pageDto(cri, totalCnt);
		
		model.addAttribute("list", list);
		model.addAttribute("tCnt", totalCnt);
		model.addAttribute("pDto", pageDto);
		//model.addAttribute("cri", cri);
		
		// return bMapper.getListXml(cri);
	}

	@Override
	public int insert(BoardVO board) {		
		return bMapper.insert(board);
	}

	@Transactional(rollbackFor = Exception.class) 
	// 파일 등록 중 오류 발생 시 글쓰기도 실패 처리 되도록 !
	@Override
	public int insertSelectKey(BoardVO board, List<MultipartFile> files) throws Exception {
		// 게시물 등록
		int res = bMapper.insertSelectKey(board);
		// 파일 첨부
		aService.fileupload(files, board.getBno());
		// 파일 첨부 중에 오류가 발생할 경우 ? : Impl과 같은 클래스에서는 throws로 넘김 -> 해당 메소드를 호출한 곳으로 패스함
		// 최종적으로는 컨트롤러에서 처리하도록 설정해두었음
		return res;
	}

	@Override
	public BoardVO getOne(int bno) {		
		return bMapper.getOne(bno);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int update(BoardVO board, List<MultipartFile> files) throws Exception {	
		int res = bMapper.update(board);
		aService.fileupload(files, board.getBno());
		return res;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(int bno, Criteria cri) {
		// 게시물 삭제 시 댓글, 첨부된 파일이 있는 경우 오류 발생
		// -> 게시물 삭제 시 댓글 및 첨부파일이 모두 삭제 된다는 알림을 띄우고
		// 확인 버튼 클릭하면 모두 삭제 처리
		
		// 1. 첨부파일 리스트 조회하면서 삭제
		List<AttachVO> aList = aService.getList(bno);
		int res = 0;
		for(AttachVO vo : aList) {
			res += aService.delete(vo);
		}
		System.out.println("첨부파일 삭제 처리 건수 : " + res);
		// 2. 댓글 삭제
		List<ReplyVO> rList = rMapper.getList(bno, cri);
		for(ReplyVO vo : rList) {
			res += rMapper.delete(vo.getRn());
		}
		
		System.out.println("댓글 삭제 처리 건수 : " + res);
		
		bMapper.delete(bno);
		
		// 3. 게시글 삭제
		return res;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return bMapper.getTotalCnt(cri);
	}

	@Override
	public int updateReplyCnt(int bno, int amount) {
		return bMapper.updateReplyCnt(bno, amount);
	}


}
