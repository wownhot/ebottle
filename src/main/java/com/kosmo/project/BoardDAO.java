
package com.kosmo.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO{
	int kkk=0;
	@Autowired
	private SqlSession conn;
	
	public ArrayList<BoardVO> boardList(int startSeq, int endSeq){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startSeq", startSeq);
		map.put("endSeq", endSeq);
		return (ArrayList)conn.selectList("boardDAO.selectlist",map);
	
	}

	public int boardlistCount(){
		return conn.selectOne("boardDAO.listcount");
	}
	
	public BoardVO boardSelect(int bseq){
		BoardVO bo = new BoardVO();
		bo.setBseq(bseq);
		return conn.selectOne("boardDAO.boardselect",bo);
	}
	
	public int boardInsert(BoardVO bo) {
		return conn.insert("boardDAO.insert",bo);
	};
	
	public int replyInsert(BoardReplyVO bro){
		return conn.insert("boardDAO.replyinsert",bro);
	}
	
	public int hitsUpdate(BoardVO vo){
		return conn.update("boardDAO.hitsupdate",vo);
	}
	
	public BoardReplyVO replySelect(int rseq){
		BoardReplyVO bro = new BoardReplyVO();
		bro.setRseq(rseq);
		System.out.println("bro"+bro.getRseq());
//		bro =conn.selectOne("boardDAO.replyselect",bro);
//		System.out.println(bro.reply);
//		System.out.println("날짜"+bro.getRegDate());
		
		return conn.selectOne("boardDAO.replyselect",bro);
	}
//런타임방식	
//	public int replyInsert(BoardReplyVO bro){
//	System.out.println("call1");
//		conn.insert("boardDAO.replyinsert",bro);
//		System.out.println("call2");
//		return conn.insert("boardDAO.replyinsert",bro);
//		
//	}
	
/*	public void replyInsert(BoardReplyVO bro)throws Exception{
		int res=0;
		System.out.println("call1");
		res =conn.insert("boardDAO.replyinsert",bro);
		kkk=res;
		
		throw new Exception();//예외 유발되면  롤백되는지
		
	}	*/
	public int boardUpdate(BoardVO vo) {
		int res=0;
		res = conn.update("boardDAO.update", vo);
		return res;
	};
	public int replyUpdate(BoardReplyVO vo) {
		int res=0;

		res = conn.update("boardDAO.replyupdate", vo);
		return res;
	};	
	public int boardDelete(int seq) {
		int res = 0;
		BoardVO vo = new BoardVO();
		vo.setBseq(seq);
		res = conn.delete("boardDAO.delete", vo);
		return res;
	}
	public int replyDelete(BoardReplyVO vo) {
		int res = 0;

		res = conn.delete("boardDAO.replydelete", vo);
		return res;
	}	
	
	public ArrayList<BoardVO> boardSearch(String col, String key, int startSeq, int endSeq){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startSeq", startSeq);
		map.put("endSeq", endSeq);
		map.put("column", col);
		map.put("key", key);
		list=(ArrayList)conn.selectList("boardDAO.searchlist", map);
		return list;
	}

	public int searchCount(String col, String key){
		int res=0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("column", col);
		map.put("key", key);
		res = conn.selectOne("boardDAO.searchcount",map);
		return res;
	}
}
