package bar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;


public class TimeBar extends JPanel{
    
	public JProgressBar progressBar;
	
	public TimeBar(){
		
		this.setBackground(new Color(58, 0, 112));
		
		progressBar = new JProgressBar();
		
		progressBar.setOrientation(JProgressBar.VERTICAL);
		progressBar.setPreferredSize(new Dimension(50,500));
		progressBar.setForeground(new Color(189, 0, 94));
		progressBar.setBackground(new Color(58, 0, 112));
		
		this.add(progressBar);

	}
	


}

