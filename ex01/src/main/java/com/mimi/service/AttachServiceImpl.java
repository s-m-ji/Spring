package com.mimi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.controller.FileUploadController;
import com.mimi.mapper.AttachMapper;
import com.mimi.vo.AttachVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class AttachServiceImpl implements AttachService {

	@Autowired
	AttachMapper aMapper;

	@Override
	public List<AttachVO> getList(int bno) {
		return aMapper.getList(bno);
	}

	@Override
	public int insert(AttachVO att) {
		return aMapper.insert(att);
	}

	@Override
	public int delete(AttachVO att) {
		// 파일을 진짜 삭제
		// 파일을 DB에서도 삭제
		
		// 우선 파일 조회, 그리고 삭제 처리 (image면 2개 삭제, 아니면 1개 삭제)
		AttachVO vo = aMapper.getOne(att);
		String savtPath = vo.getSavePath();
		String t_savtPath = vo.getT_savePath();
		
		if(savtPath != null && !savtPath.equals("")) {
			File file = new File(FileUploadController.ATTACHES_DIR + savtPath);
			if(file.exists()) {
				if(!file.delete()) { // file.delete()의 반환 타입은 boolean
					System.out.println("savtPath 파일 삭제 실패..힝");
				} 
			}
		}
		if(t_savtPath != null && !t_savtPath.equals("")) {
			File file = new File(FileUploadController.ATTACHES_DIR + t_savtPath);
			if(file.exists()) {
				if(!file.delete()) {
					System.out.println("t_savtPath 파일 삭제 실패..힝");
				}
			}
		}
		return aMapper.delete(att);
	}
	
	/**
	 * 첨부파일 저장 및 DB 등록 처리
	 * 
	 * @param files
	 * @param bno
	 * @return
	 * @throws Exception 
	 */
	public int fileupload(List<MultipartFile> files, int bno) throws Exception {
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
				File sfile = new File(FileUploadController.ATTACHES_DIR + uploadPath + saveFileName);

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
					String thumnailPath = FileUploadController.ATTACHES_DIR + uploadPath + "thum_" + saveFileName;
					// 원본파일, 크기, 저장될 경로
					Thumbnails.of(sfile).size(100, 100).toFile(thumnailPath);
				} else {
					att.setFiletype("F"); // 해당 파일은 이미지 타입이 아님
				}
				// } catch (Exception e) {
				// }

				System.out.println("att : " + att);

				int res = insert(att);

				if (res > 0) {
					insertRes++;
					System.out.println("파일 업로드 성공");
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
				// 파일 등록 중 오류 발생 시 처리
				throw new Exception("헉스베리..(IllegalStateException) 첨부 파일 등록 중에 예외 발생 !");
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("헉스베리..(IOException) 첨부 파일 등록 중에 예외 발생 !");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("헉스베리..(Exception) 첨부 파일 등록 중에 예외 발생 !");
			}

		}
		return insertRes;
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
			File saveDir = new File(FileUploadController.ATTACHES_DIR + uploadPath);
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

	
	
}
