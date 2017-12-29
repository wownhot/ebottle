package com.kosmo.project;

public class ProductVO {
	private int product_seq;
	private String product_name;
	private int product_price;
	private String product_url;
	private int product_stock;
	private String product_desc;
	private String product_uname;
	
	public String getP_uname() {
		return product_uname;
	}
	public void setP_uname(String p_uname) {
		this.product_uname = p_uname;
	}
	public int getPseq() {
		return product_seq;
	}
	public void setPseq(int pseq) {
		this.product_seq = pseq;
	}
	public String getPname() {
		return product_name;
	}
	public void setPname(String pname) {
		this.product_name = pname;
	}
	public int getPrice() {
		return product_price;
	}
	public void setPrice(int price) {
		this.product_price = price;
	}
	public String getUrl() {
		return product_url;
	}
	public void setUrl(String url) {
		this.product_url = url;
	}
	public int getStock() {
		return product_stock;
	}
	public void setStock(int stock) {
		this.product_stock = stock;
	}
	public String getDesc() {
		return product_desc;
	}
	public void setDesc(String desc) {
		this.product_desc = desc;
	}

}
