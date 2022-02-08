package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {//business logic: 서비스는 자료의 조작, 계산의 수행을 담당한다.

	@Autowired
	private BoardDao boardDao;
	
	
	//게시글 목록
	public List<BoardVo> list(){
		
		List<BoardVo> boardList = boardDao.boardList();
		
		return boardList;
	}
	
	
	//게시글 목록 (리스트 + 페이징)
	public Map<String,Object> getBoardList(int crtPage){
		System.out.println("BoardService>list2");
		
		//페이지당 글 개수--수정될 수도 있으므로 변수로 둔다.
		int listCnt = 10;
		
		//현재 페이지 결정 - 2가지 방법
		//1. 간단한 if문 - 삼항 연산자: ? 앞 조건이 충족될 경우 A : B 중 A, 불충족일 경우 B
		crtPage = (crtPage>0) ? crtPage : (crtPage=1); 
		
		/*
		2. if문으로 표현하기
		if(crtPage <= 0) {//없는 페이지 값(음수) 입력시 최신 페이지로 이동
			crtPage = 1;
		}
		*/
		
		//시작 글 번호 = 페이지(숫자)를 입력했을 때 해당 페이지의 첫 번째 글 번호가 도출되는 식
		int startRnum = (crtPage - 1)*listCnt + 1;
		
		//마지막 글 번호 = 페이지(숫자)를 입력했을 때 해당 페이지의 마지막 글 번호가 도출되는 식
		int endRnum = (startRnum + listCnt) - 1; //int endRnum = crtPage*listCnt;
		
		List<BoardVo> boardList = boardDao.selectList(startRnum, endRnum);
		
		
		
		/*페이징 버튼 구현*/
		
		//전체 글 개수 가져오기
		int totalCnt = boardDao.selectTotal();
		//System.out.println("totalCount = " + totalCnt);
	
		//페이지당 버튼 개수
		int pageBtnCount = 5;
		
		//☆마지막 버튼 번호
		int endPageBtnNo = (int)(  Math.ceil(crtPage/(double)pageBtnCount)  )*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 유무 판별
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCnt) {//계산된 값이 총 게시글 수보다 적을 경우에만 화살표 표기
			next = true;
		}else {//다음 화살표가 보이지 않으면 마지막 버튼 값을 다시 계산한다.
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}

		//이전 화살표 유무 판별
		boolean prev = false;
		if(startPageBtnNo>1) {//or (startPageNo != 1)
			prev = true;
		}
		
		
		/*도출된 값들 하나로 포장하기: return, 즉 값을 controller에게 넘겨주기 위해*/
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
	
		
		return pMap;
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
	public int boardInsert(BoardVo boardVo) {
		
		//페이징 데이터 추가 123개
		/*
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째 글 제목입니다.");
			boardVo.setContent(i + "번째 내용입니다.");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			
			boardDao.boardInsert(boardVo);
		}
		*/
		
		return boardDao.boardInsert(boardVo);
		
		//return 1;
		
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
