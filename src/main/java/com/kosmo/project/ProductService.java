package com.kosmo.project;

import java.util.ArrayList;

public interface ProductService {
	int serviceFuncInsert(ProductVO po);
	int serviceFuncDelete(int pseq);
	int serviceFuncUpdate(ProductVO po);
	ProductVO serviceFuncSelect(int pseq);
	ArrayList<ProductVO> serviceFuncList(int startSeq, int endSeq);
	ArrayList<ProductVO> serviceFuncList();
	ArrayList<ProductVO> serviceFuncSearch(String col, String key, int startSeq, int endSeq);
	int serviceFuncListCount();
	int serviceFuncSearchCount(String col, String key);
}
