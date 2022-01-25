package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao; 
	
	//방명록 리스트
	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> gbList = guestbookDao.getList();
		
		return gbList;
	}
	
	//방명록 추가
	public void add(GuestbookVo guestbookVo) {
		
		guestbookDao.guestbookInsert(guestbookVo);
		
	}
	
	//방명록 삭제
	public GuestbookVo delete(int no, String pass) {
		
		GuestbookVo guestbookVo = guestbookDao.guestbookDelete(no, pass);
		System.out.println(guestbookVo);
		
		return guestbookVo;
	}
	
}
