package com.mimi.mapper;

import com.mimi.vo.Member;

public interface MemberMapper {
	public Member login(Member meber);
	
	public int register(Member meber);
	
	public int idCheck(Member meber);
}
