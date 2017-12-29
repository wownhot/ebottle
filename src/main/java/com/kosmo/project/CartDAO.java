package com.kosmo.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO{
	@Autowired
	private SqlSession session;
	
	public ArrayList<CartVO> cList(int uSeq, int startSeq, int endSeq){
		StartEnd se = new StartEnd();
		se.setStart(startSeq);
		se.setEnd(endSeq);
		se.setUsers_seq(uSeq);
		
		return (ArrayList)session.selectList("cartDAO.selectlist", se);
	}
	
	public int cartInsert(CartVO cvo){
		return session.insert("cartDAO.insert",cvo);
	}
	
	public int cListCount(int uSeq){
		return session.selectOne("cartDAO.listcount", uSeq);
	}
	
	public CartVO cartSelect(int cart_seq){
		CartVO cvo = new CartVO();
		cvo.setCart_seq(cart_seq);
		return session.selectOne("cartDAO.selectone", cvo);
	}
	
	public int cartDelete(int cart_seq){
		CartVO cvo = new CartVO();
		cvo.setCart_seq(cart_seq);
		return session.delete("cartDAO.delete", cvo);
	}
	
	public int cartUpdate(CartVO cvo){
		return session.update("cartDAO.update", cvo);
	}
}
