package com.mimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mimi.mapper.MemberMapper;
import com.mimi.vo.Member;

@Service
public class MemberServiceImpl implements MemberService  {

	@Autowired
	MemberMapper mMapper;

	@Autowired
	// 암호화 관련 클래스 : pom.xml에서 라이브러리 추가 필요
	BCryptPasswordEncoder encoder;
	
	@Override
	public Member login(Member pMember) {
		
		// 이미 등록된 사용자 정보 조회
		Member member = mMapper.login(pMember);
		System.out.println("---------------------------------------------------------");
		System.out.println("pMember.getPass() : " + pMember.getPass());
		System.out.println("member.getPass() : " + member.getPass());
		if(member != null) {
											// 사용자가 입력한 비번, 		DB에서 가져온 암호화된 비번
			boolean matches = encoder.matches(pMember.getPass(), member.getPass()); 
			// 비번 인증에 성공하면 member 객체를 반환
			if(matches) { 
				return member;
			}
		}
		return null;
	}

	@Override
	public int register(Member member) {
		member.setPass(encoder.encode(member.getPass()));
		System.out.println("-----------------------------------------------------------------");
		System.out.println("********** 회원 가입 시 암호화 된 비밀번호 : " + member.getPass());
		return mMapper.register(member);
	}

	@Override
	public int idCheck(Member member) {
		return mMapper.idCheck(member);
	}
}
