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
public class ProductDAO{
	@Autowired
	private SqlSession conn;
	
	public ArrayList<ProductVO> productList(){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		list = (ArrayList)conn.selectList("productDAO.select");
		return list;
	}
	
	public ArrayList<ProductVO> pList(int startSeq, int endSeq){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		StartEnd se = new StartEnd();
		se.setStart(startSeq);
		se.setEnd(endSeq);
		list=(ArrayList)conn.selectList("productDAO.selectlist", se);
		return list;
	}
	
	public ArrayList<ProductVO> productSearch(String col, String key, int startSeq, int endSeq){
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startSeq", startSeq);
		map.put("endSeq", endSeq);
		map.put("column", col);
		map.put("key", key);
		list=(ArrayList)conn.selectList("productDAO.searchlist", map);
		return list;
	}
	
	public int productInsert(ProductVO po){
		int res=conn.insert("productDAO.insert",po);
		return res;
	};
	
	
	public int pListCount(){
		int res=0;
		res = conn.selectOne("productDAO.listcount");
		//res=po.getPseq();
		return res;
	}
	
	public int productSearchCount(String col, String key){
		int res=0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("column", col);
		map.put("key", key);
		res = conn.selectOne("productDAO.searchcount",map);
		//res=po.getPseq();
		return res;
	}
	
	public ProductVO productSelect(int pseq){
		ProductVO po = new ProductVO();
		po.setPseq(pseq);
		po=conn.selectOne("productDAO.selectone", po);
		return po;
	};
	public int productDelete(int pseq){
		int res = 0;
		ProductVO po = new ProductVO();
		po.setPseq(pseq);
		res= conn.delete("productDAO.delete", po);
		return res;
	};
	public int productUpdate(ProductVO po){
		int res = 0;
		res=conn.update("productDAO.update", po);
		return res;
	};
}
