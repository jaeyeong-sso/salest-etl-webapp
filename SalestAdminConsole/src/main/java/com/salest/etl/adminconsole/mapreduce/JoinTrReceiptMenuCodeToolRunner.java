package com.salest.etl.adminconsole.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class JoinTrReceiptMenuCodeToolRunner extends Configured implements Tool {
		
		private Configuration conf;
		
		public static class KeyPartitioner extends Partitioner<TextPair, Text> {
			@Override
			public int getPartition(TextPair key, Text value, int numPartitions) {
				// TODO Auto-generated method stub
				return (key.getFirst().hashCode() & Integer.MAX_VALUE) % numPartitions;
			}
		}
		
		public JoinTrReceiptMenuCodeToolRunner(){
			
		}
		
		public JoinTrReceiptMenuCodeToolRunner(Object obj) {

		}

		
	    public static void main(String[] args) throws Exception {
	        int res = ToolRunner.run(new JoinTrReceiptMenuCodeToolRunner(), args);
	        System.exit(res);
	    }
	 
		public void setConf(Configuration conf) {
			// TODO Auto-generated method stub
			this.conf = conf;
			
		}

		public Configuration getConf() {
			// TODO Auto-generated method stub
			return conf;
		}
		
	    public int run(String[] args) throws Exception {
	 
	        Job job = Job.getInstance(conf, this.getClass().toString());
	   
			job.setJarByClass(JoinTrReceiptMenuCodeToolRunner.class);
			
			Path trReceiptFilePath = new Path("/salest/raw_data/transaction_receipt");	//otherArgs[0]
			Path menuCodeFilePath  = new Path("/salest/raw_data/menu_code_info");
			Path joinedOutFilePath  = new Path("/salest/processed_data/transaction_receipt_with_menucode");
			
			MultipleInputs.addInputPath(job, trReceiptFilePath, TextInputFormat.class, JoinTrReceiptMapper.class);
			MultipleInputs.addInputPath(job, menuCodeFilePath, TextInputFormat.class, JoinMenuCodeMapper.class);
			
			FileOutputFormat.setOutputPath(job, joinedOutFilePath);
			
			job.setPartitionerClass(KeyPartitioner.class);
			job.setGroupingComparatorClass(TextPair.FirstComparator.class);
			
			job.setMapOutputKeyClass(TextPair.class);
			job.setMapOutputValueClass(Text.class);
			
			job.setReducerClass(JoinTrReceiptMenuCodeReducer.class);
		
			job.setOutputKeyClass(Text.class);
			
			
			return job.waitForCompletion(true) ? 0 : 1;
	    }
}
