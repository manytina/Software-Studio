package bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class TopBar extends JPanel implements TopBarDelegate {
	
	    private JLabel userPicLabel;
	    private JLabel userNameLabel;
	    private JLabel stageNumLabel;
	    private JLabel scoreLabel;
	    public TimeBar timeBar;
	   
		
		public TopBar(Rectangle bounds){
			
			/*add components and visualize top bar*/
			
			this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			this.setPreferredSize(new Dimension(150,1000));
			this.setBounds(bounds);
			this.setBackground(new Color(130, 192, 192));
			this.initComponents();
			this.setVisible(true);
			this.requestFocus();
			
		}
		
		/**
		 * A method for initializing what top bar contains.
		 */
		
		public void initComponents() {
			this.userNameLabel = new JLabel();
			this.userNameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			this.userNameLabel.setForeground(new Color(58, 0, 112));
			
			this.userPicLabel = new JLabel();
			
			this.stageNumLabel = new JLabel();
			this.stageNumLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			this.stageNumLabel.setForeground(new Color(58, 0, 112));
			
			this.scoreLabel = new JLabel();
			this.scoreLabel.setFont(new Font("Verdana", Font.BOLD, 20));
			this.scoreLabel.setForeground(new Color(58, 0, 112));
			
			this.timeBar = new TimeBar();
			this.timeBar.setPreferredSize(new Dimension(50,500));
			
			this.add(userNameLabel);
			this.add(userPicLabel);
			this.add(stageNumLabel);
			this.add(scoreLabel);
			this.add(timeBar);
			
		}
		
		
		/**
		 * A method for setting the user's name to show in the top bar.
		 */

		@Override
		public void setUserName(String a) {
			this.userNameLabel.setText(a+"\n");
		}
		
		/**
		 * A method for setting the current stage count to show in the top bar.
		 */

		@Override
		public void setStageNum(int stageNum) {
			this.stageNumLabel.setText("Stage "+stageNum+"\n");
		}

		/**
		 * A method for setting the current stage count to show in the top bar.
		 */
		
		@Override
		public void setScore(int currentScore) {
			this.scoreLabel.setText("Score:"+currentScore+"\n");
			
		}
		
		/**
		 * A method for setting the user picture to show in the top bar.
		 */

		@Override
		public void setUserPicLabel(ImageIcon userImage) {
			userImage.setImage(userImage.getImage().getScaledInstance(90,126,Image.SCALE_DEFAULT));
			this.userPicLabel.setIcon(userImage);
			
		}

}

