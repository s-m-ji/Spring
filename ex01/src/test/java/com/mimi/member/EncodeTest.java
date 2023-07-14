package com.mimi.member;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {
	/**
	 * 스프링 시큐리티 모듈에서 제공하는 비밀번호 암호화 및 인증 기능
	 * (Spring Security)에서 제공하는 비밀번호를 암호화 하는 데 사용할 수 있는 메소드를 가진 클래스.
	 */
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void test() {
		String pw = "안녕하새우";
		for(int i=0; i<10; i++) {
			
			// 암호화 할 때 마다 새로운 문자열을 반환하며, 복호화가 불가능함
			String encodePw = encoder.encode(pw);
			System.out.println("암호화 된 문자열 : "  + encodePw);
			
			// 첫번째 매개변수는 일치 여부를 확인하고자 하는 인코딩 되지 않은 메소드
			// 두번째 매개변수는 인코딩 된 패스워드를 입력
			
			// 제출된 인코딩 되지 않은 패스워드 (일치 여부를 확인하고자 하는 패스워드)와
			// 인코딩 된 패스워드의 일치 여부를 확인해줌.
			boolean matches = encoder.matches(pw, encodePw);
			System.out.println("인증 결과 : " + matches);
		}
	}
}
