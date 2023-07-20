package com.mimi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mimi.service.AttachService;
import com.mimi.service.BoardService;
import com.mimi.vo.AttachVO;
import com.mimi.vo.BoardVO;
import com.mimi.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController extends FileUploadController {
	
	@Autowired
	BoardService bService;
	
	@Autowired
	AttachService aSerivce;
	
	//@Autowired
	//AttachService aService;
	
	@GetMapping("/reply/test")
	public String test() {
		return "/reply/test"; // 실제 요청(url)은 /board/reply/test 이렇게니까 유의 ~
	}
	
	/*
	 * ▶ ▶ ▶ 메세지 화면으로 이동
	 */
	@GetMapping("message")
	public void message(Model model, BoardVO board) {
		
	}
	
	/*
	 * ▶ ▶ ▶ 부트스트랩 테스트용 화면 연결
	 */
	/*
	@GetMapping("list_bs")
	public void list_bs(Model model) {
		List<BoardVO> list = bService.getListXml();
		model.addAttribute("list", list);
		
	}
	*/
	/*
	@GetMapping("list_sb")
	public void list_sb(Model model) {
		List<BoardVO> list = bService.getListXml();
		model.addAttribute("list", list);
		
	}
	*/
	
	/*
	 * ▶ ▶ ▶ 게시글 목록 화면으로 이동
	 */
	@GetMapping("list")
	public void getListXml(Model model, Criteria cri) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		bService.getListXml(model, cri);
		log.info("cri ▶ ▶ ▶ " + cri);
		
		stopWatch.stop();
		log.info("수행 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
	}
	
	@GetMapping("pageNavi")
	public void page() {
				
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 상세 화면으로 이동
	 */
	@GetMapping("view")
	// 매개변수로 int bno를 넘길 시 값이 안 들어왔을 때 오류 발생을 방지하고자.
	public void getOne(Model model, BoardVO paramVO) {
		BoardVO board = bService.getOne(paramVO.getBno());
		log.info("view.여기를보세요.여기를보세요.여기를보세요.여기를보세요.여기를보세요." + board);
		model.addAttribute("book", board);
		
		// 해당 게시글에 업로드 된 파일을 조회
		//List<AttachVO> fileList = aService.getList(board.getBno());
		//model.addAttribute("fileList", fileList);
		
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 작성 화면으로 이동
	 */
	@GetMapping("write")
	public void write(Model model, BoardVO board) {
		
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 작성 완료 후 폼 처리
	 * 
	 * RedirectAttributes
	 * 
	 * 리다이렉트의 URL 화면까지 데이터를 전달
	 * Model과 같이 매개변수로 받아서 사용
	 * addFlashAttribute : 세션에 저장 후 페이지를 전환
	 */
	@PostMapping("write")
	public String writePost(RedirectAttributes rdAttr, BoardVO board, List<MultipartFile> files) {
		log.info(board);
		String msg = "";
		//if(board.getBno()>0) {
			// int res = bService.insert(board);
			int res = bService.insertSelectKey(board); 
			
			// 등록 시 해당 글에 첨부 파일도 같이 등록
			fileupload(files, board.getBno());
			
			
			// -> 이렇게하면 board.getBno() 값을 바로 활용할 수 있음
			// 시퀀스 번호를 조회해서 bno에 먼저 저장해두었기때문에 ! 
			// * 아래 테스트 시 else 항목도 제대로 나오는지 보려면 
			// if 조건에서 간단히 (res<0) 이렇게 해서 똑같이 작업해볼 수도 있당 
			// 다만 값을 다 잘 맞춰주면 결과는 fail이어도 실제로는 등록이 되기때문에 유념 ! 
			if(res>0) {
				System.out.println("******************** write 성공");
				msg = board.getBno() + "번 글 write 성공";
				rdAttr.addFlashAttribute("passMsg" , msg); 
				rdAttr.addFlashAttribute("book" , board); 
				// -> addFlash~는 값이 session에 담겨져서 넘어갔다가 새로고침하면 삭제됨
				log.info(msg);
				log.info(board);
				// return "redirect:/board/list";
			} else {
				System.out.println("******************** write 실패 : res>0 위배");
				msg = board.getBno() + "번 글  write 실패  : res>0 위배";
				rdAttr.addFlashAttribute("failMsg" , msg);
				log.info(msg);
			}
		//} else {
			//System.out.println("******************** write 실패 : getBno()>0 위배");
			// msg = board.getBno() + "번 글  write 실패  : getBno()>0 위배";
			// rdAttr.addFlashAttribute("failMsg" , msg);
			//log.info(msg);
			//return "redirect:/board/write";
		//}
		// TODO bno에 ""이 들어갈 경우를 처리해야함
		return "redirect:/board/message";
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 수정 화면으로 이동
	 */
	@GetMapping("edit")
	public String edit(Model model, BoardVO paramVO) {
		BoardVO board = bService.getOne(paramVO.getBno());
		model.addAttribute("book", board);
		return "/board/write";
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 수정 완료 후 폼 처리
	 */
	@PostMapping("edit")
	public String editPost(RedirectAttributes rdAttr, List<MultipartFile> files,
								BoardVO board, Criteria cri) {
		// pageNo를 쓰기 위해 매개변수에 Criteria 사용하여 파라미터를 자동 수집함
		
		// ?pageNo=1
		// request.getParam("pageNO");
		// ${param.pageNo}
		
		// request.getAttribute("pageNo");
		// ${pageNo}
		int res = bService.update(board); 
		
		// 수정 시 파일도 함께 수정하여 올릴 수 있도록
		fileupload(files, board.getBno());
		
		String msg = "";
		if(res>0) {
			System.out.println("******************** edit 성공");
			 msg = board.getBno() + "번 글 edit 성공";
			 
			 // session에 저장되었다가 사라짐
			 // rdAttr.addFlashAttribute("passMsgPost" , msg); 
			 // rdAttr.addFlashAttribute("book" , board);
			 
			 // 파라미터로 넘겨주는 것
			/* rdAttr.addAttribute("book" , board); */
			 
			 rdAttr.addAttribute("bno" , board.getBno());
			 rdAttr.addAttribute("pageNo" , cri.getPageNo());
			 rdAttr.addAttribute("sField" , cri.getSField());
			 rdAttr.addAttribute("sWord" , cri.getSWord());
			 
			 rdAttr.addAttribute("passMsgPost" , msg); 
			 log.info(board);
			 System.out.println("res : " + res);
		} else {
			System.out.println("******************** edit 실패");
			msg = board.getBno() + "번 글  edit 실패";
			// rdAttr.addFlashAttribute("failMsg" , msg);
			rdAttr.addAttribute("failMsg" , msg);
			log.debug(board);
			System.out.println("res : " + res);
		}
		
		// redirect 시 request 영역에 저장되지 않기 때문에  RedirectAttributes를 이용해서 값을 보내기
		return "redirect:/board/message";
	}
	
	/*
	 * ▶ ▶ ▶ 게시글 삭제 처리
	 */
	
	/*
	@GetMapping("delete")
	public String delete(RedirectAttributes rdAttr, int bno) {
		int res = bService.delete(bno); 
		String msg = "";
		if(res>0) {
			System.out.println("******************** delete 성공");
			 msg = bno + "번 글 delete 성공";
			 rdAttr.addFlashAttribute("passMsg" , msg); 
		} else {
			System.out.println("******************** delete 실패");
			msg = bno + "번 글  delete 실패";
			rdAttr.addFlashAttribute("failMsg" , msg);
		}
		
		return "redirect:/board/message";
	}
	*/
	
	@GetMapping("delete")
	public String deletePost(RedirectAttributes rdAttr, @RequestParam("bno") List<Integer> delList) {
	    StringBuilder msg = new StringBuilder();
	    int passCnt = 0;

	    for (int bno : delList) {
	        if (bService.delete(bno) > 0) {
	            System.out.println("******************** delete 성공");
	            msg.append(bno).append(",");
	            passCnt++;
	        } else {
	            System.out.println("******************** delete 실패");
	            msg.append(bno).append(",");
	        }
	    }

	    if (msg.length() > 0) {
	        msg.setLength(msg.length() - 1);
	        String resultMsg = msg.toString();
	        rdAttr.addFlashAttribute(passCnt > 0 ? "passMsg" : "failMsg", resultMsg + "번 글 delete " + (passCnt > 0 ? "성공" : "실패"));
	    }

	    return "redirect:/board/message";
	}
	
	
	
	





}
