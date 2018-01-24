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

public class RankSceneFiveSong extends JPanel implements ActionListener{

    private BufferedImage image;
    private JButton[] btn;
    private JButton[] musicBtn;
//    private BackMusic musicbtn = new BackMusic();
    private int num=0;
    private Label[] rank;
    private Label userScore;
    private Font font;
    private String line;
    private String[] sleepStyle = new String[5];
    private String[] studyStyle = new String[5];
    private String[] partyStyle = new String[5];
    private String[] sportStyle = new String[5];
    private String[] happyStyle = new String[5];
    private String[] sadStyle = new String[5];
    private String[] playList = new String[5];
    private String[][] style = new String[1000][2];
    private String[] filePath = new String[5];
    //private String path = new String();
    private Rank r = new Rank();
    
	public JButton btnPlay = new JButton("Start");
	public JButton btnStop = new JButton("Stop");
	public JButton btnResume = new JButton("Resume");
	public JButton btnPause = new JButton("Pause");
	public JButton btnNext = new JButton("Next");
	public ButtonControl btHandler = new ButtonControl();

    public RankSceneFiveSong(int[] data ,int yourScore) {
       try {                
          image = ImageIO.read(new File("res/picture/background/black&blueStripe.jpeg"));
       } catch (IOException ex) {
            System.out.println("error");
       }
       System.out.println("?????");
       btn = new JButton[6];
       btn[0] = new JButton("Sleep");
       btn[1] = new JButton("Party");
       btn[2] = new JButton("Sport");
       btn[3] = new JButton("Happy");
       btn[4] = new JButton("Sad");
       btn[5] = new JButton("Study");
       //musicBtn = new JButton[4];
       //musicBtn[0] = musicbtn.btnPlay;
       //musicBtn[1] = musicbtn.btnStop;
       //musicBtn[2] = musicbtn.btnResume;
       //musicBtn[3] = musicbtn.btnPause;
       
       
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
       this.add(btnNext);
       
       
       
       
       btn[0].addActionListener(this);
       btn[1].addActionListener(this);
       btn[2].addActionListener(this);
       btn[3].addActionListener(this);
       btn[4].addActionListener(this);
       btn[5].addActionListener(this);
       
       
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
       btnNext.setPreferredSize(new Dimension(100,50));
       
       
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
       btnNext.setForeground(new Color(0,0,0));
       btnNext.setBackground(new Color(183,183,220));
      
		btnPlay.addActionListener(btHandler);
		btnStop.addActionListener(btHandler);
		btnResume.addActionListener(btHandler);
		btnPause.addActionListener(btHandler);
		btnNext.addActionListener(btHandler);
		
 //      r.readFile();
       font = new Font(Font.DIALOG, Font.BOLD, 20);
       for(int i=0;i<10;i++)
       {
    	   rank[i] = new Label(""+(i+1)+"."+data[i]+"                                ");
    	   System.out.println(""+(i+1)+"."+data[i]);
    	   rank[i].setFont(font);
    	   this.add(rank[i]);
    	   rank[i].setBackground(Color.WHITE);
    	   
       }
       System.out.println("RankScene:"+yourScore);
       userScore = new Label("Why don't You Remember Your Score !!! Your Score is "+yourScore);
       userScore.setFont(font);
       this.add(userScore);
       userScore.setBackground(Color.WHITE);
       //this.pack();
       
       this.setVisible(true);
       
       try{
           this.readFile();
       }
       catch(IOException e){
    	   e.printStackTrace();
       }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters   
    }
    

	private class ButtonControl implements ActionListener{

