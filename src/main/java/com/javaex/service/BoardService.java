package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//게시글 목록
	public List<BoardVo> list(){
		
		List<BoardVo> boardList = boardDao.boardList();
		
		return boardList;
	}
	
	//조회수 증가
	public int read(int no) {
		
		boardDao.boardHit(no);
		
		return no;
		
	}
	
	//게시글 선택
	public BoardVo boardSelect(int no) {
		
		BoardVo boardVo = boardDao.boardSelect(no);
		
		return boardVo;
	}
	
	//게시글 추가
	public void boardInsert(BoardVo boardVo) {
		
		boardDao.boardInsert(boardVo);
		
	}
	
	//게시글 삭제
	public void boardDelete(int no) {
		
		boardDao.boardDelete(no);
		
	}
	
	//게시글 수정
	public void boardUpdate(BoardVo boardVo) {
		
		boardDao.boardUpdate(boardVo);
		
	}
}
