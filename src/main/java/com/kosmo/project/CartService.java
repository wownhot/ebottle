package com.kosmo.project;

import java.util.ArrayList;

public interface CartService {
	int serviceFuncInsert(CartVO cvo);
	int serviceFuncDelete(int cSeq);
	int serviceFuncUpdate(CartVO cvo);
	CartVO serviceFuncSelect(int cSeq);
	ArrayList<CartVO> serviceFuncList(int uSeq, int startSeq, int endSeq);
	int serviceFuncListCount(int uSeq);
}
