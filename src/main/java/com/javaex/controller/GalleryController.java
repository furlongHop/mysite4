package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	public String list() {
		System.out.println("GalleryController>list()");
		
		
		
		return "";
	}
	
}
