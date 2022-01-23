package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@RequestMapping(value="/guestbook",method= {RequestMethod.GET,RequestMethod.POST})
@Controller
public class GuestbookController {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//방명록 리스트 불러오기
	@RequestMapping(value="/addList")
	public String addList(Model model) {
		System.out.println("GuestbookController>addList");
		
		List<GuestbookVo> gbList = guestbookDao.getList();
		model.addAttribute("gbList", gbList);
		
		return "/guestbbok/addList";
	} 
	
	//방명록 추가
	@RequestMapping(value="/add")
	public String add(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("GuestbookController>add");
		
		guestbookDao.guestbookInsert(gbVo);
		
		return "redirect:/guestbook/addList";
	} 
	
	
	
	//방명록 삭제 폼
	@RequestMapping(value="/deleteForm")
	public String deleteForm() {
		System.out.println("GuestbookController>deleteForm");
		
		
		
		return "guestbook/delteForm";
	} 
	
	//방명록 삭제하기
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("no") int no, @RequestParam("password") String pass) {
		System.out.println("GuestbookController>delete");
		
		guestbookDao.guestbookDelete(no, pass);
		
		return "redirect:/guestbook/addList";
	} 
	
}
