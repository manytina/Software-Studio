package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayList {

	String[] playlists = new String[6];
	HashMap<String,HashMap<String,Integer>> songOfPlaylist = new HashMap<String,HashMap<String,Integer>>(6);
//	HashMap<String,Integer> votesOfSong = new HashMap<String,Integer>(5);
	
	public PlayList(){
		playlists[0] = "Party";
		playlists[1] = "Study";
		playlists[2] = "Sleep";
		playlists[3] = "Sad";
		playlists[4] = "Happy";
		playlists[5] = "Sports";
		
		for(String playlist: this.playlists){
			this.songOfPlaylist.put(playlist, new HashMap<String,Integer>(5));		
		}
	}
	
/*	public void readFile(){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("res/data/style.txt"), "UTF-8"));
			String str;
			
			while((str = br.readLine()) != null){
				String[] token = str.split("#");
				String playlist = token[0];
				String song = token[1];
				ArrayList<String> songs = this.songOfPlaylist.get(playlist);
				if(!songs.contains(song)){
					songs.add(song);				
				}
			}
			
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
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
				
				if(votesNum>0){
					if(this.songOfPlaylist.get(listName).size() < 5){
						this.songOfPlaylist.get(listName).put(song,votesNum);				
					}
					else{
						HashMap<String,Integer> votesOfSong = this.songOfPlaylist.get(listName);
						HashMap<String,Integer> revised;
						int min = this.getMin(votesOfSong);
						if(votesNum >= min){
							revised = this.removeMin(votesOfSong, min);
							revised.put(song,votesNum);
							this.songOfPlaylist.remove(listName);
							this.songOfPlaylist.put(listName, revised);
						}
					}
				}
	//			this.songOfPlaylist.get(playlist).put(listName, votesNum);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
/*	
	public void writeFile(){
		try{
			File file = new File("res/data/style.txt");
			BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			for(String playlist: this.playlists){
				ArrayList<String> songs = this.songOfPlaylist.get(playlist);
				for(String song: songs){
					bf.write(playlist+"#"+song);
					bf.newLine();
				}
			}
			
			bf.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}*/
	
	public void writeFile(){
		try{
			File file = new File("res/data/style.txt");
			BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			
			for(String playlist: this.playlists){
				HashMap<String,Integer> songs = this.songOfPlaylist.get(playlist);
				for(String song: songs.keySet()){
					bf.write(playlist+"#"+song);
					bf.newLine();
				}
			}
			
			bf.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
/*	
	public void addSong(String playlist, String song){
		if(!this.songOfPlaylist.get(playlist).contains(song)){
			this.songOfPlaylist.get(playlist).add(song);
		}
	}*/
	/*
	public void show(){
		for(String playlist: this.playlists){
			HashMap<String,Integer> songs = this.songOfPlaylist.get(playlist);
			for(String song: songs.keySet()){
				System.out.println()
			}
		}
	}*/
	
	public int getMin(HashMap<String,Integer> votesOfSong){
		int min=0, count = 0;
		for(String songTitle: votesOfSong.keySet()){
			if(count==0){
				min = votesOfSong.get(songTitle);
			}
			else{
				if(min > votesOfSong.get(songTitle)){
					min = votesOfSong.get(songTitle);
				}							
			}
			count++;
		}
		
		return min;
	}
	
	public HashMap<String,Integer> removeMin(HashMap<String,Integer> votesOfSong, int min){
		for(String songTitle: votesOfSong.keySet()){
			if(votesOfSong.get(songTitle)==min){
				votesOfSong.remove(songTitle);
				break;
			}
		}
		for(String songTitle: votesOfSong.keySet()){
			System.out.println(songTitle+"~~~"+votesOfSong.get(songTitle));
		}
		return votesOfSong;
	}
}

