package playScene;
import game.Game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;


public class PlayScene extends PApplet{

	private Game parentFrame;
	private Rectangle bounds;
	private PImage[] backgroundImg;
	private PImage[] eggImgs,hammerImgs, resultImgs, mopImgs;
	private HashMap<Integer,ArrayList<Egg>> eggs = new HashMap<Integer,ArrayList<Egg>>();
	private Hammer hammer;
//	private Bubble bubble;
	private Mop mop;
	
	private int unPressedEgg, preUnPressedEgg;
	private boolean resultFlag = false;
	private int score = 0;
	private int backgroundColor = 200;
	private int bgIMGCount = 0;
	
	public PlayScene(Game parentFrame, Rectangle bounds){
		this.parentFrame = parentFrame;
		this.bounds = bounds;
		/*
		this.backgroundImg = new PImage[2];
		this.backgroundImg[0] = loadImage("res/picture/background/black_stripe.jpg");
		this.backgroundImg[1] = loadImage("res/picture/background/red_black_stripe.jpg");
*/
		this.eggImgs = new PImage[11];
		this.eggImgs[0] = loadImage("res/picture/chicken/eggPrimitive.png");
		this.eggImgs[1] = loadImage("res/picture/chicken/breakEgg.png");
		this.eggImgs[2] = loadImage("res/picture/chicken/break.png");	
		this.eggImgs[3] = loadImage("res/picture/chicken/egg.png");
		this.eggImgs[4] = loadImage("res/picture/chicken/scared.png");
		this.eggImgs[5] = loadImage("res/picture/chicken/wow.png");
		this.eggImgs[6] = loadImage("res/picture/chicken/cry.png");		
		this.eggImgs[7] = loadImage("res/picture/chicken/chicken.png");
		this.eggImgs[8] = loadImage("res/picture/chicken/run.png");
		this.eggImgs[9] = loadImage("res/picture/chicken/chickenLeft.png");
		this.eggImgs[10] = loadImage("res/picture/chicken/runLeft.png");
		
		this.hammerImgs = new PImage[3];
		this.hammerImgs[0] = loadImage("res/picture/chicken/hammer3.png");
		this.hammerImgs[1] = loadImage("res/picture/chicken/hammer.png");
		this.hammerImgs[2] = loadImage("res/picture/chicken/hammer2.png");
		
		this.resultImgs = new PImage[2];
		this.resultImgs[0] = loadImage("res/picture/chicken/bubbleBlue.png");
		this.resultImgs[1] = loadImage("res/picture/chicken/bubbleRed.png");
		
		this.mopImgs = new PImage[2];
		this.mopImgs[0] = loadImage("res/picture/chicken/mop.png");
		this.mopImgs[1] = loadImage("res/picture/chicken/mop2.png");
	}
	
	public void setup(){
		
		smooth();
		size(bounds.width, bounds.height);
		frameRate(60);
		
		for(int i=0;i<3;i++){
			ArrayList<Egg> egg = new ArrayList<Egg>();
			egg.add(new Egg(this, this.eggImgs, i));
			egg.get(0).setMovement(Egg.STAY);
			this.eggs.put(i,egg);
		}
		
		this.hammer = new Hammer(this, this.hammerImgs);
//		this.bubble = new Bubble(this, this.resultImgs);
		this.mop = new Mop(this, this.mopImgs);
		
		this.chooseEgg();
	}
	
	public void draw(){
	//	image(this.backgroundImg[bgIMGCount], 0, 0, bounds.width, bounds.height);
		background(58, 0, 112);

//		frameRate(15);
		if(!keyPressed){
			for(int i: eggs.keySet()){
				for(Egg egg: this.eggs.get(i)){
					egg.display();
				}
			}
			this.hammer.display();
//			this.bubble.display();
			this.mop.display();
		}

		this.checkEggs();
		this.overLoad();
		this.mopCheck();
	}
	
	public void keyPressed(){
		this.hammer.setAnimation(true);
		if(keyCode==LEFT){
			this.hammer.setMovement(Hammer.LEFT);
			this.hammer.display();
		}
		else if(keyCode==DOWN){
			this.hammer.setMovement(Hammer.DOWN);
			this.hammer.display();
		}
		else if(keyCode==RIGHT){
			this.hammer.setMovement(Hammer.RIGHT);
			this.hammer.display();
		}

		ArrayList<Egg> egg = this.eggs.get(this.unPressedEgg);
		egg.get(egg.size()-2).setPressed();
		
		if(checkAnswer(this.unPressedEgg)){
			egg.get(egg.size()-2).setResult(true);
			this.resultFlag = true;
			this.increaseScore();
			this.bgIMGCount = 0;
//			bubble.setMovement(unPressedEgg,0);
		}
		else{
			egg.get(egg.size()-2).setResult(false);		
			this.resultFlag = false;
//			this.backgroundColor = 200;
			this.bgIMGCount = 1;

//			bubble.setMovement(unPressedEgg,1);
		}
		this.chooseEgg();
	}
	
	public void checkEggs(){
		for(int i: eggs.keySet()){
			boolean flag = false;
			ArrayList<Egg> egg = eggs.get(i);
			for(int j=0;j<egg.size()-1;j++){
				if(egg.get(j).getY()<200F){
					flag = true;
				}
			}
			if(!flag){
				egg.get(egg.size()-1).setMovement(Egg.STAY);			
			}
		}
	}
	
	public void chooseEgg(){
		Random random = new Random();
		int index = random.nextInt(3);
		this.unPressedEgg = index;
		this.eggBreak(index);
	}
	
	public void eggBreak(int x){
		ArrayList<Egg> egg = this.eggs.get(x);
		egg.get(egg.size()-1).setMovement(Egg.MOVE);
		egg.add(new Egg(this, this.eggImgs, x));
	}
	
	public boolean checkAnswer(int answer){
		if(170+answer*230 == this.hammer.getX()){
			return true;
		}
		return false;
	}
	
	public void increaseScore(){
		this.score+=10;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void overLoad(){
		if(this.score%100 ==0 && score!=0){
			this.mop.setMovement(Mop.MOP);
		}
	}
	
	public void mopCheck(){
		for(int i: eggs.keySet()){
			ArrayList<Egg> egg = eggs.get(i);
			for(int j=0;j<egg.size()-1;j++){
				Egg chicken = egg.get(j);
				if(chicken.getY()>650F && this.mop.getX()-chicken.getX()<30 && this.mop.getX()-chicken.getX()>0 && this.mop.getMovement()==1){
					chicken.setMovement(Egg.MOP);
				}
			}
		}	
	}
}
