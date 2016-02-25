package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;

//"2015-02-03,에스프레소	 커피,1,2300"

public class TrMonthlyAggByProductReducer extends Reducer<TextPair,Text,Text,Text> {
	
	public void reduce(TextPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		long sumOfSalesCnt = 0;
		long sumOfSalesAmount = 0;
		
		String menu_category = null;
		
		for (Text val : values) {
			String[] tr_items = val.toString().split(",");
			
			menu_category = tr_items[0];
			sumOfSalesCnt += Long.parseLong(tr_items[1]);
			sumOfSalesAmount += Long.parseLong(tr_items[2]);
		}
		
		String outValue = String.format("%s,%s,%s,%d,%d", 
				key.getFirst(), key.getSecond(), menu_category, sumOfSalesCnt, sumOfSalesAmount);
		
		context.write(null, new Text(outValue));
	}
}
