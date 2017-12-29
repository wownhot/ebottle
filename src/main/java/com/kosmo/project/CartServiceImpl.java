package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public int serviceFuncInsert(CartVO cvo) {
		return cartDAO.cartInsert(cvo);
	}

	@Override
	public int serviceFuncDelete(int cSeq) {
		return cartDAO.cartDelete(cSeq);
	}

	@Override
	public int serviceFuncUpdate(CartVO cvo) {
		return cartDAO.cartUpdate(cvo);
	}
	
	@Override
	public CartVO serviceFuncSelect(int cSeq) {
		return cartDAO.cartSelect(cSeq);
	}

	@Override
	public ArrayList<CartVO> serviceFuncList(int uSeq, int startSeq, int endSeq) {
		return cartDAO.cList(uSeq, startSeq, endSeq);
	}

	@Override
	public int serviceFuncListCount(int uSeq) {
		return cartDAO.cListCount(uSeq);
	}


}
