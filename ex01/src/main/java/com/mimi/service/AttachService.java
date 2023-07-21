package com.mimi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.vo.AttachVO;

@Service
public interface AttachService {
	public List<AttachVO> getList(int bno);

	public int insert(AttachVO att);
	
	public int delete(AttachVO att);
	
	// 파일 등록을 위해 추가함
	public int fileupload(List<MultipartFile> files, int bno) throws Exception;
}
