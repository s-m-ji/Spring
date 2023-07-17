package com.mimi.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * Spring Interceptor
 * 
 * HTTP 요청 처리 과정에서 요청을 가로채고 처리 전,후에 추가 작업을 수행함.
 * 인터셉터는 컨트롤러에 진입하기 전, 컨트롤러(Controller) 실행 후, 
 * 뷰(View) 렌더링 전 등 다양한 시점에서 동작
 * 사용하여 요청의 처리 흐름을 제어해주거나 조작할 수 있음 => 인증 권한 및 
 * 
 * 로그인 된 사용자만 접근할 수 있도록 화면 전환을 처리함
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * preHandle 
	 * 	컨트롤러를 실행하기 전에 실행
	 * return : true (요청 컨트롤러 실행) /false (요청 컨트롤러 실행하지않음)
	 * 
	 *  ▶ 로그인이 되어있지 않은 경우, 로그인 페이지로 이동
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			// 우선 request로 요청을 받음
			HttpSession session = request.getSession();
			if(session.getAttribute("userId") != null && !session.getAttribute("userId").equals("")) {
				return true;
			} else {
				String msg = URLEncoder.encode("로그인 후 사용 가능한 메뉴입니다아아아오", "utf-8");
				response.sendRedirect("/login?msg="+msg);
				return false;
			}
	}
}



















