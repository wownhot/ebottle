package com.kosmo.project;

import java.util.ArrayList;

public interface PointService {
	int serviceFuncInsert(PointVO vo);
	ArrayList<PointVO> serviceFuncSelect();	
	ArrayList<PointVO> serviceFuncSelect(String id);
	ArrayList<PointVO> serviceFuncMonthSpendSelect(String id);
	ArrayList<PointVO> serviceFuncMonthStackSelect(String id);
	ArrayList<PointVO> serviceFuncSelect(int start, int end);
	ArrayList<PointVO> serviceFuncSelect(int start, int end, String id);
	ArrayList<PointVO> serviceFuncMonthSelect(int start, int end, String id, int pmonth);
	ArrayList<PointVO> serviceFuncMonthSpendSelect(int start, int end, String id, int pmonth);
	ArrayList<PointVO> serviceFuncMonthStackSelect(int start, int end, String id, int pmonth);
	ArrayList<PointVO> serviceFuncSpend(int start, int end, String id);
	ArrayList<PointVO> serviceFuncStack(int start, int end, String id);
	int serviceFuncgetPoint(String id);
	int serviceFuncgetMonthPoint(String id, int pmonth);
	int serviceFuncgetMonthSpendPoint(String id, int pmonth);
	int serviceFuncgetMonthStackPoint(String id, int pmonth);
	int serviceFuncgetSpend(String id);
	int serviceFuncgetStack(String id);
	int serviceFuncsumPoint(String id);
	int serviceFunclistCount();
	int serviceFunclistCount(String id);
	int serviceFunclistSpendCount(String id);
	int serviceFunclistStackCount(String id);
	int serviceFunclistCount(String id,int pmonth);
	int serviceFunclistSpendCount(String id,int pmonth);
	int serviceFunclistStackCount(String id,int pmonth);
}
