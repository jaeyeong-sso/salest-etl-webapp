package com.salest.etl.adminconsole.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class TrReceiptDailyAggJob {

	public static class TrReceiptDailyAggMapper extends Mapper<Object, Text, Text, LongPair> {

		public void map(Object key, Text value, Context context
	              ) throws IOException, InterruptedException {
			
			String[] columns = value.toString().split(",");
			String date = columns[0].substring(0, columns[0].lastIndexOf("-"));
			long amount = Long.parseLong(columns[4]);
				
			context.write(new Text(date), new LongPair((long)1, amount));
		}	
	}
	
	public static class TrReceiptDailyAggReducer extends Reducer<Text,LongPair,Text,LongPair> {
		
		private LongPair result = new LongPair();
		
		public void reduce(Text key, Iterable<LongPair> values, Context context)
				throws IOException, InterruptedException {
			
			long transactionCnt = 0;
			long sumOfAmount = 0;
			
			for (LongPair val : values) {
				transactionCnt += val.getFirst();
				sumOfAmount += val.getSecond();
			}
			result.set(transactionCnt, sumOfAmount);
			context.write(key, result);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: HaashtagCount <in> <out>");
			System.exit(2);
		}
		Job job = Job.getInstance(conf, "TrReceiptDailyAggJob");
		
		job.setJarByClass(TrReceiptDailyAggJob.class);
		job.setMapperClass(TrReceiptDailyAggMapper.class);
		job.setReducerClass(TrReceiptDailyAggReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongPair.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
