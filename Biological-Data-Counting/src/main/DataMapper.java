//package main;

import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class DataMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	private static IntWritable one = new IntWritable(1);
	private Text inputText = new Text();
	
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException
	{
		String[] line = value.toString().split("\\s+");
		if(line.length == 4)
		{
			Integer chrom1 = Integer.parseInt(line[0]);
			long chrom1BinPairs = Long.parseLong(line[1]);
			Integer chrom2 = Integer.parseInt(line[2]);
			long chrom2BinPairs = Long.parseLong(line[3]);
			
			//Checks for errors
			//If there is an error it sets write to false and it doesn't write it to a file
			//Writes to file
			if(Hadoop.chromTable.validBin(chrom1, chrom1BinPairs) && Hadoop.chromTable.validBin(chrom2, chrom2BinPairs))
			{
				long chrom1Bin = Hadoop.chromTable.getBin(chrom1, chrom1BinPairs);
				long chrom2Bin = Hadoop.chromTable.getBin(chrom2, chrom2BinPairs);
				
				if(chrom1Bin > chrom2Bin)
				{
					long temp = chrom1Bin;
					chrom1Bin = chrom2Bin;
					chrom2Bin = temp;
				}
				context.write(new Text("(" + chrom1Bin + ", " + chrom2Bin + ") "), one);
			}
		}
	}
}
