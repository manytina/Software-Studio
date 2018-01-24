package Rank;

import java.util.ArrayList;

import music.*;
import game.*;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;


public class Rank{
//	private Game score = new Game();
	//private Database name;
	//private ArrayList<Integer> data = new ArrayList<>();
	int[] data = new int[100];
	//private String[] str = data.toArray(new String[data.size()]);
	private int count=0;
	private String line;
	int[] intArray = new int[100];
	private int score;
	
	public void addData()
	{
		data[0] = score;
		count++;
	}
	
	public void sort()
	{
		/*Collections.sort(data,new Comparator<Database>() {
			public int compare(Database o1, Database o2) {
				return o2.calculateScoreForVoting(song.getSongTitle(stageNum.numOfStages), playList.playlistsNames)-o1.calculateScoreForVoting(song.getSongTitle(stageNum.numOfStages), playList.playlistsNames);
		    }
		});*/
		Arrays.sort(data);
		for(int i=0;i<100;i++){
			intArray[i] = data[99-i];
			System.out.println("~~~~~"+intArray[i]+"~~~~~");
		}
	}
	
	public void readFile()
	{
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("res/data/rank.txt"), "UTF-8"));
			
			int i = 0;

	        while (i<99) {
				line = in.readLine();
				if(line==null)	break;
	          //place ints in array and increament the count of ints
	          data[i] = Integer.parseInt(line);
//	          System.out.println(i+"~~~~~"+data[i]);
	          count++;
	          i++;
	          

	        }
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public void writeFile()
	{
		try{
			File f = new File("res/data/rank.txt");
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
//			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));

			for(int i=0;i<data.length;i++){
				out.write(Integer.toString(data[i]));
		         System.out.println(data[i]+"~~~~~");
				out.newLine();			
			}
			out.close();			
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int[] getData(){
		return intArray;
	}
}
