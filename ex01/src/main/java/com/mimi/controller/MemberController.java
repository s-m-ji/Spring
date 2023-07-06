package com.mimi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mimi.service.MemberService;
import com.mimi.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	
	/**
	 * 로그인 페이지로 이동
	 * @return
	 */
	@GetMapping("/login") // 1. /login 이라는 요청이 들어오면
	public String login() { // 메소드 이름은 임의 설정
		
		return "login"; // 2. login.jsp 파일을 호출해줌
	} 
	
	
	@PostMapping("/loginAction")
	//public String loginAction(Member member, Model model) { 
		public String loginAction(Member member, RedirectAttributes rdAttr) { 
		// 매개변수로 member 객체를 넣으면 알아서 id, pw 등등 가져와줌
		// 그리고 Model 객체를 통해 메세지를 출력 (like HttpServletResponse 객체)
		
		//System.out.println("ID : " + member.getId());
		//System.out.println("PW : " + member.getPw());
		//System.out.println("NAME : " + member.getName());
		
		service.login(member, rdAttr);
		//model.addAttribute("message", member.getId() + " 환상의 나라로 오세요 ~ 🎈🎆✨🎉🎢🎪🎠🎡  ");
		
		System.out.println("-------- MemberController service 실행 완료");
		
		// return "main";
		return "redirect:/board/list";
	}
	
}
