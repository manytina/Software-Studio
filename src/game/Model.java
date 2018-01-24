package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Model {

	private HashMap<String,String> songs = new HashMap<String,String>();
	private String[] songName = new String[100];
	String[] chosenSongTitles = new String[3];
	private int numOfSongs = 0;
	private int numOfStages = 3;
	
	public Model(){
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/data/song.txt"), "UTF-8"));
			String str;
			
			while((str = br.readLine()) != null){
				String[] token = str.split(" ");
				songName[numOfSongs] = token[0];
				songs.put(token[0], token[1]);
				this.numOfSongs++;

			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int getNumber(){
		return this.numOfSongs;
	}
	
	public String getSongPath(String name){
		return this.songs.get(name);
	}
	
	public String[] getSongName(){
		return this.songName;
	}
	
	public void chooseSongTitles(){
		
		Random rand = new Random();
		ArrayList nums = new ArrayList();
		int [] chosenNums = new int [numOfStages];
		boolean [] allNums = new boolean [numOfSongs];

		for(int i=0;i<numOfStages;i++){
			int tmp = rand.nextInt(numOfSongs);
			if(!allNums[tmp]){
				chosenNums[i] = tmp;
				chosenSongTitles[i] = songName[chosenNums[i]];
				allNums[tmp] = true;
			}
			else{
				tmp = rand.nextInt(numOfSongs);
				i--;
			}
		}
		for(int i=0;i<3;i++){
			System.out.println(chosenNums[i]);
		}
	}
	
	public String getSongTitle(int stageNum){
		return chosenSongTitles[stageNum];
	}
}
