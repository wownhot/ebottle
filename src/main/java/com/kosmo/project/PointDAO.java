package com.kosmo.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAO{
	@Autowired
	private SqlSession conn;
	public int pointInsert(PointVO vo) {
		int res = 0;
		res = conn.insert("pointDAO.insert", vo);
		return res;
	};
	public ArrayList<PointVO> pointSelect() {	
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		list = (ArrayList)conn.selectList("pointDAO.select");
		return list;
	};
	public ArrayList<PointVO> pointSelect(String id) {
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.selectid", vo);
		return list;
	};

	public ArrayList<PointVO> monthSpendSelect(String id) {
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.monthspendid", vo);
		return list;
	};	

	public ArrayList<PointVO> monthStackSelect(String id) {
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.monthstackid", vo);
		return list;
	};	
	
	public ArrayList<PointVO> pointSelect(int start, int end){
	
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		list = (ArrayList)conn.selectList("pointDAO.selectlist", vo);
		return list;
	}
	public ArrayList<PointVO> pointSelect(int start, int end, String id){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.selectlistid", vo);
		return list;
	}

	public ArrayList<PointVO> monthSelect(int start, int end, String id,int pmonth){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		list = (ArrayList)conn.selectList("pointDAO.monthlistid", vo);
		return list;
	}	

	public ArrayList<PointVO> monthSpendSelect(int start, int end, String id,int pmonth){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		list = (ArrayList)conn.selectList("pointDAO.monthspendlistid", vo);
		return list;
	}		
	
	public ArrayList<PointVO> monthStackSelect(int start, int end, String id,int pmonth){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		list = (ArrayList)conn.selectList("pointDAO.monthstacklistid", vo);
		return list;
	}		
	public ArrayList<PointVO> spendSelect(int start, int end, String id){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.spendlistid", vo);
		return list;
	}
	
	public ArrayList<PointVO> stackSelect(int start, int end, String id){
		
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO vo = new PointVO();
		vo.setPseq(start);
		vo.setPval(end);
		vo.setUid(id);
		list = (ArrayList)conn.selectList("pointDAO.stacklistid", vo);
		return list;
	}	
	
	
	public int listCount(){
		int res = 0;
		res = conn.selectOne("pointDAO.listcount");
		return res;
	}
	
	public int listCount(String id){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.listcountid", vo);
		return res;
	}

	public int listSpendCount(String id){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.spendlistcountid", vo);
		return res;
	}	

	public int listStackCount(String id){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.stacklistcountid", vo);
		return res;
	}
	
	public int listCount(String id,int pmonth){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthlistcountid", vo);
		return res;
	}	
	public int listSpendCount(String id,int pmonth){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthspendlistcountid", vo);
		return res;
	}		
	public int listStackCount(String id,int pmonth){
		int res = 0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthstacklistcountid", vo);
		return res;
	}		
	
	public int getPoint(String id){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.getpoint", vo);
		return res;
	}

	public int getMonthPoint(String id, int pmonth){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthgetpoint", vo);
		return res;
	}	
	
	public int getSpend(String id){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.getspend", vo);
		return res;
	}
	
	public int getMonthSpend(String id,int pmonth){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthgetspend", vo);
		return res;
	}	
	public int getStack(String id){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.getstack", vo);
		return res;
	}	
	
	public int getMonthStack(String id,int pmonth){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		vo.setPoint_month(pmonth);
		res = conn.selectOne("pointDAO.monthgetstack", vo);
		return res;
	}		
	public int sumPoint(String id){
		int res=0;
		PointVO vo = new PointVO();
		vo.setUid(id);
		res = conn.selectOne("pointDAO.sumpoint", vo);
		return res;
	}
}
