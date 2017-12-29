package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftServiceImpl implements GiftService{

	@Autowired
	GiftDAO dao;
	@Override
	public int serviceFuncInsert(GiftVO vo) {
		return dao.giftInsert(vo);
	}

	@Override
	public GiftVO serviceFuncSelect(GiftVO vo) {
		return dao.giftSelect(vo);
	}

	@Override
	public ArrayList<GiftVO> serviceFuncSelect(int start, int end, String id) {
		return dao.giftSelect(start, end, id);
	}

	@Override
	public int serviceFuncListCount(String id) {
		return dao.listCount(id);
	}
	
	@Override
	public int serviceFuncUpdate(GiftVO vo) {
		return dao.update(vo);
	}
	
	
}
