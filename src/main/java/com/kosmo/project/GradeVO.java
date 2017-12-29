package com.kosmo.project;

public class GradeVO {
	private String users_account;
	private int requestpoint;
	private int discountrate;
	private int grade_seq;
	private String grade_url;
	
	public String getGrade_url() {
		return grade_url;
	}
	public void setGrade_url(String grade_url) {
		this.grade_url = grade_url;
	}
	public int getGrade_seq() {
		return grade_seq;
	}
	public void setGrade_seq(int grade_seq) {
		this.grade_seq = grade_seq;
	}
	public String getUsers_account() {
		return users_account;
	}
	public void setUsers_account(String users_account) {
		this.users_account = users_account;
	}
	public int getRequestpoint() {
		return requestpoint;
	}
	public void setRequestpoint(int requestpoint) {
		this.requestpoint = requestpoint;
	}
	public int getDiscountrate() {
		return discountrate;
	}
	public void setDiscountrate(int discountrate) {
		this.discountrate = discountrate;
	}
	

}
