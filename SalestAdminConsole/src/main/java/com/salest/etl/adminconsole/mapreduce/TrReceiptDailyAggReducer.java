package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class TrReceiptDailyAggReducer extends Reducer<Text,LongPair,Text,LongPair> {
	
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
