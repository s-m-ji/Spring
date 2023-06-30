package com.mimi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.mimi.vo.Member;

@Component // 이렇게 컴포넌트로 등록해두어야 MemberSevice에서 주입할 수 있음 
public class MemberDao {
	
	public Member login(Member paramMember) {
		Member member = null;
		
		String sql = 
				String.format("select * from member where id='%s' and pass='%s'"
						, paramMember.getId(), paramMember.getPw());
		
		try (Connection conn = com.mimi.dao.ConnectionUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){

			if(rs.next()) {
				String name = rs.getString("name");
								
				member = new Member();
				member.setId(paramMember.getId());
				member.setName(name);
			}
			
			System.out.println(" ---------- MemberDao 로그인 성공 ");
			System.out.println(" ---------- getPw() " + paramMember.getPw());
			System.out.println(" ---------- getId() " + paramMember.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" ---------- MemberDao 로그인 실패 ");
			System.out.println(" ---------- getPw() " + paramMember.getPw());
			System.out.println(" ---------- getId() " + paramMember.getId());
		}
		
		return member;
	}

}
