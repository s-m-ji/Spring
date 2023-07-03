package com.mimi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mimi.vo.Member;
import com.mimi.vo.MemberList;

/*
 * 스프링MVC에서 제공하고있는 @어노테이션을 이용하여 Controller를 작성해보장
 * 
 * 톰켓 서버를 실행하면 web.xml파일의 설정을 읽어 서버를 시작. 
 * web.xml파일에 기술되어있는 servlet-context.xml파일의 
 * component-scan에 등록된 패키지를 탐색하며 클래스를 조사하고
 * 객체 설정에 사용되는 어노테이션들을 가진 클래스를 객체로 생성하고 관리.
 * 
 * MVC에서 사용되는 어노테이션을 학습해보장
 *
 * ▶ ▶ ▶  @Controller
 *	해당 클래스의 인스턴스를 스프링의 빈으로 등록하고 컨트롤러로 사용
 * <장점>
 * 1) 파라미터를 자동으로 수집
 * 2) URL 매핑을 메소드 단위로 처리
 * 3) 화면에 전달할 데이터는 Model에 담아주기만 하면 됨
 * 4) 간단한 페이지 전환(forward, redirect)
 * 5) 상속/인터페이스 방식 대신에 어노테이션만으로도 필요한 설정 가능 
 */

@Controller
//아래의 value = "/" 값이 HomeController와 겹쳐서 해당 클래스에 @RequestMapping를 추가함
@RequestMapping("/mapping/*")

/* ▶ ▶ ▶  @RequestMapping
 * 클래스의 상단에 적용 시 현재 클래스의 모든 메소드들의 기본 URL 경로를 지정
 * 메소드의 상단에 적용 시 해당 메소드의 URL 경로를 지정
 * 
 * get, post 방식 모두 처리하고 싶은 경우 배열로 받을 수 잇음.
 * 
 *  /mapping/requestMapping URL를 GET 메소드를 호출하면 해당 메소드가 실행됨.
 */

public class MappingController {
	
	// 사용자의 요청을 받음 ( @GetMapping, @PostMapping과 비슷?)
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String requestMapping() {

		return "mapping";
	}

	// get, post 두가지 방식을 모두 사용
	// /mapping/requestMapping URI를 GET 메소드르 호출하면 해당 메소드가 실행됨
	@RequestMapping(value="/requestMapping", method= {RequestMethod.GET, RequestMethod.POST})
	public String requestMapping2() {
		
		System.out.println("---------- /requestMapping 호출 ~~~");
		return "mapping";
	}
	
	
	@GetMapping("/getMapping")
	/*
	 * 스프링 4.3이후에는 GetMapping, PostMapping 등으로 간단히 표현 가능
	 * 어노테이션 사용이 불가능할 경우 스프링의 버전을 확인
	 * 
	 * ▶ ▶ ▶  @GetMapping : Get 방식의 요청을 처리
	 * 
	 * 파라미터 자동 수집
	 * @RequestParam을 이용하면 기본 타입의 데이터를 지정한 타입으로 받을 수 있음
	 * VO 객체를 지정할 경우 객체를 생성 후 파라미터의 name 속성과 일치하는 필드에 셋팅
	 * -> 단, 둘 다  타입 불일치라면 400 오류(사용자 요청 오류) 발생 가능
	 * 
	 */
	
	// 리퀘스트로부터 넘어온 파라미터 name의 값을 
	public String getMapping(
			@RequestParam("name") String name
			, @RequestParam("age") int age
			, Model model
			) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		System.out.println("---------- /getMapping 호출 ~~~");
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		
		return "mapping";
	}
	
	
	/*
	 * 파라미터를 Vo, Dto에 수집한 경우, 별도의 저장 없이 화면까지 전달된다.
	 * 
	 * 그 외 화면에 값을 전달하려면 Model 객체를 매개변수로 받아서 addAttribute 메소드 이용 
	 * model.addAttribute(이름, 값)
	 */
	@GetMapping("getMappingVO")
	public String getMappingVO(Member member, Model model) {

		return "mapping";
	}
	
	@GetMapping("getMappingMSG")
	public String getMappingMSG(Member member, Model model) {
		
		model.addAttribute("msg", "파라미터 자동수집 ~~~");
		return "mapping";
	}
	
	@GetMapping("getMappingArr")
	public String getMappingArr(@RequestParam("ids") String[] ids) {
		
		for(String id : ids) {
			System.out.println("id : " + id);
		}
		return "mapping";
	}
	
	
	@GetMapping("getMappingList")
	public String getMappingList(@RequestParam("ids") List<String> ids) {
		
		/*
		 * ForEach : 익명의 함수를 이용한 컬렉션의 반복 처리
		 * collection.forEach (변수 -> 반복처리(변수))
		 */
		
		// 람다식
		ids.forEach(id -> {;
			System.out.println("id : " + id);
		});
		return "mapping";
	}
	
	
	@GetMapping("getMappingMemberList")
	public String getMappingMemberList(MemberList list) {
		System.out.println("list : " + list);
		
		return "mapping";
	}

}
















