package com.mimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mimi.mapper.MemberMapper;
import com.mimi.vo.Member;

@Service
public class MemberServiceImpl implements MemberService  {

	@Autowired
	MemberMapper mMapper;

	@Override
	public Member login(Member member) {
		return mMapper.login(member);
	}
}
