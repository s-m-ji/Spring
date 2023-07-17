package com.mimi.mapper;

import java.util.List;

import com.mimi.vo.Member;

public interface MemberMapper {
	public Member login(Member meber);
	
	public int register(Member meber);
	
	public int idCheck(Member meber);

	public List<String> getMemberRole(String id);
}
