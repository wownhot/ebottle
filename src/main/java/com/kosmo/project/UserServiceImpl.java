package com.kosmo.project;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	public UserVO serviceFuncLogin(String id, String pw){
		return userDAO.userLogin(id, pw);
	}
	
	public int serviceFuncInsert(UserVO vo) {
		return userDAO.userInsert(vo);
	};
	
	public int serviceFuncInsert(UserVO vo, String account) {
		return userDAO.userInsert(vo, account);
	}
	
	public ArrayList<UserVO> serviceFuncSelect() {
		return userDAO.userSelect();
	}
	
	public int	serviceFuncCheckid(String id){
		return userDAO.userCheckid(id);
	}
	
	public UserVO serviceFuncSelect(int seq) {
		return userDAO.userSelect(seq);
	}
	@Override
	public int serviceFuncSelect(String id) {
		return userDAO.userSelect(id);
	}
	@Override
	public int serviceFuncUpdate(UserVO vo) {
		return userDAO.userUpdate(vo);
	}
	@Override
	public int serviceFuncDelete(int seq) {
		return userDAO.userDelete(seq);
	}
	@Override
	public ArrayList<UserVO> serviceFuncSelect(int start, int end) {
		return userDAO.userSelect(start, end);
	}
	@Override
	public int serviceFunclistCount() {
		return userDAO.listCount();
	}

	@Override
	public int serviceFuncChecklog(String id, String pw) {
		return userDAO.userChecklog(id, pw);
	}

	@Override
	public UserVO serviceFuncSelectId(String id) {
		return userDAO.userSelectId(id);
	}

	@Override
	public ArrayList<UserVO> serviceFuncSearch(String col, String key, int start, int end) {
		return userDAO.userSearch(col, key, start, end);
	}

	@Override
	public int serviceFuncSearchCount(String col, String key) {
		return userDAO.searchCount(col, key);
	}
	
	
}

