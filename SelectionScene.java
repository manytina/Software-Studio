package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.DataBase;

public class SelectionScene extends JPanel implements ActionListener {
	
	private JButton selectPartyButton;
	private JButton selectStudyButton;
	private JButton selectSleepButton;
	private JButton selectFamilyButton;
	private JButton selectEatingButton;
	private JButton selectSportsButton;
	private JButton doneSelectButton;
	private String currentSong;
	private DataBase currentDatabase;
	private boolean isDoneSelection = false;
	
	public SelectionScene(String currentSongTitle){
		
		this.currentSong = currentSongTitle;
		
		selectPartyButton = new JButton("Party\nPlayList");
		selectStudyButton = new JButton("Study\nPlayList");
		selectSleepButton = new JButton("Sleep\nPlayList");
		selectFamilyButton = new JButton("Family\nPlayList");
		selectEatingButton = new JButton("Eating\nPlayList");
		selectSportsButton = new JButton("Sports\nPlayList");
		doneSelectButton = new JButton("Done!");
		
		selectPartyButton.addActionListener(this);
		selectStudyButton.addActionListener(this);
		selectSleepButton.addActionListener(this);
		selectFamilyButton.addActionListener(this);
		selectEatingButton.addActionListener(this);
		selectSportsButton.addActionListener(this);
		doneSelectButton.addActionListener(this);
		
		
		
		selectPartyButton.setPreferredSize(new Dimension(120,80));
		selectStudyButton.setPreferredSize(new Dimension(120,80));
		selectSleepButton.setPreferredSize(new Dimension(120,80));
		selectFamilyButton.setPreferredSize(new Dimension(120,80));
		selectEatingButton.setPreferredSize(new Dimension(120,80));
		selectSportsButton.setPreferredSize(new Dimension(120,80));
		doneSelectButton.setPreferredSize(new Dimension(120,80));
		
		selectPartyButton.setForeground(new Color(58, 0, 112));
		selectPartyButton.setBackground(new Color(130, 192, 192));
		
		
		selectStudyButton.setForeground(new Color(58, 0, 112));
		selectStudyButton.setBackground(new Color(130, 192, 192));

		selectSleepButton.setForeground(new Color(58, 0, 112));
		selectSleepButton.setBackground(new Color(130, 192, 192));

		selectFamilyButton.setForeground(new Color(58, 0, 112));
		selectFamilyButton.setBackground(new Color(130, 192, 192));

		selectEatingButton.setForeground(new Color(58, 0, 112));
		selectEatingButton.setBackground(new Color(130, 192, 192));

		selectSportsButton.setForeground(new Color(58, 0, 112));
		selectSportsButton.setBackground(new Color(130, 192, 192));

		doneSelectButton.setForeground(new Color(58, 0, 112));
		doneSelectButton.setBackground(new Color(130, 192, 192));



		this.add(selectPartyButton);
		this.add(selectStudyButton);
		this.add(selectSleepButton);
		this.add(selectFamilyButton);
		this.add(selectEatingButton);
		this.add(selectSportsButton);
		this.add(doneSelectButton);
		


	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == selectPartyButton){
			currentDatabase.addVote(this.currentSong, "Party");
			currentDatabase.calculateScoreForVoting(currentSong,"Party");
		}	
		else if(event.getSource() == selectStudyButton){
			currentDatabase.addVote(this.currentSong, "Study");
			currentDatabase.calculateScoreForVoting(currentSong,"Study");
		}
        else if(event.getSource() == selectSleepButton){
			currentDatabase.addVote(this.currentSong, "Sleep");
			currentDatabase.calculateScoreForVoting(currentSong,"Sleep");
		}
        else if(event.getSource() == selectFamilyButton){
			currentDatabase.addVote(this.currentSong, "Family");
			currentDatabase.calculateScoreForVoting(currentSong,"Family");
        }
        else if(event.getSource() == selectEatingButton){
			currentDatabase.addVote(this.currentSong, "Eating");
			currentDatabase.calculateScoreForVoting(currentSong,"Eating");
        }
        else if(event.getSource() == selectSportsButton){
			currentDatabase.addVote(this.currentSong, "Sports");
			currentDatabase.calculateScoreForVoting(currentSong,"Sports");
        }
        else if(event.getSource() == doneSelectButton){
			this.isDoneSelection = true;
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
	
}
