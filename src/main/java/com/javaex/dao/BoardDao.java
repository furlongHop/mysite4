package com.javaex.dao;

import java.util.List;

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
	public void boardInsert(BoardVo boardVo) {
		
		sqlSession.insert("board.insert", boardVo);
		
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
