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
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao>selectUser");
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		
		return authUser;
	}
	
	//유저 정보 추가하기(회원가입)
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao>userInsert");
		
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println("유저" + count + "명이 새로 가입했습니다.");
		
		return count;
	}

}
