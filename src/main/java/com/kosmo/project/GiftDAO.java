package com.kosmo.project;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GiftDAO {
	
	@Autowired
	SqlSession conn;
	
	public int giftInsert(GiftVO vo){
		int res=0;
		res = conn.insert("giftDAO.insert", vo);
		return res;
	}
	
	public GiftVO giftSelect(GiftVO vo){
		return conn.selectOne("giftDAO.selectone", vo);
	}
	
	public ArrayList<GiftVO> giftSelect(int start, int end,String id) {
		ArrayList<GiftVO> list = new ArrayList<GiftVO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("users_id", id);
		list = (ArrayList)conn.selectList("giftDAO.selectlist", map);
		return list;
	}
	
	public int listCount(String id){
		int res = 0;
		GiftVO vo = new GiftVO();
		vo.setUsersid(id);
		res = conn.selectOne("giftDAO.listcount", vo);
		return res;
	}
	
	public int update(GiftVO vo){
		int res=0;
		res = conn.update("giftDAO.update", vo);
		return res;
	}

}
