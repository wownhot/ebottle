package com.kosmo.project;

public class UserVO {
	private int users_seq;
	
	private String users_id;
	private String users_pw;
	private String users_name;
	private String users_email;
	private String users_account;
	public int getSeq() {
		return users_seq;
	}
	public void setSeq(int useq) {
		this.users_seq = useq;
	}

	public String getId() {
		return users_id;
	}
	public void setId(String id) {
		this.users_id = id;
	}
	public String getPw() {
		return users_pw;
	}
	public void setPw(String pw) {
		this.users_pw = pw;
	}
	public String getName() {
		return users_name;
	}
	public void setName(String uname) {
		this.users_name = uname;
	}
	public String getEmail() {
		return users_email;
	}
	public void setEmail(String email) {
		this.users_email = email;
	}
	
	public String getAccount() {
		return users_account;
	}
	public void setAccount(String account) {
		this.users_account = account;
	}
}
