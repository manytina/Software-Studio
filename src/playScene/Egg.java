package playScene;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Egg{
	
	public static final int MOVE = 0;
	public static final int BREAK = 1;
	public static final int HIDE = 2;
	public static final int STAY = 3;
	public static final int MOP = 4;
	
	private PApplet parent;
	private PImage[] images;
	private float x, y, w, h;
	private int movement, position, frame, imageCount;
	private boolean pressedFlag = false;
	private boolean resultFlag = false;
	
	public Egg(PApplet parent, PImage[] images, int position){
		this.parent = parent;
		this.images = images;
		this.position = position;
		this.imageCount = 0;
		this.frame = 0;
		this.movement = HIDE;
		this.x = 100 + position*(images[0].width+100);
		this.y = 100;
	}
	
	public void display(){
		switch(this.movement){
			case MOVE:
				if(this.imageCount==0	/*eggColor*/){
					this.frame = (frame+1)%50;
					if(frame==25){
						this.imageCount = 1;						
					}
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
				}
				else if(this.imageCount==1    /*eggPressed*/){
					this.frame = (frame+1)%50;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					if(this.pressedFlag){
						if(this.resultFlag){
							this.imageCount = 2;
//							this.y += 100;
							this.setMovement(BREAK);							
						}
						else{
							this.setMovement(HIDE);
							this.y = 250;
						}
					}
				}
				break;
			
			case BREAK:
				if(this.imageCount==2    /*eggShow*/){
					this.frame = (frame+1)%10;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					this.imageCount = imageCount + (int)frame/9;
				}
				else if(this.imageCount==3    /*eggBreak*/){
					this.frame = (frame+1)%10;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					this.imageCount = imageCount + (int)frame/9;
				}
				else if(this.imageCount==4    /*scared*/){
					this.frame = (frame+1)%10;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					this.imageCount = imageCount + (int)frame/9;
				}
				else if(this.imageCount==5    /*w o w*/){
					y += 10;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					if(y>650){
						y += 5;
						this.imageCount = 6;
					}
				}
				else if(this.imageCount==6    /*cry*/){
					this.frame = (frame+1)%100;
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
					if(this.frame==80){
						Random random = new Random();
						this.imageCount = 7+random.nextInt(2)*2;
					}
				}
				else if(this.imageCount==7    /*chickenStayRight*/){
					this.frame = (frame+1)%20;
					this.imageCount = 7+(int)frame/10;
					this.checkRight();
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
				}
				else if(this.imageCount==8    /*chickenRunRight*/){
					this.frame = (frame+1)%20;
					this.imageCount = 7+(int)frame/10;
					this.checkRight();
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
				}
				else if(this.imageCount==9    /*chickenStayLeft*/){
					this.frame = (frame+1)%20;
					this.imageCount = 9+(int)frame/10;
					this.checkLeft();
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);
				}
				else if(this.imageCount==10    /*chickenRunLeft*/){
					this.frame = (frame+1)%20;
					this.imageCount = 9+(int)frame/10;
					this.checkLeft();
					this.parent.image(images[imageCount], x, y, images[imageCount].width, images[imageCount].height);					
				}
				break;
				
			case HIDE:
				break;
				
			case STAY:
				this.parent.image(images[0], x, y, images[imageCount].width, images[imageCount].height);
				break;
				
			case MOP:
				this.x -= 5;
				this.parent.image(images[6], x, y, images[6].width, images[6].height);
				break;
				
			default:
				break;
		}
//		System.out.println(position+" "+this.y);
	}
	
	public void setMovement(int m){
		this.movement = m;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public float getW(){
		return this.w;
	}
	
	public float getH(){
		return this.h;
	}
	
	public void setPressed(){
		this.pressedFlag = true;
	}
	
	public void setResult(boolean resultFlag){
		this.resultFlag = resultFlag;
	}
	
	public void checkLeft(){
		if(this.x>0){
			this.x--;
		}
		else if(this.x==0){
			this.imageCount = 7;
		}
	}
	
	public void checkRight(){
		if(this.x<675){
			this.x++;
		}
		else if(this.x==675){
			this.imageCount = 9;
		}		
	}
}
