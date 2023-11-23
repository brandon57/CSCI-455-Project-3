package main;

import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
//import java.util.to

public class DataMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static IntWritable one = new IntWritable(1);
	private Text inputText = new Text();
	
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException
	{
		StringTokenizer data = new StringTokenizer(value.toString());
		while(data.hasMoreTokens())
		{
			inputText.set(data.nextToken());
			context.write(inputText, one);
		}
	}
	

}
