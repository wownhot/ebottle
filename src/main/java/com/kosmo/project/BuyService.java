package com.kosmo.project;

import java.util.ArrayList;


public interface BuyService {
	int serviceFuncListCount(int useq);
	int serviceFuncInsert(BuyVO bo);
	int serviceFuncUpdate(BuyVO bo);
	BuyVO serviceFuncSelect(int bseq);
	BuyVO serviceFuncSelect(String barcode);
	ArrayList<BuyVO> serviceFuncList(int startSeq, int endSeq);
	ArrayList<BuyVO> serviceFuncList(int useq, int startSeq, int endSeq);
}
