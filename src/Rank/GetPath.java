package Rank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetPath {
	private String line;
	private String sleepStyle = null;
    private String studyStyle = null;
    private String partyStyle = null;
    private String sportStyle = null;
    private String happyStyle = null;
    private String sadStyle = null;
    private String playList = new String();
    private String[][] style = new String[1000][2];
	public void readFile() throws IOException
	{
		FileReader fr = new FileReader("res/data/style.txt");
		BufferedReader in = new BufferedReader(fr);
			
		//int sleepCount=0,partyCount=0,studyCount=0,sportCount=0,happyCount=0,sadCount=0;
		int i = 0;
	    while ((line = in.readLine()) != null) {
	          //place ints in array and increament the count of ints
	    	style[i] = line.split("#");
	    	if(style[i][0]=="Sleep" && sleepStyle==null)
	    	{
	    		sleepStyle = style[i][1];
	    		
	    	}
	    	else if(style[i][0]=="Party" && partyStyle==null)
	    	{
	    		partyStyle = style[i][1];
	    		
	    	}
	    	else if(style[i][0]=="Study" && studyStyle==null)
	    	{
	    		studyStyle = style[i][1];
	    		
	    	}
	    	else if(style[i][0]=="Sport" && sportStyle==null)
	    	{
	    		sportStyle = style[i][1];
	    		
	    	}
	    	else if(style[i][0]=="Happy" && happyStyle==null)
	    	{
	    		happyStyle = style[i][1];
	    		
	    	}
	    	else if(style[i][0]=="Sad" && sadStyle==null)
	    	{
	    		sadStyle = style[i][1];
	    		
	    	}
	    i++;
	    }
	    fr.close();
	}
}
