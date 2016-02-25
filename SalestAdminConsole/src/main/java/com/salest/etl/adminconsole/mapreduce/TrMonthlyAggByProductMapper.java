package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;


// From "1	커피,에스프레소,2500	 2015-02-03-21,20:49:01,1,2300"
// To "2015-02-03 ],에스프레소	 커피,1,2300"

public class TrMonthlyAggByProductMapper extends Mapper<LongWritable, Text, TextPair, Text> {

	public void map(LongWritable key, Text value, Context context
              ) throws IOException, InterruptedException {
		
		String[] parts = value.toString().split("\t");
		
		String[] menuinfo_part = parts[1].split(",");
		String[] tr_part = parts[2].split(",");
		
		String date_month = tr_part[0].substring(0, tr_part[0].lastIndexOf("-"));
		String product_name = menuinfo_part[1];
		
		String outValue = String.format("%s,%s,%s", menuinfo_part[0],tr_part[2],tr_part[3]);
		
		context.write(new TextPair(date_month,product_name), new Text(outValue));
	}	
}
