
 package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;//

	@Override
	public int serviceFuncInsert(BoardVO bo){
		// TODO Auto-generated method stub
		return boardDAO.boardInsert(bo);
	}
	@Override
	public int serviceFuncReplyInsert(BoardReplyVO bro){
		// TODO Auto-generated method stub
		return boardDAO.replyInsert(bro);
	}
//	public int serviceFuncReplyInsert(BoardReplyVO bro){
//		// TODO Auto-generated method stub
//			try {
//				boardDAO.replyInsert(bro);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			int  r = boardDAO.kkk;
//		return r;
//	}	
	@Override
	public ArrayList<BoardVO> serviceBoardSelect(int startSeq, int endSeq){
		return boardDAO.boardList(startSeq, endSeq);
	}

	@Override
	public int serviceFuncListCount() {
		// TODO Auto-generated method stub
		return boardDAO.boardlistCount();
	}

	@Override
	public BoardVO serviceFuncSelect(int bseq) {
		// TODO Auto-generated method stub
		return boardDAO.boardSelect(bseq);
	}
	public int serviceFuncUpdate(BoardVO vo){
		return boardDAO.boardUpdate(vo);
	}
	public int serviceFuncHitsUpdate(BoardVO vo){
		return boardDAO.hitsUpdate(vo);
	}	

	public int	serviceFuncDelete(int seq){
		return boardDAO.boardDelete(seq);
	}
	
	public int	serviceFuncReplyUpdate(BoardReplyVO vo){
		return boardDAO.replyUpdate(vo);
	}
	public int	serviceFuncReplyDelete(BoardReplyVO vo){
		return boardDAO.replyDelete(vo);
	}

	public BoardReplyVO serviceFuncReplySelect(int rseq) {
		return boardDAO.replySelect(rseq);
	}
	@Override
	public ArrayList<BoardVO> serviceBoardSearch(String col, String key, int startSeq, int endSeq) {
		return boardDAO.boardSearch(col, key, startSeq, endSeq);
	}
	@Override
	public int serviceFuncSearchCount(String col, String key) {
		return boardDAO.searchCount(col, key);
	}

	
}

