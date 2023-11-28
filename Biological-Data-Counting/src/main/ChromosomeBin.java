//package main;

//import lombok.Getter;
//import lombok.Setter;

public class ChromosomeBin {
	
	//private @Getter @Setter Integer startingIndex;
	//private @Getter @Setter Integer endingIndex;
	
	private Integer startingIndex;
	private Integer endingIndex;
	
	public void setStartingIndex(Integer bin)
	{
		startingIndex = bin;
	}
	
	public Integer getStartingIndex()
	{
		return startingIndex;
	}
	
	public void setEndingIndex(Integer bin)
	{
		endingIndex = bin;
	}
	
	public Integer getEndingIndex()
	{
		return endingIndex;
	}
	
}
