package com.salest.etl.adminconsole.batch.model;

import java.util.Date;

public class DailyProductTrSummary {
	
	private Date date;
	private String product_name;
	private String product_cate;
	private byte num_of_product;
	private long total_amount;
	
	public Date getDate(){
		return this.date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	
	public String getProduct_name(){
		return this.product_name;
	}
	public void setProduct_name(String product_name){
		this.product_name = product_name;
	}
	
	public String getProduct_cate(){
		return this.product_cate;
	}
	public void setProduct_cate(String product_cate){
		this.product_cate = product_cate;
	}
	
	public byte getNum_of_product(){
		return this.num_of_product;
	}
	public void setNum_of_product(byte num_of_product){
		this.num_of_product = num_of_product;
	}
	
	public long getTotal_amount(){
		return this.total_amount;
	}
	public void setTotal_amount(long total_amount){
		this.total_amount = total_amount;
	}
	
}
