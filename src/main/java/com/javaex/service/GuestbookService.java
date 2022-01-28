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
	
	//방명록 추가 --> 추가한 방명록 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService>addGuestResultVo");
		
		//저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no);
	}
	
	//방명록 삭제
	public GuestbookVo delete(int no, String pass) {
		
		GuestbookVo guestbookVo = guestbookDao.guestbookDelete(no, pass);
		System.out.println(guestbookVo);
		
		return guestbookVo;
	}
	
}
