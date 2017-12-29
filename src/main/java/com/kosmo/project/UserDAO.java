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
public class UserDAO{
	
	@Autowired
	private SqlSession conn;

	public UserVO userLogin(String id, String pw) {
		UserVO vo=new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		return conn.selectOne("userDAO.login",vo);
	}
	
	public int userChecklog(String id, String pw){
		UserVO vo=new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		return conn.selectOne("userDAO.checklog",vo);
	}


	public int userInsert(UserVO vo) {
		int res = 0;
		res = conn.insert("userDAO.insert",vo);
		return res;
	};
	

	public int userInsert(UserVO vo, String account) {
		int res = 0;
		vo.setAccount(account);
		res = conn.insert("userDAO.insert2",vo);
		return res;
	};

	public ArrayList<UserVO> userSelect() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list = (ArrayList)conn.selectList("userDAO.select");
		return list;
	};

	public UserVO userSelect(int seq) {
		UserVO vo = new UserVO();
		vo.setSeq(seq);
		return conn.selectOne("userDAO.selectseq", vo);
	};
	
	public int userCheckid(String id){
		int res=0;
		UserVO vo = new UserVO();
		vo.setId(id);
		res = conn.selectOne("userDAO.checkid", vo);
		return res;
	}
	
	public int userSelect(String id) {
		int res=0;
		UserVO vo = new UserVO();
		vo.setId(id);
		vo = conn.selectOne("userDAO.selectid", vo);
		if(vo!=null)
			res=1;
		return res;
	};
	
	public UserVO userSelectId(String id){
		UserVO vo = new UserVO();
		vo.setId(id);
		vo = conn.selectOne("userDAO.selectid", vo);
		return vo;
	}

	
	public int userUpdate(UserVO vo) {
		int res=0;
		res = conn.update("userDAO.update", vo);
		return res;
	};
	
	public int userDelete(int seq) {
		int res = 0;
		UserVO vo = new UserVO();
		vo.setSeq(seq);
		res = conn.delete("userDAO.delete", vo);
		return res;
	}
	
	public int listCount(){
		int res = 0;	
		res = conn.selectOne("userDAO.listcount");
		return res;
	}

	public ArrayList<UserVO> userSelect(int start, int end){
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		StartEnd se = new StartEnd();
		se.setStart(start);
		se.setEnd(end);
		list = (ArrayList)conn.selectList("userDAO.selectlist", se);
		return list;
	}
	
	public ArrayList<UserVO> userSearch(String col, String key, int startSeq, int endSeq){
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startSeq", startSeq);
		map.put("endSeq", endSeq);
		map.put("column", col);
		map.put("key", key);
		list=(ArrayList)conn.selectList("userDAO.searchlist", map);
		return list;
	}

	public int searchCount(String col, String key){
		int res=0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("column", col);
		map.put("key", key);
		res = conn.selectOne("userDAO.searchcount",map);
		return res;
	}
	
}
