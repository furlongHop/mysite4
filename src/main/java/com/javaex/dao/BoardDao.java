package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//게시글 목록
	public List<BoardVo> boardList(){
		
		List<BoardVo> bList = sqlSession.selectList("board.selectList");
		
		return bList;
	}
	
	//게시글 목록 (리스트 + 페이징)
	public List<BoardVo> selectList(int startRnum, int endRnum) {
		System.out.println("BoardDao>list2");
		//System.out.println(startRnum + "," + endRnum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", map);
		//System.out.println(boardList);
		
		return boardList;
	}
	
	//전체 글 개수 가져오기
	public int selectTotal() {
		System.out.println("boardDao>selectTotal");
		
		return sqlSession.selectOne("board.totalCount");
	}
	
	
	
	//조회수 증가
	public void boardHit(int no) {
		
		sqlSession.selectOne("board.updateHit", no);
		
	}
	
	//게시글 선택
	public BoardVo boardSelect(int no) {
		
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		
		return boardVo;
	} 
	
	//게시글 추가
	public int boardInsert(BoardVo boardVo) {
		
		return sqlSession.insert("board.insert", boardVo);
		
	}
	
	//게시글 삭제
	public void boardDelete(int no) {
		
		sqlSession.delete("board.delete", no);
		
	}
	
	//게시글 수정
	public void boardUpdate(BoardVo boardVo) {
		
		sqlSession.update("board.update", boardVo);
		
	}
	
}
