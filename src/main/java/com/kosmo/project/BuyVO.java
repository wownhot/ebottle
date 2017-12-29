package com.kosmo.project;

public class BuyVO {
	private int buy_seq;
	private String product_name;
	private int product_price;
	private int users_seq;
	private int product_seq;
	private String buy_date;
	private String barcode;
	private String barcode_img;
	private String product_img;
	private String used;
	
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public String getBarcode_img() {
		return barcode_img;
	}
	public void setBarcode_img(String barcode_img) {
		this.barcode_img = barcode_img;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getBseq() {
		return buy_seq;
	}
	public void setBseq(int buy_seq) {
		this.buy_seq = buy_seq;
	}
	public String getPname() {
		return product_name;
	}
	public void setPname(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return product_price;
	}
	public void setPrice(int product_price) {
		this.product_price = product_price;
	}
	public int getUseq() {
		return users_seq;
	}
	public void setUseq(int users_seq) {
		this.users_seq = users_seq;
	}
	public int getPseq() {
		return product_seq;
	}
	public void setPseq(int product_seq) {
		this.product_seq = product_seq;
	}
	public String getDate() {
		return buy_date;
	}
	public void setDate(String buy_date) {
		this.buy_date = buy_date;
	}
	
	
}