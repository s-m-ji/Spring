package com.mimi.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mimi.vo.Member;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		HttpSession session = request.getSession();
		if(session.getAttribute("member") != null) {
			Member mem = (Member)session.getAttribute("member");
			List<String> role = mem.getRole();
			// System.out.println("TEST ********** role : " + role);
			if(role.contains("admin_role")) {
				return true; // 어드민이 맞음
			}
		}; 
		
		String msg = URLEncoder.encode("관리자로 로그인 후 사용 가능한 메뉴입니다.", "utf-8");
		response.sendRedirect("/login?msg=" + msg);
		return false; // 어드민 아님 -> 로그인 페이지로 이동시킴
	}
}
