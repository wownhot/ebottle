package com.kosmo.project;

public class GiftVO {
	private String barcode;
	private String barcode_img;
	private String product_img;
	private String users_id;
	private String gift_date;
	private String product_name;
	private int product_price;
	private String used;
	
	public String getGiftdate() {
		return gift_date;
	}
	public void setGiftdate(String giftdate) {
		this.gift_date = giftdate;
	}
	public String getProductname() {
		return product_name;
	}
	public void setProductname(String productname) {
		this.product_name = productname;
	}
	public int getProductprice() {
		return product_price;
	}
	public void setProductprice(int productprice) {
		this.product_price = productprice;
	}
	public String getUsing() {
		return used;
	}
	public void setUsing(String using) {
		this.used = using;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getBarcodeimg() {
		return barcode_img;
	}
	public void setBarcodeimg(String barcodeimg) {
		this.barcode_img = barcodeimg;
	}
	public String getProductimg() {
		return product_img;
	}
	public void setProductimg(String productimg) {
		this.product_img = productimg;
	}
	public String getUsersid() {
		return users_id;
	}
	public void setUsersid(String usersid) {
		this.users_id = usersid;
	}
}
