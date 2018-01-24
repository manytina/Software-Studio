package music;

import java.util.Random;

public class User {
	int flag;
	public User(int i){
		this.flag = i;
		playerName(flag);
	}
	public String playerName(int flag){
		String[] boy,girl;
		boy = new String[5];
		girl = new String[5];
		String selectName = new String();
		Random rand = new Random();
		int randNum;
		boy[0]="Steve";
		boy[1]="James";
		boy[2]="Timmy";
		boy[3]="Frank";
		boy[4]="Duncan";
		girl[0]="Janet";
		girl[1]="Tina";
		girl[2]="Christine";
		girl[3]="Lena";
		girl[4]="Hebe";
		if(flag==1){
			randNum = rand.nextInt(5);
			selectName = boy[randNum];
		}
		if(flag==2){
			randNum = rand.nextInt(5);
			selectName = girl[randNum];
		}
		return selectName;
	}
}
