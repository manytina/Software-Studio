package music;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class StartInterface extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private PImage startImage;
	private PImage backImage;
	private User user;
	private String userName;
	private PFont font;
	
	JButton b = new JButton();
	private boolean startFlag = false;

	public StartInterface(){
		
	}
	
	public void setup(){
		smooth();
		size(1008,1000);
			
		this.startImage = loadImage("res/picture/bar/littleChicken.png");
		this.backImage = loadImage("res/picture/background/black&blueStripe.jpeg");
		this.font = createFont("res/fonts/FromCartoonBlocks.ttf",90);
	//	this.font = createFont(this.getClass().getResource("res/fonts/FromCartoonBlocks.ttf").getPath(), 90);
	}
	public void draw(){
		this.image(backImage, 0, 0, 1008, 1000);
		textSize(60);
		textFont(font);
		text("Bomb ! Chicken's revenge",45,130);
		fill(255,97,176);//pink
		textSize(55);
		text("Press Right if You Are a Girl !",162,720);
		fill(209,233,255);//blue
		textSize(55);
		text("Press Left if You Are a Boy !",172,800);
		fill(255,255,255);//white
		image(startImage,0,-100,startImage.height,startImage.width);
	}
	public void keyPressed(){
		if(this.keyCode==LEFT){
			this.user = new User(1);
			this.userName = this.user.playerName(1);
			startFlag = true;
		}
		else if(this.keyCode==RIGHT){
			this.user = new User(2);
			this.userName = this.user.playerName(2);
			startFlag = true;
		}
	}
	
	public boolean isStart(){
		return startFlag;
	}
	
	public void isClosed(){
		startFlag = false;
	}
	
	public String getUserName(){
		return this.userName;
	}
}

