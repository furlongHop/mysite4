package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("ApiGuestbookController.addList()");
	
		return "aGuestbook/addList";
	}
	
	//request: ajax 형태로 요청(addList.jsp 파일 참조)
	//data를 response body에 text(json 형태)로 담아 보낸다. 
	@ResponseBody
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write()");
		
		//저장 후 저장한 값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		//jsp 파일(화면)을 요청한 것이 아니라 data만 요청했기 때문에 data를 return 해준다.
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write2()");
		
		//저장 후 저장한 값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		//jsp 파일(화면)을 요청한 것이 아니라 data만 요청했기 때문에 data를 return 해준다.
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(guestbookVo);
		
		String result = guestbookService.remove(guestbookVo);
		//"success" "fail"
		System.out.println(result);
		
		return result;
	}
}
