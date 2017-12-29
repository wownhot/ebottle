package com.kosmo.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BuyDAO{

	@Autowired
	private SqlSession conn;
	
	public int buyInsert(BuyVO bo) {
		int res = conn.insert("buyDAO.insert",bo);
		return res;
				
	}

	public int buyListCount(int useq){
		int res=0;
		BuyVO vo = new BuyVO();
		vo.setUseq(useq);
		res = conn.selectOne("buyDAO.buylistcount", vo);
		return res;


	}
	public ArrayList<BuyVO> buyList(int startseq, int endseq){
		ArrayList<BuyVO> list = new ArrayList<BuyVO>();
		/*StartEnd se = new StartEnd();
		se.setStart(startseq);
		se.setEnd(endseq);
		list=(ArrayList)conn.selectList("buyDAO.selectlist", se);*/
		return list;
		
	}
	public ArrayList<BuyVO> buyList(int useq, int startseq, int endseq){
		ArrayList<BuyVO> list = new ArrayList<BuyVO>();
		StartEnd se = new StartEnd();
		se.setStart(startseq);
		se.setEnd(endseq);
		se.setUsers_seq(useq);
		list=(ArrayList)conn.selectList("buyDAO.buyselectlist", se);
		return list;
		
	}
	public BuyVO buySelect(int bseq){
		BuyVO bo = new BuyVO();
		bo.setPseq(bseq);
		bo=conn.selectOne("buyDAO.selectone", bo);
		
		
		return bo;
	};
	
	public BuyVO buySelect(String barcode){
		BuyVO bo = new BuyVO();
		bo.setBarcode(barcode);
		bo=conn.selectOne("buyDAO.buyselectbarcode", bo);
		return bo;
	};
	
	public int buyUpdate(BuyVO vo){
		int res=0;
		res = conn.update("buyDAO.buyupdate", vo);
		return res;
	}
}
