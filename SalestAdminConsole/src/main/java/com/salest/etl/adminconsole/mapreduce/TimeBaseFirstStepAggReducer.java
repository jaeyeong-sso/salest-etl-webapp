package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// [input]  : 2014-12-01  17:14:57, 2500
// [output] : 2014-12-01, 5000,0,0,0,0,0,0,0,0,0,0,0,0,0

public class TimeBaseFirstStepAggReducer extends Reducer<Text,Text,Text,Text> {
	
	private Text result = new Text();
	private String resultStr;
	
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
	
		ArrayList<Long> listSalesAmountsPerTime = new ArrayList<Long>();
		
		// hours o'clock : 10,11,12,13,14,15,16,17,18,19,20,21,22,23
		for(Integer i=10; i<24; i++){
			listSalesAmountsPerTime.add((long) 0);
		}
		
		for(Text val : values) {
			String[] columns = val.toString().split(",");
			String[] timeStr = columns[0].split(":");
			
			int idxHour = Integer.parseInt(timeStr[0]);
			
			if(idxHour >= 10){
				long amount = Long.parseLong(columns[1]);
				long accAmount  = listSalesAmountsPerTime.get(idxHour-10);
				
				long totalAmount = amount + accAmount;
				listSalesAmountsPerTime.set(idxHour-10, totalAmount);
			}
		}
	
		resultStr = "";
		for(long amountPerTime : listSalesAmountsPerTime){
			resultStr += String.format("%d,", amountPerTime);
		}

		result.set(resultStr.substring(0, (resultStr.length()-1)));
		context.write(key, result);
	}
}
