package Rank;

import music.MusicPlayer;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BackMusic extends JFrame {
	public JButton btnPlay = new JButton("Start");
	public JButton btnStop = new JButton("Stop");
	public JButton btnResume = new JButton("Resume");
	public JButton btnPause = new JButton("Pause");
	public ButtonControl btHandler = new ButtonControl();
	
	public BackMusic(){
		btnPlay.addActionListener(btHandler);
		btnStop.addActionListener(btHandler);
		btnResume.addActionListener(btHandler);
		btnPause.addActionListener(btHandler);
	}
	
	private class ButtonControl implements ActionListener{
		MusicPlayer audio = null;

		public void actionPerformed(ActionEvent e){
			//start
			if(e.getSource() == btnPlay){
				btnPlay.setEnabled(false);
				audio = new MusicPlayer();
				String filePath = "D:/anny.mp3";
				audio.loadAudio(filePath);
				audio.play();
				return;
			}
			if(e.getSource() == btnStop){
				audio.stop();
			}
			if(e.getSource() == btnResume){
				audio.resume();
			}
			if(e.getSource() == btnPause){
				audio.pause();
			}
		}
	}
}

