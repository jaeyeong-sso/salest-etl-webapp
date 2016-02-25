package com.salest.etl.adminconsole.mapreduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinTrReceiptMenuCodeReducer extends Reducer<TextPair,Text,Text,Text> {
	
	public void reduce(TextPair key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Iterator<Text> iter = values.iterator();
		Text firstRecode = new Text(iter.next());
		
		while(iter.hasNext()){
			Text nextRecode = iter.next();
			Text outValue = new Text(firstRecode.toString() + "\t" + nextRecode.toString());
			context.write(key.getFirst(), outValue);
		}
		
	}
}
