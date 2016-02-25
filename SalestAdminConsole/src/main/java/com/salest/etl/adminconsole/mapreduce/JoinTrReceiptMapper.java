package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

// 2015-12-31-10,14:46:32,194,1,2900
 
public class JoinTrReceiptMapper extends Mapper<LongWritable, Text, TextPair, Text>{
	
	public void map(LongWritable key, Text value, Context context
            ) throws IOException, InterruptedException {
		
		String[] columns = value.toString().split(",");
		String product_code = columns[2];
		
		String outValue = String.format("%s,%s,%s,%s", columns[0],columns[1],columns[3],columns[4]);
	
		context.write(new TextPair(product_code, "TR-RECEIPT-RECODE"), new Text(outValue));
	}	
}