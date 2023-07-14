package com.mimi.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.service.MemberService;
import com.mimi.vo.Member;

// @RestController // 값을 rest 형식으로 내보낼 때 쓰는 ?
@Controller 
public class MemberController extends CommonRestController {
	
	@Autowired
	MemberService mService;
	
	
	/**
	 * 로그인 페이지로 이동
	 * @return
	 */
	@GetMapping("/login") // 1. /login 이라는 요청이 들어오면
	public String login() { // 메소드 이름은 임의 설정
		return "login"; // 2. login.jsp 파일을 호출해줌
	} 
	
	/**
	 * 로그아웃 처리 후 로그인 페이지로 이동
	 * @param session
	 * @return
	 */
	@GetMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate();
		return "login"; 
	} 
	

	@PostMapping("/loginAction")
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, HttpSession session) {
		//@ResponseBody가 없으면 404 오류 발생함 : PageNotFound - No mapping found for HTTP request with URI [/loginAction] 
		// JSON 타입으로 데이터를 받고, 다시 그 데이터를 전달하기 위해서 어노테이션을 각각 사용함
		System.out.println("---------------------------------------------------------");
		System.out.println("id : " + member.getId());
		System.out.println("pass : " + member.getPass());
		
		member = mService.login(member);
		
		if(member != null) {
			session.setAttribute("member", member); // ${member.id}로 쓰면 getId()랑 동일한 결과 출력 가능
			session.setAttribute("userId", member.getId()); 
			return responseMap(REST_SUCCESS, "로그인 성공 축하축하 ~");
		} else {
			return responseMap(REST_FAIL, " 아이디, 비밀번호를 확인해줘잉 \n ※ 암호화 되지 않은 비번은 이제 쓸 수 없다!");
		}
		
	}
	
	@PostMapping("/regiAction")
	public @ResponseBody Map<String, Object> regiAction(@RequestBody Member member){
		System.out.println("---------------------------------------------------------");
		System.out.println("id : " + member.getId());
		
		try { // 회원가입 중 발생할 수 있는 오류를 방지하고자 try 구문으로 감싸주기
			int res = mService.register(member); 
			// 아이디 중복 체크 등등은 프론트에서 거르고 백으로 보내줌. (login.jsp 파일 내 javascript 참고)
			return responseWriteMap(res);
		} catch (Exception e) {
			e.printStackTrace();
			return responseMap(REST_FAIL, "등록 중 예외 사항이 발생 흑흑 !");
		}
	}
	
	
	@PostMapping("/idCheckAction")
	public @ResponseBody Map<String, Object> idCheckAction(@RequestBody Member member){
		int chk = mService.idCheck(member);
		if(chk == 0) {
			return responseMap(REST_SUCCESS, "사용 가능한 아이디유 ~");
		} else {
			return responseMap(REST_FAIL, "이미 존재하는 아이디유 !");
		}
	}
		


	// dao로 비즈니스 로직을 처리했던 기록
	/* 
	@PostMapping("/loginAction")
	//public String loginAction(Member member, Model model) { 
		public String loginAction(Member member, RedirectAttributes rdAttr) { 
		// 매개변수로 member 객체를 넣으면 알아서 id, pw 등등 가져와줌
		// 그리고 Model 객체를 통해 메세지를 출력 (like HttpServletResponse 객체)
		
		//System.out.println("ID : " + member.getId());
		//System.out.println("PW : " + member.getPw());
		//System.out.println("NAME : " + member.getName());
		
		//service.login(member, rdAttr);
		//model.addAttribute("message", member.getId() + " 환상의 나라로 오세요 ~ 🎈🎆✨🎉🎢🎪🎠🎡  ");
		
		System.out.println("-------- MemberController service 실행 완료");
		
		// return "main";
		return "redirect:/board/list";
	}
	*/
	
	
	
}
