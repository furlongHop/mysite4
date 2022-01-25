package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@RequestMapping(value = "/board", method = { RequestMethod.GET, RequestMethod.POST })
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시판 목록
	@RequestMapping(value = "/list")
	public String list(Model model) {
		System.out.println("boardController>list");
		
		List<BoardVo> boardList = boardService.list();
		model.addAttribute("boardList", boardList);
		
		return "redirect:/board/list";
	}
	
	// 게시글 읽기
	@RequestMapping(value = "/read")
	public String read() {
		System.out.println("boardController>read");

	
		return "board/read";
	}
	
	
	// 게시글 작성
	@RequestMapping(value = "/write")
	public String write() {
		System.out.println("boardController>write");

	
		return "redirect:/board/list";
	}
	
	// 게시글 작성폼
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		System.out.println("boardController>writeForm");

	
		return "board/write";
	}
	
	
	// 게시글 수정
	@RequestMapping(value = "/modify")
	public String modify() {
		System.out.println("boardController>modify");

	
		return "redirect:/board/list";
	}
	
	// 게시글 수정폼
	@RequestMapping(value = "/modifyForm")
	public String modifyForm() {
		System.out.println("boardController>modifyForm");

	
		return "/board/modify";
	}
	
	
	// 게시글 삭제
	@RequestMapping(value = "/delete")
	public String delete() {
		System.out.println("boardController>delete");

	
		return "redirect:/board/list";
	}

}
