package com.kosmo.project;

public class PointVO {
	private int point_seq;
	private String users_id;
	private int point_val;
	private String point_date;
	private int point_month;
	public int getPoint_month() {
		return point_month;
	}
	public void setPoint_month(int point_month) {
		this.point_month = point_month;
	}
	public int getPseq() {
		return point_seq;
	}
	public void setPseq(int pseq) {
		this.point_seq = pseq;
	}
	public String getUid() {
		return users_id;
	}
	public void setUid(String uid) {
		this.users_id = uid;
	}
	public int getPval() {
		return point_val;
	}
	public void setPval(int pval) {
		this.point_val = pval;
	}
	public String getDate() {
		return point_date;
	}
	public void setDate(String date) {
		this.point_date = date;
	}
}
