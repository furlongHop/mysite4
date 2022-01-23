package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//유저 정보 가져오기(로그인 때 사용)
	public void getUser(UserVo userVo) {
		System.out.println("UserDao>getUser");
		System.out.println(userVo);
		
		
	}

}
