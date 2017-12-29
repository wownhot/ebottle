package com.kosmo.project;

import java.util.ArrayList;

public interface GiftService {
	
	ArrayList<GiftVO> serviceFuncSelect(int start, int end, String id);
	int serviceFuncListCount(String id);
	int serviceFuncInsert(GiftVO vo);
	GiftVO serviceFuncSelect(GiftVO vo);
	int serviceFuncUpdate(GiftVO vo);
}
