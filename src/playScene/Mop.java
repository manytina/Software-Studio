package playScene;

import processing.core.PApplet;
import processing.core.PImage;

public class Mop {
	
	public static final int HIDE = 0;
	public static final int MOP = 1;	
	
	private PApplet parent;
	private PImage[] images;
	private float x, y, w, h;
	private int  position, imageCount, frame, result, movement;
	private boolean moveFlag = false;
	
	public Mop(PApplet parent, PImage[] images){
		this.parent = parent;
		this.images = images;
		this.imageCount = 0;
		this.x = 700;
		this.y = 500;	
	}
	
	public void display(){
		switch(this.movement){
			case HIDE:
				break;
				
			case MOP:
				this.moveFlag = true;
				break;
			
			default:
				break;
		}
		if(this.moveFlag){
			this.frame = (frame+1)%20;
			this.x -= 5;
			this.parent.image(images[imageCount+frame/10], x, y);
		}
		if(this.x<0){
			this.moveFlag = false;
			this.setMovement(HIDE);
			this.x = 700F;
		}
	}
	
	public void setMovement(int m){
		this.movement = m;
	}
	
	public float getX(){
		return this.x;
	}
	
	public int getMovement(){
		return this.movement;
	}
	
}
