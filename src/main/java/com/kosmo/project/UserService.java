package com.kosmo.project;

import java.util.ArrayList;

public interface UserService {
	UserVO serviceFuncLogin(String id, String pw);
	int serviceFuncInsert(UserVO vo);
	int serviceFuncInsert(UserVO vo, String account);
	ArrayList<UserVO> serviceFuncSelect();
	UserVO serviceFuncSelect(int seq);
	
	int serviceFuncSelect(String id);
	UserVO serviceFuncSelectId(String id);
	int	serviceFuncCheckid(String id);
	int	serviceFuncChecklog(String id, String pw);
	
	int	serviceFuncUpdate(UserVO vo);
	int	serviceFuncDelete(int seq);
	ArrayList<UserVO> serviceFuncSelect(int start, int end);
	ArrayList<UserVO> serviceFuncSearch(String col, String key, int start, int end);
	int serviceFunclistCount();
	int serviceFuncSearchCount(String col, String key);
}
