package com.javaex.dao;

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
	
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		
		return guestbookList;
	}
	
	
	//방명록 추가
	public int guestbookInsert(GuestbookVo gbVo){
		System.out.println("guestbookInsert");
	
		int count =sqlSession.insert("guestbook.insert", gbVo);
		System.out.println(count + "건 추가 되었습니다.");
		
		return count;
	}
	
	//방명록 저장(selectKey)
	public int insertSelectKey(GuestbookVo guestbookVo){
		System.out.println("insertSelectKey");
		
		/*
		 * System.out.println(guestbookVo);
		 * sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		 * System.out.println(guestbookVo);
		 */
		
		//성공한 개수 리턴
		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);	
	}
	
	//방명록 글 1개 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("guestbookDao/selectGuest");

		return sqlSession.selectOne("guestbook.selectByNo", no);
	}
	
	//방명록 삭제
	public int guestbookDelete(GuestbookVo guestbookVo) {
		System.out.println("guestbookDelete");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);//("namespace.id",parameterType)
		System.out.println(count + "건 삭제되었습니다.");
		
		return count;
	}
	
	
}
