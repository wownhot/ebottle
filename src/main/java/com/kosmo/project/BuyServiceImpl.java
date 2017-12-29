package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyServiceImpl implements BuyService{
	@Autowired
	private BuyDAO buyDAO;
	@Override
	public int serviceFuncListCount(int useq) {
		return buyDAO.buyListCount(useq);
	}

	@Override
	public int serviceFuncInsert(BuyVO bo) {
		return buyDAO.buyInsert(bo);
	}

	@Override
	public BuyVO serviceFuncSelect(int bseq) {
		return buyDAO.buySelect(bseq);
	}

	@Override
	public ArrayList<BuyVO> serviceFuncList(int startSeq, int endSeq) {
		return buyDAO.buyList(startSeq, endSeq);
	}

	@Override
	public ArrayList<BuyVO> serviceFuncList(int useq, int startSeq, int endSeq) {
		return buyDAO.buyList(useq, startSeq, endSeq);
	}

	@Override
	public BuyVO serviceFuncSelect(String barcode) {
		return buyDAO.buySelect(barcode);
	}

	@Override
	public int serviceFuncUpdate(BuyVO bo) {
		return buyDAO.buyUpdate(bo);
	}

}
