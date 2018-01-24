package game;

import Rank.*;
import bar.TimeBar;

import java.awt.event.MouseEvent;

import bar.TopBar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import music.MusicPlayer;
import music.StartInterface;
import playScene.PlayScene;


public class Game extends JFrame implements Runnable{
	
	private Rectangle bounds = new Rectangle(850,1000);
	private Queue<PlayScene> playScenes = new LinkedList<PlayScene>();
	private PlayScene currentPlayScene;
	private TopBar topBar;
	private Thread gameThread;
	private DataBase dataBase = new DataBase();
	private DataBase currentDataBase;
	private SelectionScene currentSelectionScene;
	private Queue<SelectionScene> selectionScenes = new LinkedList<SelectionScene>();
	private StartInterface appletStart;
	private Model model;
	private RankScene rankScene;
	private MusicPlayer audio = new MusicPlayer();
	private ImagePanel panel ;
	private Rank rank;
	private PlayList playList = new PlayList();
	
	private int playSceneNumber = 0;
	private int stageScore = 0, styleScore = 0, totalScore = 0;
	private String userName = null;
	private int time = 30000;
	private boolean isSelecting = false;
	private boolean gameStart = false;
	private String currentSongTitle = null;
	private boolean writeFlag = false;
	private boolean gameOver = false;
	private int[] data = new int[100];
	
	private int testTime;	// test case

	
	public Game(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0,0,1008,1000);
		this.setVisible(true);
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		this.model = new Model();
		this.model.chooseSongTitles();
		
