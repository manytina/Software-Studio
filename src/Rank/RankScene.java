package Rank;

import game.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;




//import Rank.BackMusic.ButtonControl;
import music.*;

public class RankScene extends JPanel implements ActionListener{
	private BufferedImage image;
    private JButton[] btn;
    private JButton[] musicBtn;
    private BackMusic musicbtn = new BackMusic();
    private Label[] rank;
    private Font font;
    private String line;
    private String sleepStyle = null;
    private String studyStyle = null;
    private String partyStyle = null;
    private String sportStyle = null;
    private String happyStyle = null;
    private String sadStyle = null;
    private String playList = new String();
    private String[][] style = new String[1000][2];
    private String path = new String();
    private Rank r = new Rank();
    public JButton btnPlay = new JButton("Start");
	public JButton btnStop = new JButton("Stop");
	public JButton btnResume = new JButton("Resume");
	public JButton btnPause = new JButton("Pause");
	public ButtonControl btHandler = new ButtonControl();
	private Label userScore;

	 public RankScene(int[] data ,int yourScore){
       try {                
          image = ImageIO.read(new File("res/picture/background/black&blueStripe.jpeg"));
       } catch (IOException ex) {
            System.out.println("error");
       }
       try {
			this.readFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       btn = new JButton[6];
       btn[0] = new JButton("Sleep");
       btn[1] = new JButton("Party");
       btn[2] = new JButton("Sport");
       btn[3] = new JButton("Happy");
       btn[4] = new JButton("Sad");
       btn[5] = new JButton("Study");
       //musicBtn = new JButton[4];
       btnPlay = new JButton("Play");
       btnStop = new JButton("Stop");
       btnResume = new JButton("Resume");
       btnPause = new JButton("Pause");
       
       
       rank = new Label[11];
       
       this.setLayout(new FlowLayout(FlowLayout.CENTER,60,50));
       this.add(btn[0]);
       this.add(btn[1]);
       this.add(btn[2]);
       this.add(btn[3]);
       this.add(btn[4]);
       this.add(btn[5]);
       
       this.add(btnPlay);
       this.add(btnStop);
       this.add(btnResume);
       this.add(btnPause);
       
       btn[0].addActionListener(this);
       btn[1].addActionListener(this);
       btn[2].addActionListener(this);
       btn[3].addActionListener(this);
       btn[4].addActionListener(this);
       btn[5].addActionListener(this);
       
       btnPlay.addActionListener(btHandler);
		btnStop.addActionListener(btHandler);
		btnResume.addActionListener(btHandler);
		btnPause.addActionListener(btHandler);
      
       
       
       btn[0].setPreferredSize(new Dimension(100,70));
       btn[1].setPreferredSize(new Dimension(100,70));
       btn[2].setPreferredSize(new Dimension(100,70));
       btn[3].setPreferredSize(new Dimension(100,70));
       btn[4].setPreferredSize(new Dimension(100,70));
       btn[5].setPreferredSize(new Dimension(100,70));
       
       btnPlay.setPreferredSize(new Dimension(100,50));
       btnStop.setPreferredSize(new Dimension(100,50));
       btnResume.setPreferredSize(new Dimension(100,50));
       btnPause.setPreferredSize(new Dimension(100,50));
       
       
       
       btn[0].setForeground(new Color(72,72,147));
       btn[0].setBackground(new Color(197,226,226));
       btn[1].setForeground(new Color(72,72,147));
       btn[1].setBackground(new Color(197,226,226));
       btn[2].setForeground(new Color(72,72,147));
       btn[2].setBackground(new Color(197,226,226));
       btn[3].setForeground(new Color(72,72,147));
       btn[3].setBackground(new Color(197,226,226));
       btn[4].setForeground(new Color(72,72,147));
       btn[4].setBackground(new Color(197,226,226));
       btn[5].setForeground(new Color(72,72,147));
       btn[5].setBackground(new Color(197,226,226));
       
       btnPlay.setForeground(new Color(0,0,0));
       btnPlay.setBackground(new Color(183,183,220));
       btnStop.setForeground(new Color(0,0,0));
       btnStop.setBackground(new Color(183,183,220));
       btnResume.setForeground(new Color(0,0,0));
       btnResume.setBackground(new Color(183,183,220));
       btnPause.setForeground(new Color(0,0,0));
       btnPause.setBackground(new Color(183,183,220));
      
       
       font = new Font(Font.DIALOG, Font.BOLD, 20);
       for(int i=0;i<10;i++)
       {
    	   rank[i] = new Label(""+(i+1)+"."+data[i]+"                                             ");
    	   rank[i].setFont(font);
    	   this.add(rank[i]);
    	   rank[i].setBackground(Color.WHITE);
       }
       //this.pack();
       userScore = new Label("Why don't You Remember Your Score !!! Your Score is "+yourScore);
       userScore.setFont(font);
       this.add(userScore);
       userScore.setBackground(Color.WHITE);
       
       this.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters   
    }
    

	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		
		
		if(e.getSource()==btn[0])
		{
			playList = sleepStyle;
			//btn[0].setEnabled(false);
		}
		else if(e.getSource()==btn[1])
		{
			playList = partyStyle;

		}
		else if(e.getSource()==btn[2])
		{
			playList = sportStyle;
		}
		else if(e.getSource()==btn[3])
		{
			playList = happyStyle;
		}
		else if(e.getSource()==btn[4])
		{
			playList = sadStyle;
		}
		else if(e.getSource()==btn[5])
		{
			playList = studyStyle;
		}			
	}
	
