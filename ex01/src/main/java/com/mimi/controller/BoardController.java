package com.mimi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mimi.service.BoardService;
import com.mimi.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	@GetMapping("list")
	public void getListXml(Model model) {
		List<BoardVO> list = bService.getListXml();
		log.info("여기를보세요.여기를보세요.여기를보세요.여기를보세요.여기를보세요." + list);
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("view")
	public void getOne(Model model, int bno) {
		BoardVO board = bService.getOne(bno);
		log.info("여기를보세요.여기를보세요.여기를보세요.여기를보세요.여기를보세요." + board);
		model.addAttribute("book", board);
		
	}
	
	@GetMapping("write")
	public void write(Model model, BoardVO board) {
		
	}
	
	@PostMapping("write")
	public String writeAction(Model model, BoardVO board) {
		log.info(board);
		int res = bService.insert(board);
		String msg = "";
		if(res>0) {
			System.out.println("******************** write 성공");
			 msg = "write 성공";
			 model.addAttribute("msg" , msg);
		} else {
			System.out.println("******************** write 실패");
			msg = "write 실패";
			model.addAttribute("msg" , msg);
		}
		return "redirect:/board/list";
	}

}
