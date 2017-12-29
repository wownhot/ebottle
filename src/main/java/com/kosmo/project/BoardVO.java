package com.kosmo.project;

import java.util.List;

public class BoardVO {
	int bseq;
	String title;
	String userid;
	String contents;
	String regDate;
	String origFname;
	String sysFname;
	int hits;
	
	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	private List<BoardReplyVO> rlist;
	//private MultipartFile file;

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getOrigFname() {
		return origFname;
	}

	public void setOrigFname(String origFname) {
		this.origFname = origFname;
	}

	public String getSysFname() {
		return sysFname;
	}

	public void setSysFname(String sysFname) {
		this.sysFname = sysFname;
	}

	public List<BoardReplyVO> getRlist() {
		return rlist;
	}

	public void setRlist(List<BoardReplyVO> rlist) {
		this.rlist = rlist;
	}
	
}
