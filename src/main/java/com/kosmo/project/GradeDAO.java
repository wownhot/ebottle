package com.kosmo.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDAO{
	
	@Autowired
	private SqlSession conn;


	public int gradeInsert(GradeVO vo) {
		int res = 0;
		res = conn.insert("gradeDAO.insert",vo);
		return res;
	};

	
	public int gradeUpdate(GradeVO vo) {
		int res=0;
		res = conn.update("gradeDAO.update", vo);
		return res;
	};
	
	public int gradeDelete(int seq) {
		int res = 0;
		GradeVO vo = new GradeVO();
		vo.setGrade_seq(seq);
		res = conn.delete("gradeDAO.delete", vo);
		return res;
	}
	
	public int listCount(){
		int res = 0;	
		res = conn.selectOne("gradeDAO.listcount");
		return res;
	}

	public ArrayList<GradeVO> gradeSelect(int start, int end){
		ArrayList<GradeVO> list = new ArrayList<GradeVO>();
		StartEnd se = new StartEnd();
		se.setStart(start);
		se.setEnd(end);
		list = (ArrayList)conn.selectList("gradeDAO.selectlist", se);
		return list;
	}
	public GradeVO gradeSelect(int seq) {
		GradeVO vo = new GradeVO();
		vo.setGrade_seq(seq);
		return conn.selectOne("gradeDAO.selectseq", vo);
	};
	
	public ArrayList<GradeVO> gradeSelectAll(){
		ArrayList<GradeVO> list = new ArrayList<GradeVO>();
		list = (ArrayList)conn.selectList("gradeDAO.selectall");
		return list;
	}
	
	public int gradeSelectAccount(UserVO vo) {
		return conn.selectOne("gradeDAO.selectaccount", vo);
	};
}
