package com.mimi.service;

import org.springframework.stereotype.Service;

import com.mimi.vo.Member;

@Service // 서비스 어노테이션을 달아야 자동 인식
public interface MemberService {
	
	/*
	// @Autowired // new MemberDao() 생성 과정을 생략할 수 있음
	// MemberDao dao;
	
	public Member login(Member paramMember, RedirectAttributes rdAttr) {
		Member member = dao.login(paramMember);
		if(member == null) {
			//model.addAttribute("message", "ID 또는 PW를 확인해줘잉");
			System.out.println(" ---------- MemberSevice 로그인 실패");
		} else {
			//model.addAttribute("message", member.getId() + " 환상의 나라로 오세요 ~ 🎈🎆✨🎉🎢🎪🎠🎡  ");
			rdAttr.addFlashAttribute("message", member.getId() + " 환상의 나라로 오세요 ~ 🎈🎆✨🎉🎢🎪🎠🎡  ");
			System.out.println(" ---------- MemberSevice 로그인 성공");
		}
		return member;
	}
	*/
	
	public Member login(Member member);
}
