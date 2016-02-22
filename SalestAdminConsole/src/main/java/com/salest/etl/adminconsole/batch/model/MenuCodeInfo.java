package com.salest.etl.adminconsole.batch.model;

public class MenuCodeInfo  {
	
	private String category;
	private int product_code;
	private String product_name;
	private int price;
	
	public MenuCodeInfo(){
		
	}
	
	public String getCategory(){
		return this.category;
	}
	public void setCategory(String category){
		this.category = category;
	}
	
	public int getProduct_code(){
		return this.product_code;
	}
	public void setProduct_code(int product_code){
		this.product_code = product_code;
	}
	
	public String getProduct_name(){
		return this.product_name;
	}
	public void setProduct_name(String product_name){
		this.product_name = product_name;
	}
	
	public int getPrice(){
		return this.price;
	}
	public void setPrice(int price){
		this.price = price;
	}
}

