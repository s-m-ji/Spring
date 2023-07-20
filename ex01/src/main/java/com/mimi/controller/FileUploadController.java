package com.mimi.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mimi.service.AttachService;
import com.mimi.vo.AttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@Controller
public class FileUploadController extends CommonRestController {

	@Autowired
	AttachService aService;

	// private static final String ATTACHES_DIR = "C:\\upload\\";
	private static final String ATTACHES_DIR = "C:\\Users\\user\\git\\Spring\\ex01\\src\\main\\webapp\\resources\\images\\";

	@GetMapping("/file/fileupload")
	public void fileUpload() {

	}

	/**
	 * 전달된 파일이 없는 경우 ? file.forEach로 넘어가는 부분에서 null 예외 발생 => 추후 별도의 메소드로 맹글어서 처리 예정
	 * ~
	 * 
	 * ※ 파일 업로드 실행 전 설정 확인 - 라이브러리 추가 (commons-fileupload) - bean 객체 생성
	 * (multipartResolver)
	 */
	// 파일 업로드
	@PostMapping("/file/fileUploadAction")
	public String fileAction(List<MultipartFile> files, int bno, RedirectAttributes rttr) {

		int insertRes = fileupload(files, bno); // 업로드 중 몇 건이 처리되었는지 파악하기위한 용도

		String msg = "업로드 " + insertRes + " 건 저장되었습니다.";
		rttr.addAttribute("msg", msg);
		// return "redirect:/file/fileupload?msg=" + msg; // /file ~ 이렇게 루트 경로를 잡아야 합네당
		return "redirect:/file/fileupload"; // 한글 깨짐 방지 처리를 위해서 RedirectAttributes 사용
		// 결과 : jps 파일을 반환
	}

	// 파일 업로드 - fetch
	@PostMapping("/file/fileUploadActionFetch")
	public @ResponseBody Map<String, Object> fileActionFetch(List<MultipartFile> files, int bno) {
		log.info("------------------- fileUploadActionFetch -------------------");
		int insertRes = fileupload(files, bno);
		log.info("insertRes : " + insertRes);
		return responseMap("success", "업로드 " + insertRes + " 건 저장되었습니다.!!!!!");
		// 결과 : Map 객체를 반환
	}

	// 파일 목록 조회
	@GetMapping("/file/list/{bno}") // rest 방식으로 호출 할 예정
	public @ResponseBody Map<String, Object> fileUploadList(@PathVariable("bno") int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", aService.getList(bno));
		// System.out.println("*****test : " + aService.getList(bno));
		return map;
	}

	// 파일 삭제
	@GetMapping("/file/delete/{uuid}/{bno}")
	public @ResponseBody Map<String, Object> fileDelete(@PathVariable("uuid") String uuid,
			@PathVariable("bno") int bno) {

		AttachVO att = new AttachVO();
		att.setUuid(uuid);
		att.setBno(bno);

		int res = aService.delete(att);
		if (res > 0) {
			return responseDeleteMap(res);
		} else {
			return responseDeleteMap(res);
		}

	}

