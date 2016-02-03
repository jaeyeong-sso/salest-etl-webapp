package com.salest.etl.adminconsole.batch.model;

import java.util.Date;

public class DailyTrSummary {
	
	private Date date;
	private byte num_of_order;
	private long total_amount;
	
	public DailyTrSummary(){
		
	}
	
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	
	public byte getNum_of_order(){
		return num_of_order;
	}
	public void setNum_of_order(byte num_of_order){
		this.num_of_order = num_of_order;
	}
	
	public long getTotal_amount(){
		return total_amount;
	}
	public void setTotal_amount(long total_amount){
		this.total_amount = total_amount;
	}
}
