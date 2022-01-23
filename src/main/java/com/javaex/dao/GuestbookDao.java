package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
		
	//방명록 리스트 가져오기
	public List<GuestbookVo> getList(){
		System.out.println("getList");
	
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.getList");
		
		return guestbookList;
	}
	
	
	//방명록 추가
	public int guestbookInsert(GuestbookVo gbVo){
		System.out.println("guestbookInsert");
	
		int count =sqlSession.insert("guestbook.insert", gbVo);
		System.out.println(count + "건 추가 되었습니다.");
		
		return count;
	}
	
	//방명록 삭제
	public int guestbookDelete(int no, String password) {
		System.out.println("guestbookDelete");
		
		//<map> 사용해보기
		GuestbookVo guestbookVo = new GuestbookVo(no,password);
		int count = sqlSession.delete("guestbook.delete", guestbookVo);//("namespace.id",parameterType)
		System.out.println(count + "건 삭제되었습니다.");
		
		return count;
	}
	
	
}
