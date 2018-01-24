package playScene;

import processing.core.PApplet;
import processing.core.PImage;

public class Bubble {
	
	private PApplet parent;
	private PImage[] images;
	private float x, y, w, h;
	private int  position, imageCount, frame, result;
	
	public Bubble(PApplet parent, PImage[] images){
		this.parent = parent;
		this.images = images;
		this.x = 700;
		this.y = 80;	
	}
	
	public void display(){
		if(frame!=0){
			this.frame = (frame+1)%10;
			parent.image(this.images[this.result], 80+position*(images[0].width+100), 80);
		}
	}
	
	public void setMovement(int index, int result){
		this.position = index;
		this.result = result;
		this.frame++;
	}
}
