package com.kosmo.project;

import java.util.ArrayList;

public interface GradeService {
	int serviceFuncInsert(GradeVO go);
	int	serviceFuncUpdate(GradeVO go);
	int	serviceFuncDelete(int seq);
	ArrayList<GradeVO> serviceFuncSelect(int start, int end);
	ArrayList<GradeVO> serviceFuncSelectAll();
	int serviceFunclistCount();
	int serviceFuncSelectAccount(UserVO vo);
	GradeVO serviceFuncSelect(int seq);
}
