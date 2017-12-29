package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService{
	@Autowired
	PointDAO pointDAO;
	@Override
	public int serviceFuncInsert(PointVO vo) {
		return pointDAO.pointInsert(vo);
	}

	@Override
	public ArrayList<PointVO> serviceFuncSelect() {
		return pointDAO.pointSelect();
	}

	@Override
	public ArrayList<PointVO> serviceFuncSelect(String id) {
		return pointDAO.pointSelect(id);
	}
	
	@Override
	public ArrayList<PointVO> serviceFuncMonthSpendSelect(String id) {
		return pointDAO.monthSpendSelect(id);
	}	

	@Override
	public ArrayList<PointVO> serviceFuncMonthStackSelect(String id) {
		return pointDAO.monthStackSelect(id);
	}	
	
	@Override
	public ArrayList<PointVO> serviceFuncSelect(int start, int end) {
		return pointDAO.pointSelect(start, end);
	}

	@Override
	public ArrayList<PointVO> serviceFuncSelect(int start, int end, String id) {
		return pointDAO.pointSelect(start, end, id);
	}
	
	@Override
	public ArrayList<PointVO> serviceFuncMonthSelect(int start, int end, String id, int pmonth) {
		return pointDAO.monthSelect(start, end, id,pmonth);
	}

	@Override
	public ArrayList<PointVO> serviceFuncMonthSpendSelect(int start, int end, String id, int pmonth) {
		return pointDAO.monthSpendSelect(start, end, id,pmonth);
	}
	
	@Override
	public ArrayList<PointVO> serviceFuncMonthStackSelect(int start, int end, String id, int pmonth) {
		return pointDAO.monthStackSelect(start, end, id,pmonth);
	}
	
	@Override
	public ArrayList<PointVO> serviceFuncSpend(int start, int end, String id) {
		return pointDAO.spendSelect(start, end, id);
	}

	@Override
	public ArrayList<PointVO> serviceFuncStack(int start, int end, String id) {
		return pointDAO.stackSelect(start, end, id);
	}	
	@Override
	public int serviceFuncgetPoint(String id) {
		return pointDAO.getPoint(id);
	}
	
	@Override
	public int serviceFuncgetMonthPoint(String id,int pmonth) {
		return pointDAO.getMonthPoint(id,pmonth);
	}

	@Override
	public int serviceFuncgetMonthSpendPoint(String id,int pmonth) {
		return pointDAO.getMonthSpend(id, pmonth);
	}
	@Override
	public int serviceFuncgetMonthStackPoint(String id,int pmonth) {
		return pointDAO.getMonthStack(id, pmonth);
	}
	@Override
	public int serviceFuncgetSpend(String id) {
		return pointDAO.getSpend(id);
	}
	
	
	@Override
	public int serviceFuncgetStack(String id) {
		return pointDAO.getStack(id);
	}	
	@Override
	public int serviceFunclistCount() {
		return pointDAO.listCount();
	}

	@Override
	public int serviceFunclistCount(String id) {
		return pointDAO.listCount(id);
	}

	@Override
	public int serviceFunclistSpendCount(String id) {
		return pointDAO.listSpendCount(id);
	}
	@Override
	public int serviceFunclistStackCount(String id) {
		return pointDAO.listStackCount(id);
	}
	@Override
	public int serviceFunclistCount(String id,int pmonth) {
		return pointDAO.listCount(id,pmonth);
	}
	@Override
	public int serviceFunclistSpendCount(String id,int pmonth) {
		return pointDAO.listSpendCount(id,pmonth);
	}
	@Override
	public int serviceFunclistStackCount(String id,int pmonth) {
		return pointDAO.listStackCount(id,pmonth);
	}
	@Override
	public int serviceFuncsumPoint(String id) {
		return pointDAO.sumPoint(id);
	}

}
