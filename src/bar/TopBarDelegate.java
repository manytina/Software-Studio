package bar;
import java.awt.Image;

import javax.swing.ImageIcon;


public interface TopBarDelegate {
	
	public void setUserName(String a);
	public void setUserPicLabel(ImageIcon userImage);
	public void setStageNum(int stageNum);
	public void setScore(int currentScore);
}

