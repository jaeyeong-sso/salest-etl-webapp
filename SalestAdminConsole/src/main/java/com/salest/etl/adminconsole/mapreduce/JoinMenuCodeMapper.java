package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

// 커피,194,아이스아메리카노UP,2900

public class JoinMenuCodeMapper extends Mapper<LongWritable, Text, TextPair, Text>{
	
	public void map(LongWritable key, Text value, Context context
            ) throws IOException, InterruptedException {
		
		String[] columns = value.toString().split(",");
		String product_code = columns[1];
		
		String outValue = String.format("%s,%s,%s", columns[0],columns[2],columns[3]);
	
		context.write(new TextPair(product_code, "MENUCODE-RECODE"), new Text(outValue));
	}	
}