	public String getPlayList()
	{
		Model model = new Model();
		path = model.getSongPath(playList);
		System.out.println(path);
		return path;
	}
	
	public void readFile() throws IOException
	{
		FileReader fr = new FileReader("res/data/style.txt");
		BufferedReader in = new BufferedReader(fr);
			
		//int sleepCount=0,partyCount=0,studyCount=0,sportCount=0,happyCount=0,sadCount=0;
		int i = 0;
	    while ((line = in.readLine()) != null) {
	          //place ints in array and increament the count of ints
	    	style[i] = line.split("#");
	    	if(style[i][0].equals("Sleep")&& sleepStyle==null)
	    	{
	    		sleepStyle = style[i][1];
	    		System.out.println(sleepStyle);
	    	}
	    	else if(style[i][0].equals("Party")&& partyStyle==null)
	    	{
	    		partyStyle = style[i][1];
	    		System.out.println(partyStyle);
	    		
	    	}
	    	else if(style[i][0].equals("Study") && studyStyle==null)
	    	{
	    		studyStyle = style[i][1];
	    		System.out.println(studyStyle );
	    	}
	    	else if(style[i][0].equals("Sports") && sportStyle==null)
	    	{
	    		sportStyle = style[i][1];
	    		System.out.println(sportStyle);
	    	}
	    	else if(style[i][0].equals("Happy") && happyStyle==null)
	    	{
	    		happyStyle = style[i][1];
	    		System.out.println(happyStyle);
	    	}
	    	else if(style[i][0].equals("Sad") && sadStyle==null)
	    	{
	    		sadStyle = style[i][1];
	    		System.out.println(sadStyle );
	    	}
	    i++;
	    }
	    fr.close();
	}

private class ButtonControl implements ActionListener{
	MusicPlayer audio = null;
	public ButtonControl() {
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e){
		//start
		if(e.getSource() == btnPlay){
			getPlayList();
			btnPlay.setEnabled(false);
			audio = new MusicPlayer();
			//Object callbackObj = "end";//end music return object
			//audio.setCallbackTartet(this, callbackObj);
			//String filePath = path;
			System.out.println(path+"~~");
			audio.loadAudio(path);
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
	public void audioPlayEnd(Object callbackObj) {
		//System.out.println("message for music end"  + callbackObj);
		btnPlay.setEnabled(true);
	}
}



}
