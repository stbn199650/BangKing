package BangKing;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Game extends JFrame implements ActionListener{
	public static final int[][] model_map=new int[][]{
		{1,0,0,0,1,1,1,1,1,1,0,0,0,0,1},
		{1,1,0,1,0,1,1,1,1,0,0,0,0,1,0},
		{0,1,1,1,1,1,1,0,1,0,1,1,1,1,2},
		{0,1,1,0,1,1,1,1,1,1,0,0,1,1,0},
		{0,1,3,1,0,0,0,1,0,0,1,1,1,0,1},
		{1,0,1,1,0,1,0,0,0,1,1,0,0,1,1},
		{1,0,1,1,0,1,1,0,6,1,1,0,0,1,1},
		{1,1,1,1,1,1,0,0,2,1,3,1,1,1,0},
		{0,1,0,3,1,1,0,0,0,1,1,0,0,1,0},
		{0,1,0,0,1,2,1,1,0,0,1,0,0,1,0},
		{1,0,1,1,1,1,1,0,1,0,1,1,1,1,0},
		{1,0,1,1,1,0,1,1,1,0,1,1,0,1,1},
		{1,0,0,1,0,0,0,1,0,0,1,0,0,3,1},
		{1,0,1,1,1,1,0,1,1,1,0,0,0,0,1},
		{1,0,0,0,1,0,0,0,0,1,0,0,5,1,0}
	};
	/*initial map*/
	public static Game welcome;
	public static int[][] map = new int[][]{// 1:box 2:toolNum 3:toolSpeed 4:pl_bomb 5:player1 6:player2 0:space 7:overlap 8:p2_bomb
		{1,0,0,0,1,1,1,1,1,1,0,0,0,0,1},
		{1,1,0,1,0,1,1,1,1,0,0,0,0,1,0},
		{0,1,1,1,1,1,1,0,1,0,1,1,1,1,2},
		{0,1,1,0,1,1,1,1,1,1,0,0,1,1,0},
		{0,1,3,1,0,0,0,1,0,0,1,1,1,0,1},
		{1,0,1,1,0,1,0,0,0,1,1,0,0,1,1},
		{1,0,1,1,0,1,1,0,6,1,1,0,0,1,1},
		{1,1,1,1,1,1,0,0,2,1,3,1,1,1,0},
		{0,1,0,3,1,1,0,0,0,1,1,0,0,1,0},
		{0,1,0,0,1,2,1,1,0,0,1,0,0,1,0},
		{1,0,1,1,1,1,1,0,1,0,1,1,1,1,0},
		{1,0,1,1,1,0,1,1,1,0,1,1,0,1,1},
		{1,0,0,1,0,0,0,1,0,0,1,0,0,3,1},
		{1,0,1,1,1,1,0,1,1,1,0,0,0,0,1},
		{1,0,0,0,1,0,0,0,0,1,0,0,5,1,0}};
	
	public static void main(String[] args)
	{
		/*menu start*/
		welcome=new Game();
		welcome.setVisible(true);		
	}
	
	public Game(){
		/*frame*/	
		setSize(440, 370);		
		setLayout(null);
		setLocation(450, 180);

		/*background*/
		JPanel imagePanel = null;
	    JLabel  bgLabel = null;
	    ImageIcon background = null;
		background = new ImageIcon(getClass().getResource("start.jpg"));       
	    bgLabel = new JLabel(background);     
	    bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());   
	    imagePanel = (JPanel) this.getContentPane();
	    imagePanel.setOpaque(false);
	    getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
	
	    /*button*/		
		JButton start=new JButton("Start!!");
		start.setLocation(300,70);
		start.setSize(start.getPreferredSize());
		start.addActionListener(this);
		add(start);
		JButton exit=new JButton("Exit");				
		exit.setLocation(300,150);
		exit.setSize(start.getPreferredSize());
		exit.addActionListener(this);
		add(exit);
	}
	public void actionPerformed(ActionEvent e)
	{
		String command =e.getActionCommand();
		
		if(command.equals("Start!!"))
		{
			ChoosePlayer chooseWindow = new ChoosePlayer();
			chooseWindow.setVisible(true);
			//play background music
		    File BGM = new File("playmusic.WAV");
			PlaySound(BGM);
			welcome.dispose();
		}
		else if(command.equals("Exit"))
		{
			System.exit(0);
		}
	}
	
	//play background music
	static void PlaySound(File Sound){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		}
			catch(Exception e){}
	}

}
