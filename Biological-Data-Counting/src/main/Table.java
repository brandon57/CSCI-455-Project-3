//package main;

import java.util.HashMap;
import java.util.Map;

public class Table {
	
	private Map<Integer, Integer> chromosomes = new HashMap();
	private Map<Integer, Integer> bins = new HashMap();
	public static Map<Integer, ChromosomeBin> binPairs = new HashMap();
	
	public Table()
	{
		startUp();	
	}
	
	private void startUp()
	{
		chromosomes.put(1, 248956422);
		chromosomes.put(2, 242193529);
		chromosomes.put(3, 198295559);
		chromosomes.put(4, 190214555);
		chromosomes.put(5, 181538259);
		chromosomes.put(6, 170805979);
		chromosomes.put(7, 159345973);
		chromosomes.put(8, 145138636);
		chromosomes.put(9, 138394717);
		chromosomes.put(10, 133797422);
		chromosomes.put(11, 135086622);
		chromosomes.put(12, 133275309);
		chromosomes.put(13, 114364328);
		chromosomes.put(14, 107043718);
		chromosomes.put(15, 101991189);
		chromosomes.put(16, 90338345);
		chromosomes.put(17, 83257441);
		chromosomes.put(18, 80373285);
		chromosomes.put(19, 58617616);
		chromosomes.put(20, 64444167);
		chromosomes.put(21, 46709983);
		chromosomes.put(22, 50818468);
		chromosomes.put(23, 156040895);
		//chromosomes.put(24, 57227415);
		createBins();
		createBinPairs();
	}
	
	private void createBins()
	{
		for(Map.Entry<Integer, Integer> temp : chromosomes.entrySet())
		{
			bins.put(temp.getKey(), (temp.getValue()+100000) / 100000);
		}
	}
	
	private void createBinPairs()
	{
		Integer start = 0;
		Integer end = 0;
		for(Map.Entry<Integer, Integer> temp : bins.entrySet())
		{
			ChromosomeBin index = new ChromosomeBin();
			if(temp.getKey().equals(1))
			{
				index.setStartingIndex(1);
				index.setEndingIndex(temp.getValue());
			}
			else
			{
				for(Map.Entry<Integer, ChromosomeBin> temp2 : binPairs.entrySet())
				{
					start += temp2.getValue().getStartingIndex();
					end += temp2.getValue().getEndingIndex();
				}
				index.setStartingIndex(start);
				index.setEndingIndex(end + temp.getValue());
			}
			binPairs.put(temp.getKey(), index);
			
			//.put(temp.getKey(), temp.getValue());
		}
	}
	
	public long getBin(Integer chrom, long basePair)
	{
		long tempBin = (basePair + 100000) / 100000;
		int i = 0;
		for(Map.Entry<Integer, ChromosomeBin> temp2 : binPairs.entrySet())
		{
			if(i < chrom)
			{
				tempBin += temp2.getValue().getEndingIndex();
			}
			else
			{
				break;
			}
		}
		return tempBin;
	}
	
	//Checks if the Bins are valid
	public boolean validBin(Integer chrom, long basePair)
	{
		//long tempBin = (basePair + 100000) / 100000;
		
		long tempBin = getBin(chrom, basePair);
//		for(Map.Entry<Integer, ChromosomeBin> temp2 : binPairs.entrySet())
//		{
//			tempBin += temp2.getValue().getStartingIndex();
//		}
		long length = binPairs.get(chrom).getEndingIndex() - binPairs.get(chrom).getStartingIndex();
		
		return (chrom != null || (tempBin < binPairs.get(chrom).getStartingIndex() && tempBin > binPairs.get(chrom).getEndingIndex()));
//		if(chrom != (null) && (tempBin > binPairs.get(chrom).getStartingIndex() && tempBin < binPairs.get(chrom).getEndingIndex()))
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
	}
	
	

}
