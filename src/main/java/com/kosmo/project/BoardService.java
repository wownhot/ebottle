
package com.kosmo.project;

import java.util.ArrayList;

public interface BoardService {
	int serviceFuncInsert(BoardVO bo);
	ArrayList<BoardVO> serviceBoardSelect(int startSeq, int endSeq);
	ArrayList<BoardVO> serviceBoardSearch(String col, String key,int startSeq, int endSeq);
	
	int serviceFuncListCount();
	int serviceFuncSearchCount(String col, String key);
	
	BoardVO serviceFuncSelect(int bseq);
	int serviceFuncReplyInsert(BoardReplyVO bro);
	int	serviceFuncUpdate(BoardVO vo);
	int	serviceFuncDelete(int seq);	
	int	serviceFuncReplyUpdate(BoardReplyVO vo);
	int	serviceFuncHitsUpdate(BoardVO vo);
	int	serviceFuncReplyDelete(BoardReplyVO vo);	
	BoardReplyVO serviceFuncReplySelect(int rseq);
	
}
