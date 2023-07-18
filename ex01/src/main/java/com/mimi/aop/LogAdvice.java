package com.mimi.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.mimi.service.LogService;
import com.mimi.vo.LogVo;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	/**
	 * AOP(Aspect-Oriented Programming)
	 * 	관점지향 프로그래밍
	 * 	핵심 비즈니스 로직과 부가적인 관심사를 분리하여 개발하는 방법론
	 * 
	 *  코드의 중복을 줄이고 유지보수성을 향상 시킬 수 있다.
	 *  
	 *  부가적인 관심사
	 *  	로깅, 보안, 트랜잭션 관리 등
	 *  	애플리케이션에서 공통적으로 처리해야하는 기능
	 *  
	 *  Aspect
	 *  	부가적인 관심사를 모듈화한 단위
	 *  	(Advice를 그룹화) Cross Concern : 횡단 관심사
	 *   	 주 업무로직 이외의 부가적인 기능을 의미
	 *  Advice
	 *  	부가적인 관심사
	 *  
	 *  Point cut
	 *  	부가기능이 적용되는 시점 (언제 ? 어디에? 적용할 것인지를 기술함)
	 *  
	 *  Target
	 *  	핵심 기능을 구현한 객체 (Core Concern : 핵심 관심사)
	 *  
	 *  Proxy
	 *  	Target + Advice
	 *  
	 *  예시)  오류 발생 시 데이터베이스에 저장
	 */

	/**
	 * 	Before 
	 * 		타겟 객체의 메소드가 실행되기 전에 호출되는 어드바이스
	 * 		Joinpoint를 통해 파라미터 정보 참조 가능 	
	 */
	/*
	@Before("execution(* com.mimi.service.Board*.*(..))") 
	// Before : 메소드 실행 전에 !
	private void logBefore() {
		log.info("[logBefore] ========================================================");
	}
	*/
	
	/**
	 * JoinPoint
	 * 	타겟에 대한 정보와 상태를 담고 있는 객체로 매개변수로 받아서 사용
	 */
	@Before("execution(* com.mimi.service.Reply*.*(..))") 
	private void logBeforeParams(JoinPoint joinPoint) {
		log.info("[logBeforeParams] ========================================================");
		log.info("Param : " + Arrays.toString(joinPoint.getArgs()));
		log.info("Target : " + joinPoint.getTarget());
		log.info("Method : " + joinPoint.getSignature().getName());
	}
	
	
	/**
	 * Around
	 * 	타겟의 메소드가 호출되기 이전 시점과 이후 시점에 모두 처리해야 할 필요가 있는 부가 기능 정의.
	 *  주 업무 로직을 실행하기 위해 JoinPoint 하위 클래스인 
	 *  ProceedingJoinPoint 타입의 파라미터를 필수적으로 선언해야함 !!!
	 */
	
	/*
	@Around("execution(* com.mimi.service.Board*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) { // 타겟 메소드 실행 결과를 반환하기 위해 매개변수를 받음

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object res = "";
		try {
			res = pjp.proceed(); // 메소드 실행 결과를 꼭 res에 담아두어야함 
			// 주 업무로직 실행 (타켓 메소드의 실행 시점을 내가 지정할 수 있음)
		} catch (Throwable e) {
			e.getStackTrace();
			e.getMessage();
		}
		
		stopWatch.stop();

		log.info("[logTime] ========================================================");
		// 어떤 메소드가 실행되어서 logTime이 찍히는지에 대한 정보를 추가적으로 남겨두면 콘솔창에서 볼 수 있음 ! 
		log.info( "************************** " + pjp.getTarget() + "." +	pjp.getSignature().getName() );
		log.info( "************************** " + "▶▶▶  수행시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return res;
	}
	*/
	
	@Autowired
	LogService lService;
	
	/**
	 * AfterThrowing
	 * 	타켓 메소드 실행 중 예외가 발생한 뒤에 실행할 부가 기능
	 * 	오류가 발생한 내역을 테이블에 등록	
	 */
	
	// 로그 남기는 객체를 자동 주입 받기  
	@AfterThrowing(pointcut="execution(* com.mimi.service.*.*(..))" 
									// service 하위의 모든 메소드를 대상으로 함
					, throwing = "exception") 
	public void logException(JoinPoint joinPoint, Exception exception) {
		// 예외 발생 그 내용을 테이블에 저장함.
		try {
			LogVo vo = new LogVo();
			vo.setClassname(joinPoint.getTarget().getClass().getName());
			vo.setMethodname(joinPoint.getSignature().getName());
			vo.setParams(Arrays.toString(joinPoint.getArgs())); 
						// 파라미터는 배열로 들어오기 때문에 스트링화 하였음
			vo.setErrmsg(exception.getMessage());
			
			lService.insert(vo);
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>> 로그 테이블에 저장됨 "); 
					// 트랜잭션 처리가 된 부분은 오류 발생 시 원복되기때문에 로그 테이블에 남지 않음 
			
		} catch (Exception e) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>> 로그 테이블 저장 중 예외 발생");
			e.getMessage();
			e.getStackTrace();
		}
	}
	
	
	
}










