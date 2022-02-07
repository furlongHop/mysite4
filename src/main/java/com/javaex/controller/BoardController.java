package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

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
		
		return "board/list";
	}
	
	// 게시판 목록 (리스트+페이징)
	@RequestMapping(value = "/list2")
	public String list2(Model model, 
						//@RequestParam을 쓸 때엔 반드시 그 값이 존재해야 한다. 없을 수도 있다면 그 경우 디폴트값으로 보낼 수 있게 코드를 추가해야 한다.
						//required = false: 해당 파라미터를 받지 못했다면, defaultValue = "1": 해당 파라미터 값을 1로 처리한다.
						@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("boardController>list2");
		System.out.println(crtPage);
		
		//해당 페이지의 글 목록 10개
		Map<String, Object> pMap = boardService.getBoardList(crtPage);
		
		model.addAttribute("pMap", pMap);
		
		
		return "board/list";
	}
		
		
	
	// 게시글 읽기
	@RequestMapping(value = "/read")
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("boardController>read");
		
		boardService.read(no);
		BoardVo boardVo = boardService.boardSelect(no);
		model.addAttribute("board", boardVo);
		
		System.out.println(boardVo);
		
		return "board/read";
	}
	
	
	// 게시글 작성
	@RequestMapping(value = "/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("boardController>write");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		boardService.boardInsert(boardVo);
	
		return "redirect:/board/list";
	}
	
	// 게시글 작성폼
	@RequestMapping(value = "/writeForm")
	public String writeForm() {
		System.out.println("boardController>writeForm");

	
		return "board/writeForm";
	}
	
	
	// 게시글 수정
	@RequestMapping(value = "/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("boardController>modify");

		boardService.boardUpdate(boardVo);
		
		return "redirect:/board/list";
	}
	
	// 게시글 수정폼
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("boardController>modifyForm");

		BoardVo boardVo = boardService.boardSelect(no);
		model.addAttribute("board", boardVo);
		
		return "/board/modify";
	}
	
	
	// 게시글 삭제
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("boardController>delete");

		boardService.boardDelete(no);
	
		return "redirect:/board/list";
	}

}