	// 파일 업로드 경로 생성 (중복 방지용 : 업로드 날짜 폴더를 이름으로 사용함)
	public String getFolder() {
		LocalDate currentDate = LocalDate.now();
		// 2023-7-18 에 들어가는 '-'를 폴더 구분 기호로 바꿔줌 => 2023/7/18/
		// 참고로 File.separator 이건 운영체제(윈도우, 리눅스 등)마다 알아서 매치된다고 함 ~
		// 년,월,'일'에 해당하는 폴더까지 잘 구성하기 위해서 마지막에 File.separator를 한 번 더 넣어줌
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;

		log.info("currentDate : " + currentDate);
		log.info("uploadPath : " + uploadPath);

		// 날짜에 해당하는 폴더가 없으면 생성할 수 있도록 처리
		File saveDir = new File(ATTACHES_DIR + uploadPath);
		if (!saveDir.exists()) { // 폴더가없으면 ?
			if (saveDir.mkdirs()) { // 폴더를 만들어줘 !
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

	/**
	 * 첨부파일 저장 및 DB 등록 처리
	 * 
	 * @param files
	 * @param bno
	 * @return
	 */
	public int fileupload(List<MultipartFile> files, int bno) {
		int insertRes = 0;
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				// 현재 폼에 input[type="file"]이 3개가 있기 때문에
				// 파일이 업로드되지 않고 submit 된 태그가 있을 경우에는 알아서 패스하도록 처리함
				continue;
			}

			System.out.println("----------------------------------------------------------");
			log.info("OriginalFilename : " + file.getOriginalFilename());
			log.info("Name : " + file.getName()); // input 타입의 name을 말함
			log.info("Size : " + file.getSize());

			try {
				/**
				 * UUID 소프트웨어 구축에 쓰이는 식별자 표준 (고유한 문자열이 생성되어 최대한 중복되지 않는 값을 붙여준다.) 파일명이 중복되어 파일이
				 * 소실되지 않도록 uuid를 붙여서 저장함
				 */
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid + "_" + file.getOriginalFilename();

				String uploadPath = getFolder();
				// getFolder()는 별도 생성한 메소드임 : 파일 업로드 경로를 반환해줌
				// c:/upload /2023/7/18/ 파일명
				File sfile = new File(ATTACHES_DIR + uploadPath + saveFileName);

				file.transferTo(sfile); // file : 원본 파일 , sfile : 저장 대상 파일
				System.out.println("sfile : " + sfile);

				// 업로드된 파일 정보를 테이블에 저장
				AttachVO att = new AttachVO();
				att.setBno(bno);
				att.setFileName(file.getOriginalFilename());
				// att.setFiletype("I"); // 아래의 if문에서 셋팅해줄겨
				att.setUploadPath(uploadPath);
				att.setUuid(uuid.toString());

				// try {
				// 주어진 파일의 Mime 타입을 먼저 확인
				String contentType = Files.probeContentType(sfile.toPath());
				// Mime 타입이 이미지라면 썸네일을 자동으로 생성해주기
				if (contentType != null && contentType.startsWith("image")) {
					att.setFiletype("I");
					String thumnailPath = ATTACHES_DIR + uploadPath + "thum_" + saveFileName;
					// 원본파일, 크기, 저장될 경로
					Thumbnails.of(sfile).size(100, 100).toFile(thumnailPath);
				} else {
					att.setFiletype("F"); // 해당 파일은 이미지 타입이 아님
				}
				// } catch (Exception e) {
				// }

				System.out.println("att : " + att);

				int res = aService.insert(att);

				if (res > 0) {
					insertRes++;
					System.out.println("파일 업로드 성공");
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return insertRes;
	}

	/**
	 * 파일 다운로드 컨텐츠 타입을 다운로드 받을 수 있는 형식으로 지정하여 브라우저에서 파일을 다운로드 할 수 있게 처리
	 * 
	 * @param fileName
	 * @return
	 */
	@GetMapping("/file/download")
	public @ResponseBody ResponseEntity<byte[]> download(String fileName) {
		// ResponseEntity 이 친구로 헤더를 직접 설정할 수 있음
		log.info("download file : " + fileName);

		HttpHeaders headers = new HttpHeaders();

		File file = new File(ATTACHES_DIR + fileName);

		if (file.exists()) {
			// 컨텐츠 타입을 지정
			// APPLICATION_OCTET_STREAM 이진 파일(바이너리)의 컨텐츠 유형
			headers.add("contentType", MediaType.APPLICATION_OCTET_STREAM.toString());

			// 컨텐츠에 대한 추가 설명 및 파일명 한글 처리
			try {
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
				System.out.println("********** 파일 다운로드 성공");
				return new ResponseEntity<>(
						// FileCopyUtils : 스프링에서 제공해주는 객체
						FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				// 문제가 있을 시 500 오류로 처리
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else { // 파일이 없을 경우 ?
			System.out.println("********** 다운로드 할 파일이 없음");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