		public void actionPerformed(ActionEvent e){
			MusicPlayer a = null;
			MusicPlayer s = null;
			MusicPlayer d = null;
			MusicPlayer z = null;
			MusicPlayer x = null;
			//player for five songs
			a = new MusicPlayer();
			s = new MusicPlayer();
			d = new MusicPlayer();
			z = new MusicPlayer();
			x = new MusicPlayer();
			//load each songs
			getPlayList();
			a.loadAudio(filePath[0]);
			s.loadAudio(filePath[1]);
			d.loadAudio(filePath[2]);
			z.loadAudio(filePath[3]);
			x.loadAudio(filePath[4]);
			System.out.println("ddsssss"+filePath[0]);
			/*if(a.forNext()==1 || e.getSource()==btnNext){
				num++;
				if(num==4){
					num=0;
				}
			}
			if(s.forNext()==1 || e.getSource()==btnNext){
				num++;
				if(num==4){
					num=0;
				}
			}
			if(d.forNext()==1 || e.getSource()==btnNext){
				num++;
				if(num==4){
					num=0;
				}
			}
			if(z.forNext()==1 || e.getSource()==btnNext){
				num++;
				if(num==4){
					num=0;
				}
			}
			if(x.forNext()==1 || e.getSource()==btnNext){
				num++;
				if(num==4){
					num=0;
				}
			}*/
			if(num==0){
				if(e.getSource() == btnPlay){
					btnPlay.setEnabled(false);
					a.play();
					return;
				}
				if(e.getSource() == btnStop){
					a.stop();
				}
				if(e.getSource() == btnResume){
					a.resume();
				}
				if(e.getSource() == btnPause){
					a.pause();
				}
			}
			if(num==1){
				if(e.getSource() == btnPlay){
					btnPlay.setEnabled(false);
					s.play();
					return;
				}
				if(e.getSource() == btnStop){
					s.stop();
				}
				if(e.getSource() == btnResume){
					s.resume();
				}
				if(e.getSource() == btnPause){
					s.pause();
				}
			}
			if(num==2){
				if(e.getSource() == btnPlay){
					btnPlay.setEnabled(false);
					d.play();
					return;
				}
				if(e.getSource() == btnStop){
					d.stop();
				}
				if(e.getSource() == btnResume){
					d.resume();
				}
				if(e.getSource() == btnPause){
					d.pause();
				}
			}
			if(num==3){
				if(e.getSource() == btnPlay){
					btnPlay.setEnabled(false);
					z.play();
					return;
				}
				if(e.getSource() == btnStop){
					z.stop();
				}
				if(e.getSource() == btnResume){
					z.resume();
				}
				if(e.getSource() == btnPause){
					z.pause();
				}
			}
			if(num==4){
				if(e.getSource() == btnPlay){
					btnPlay.setEnabled(false);
					x.play();
					return;
				}
				if(e.getSource() == btnStop){
					x.stop();
				}
				if(e.getSource() == btnResume){
					x.resume();
				}
				if(e.getSource() == btnPause){
					x.pause();
				}
			}
		}
		public void audioPlayEnd(Object callbackObj) {
			//System.out.println("message for music end"  + callbackObj);
			btnPlay.setEnabled(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
/*		try {
//			GetPath getPath = new GetPath();
			
//			getPath.readFile();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		if(e.getSource()==btn[0])
		{
			playList = sleepStyle;
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
	
	public void getPlayList()
	{
		Model model = new Model();
		filePath[0] = model.getSongPath(playList[0]);
		filePath[1] = model.getSongPath(playList[1]);
		filePath[2] = model.getSongPath(playList[2]);
		filePath[3] = model.getSongPath(playList[3]);
		filePath[4] = model.getSongPath(playList[4]);
		System.out.println("dd");
	}
	public void readFile() throws IOException
	{
		FileReader fr = new FileReader("res/data/style.txt");
		BufferedReader in = new BufferedReader(fr);
			
		int sleepCount=0,partyCount=0,studyCount=0,sportCount=0,happyCount=0,sadCount=0;
		int i = 0;
	    while ((line = in.readLine()) != null) {
	          //place ints in array and increament the count of ints
	    	style[i] = line.split("#");
	    	if(style[i][0]=="Sleep")
	    	{
	    		sleepStyle[sleepCount] = style[i][1];
	    		System.out.println(sleepStyle[sleepCount]);
	    		sleepCount++;
	    	}
	    	else if(style[i][0]=="Party")
	    	{
	    		partyStyle[partyCount] = style[i][1];
	    		System.out.println(partyStyle[partyCount]);
	    		partyCount++;
	    		
	    	}
	    	else if(style[i][0]=="Study")
	    	{
	    		studyStyle[studyCount] = style[i][1];
	    		System.out.println(studyStyle[studyCount]);
	    		studyCount++;
	    	}
	    	else if(style[i][0]=="Sport")
	    	{
	    		sportStyle[sportCount] = style[i][1];
	    		System.out.println(sportStyle[sportCount]);
	    		sportCount++;
	    	}
	    	else if(style[i][0]=="Happy")
	    	{
	    		happyStyle[happyCount] = style[i][1];
	    		System.out.println(happyStyle[happyCount]);
	    		happyCount++;
	    	}
	    	else if(style[i][0]=="Sad")
	    	{
	    		sadStyle[sadCount] = style[i][1];
	    		System.out.println(sadStyle[sadCount]);
	    		sadCount++;
	    	}
	    i++;
	    }
	    for(int j=0;j<partyCount;j++){
	    	System.out.println(partyStyle[j]);
	    }
	    fr.close();
	}

}

class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}