package com.salest.etl.adminconsole.hadoop;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class TrReceiptDailyAggMapper extends Mapper<Object, Text, Text, LongPair> {

	public void map(Object key, Text value, Context context
              ) throws IOException, InterruptedException {
		
		String[] columns = value.toString().split(",");
		String date = columns[0].substring(0, columns[0].lastIndexOf("-"));
		long amount = Long.parseLong(columns[4]);
			
		context.write(new Text(date), new LongPair((long)1, amount));
	}	
}
