package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.DataBase;

public class SelectionScene extends JPanel implements ActionListener {
	
	private JButton selectPartyButton;
	private JButton selectStudyButton;
	private JButton selectSleepButton;
	private JButton selectSadButton;
	private JButton selectHappyButton;
	private JButton selectSportsButton;
	private JButton doneSelectButton;
	private String currentSong;
	private DataBase currentDatabase;
	private boolean isDoneSelection = false;
	private int score = 0;
	private PlayList playList;
	
	public SelectionScene(String currentSongTitle){
		
		currentDatabase = new DataBase();
		playList = new PlayList();
		
		this.currentSong = currentSongTitle;
		
		selectPartyButton = new JButton("<html>Party<br>PlayList</html>");
		selectStudyButton = new JButton("<html>Study<br>PlayList</html>");
		selectSleepButton = new JButton("<html>Sleep<br>PlayList</html>");
		selectSadButton = new JButton("<html>Sad<br>PlayList</html>");
		selectHappyButton = new JButton("<html>Happy<br>PlayList</html>");
		selectSportsButton = new JButton("<html>Sports<br>PlayList</html>");
		doneSelectButton = new JButton("Done!");
		
		selectPartyButton.addActionListener(this);
		selectStudyButton.addActionListener(this);
		selectSleepButton.addActionListener(this);
		selectSadButton.addActionListener(this);
		selectHappyButton.addActionListener(this);
		selectSportsButton.addActionListener(this);
		doneSelectButton.addActionListener(this);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,100,150));

		selectPartyButton.setPreferredSize(new Dimension(120,70));
	//	selectPartyButton.setLocation(100, 450);
		
		
		selectStudyButton.setPreferredSize(new Dimension(120,70));
	//	selectStudyButton.setLocation(100, 450);

		selectSleepButton.setPreferredSize(new Dimension(120,70));
	//	selectSleepButton.setLocation(150, 450);

		selectSadButton.setPreferredSize(new Dimension(120,70));
	//	selectFamilyButton.setLocation(200, 450);

		selectHappyButton.setPreferredSize(new Dimension(120,70));
	//	selectEatingButton.setLocation(250, 450);

		selectSportsButton.setPreferredSize(new Dimension(120,70));
	//	selectSportsButton.setLocation(300, 450);

		doneSelectButton.setPreferredSize(new Dimension(100,70));
	//	doneSelectButton.setLocation(350, 450);

		
		selectPartyButton.setForeground(new Color(58, 0, 112));
		selectPartyButton.setBackground(new Color(130, 192, 192));
		
		
		selectStudyButton.setForeground(new Color(58, 0, 112));
		selectStudyButton.setBackground(new Color(130, 192, 192));

		selectSleepButton.setForeground(new Color(58, 0, 112));
		selectSleepButton.setBackground(new Color(130, 192, 192));

		selectSadButton.setForeground(new Color(58, 0, 112));
		selectSadButton.setBackground(new Color(130, 192, 192));

		selectHappyButton.setForeground(new Color(58, 0, 112));
		selectHappyButton.setBackground(new Color(130, 192, 192));

		selectSportsButton.setForeground(new Color(58, 0, 112));
		selectSportsButton.setBackground(new Color(130, 192, 192));

		doneSelectButton.setForeground(new Color(255, 255, 255));
		doneSelectButton.setBackground(new Color(58, 0, 112));
		

		this.add(selectPartyButton);
		this.add(selectStudyButton);
		this.add(selectSleepButton);
		this.add(selectSadButton);
		this.add(selectHappyButton);
		this.add(selectSportsButton);
		this.add(doneSelectButton);
		
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		this.currentDatabase.readFile();
//		this.playList.readFile();
//		this.playList.show();
		
		if(event.getSource() == selectPartyButton){
			currentDatabase.addVote(this.currentSong, "Party");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Party");
			currentDatabase.writeFile();
			selectPartyButton.setEnabled(false);
		}	
		else if(event.getSource() == selectStudyButton){
			currentDatabase.addVote(this.currentSong, "Study");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Study");
			currentDatabase.writeFile();
			selectStudyButton.setEnabled(false);
		}
        else if(event.getSource() == selectSleepButton){
			currentDatabase.addVote(this.currentSong, "Sleep");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Sleep");
			currentDatabase.writeFile();
			selectSleepButton.setEnabled(false);
		}
        else if(event.getSource() == selectSadButton){
			currentDatabase.addVote(this.currentSong, "Sad");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Sad");
			currentDatabase.writeFile();
			selectSadButton.setEnabled(false);
        }
        else if(event.getSource() == selectHappyButton){
			currentDatabase.addVote(this.currentSong, "Happy");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Happy");
			currentDatabase.writeFile();
			selectHappyButton.setEnabled(false);
        }
        else if(event.getSource() == selectSportsButton){
			currentDatabase.addVote(this.currentSong, "Sports");
			score = currentDatabase.calculateScoreForVoting(currentSong,"Sports");
			currentDatabase.writeFile();
			selectSportsButton.setEnabled(false);
        }
        else if(event.getSource() == doneSelectButton){
			this.isDoneSelection = true;
			doneSelectButton.setEnabled(false);
//			playList.writeFile();
        }
	}
	
	public void setCurrentSong(String songName){
		this.currentSong = songName;
	}
	
	public void setCurrentDatabase(DataBase database){
		this.currentDatabase = database;
	}
	
	public boolean getIsDoneSelection(){
		return this.isDoneSelection;
	}
	
	public int getScore(){
		return this.score;
	}
}
