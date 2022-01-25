package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	//로그인-회원 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}

	//회원가입
	public void join(UserVo userVo) {
		userDao.userInsert(userVo);

	}
	
	//회원 정보 수정
	public void modify(UserVo userVo) {
		
		userDao.userUpdate(userVo);
		
	}
}
