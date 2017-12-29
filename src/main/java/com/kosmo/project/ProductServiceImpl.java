package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	@Override
	public int serviceFuncInsert(ProductVO po) {
		return productDAO.productInsert(po);
	}

	@Override
	public int serviceFuncDelete(int pseq) {
		return productDAO.productDelete(pseq);
	}

	@Override
	public int serviceFuncUpdate(ProductVO po) {
		return productDAO.productUpdate(po);
	}

	@Override
	public ProductVO serviceFuncSelect(int pseq) {
		return productDAO.productSelect(pseq);
	}

	@Override
	public ArrayList<ProductVO> serviceFuncList(int startSeq, int endSeq) {
		return productDAO.pList(startSeq, endSeq);
	}

	@Override
	public int serviceFuncListCount() {
		return productDAO.pListCount();
	}

	@Override
	public ArrayList<ProductVO> serviceFuncSearch(String col, String key, int startSeq, int endSeq) {
		return productDAO.productSearch(col, key, startSeq, endSeq);
	}

	@Override
	public int serviceFuncSearchCount(String col, String key) {
		return productDAO.productSearchCount(col, key);
	}

	@Override
	public ArrayList<ProductVO> serviceFuncList() {
		return productDAO.productList();
	}

}
