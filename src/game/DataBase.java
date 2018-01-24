package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DataBase {
	
	private HashMap<String,HashMap<String,Integer>> playlistsOfSongs;
	private HashMap<String,Integer> votesOfPlaylists;
    int numOfStages = 3;
	int numOfSongs;
	int numOfPlaylists = 6;
	String [] songTitles;
	String [] playlistsNames;
	int unitScoreForVoting = 50;
	int scoreForVotingCurrentPlaylist;
	int totalScoreForVotingOfThisSong = 0;
	
	private Model model = new Model();
	
	public DataBase(){
		
		numOfSongs = this.model.getNumber();
		songTitles = this.model.getSongName();
		
		String[] lists = new String[6];
		lists[0] = "Party";
		lists[1] = "Study";
		lists[2] = "Sleep"; 
		lists[3] = "Sad";
		lists[4] = "Happy";
		lists[5] = "Sports";
		playlistsNames = lists;
		
		playlistsOfSongs = new HashMap<String,HashMap<String,Integer>>(numOfSongs);
		
		/*store data in hash map*/
		for(String i:songTitles){
			votesOfPlaylists = new HashMap<String,Integer>(numOfPlaylists);
			for(String j:playlistsNames){
				votesOfPlaylists.put(j, 0);
			}
			playlistsOfSongs.put(i, votesOfPlaylists);
		}
		
//		this.readFile();
		
	}
	
	public void addVote(String songTitle,String playlistName){
		this.playlistsOfSongs.get(songTitle).put(playlistName, this.playlistsOfSongs.get(songTitle).get(playlistName)+1) ;
	}
	
	public int getTotalVotesOfSong(String songTitle){
		int totalVotes=0;
		for(String j:playlistsNames){
			totalVotes += this.playlistsOfSongs.get(songTitle).get(j);
		}
		return totalVotes;
	}
	
	public int calculateScoreForVoting(String songTitle, String playlistName){
		scoreForVotingCurrentPlaylist = unitScoreForVoting * this.playlistsOfSongs.get(songTitle).get(playlistName)/this.getTotalVotesOfSong(songTitle);
		return scoreForVotingCurrentPlaylist;
	}
/*	
	public int getTotalScoreForVotingOfOneSong(String songTitle){
		return totalScoreForVotingOfThisSong;
	}*/
	
	public void readFile(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/data/data.txt"), "UTF-8"));
			String str;
			
			while((str = br.readLine()) != null){
//				System.out.println(str);
				String[] songToken = str.split("#");
				String song = songToken[0];
				String[] listToken = songToken[1].split(":");
				String listName = listToken[0];
				int votesNum = Integer.parseInt(listToken[1]);
				
				this.playlistsOfSongs.get(song).put(listName, votesNum);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void writeFile(){
		try{
			File file = new File("res/data/data.txt");
			BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			for(String song: this.songTitles){
				if(song==null){
					break;
				}
				HashMap<String,Integer> votes = this.playlistsOfSongs.get(song);
				for(String listName: this.playlistsNames){
					bf.write(song+"#"+listName+":"+votes.get(listName));
					bf.newLine();
//					System.out.println(song+"#"+listName+":"+votes.get(listName));
				}
			}
			
			bf.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void reviseFile(String songTitle, String playList){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/data/data.txt"), "UTF-8"));
			String str;
			while((str = br.readLine()) != null){
				String[] songToken = str.split("#");
				String song = songToken[0];
				String[] listToken = songToken[1].split(":");
				String listName = listToken[0];
				int votesNum = Integer.parseInt(listToken[1]);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