		this.addStartScene();
	}
	
	public void start() {
		if (this.gameThread == null) {
            this.gameThread = new Thread(this);
        }
		this.gameThread.start();
		this.setVisible(true);
	}
	
	public void addTopBar(){
		this.topBar=new TopBar(new Rectangle(850,0,300,350));
		this.topBar.setPreferredSize(new Dimension(150,1000));
	//	this.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		topBar.timeBar.progressBar.setValue(time/300);
		
		/*test data*/
		try{
			this.topBar.setUserName(this.userName);
			topBar.setUserPicLabel(new ImageIcon(ImageIO.read(new File("res/picture/bar/userChicken.png"))));			
		}
		catch(Exception e){
			e.printStackTrace();
		}

		this.add(topBar);		
	}
	
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(850, 1000);
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }
		}
	
	public void addSelectionScene(){
		panel = new ImagePanel(new ImageIcon("res/picture/background/black&blueStripe.jpeg").getImage());
		SelectionScene selectScene = new SelectionScene(this.currentSongTitle);
/*		selectScene.setSize(this.bounds.width,this.bounds.height+300);
		selectScene.setVisible(true);
		this.selectionScenes.add(selectScene);*/
		selectScene.setPreferredSize(new Dimension(850,1000));
		selectScene. setOpaque(false);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		panel.add(selectScene);
		this.setContentPane(panel);
		panel.setVisible(true);
		this.selectionScenes.add(selectScene);
	}
	 
	public void nextSelectionScene(){
		this.currentSelectionScene = this.selectionScenes.poll();
		this.currentSelectionScene.setSize(this.bounds.width,this.bounds.height+300);
		this.currentSelectionScene.setLocation(0,0);
		this.currentSelectionScene.setVisible(true);
		
		this.add(currentSelectionScene);
	}
	
	public void addRankScene(){
		this.rankScene = new RankScene(data, totalScore);
		System.out.println("Game:"+totalScore);
		this.rankScene.setLocation(0,0);
		this.rankScene.setPreferredSize(new Dimension(1008,1000));
//		this.rankScene.setBounds(0, 0, 1000, 1000);
		this.rankScene.setVisible(true);
		this.add(rankScene);
	}
	
	public void addStartScene(){
		this.appletStart = new StartInterface();
		this.appletStart.setVisible(true);
		this.add(appletStart);
		this.appletStart.requestFocus();
		
		appletStart.init();
		appletStart.start();
	}
	
	public void addPlayScene(){
		PlayScene playScene = new PlayScene(this, this.bounds);
		playScene.setVisible(true);
		playScenes.add(playScene);
	}
	
	public void nextPlayScene(){
		this.currentPlayScene = this.playScenes.poll();
		this.currentPlayScene.init();
		this.currentPlayScene.start();
		this.add(this.currentPlayScene);
		this.currentPlayScene.requestFocus();
		
		this.playSceneNumber++;
		
		this.musicPlay();
	}
	
	public void addScene_play(){
		this.addPlayScene();
		this.nextPlayScene();
		this.addTopBar();
	}
	
	public void removeScene_play(){
		this.remove(this.currentPlayScene);
		this.remove(this.topBar);
	}
	
	public void addScene_select(){
		this.addSelectionScene();
		this.nextSelectionScene();
		this.addTopBar();
	}
	
	public void removeScene_select(){
		this.remove(this.currentSelectionScene);
		this.remove(this.panel);
		this.remove(this.topBar);
		this.musicStop();
	}
	
	public void musicPlay(){
		this.currentSongTitle = this.model.getSongTitle(playSceneNumber-1);
		String filePath = this.model.getSongPath(currentSongTitle);
		this.audio.loadAudio(filePath);
		this.audio.play();
		
		System.out.println("song :::::"+currentSongTitle+":::::"+filePath);
	}
	
	public void musicStop(){
		this.audio.stop();
	}
	
	public void addRank(){
		this.rank = new Rank();
		rank.setScore(this.totalScore);
		rank.readFile();
		rank.addData();
		rank.sort();
		rank.writeFile();
		this.data = rank.getData();
	}

	public void timeRunning(){
		
		class thread extends Thread{
			
			@Override
			public void run(){
				while (time>0){
					try{
						Thread.sleep(270);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					
					time -= 10;
//					System.out.printf("time:%d\n",time);
					topBar.timeBar.progressBar.setValue(time/300);
//					System.out.printf("value:%d\n",topBar.timeBar.progressBar.getValue());
					if(time==0){
						//***********submit stage*****//
						break;
					}
				}
			}
		}
		
		thread timeControll;
		timeControll = new thread();
		timeControll.start();
	}
	
	
	@Override
	public void run() {
		
		while(Thread.currentThread() == this.gameThread){
			try{
				if(appletStart.isStart()){
					this.remove(appletStart);
					this.userName = appletStart.getUserName();
					this.addScene_play();
					this.gameStart = true;
					this.appletStart.isClosed();
				}
				
				if(this.gameStart){
					this.timeRunning();
	            	this.testTime++;
	            	
	            	/*playing*/
	            	if(!this.isSelecting){
	                	this.stageScore = currentPlayScene.getScore();
	        			this.topBar.setStageNum(playSceneNumber);
	    				this.topBar.setScore(totalScore+stageScore);
	            	}
	            	
	            	/********when it times up, it removes play scene and add select scene
	            	 * it's waiting for player to select now*******/
	            	
	            	if(topBar.timeBar.progressBar.getValue()==0 && !this.isSelecting){
	            	
	            		System.out.println("A");
	 
	            		this.removeScene_play();
	            		
	            		this.addScene_select();
	            		System.out.println(currentSelectionScene.getIsDoneSelection());
	            		
	            		this.isSelecting = true;
	            	}
	            	
	            	/**********selection is done
	            	 * it removes select scene and adds play scene*********/
	            	
	            	if(isSelecting && !currentSelectionScene.getIsDoneSelection()){
	         			this.topBar.setStageNum(playSceneNumber);
	        			this.topBar.setScore(totalScore+stageScore+styleScore);
	//        			System.out.println(currentSelectionScene.getScore());
	            	}
	            	
	            	if(isSelecting && currentSelectionScene.getIsDoneSelection()){
	        			totalScore  += styleScore;
	            		this.removeScene_select();

	        			if(playSceneNumber==3){
	        				totalScore += stageScore;
	        				this.playList.readFile();
	        				this.playList.writeFile();
	                		this.addRank();
	        				this.addRankScene();
	                		this.isSelecting = false;
	                		this.gameOver = true;
	                		this.gameStart = false;
	        			}
	        			else{
	                		this.addScene_play();
	                  		totalScore += stageScore;
	                		this.testTime = 0;
	                		time = 30000;
	                		topBar.timeBar.progressBar.setValue(time);
	                		this.isSelecting = false;
	        			}
	            	}				
				}
				
				if(gameOver){
//					System.out.println("why???QQQQQ");
					
	//				this.addRank();	
					
		
				}
				
				Thread.sleep(500);
			
			}
            catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getScore(){
		return this.totalScore;
	}
}
