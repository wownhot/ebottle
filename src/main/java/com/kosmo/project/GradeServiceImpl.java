package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeDAO gradeDAO;
	
	public int serviceFuncInsert(GradeVO vo) {
		return gradeDAO.gradeInsert(vo);
	};
	

	public int serviceFuncUpdate(GradeVO vo) {
		return gradeDAO.gradeUpdate(vo);
	}
	@Override
	public int serviceFuncDelete(int seq) {
		return gradeDAO.gradeDelete(seq);
	}
	@Override
	public ArrayList<GradeVO> serviceFuncSelect(int start, int end) {
		return gradeDAO.gradeSelect(start, end);
	}
	@Override
	public int serviceFunclistCount() {
		return gradeDAO.listCount();
	}


	@Override
	public GradeVO serviceFuncSelect(int seq) {
		//
		return gradeDAO.gradeSelect(seq);
	}


	@Override
	public ArrayList<GradeVO> serviceFuncSelectAll() {
		return gradeDAO.gradeSelectAll();
	}


	@Override
	public int serviceFuncSelectAccount(UserVO vo) {
		return gradeDAO.gradeSelectAccount(vo);
	}


}

