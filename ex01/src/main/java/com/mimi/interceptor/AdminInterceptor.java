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
public class AdminInterceptor implements HandlerInterceptor { // 인터셉터는 핸들러의 구현체이므로 implements 할 것.
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
		
		String msg = URLEncoder.encode("관리자로 로그인 후 사용 가능한 메뉴입니다.", "utf-8"); // 인터셉터에서는 한글 처리가 되지 않으므로 여기서 encode 처리 ! 
		response.sendRedirect("/login?msg=" + msg);
		return false; // 어드민 아님 -> 로그인 페이지로 이동시킴
	}
}
