package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;

// 2014-12-01-01,12:34:15,29,1,3000

public class TimeBaseFirstStepAggMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context
              ) throws IOException, InterruptedException {
		
		String[] columns = value.toString().split(",");
		String date = columns[0].substring(0, columns[0].lastIndexOf("-"));
		
		// transaction time + sales amount
		context.write(new Text(date), new Text(columns[1] + "," + columns[4]));
	}	
}
