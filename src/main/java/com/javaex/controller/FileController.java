package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {

	@Autowired
	private FileService fileService;
	
	//파일 업로드 폼
	@RequestMapping("/form")
	public String Form() {
		System.out.println("FileController.form()");
		
		//forward
		return "fileupload/form";
	}
	
	//파일 업로드 처리
	@RequestMapping("/upload") //MultipartFile: 모든 종류의 첨부 파일을 담을 수 있다.
	public String result(@RequestParam("file") MultipartFile file, Model model) {//file을 파라미터로 받아 MultipartFile에 담는다.
		System.out.println("FileController.upload()");
		
		//★주의사항: file만 출력시 MultipartFile 안에 첨부파일 정보가 존재하지 않아도 무조건 출력된다.
		//System.out.println(file);
		
		//file이 제대로 request body에 첨부되어 도착했는지 확인하기 위해서는 다음과 같은 메소드를 출력해야 한다.
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getSize());
		
		String saveName = fileService.restore(file);
		model.addAttribute("saveName", saveName);
		
		// forward
		return "fileupload/result";
	}
	
}
