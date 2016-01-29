package com.salest.etl.adminconsole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name="daily_tr_summary")
public class DailyTrSummary  {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
	private Date date;
	private byte num_of_order;
	private long total_amount;
	
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	
	public byte getNumOfOrder(){
		return num_of_order;
	}
	public void setNumOfOrder(byte numOfOrder){
		this.num_of_order = numOfOrder;
	}
	
	public long getTotalAmount(){
		return total_amount;
	}
	public void setTotalAmount(long total_amount){
		this.total_amount = total_amount;
	}
	
	@Override
    public String toString(){
        return "date="+date+", numOfOrder="+num_of_order+", totalAmount="+total_amount;
    }
}
