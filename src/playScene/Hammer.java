package playScene;

import processing.core.PApplet;
import processing.core.PImage;

public class Hammer {
	
	public static final int LEFT = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	
	private PApplet parent;
	private PImage[] images;
	private float x, y, w, h;
	private int  movement, imageCount, frame;
	private boolean moveFlag = false;
	
	public Hammer(PApplet parent, PImage[] images){
		this.parent = parent;
		this.images = images;
		this.x = 700;
		this.y = 0;
		this.movement = 3;	
	}
	
	public void setMovement(int m){
		this.movement = m;
		this.reset();
	}
	
	public void setAnimation(boolean moveFlag){
		this.moveFlag = moveFlag;
	}
	
	public void display(){
		switch(this.movement){
			case LEFT:
				this.x = 170;
				break;
			case DOWN:
				this.x = 400;
				break;
			case RIGHT:
				this.x = 630;
				break;
			default:
				break;
		}
		if(moveFlag){
			switch(this.imageCount){
				case 0:
					this.frame = (frame+1)%30;
					if(frame==10){
						this.imageCount = 1;
					}
					break;
				case 1:
					this.frame = (frame+1)%30;
					if(frame==15){
						this.imageCount = 2;
					}
					break;
				case 2:
					this.frame = (frame+1)%30;
					if(frame==25){
						this.imageCount = 0;
						this.setAnimation(false);
					}
					break;
				default:
					break;
			}			
		}
		this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
	}
	
	public void reset(){
		this.imageCount = 0;
		this.y = 0;
	}
	
	public float getX(){
		return this.x;
	}
	
}
