package com.mimi.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mimi.service.AttachService;
import com.mimi.vo.AttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@Controller
public class FileUploadController {
	
	@Autowired
	AttachService aService;
	
	//private static final String ATTACHES_DIR = "C:\\upload\\";
	private static final String ATTACHES_DIR = "C:\\Users\\user\\git\\Spring\\ex01\\src\\main\\webapp\\resources\\images\\";
	
	@GetMapping("/file/fileupload")
	public void fileUpload() {
		
	}
	
	/**
	 * 전달된 파일이 없는 경우 ? file.forEach로 넘어가는 부분에서 null 예외 발생 
	 * => 추후 별도의 메소드로 맹글어서 처리 예정 ~ 
	 * 
	 * ※ 파일 업로드 실행 전 설정 확인
	 * 	- 라이브러리 추가 (commons-fileupload)
	 * 	- bean 객체 생성 (multipartResolver)
	 */
	@PostMapping("/file/fileUploadAction")
	public String fileAction(List<MultipartFile> files, int bno, RedirectAttributes rttr) {
		
		int insertRes = 0; // 업로드  중 몇 건이 처리되었는지 파악하기위한용도 
		// files.forEach(file -> { 람다 표현식이어서 continue와 외부 변수를 쓸 수 없기에 일반 for문으로 바꿈...
			for(MultipartFile file : files) {
				
				if(file.isEmpty()) { 
					// 현재 폼에 input[type="file"]이 3개가 있기 때문에 
					// 파일이 업로드되지 않고 submit 된 태그가 있을 경우에는 알아서 패스하도록 처리함
					continue;
				}
					
				System.out.println("----------------------------------------------------------");
				log.info("OriginalFilename : " + file.getOriginalFilename());
				log.info("Name : " + file.getName());  // input 타입의 name을 말함
				log.info("Size : " + file.getSize());
				
				try {
					/**
					 *  UUID
					 *  	소프트웨어 구축에 쓰이는 식별자 표준 (고유한 문자열이 생성되어 최대한 중복되지 않는 값을 붙여준다.)
					 *  	파일명이 중복되어 파일이 소실되지 않도록 uuid를 붙여서 저장함 
					 */
					UUID uuid = UUID.randomUUID();
					String saveFileName = uuid + "_" + file.getOriginalFilename();

					String uploadPath = getFolder();
					// getFolder()는 아래에 생성해준 메소드임 : 파일 업로드 경로를 반환해줌
					//						c:/upload    /2023/7/18/ 		파일명
					File sfile = new File(ATTACHES_DIR + uploadPath + saveFileName);
					
					file.transferTo(sfile); // file : 원본 파일 , sfile : 저장 대상 파일
					System.out.println("sfile : " + sfile);
				
					// 업로드된 파일 정보를 테이블에 저장
					AttachVO att = new AttachVO();
					att.setBno(bno);
					att.setFileName(file.getOriginalFilename());
					att.setFiletype("I"); 
					att.setUploadPath(uploadPath);
					att.setUuid(uuid.toString());
					
					//try {
						// 주어진 파일의 Mime 타입을 먼저 확인
						String contentType = Files.probeContentType(sfile.toPath());
						// Mime 타입이 이미지라면 썸네일을 자동으로 생성해주기
						if(contentType != null && contentType.startsWith("image")) {
							att.setFiletype("I");
							String thumnailPath = ATTACHES_DIR + uploadPath + "thum_" + saveFileName;
							//            원본파일,  크기,           저장될 경로
							Thumbnails.of(sfile).size(100, 100).toFile(thumnailPath);
							} else {
								att.setFiletype("F"); // 해당 파일은 이미지 타입이 아님
							}
						//} catch (Exception e) {
					//}
					
					System.out.println("att : " + att);
					
					int res = aService.insert(att);
					
					if(res > 0) {
						insertRes++;
						System.out.println("파일 업로드 성공");
					}
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
			}
		// });
			
		String msg = "업로드 " + insertRes + " 건 저장되었습니다.";
		rttr.addAttribute("msg", msg);
		//return "redirect:/file/fileupload?msg=" + msg; // /file ~ 이렇게 루트 경로를 잡아야 합네당
		return "redirect:/file/fileupload"; // 한글 깨짐 방지 처리를 위해서  RedirectAttributes 사용
	}
	
	// 파일 목록 조회
	@GetMapping("/file/list/{bno}") // rest 방식으로 호출 할 예정
	public @ResponseBody Map<String, Object> fileUploadList(@PathVariable("bno") int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", aService.getList(bno));
		return map;
	}
	
	// 파일 업로드 경로 생성 (중복 방지용 : 업로드 날짜 폴더를 이름으로 사용함)
	public String getFolder() {
		LocalDate currentDate = LocalDate.now();
		// 2023-7-18 에 들어가는 '-'를 폴더 구분 기호로 바꿔줌  => 2023/7/18/
		// 참고로 File.separator 이건 운영체제(윈도우, 리눅스 등)마다 알아서 매치된다고 함 ~
		// 년,월,'일'에 해당하는 폴더까지 잘 구성하기 위해서 마지막에 File.separator를 한 번 더 넣어줌
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;
		
		log.info("currentDate : " + currentDate);
		log.info("uploadPath : " + uploadPath);
		
		// 날짜에 해당하는 폴더가 없으면 생성할 수 있도록 처리
		File saveDir = new File(ATTACHES_DIR + uploadPath);
		
		if(!saveDir.exists()) { // 폴더가없으면 ?
			if(saveDir.mkdirs()) { // 폴더를 만들어줘 !
				// mkdir() 이건 디렉토리를 하나만 만들어줌
				log.info("폴더 생성 완료");
			} else {
				log.info("폴더 생성 실패");
			}
		}
		return uploadPath;
	}
	
	// 간단히 테스트 해보기 위해서 메인메소드를 활용
	public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;
		
		log.info("currentDate : " + currentDate);
		log.info("uploadPath : " + uploadPath);
	}
	
}












