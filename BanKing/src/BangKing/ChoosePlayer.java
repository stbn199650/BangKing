package BangKing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.imageio.*;

public class ChoosePlayer extends JFrame implements ActionListener{
	
	public static char character1, character2;
	public Image purple,blue,red,sponge,star,bug;
    public JButton purpleplayer,blueplayer,redplayer,spongeplayer,starplayer,bugplayer;
    public JButton select1,select2,play;
    public JLabel showLabel1, showLabel2, warning;
    public int selectFinish1,selectFinish2 = 0;
    
    PlayFrame playWindow;
	
	public ChoosePlayer(){
		
		setSize(700, 450);
		setLayout(null);
		setLocation(350, 150);
	
		JPanel imagePanel = null;
	    JLabel  bgLabel = null;
	    ImageIcon background = null;
		
		background = new ImageIcon(getClass().getResource("background2.jpg"));       
	    bgLabel = new JLabel(background);     
	    bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());   
	       
	    imagePanel = (JPanel) this.getContentPane();
	    imagePanel.setOpaque(false);
	    getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
	    
	    //show all character
	    purpleplayer = new JButton("A");
	    blueplayer = new JButton("B");
	    redplayer = new JButton("C");
	    spongeplayer = new JButton("D");
	    starplayer = new JButton("E");
	    bugplayer = new JButton("F");
	    showLabel1 = new JLabel();
	    showLabel2 = new JLabel();
	    
	    purpleplayer.addActionListener(this);
	    blueplayer.addActionListener(this);
	    redplayer.addActionListener(this);
	    spongeplayer.addActionListener(this);
	    starplayer.addActionListener(this);
	    bugplayer.addActionListener(this);
	    
	    try{
	    	purple = ImageIO.read(getClass().getResource("purplePlayer.png"));
	    	blue = ImageIO.read(getClass().getResource("bluePlayer.png"));
	    	red = ImageIO.read(getClass().getResource("redPlayer.png"));
	    	sponge = ImageIO.read(getClass().getResource("spongePlayer.png"));
	    	star = ImageIO.read(getClass().getResource("starPlayer.png"));
	    	bug = ImageIO.read(getClass().getResource("bugPlayer.png"));
	    	
	        purpleplayer.setIcon(new ImageIcon(purple));
	    	blueplayer.setIcon(new ImageIcon(blue));
	    	redplayer.setIcon(new ImageIcon(red));
	    	spongeplayer.setIcon(new ImageIcon(sponge));
	    	starplayer.setIcon(new ImageIcon(star));
	    	bugplayer.setIcon(new ImageIcon(bug));
	    	//set both players to the purple one first
	    	showLabel1.setIcon(new ImageIcon(purple));
	    	showLabel2.setIcon(new ImageIcon(purple));
	    	character1='A';
	    	character2='A';
	    }
	    catch(Exception e){
	    	System.out.println(e);
	    }
	    
	    //add to imagePanel
	    imagePanel.add(purpleplayer);	 
	    imagePanel.add(blueplayer);	
	    imagePanel.add(redplayer);	
	    imagePanel.add(spongeplayer);	
	    imagePanel.add(starplayer);	
	    imagePanel.add(bugplayer);
	    imagePanel.add(showLabel1);
	    imagePanel.add(showLabel2);
	    
	    //setSize
	    purpleplayer.setSize(100, 100);
	    blueplayer.setSize(100, 100);
	    redplayer.setSize(100, 100);
	    spongeplayer.setSize(100, 100);
	    starplayer.setSize(100, 100);
	    bugplayer.setSize(100, 100);
	    showLabel1.setSize(100, 100);
	    showLabel2.setSize(100, 100);
	    
	    //setLocation
	    purpleplayer.setLocation(50, 50);
	    blueplayer.setLocation(200, 50);
	    redplayer.setLocation(350, 50);
	    spongeplayer.setLocation(50, 180);
	    starplayer.setLocation(200, 180);
	    bugplayer.setLocation(350, 180);
	    showLabel1.setLocation(480, 50);
	    showLabel2.setLocation(480, 180);
	    						
	    JLabel player1=new JLabel("Player1");
	    JLabel player2=new JLabel("Player2");
	    player1.setSize(50, 20);
	    player2.setSize(50, 20);
	    player1.setLocation(480, 30);
	    player2.setLocation(480, 160);
	    
	    //hint
	    JLabel hint1 = new JLabel("First, choose player1 and press \"Player1 selected!!\" button.");
	    JLabel hint2 = new JLabel("Second, choose player2 and press \"Player2 selected!!\" button.");
	    JLabel hint3 = new JLabel("Finally, press \"Play!!\" button to start the game.");
	    hint1.setSize(400,20);
	    hint1.setLocation(50, 335);
	    hint2.setSize(400,20);
	    hint2.setLocation(50, 355);
	    hint3.setSize(400,20);
	    hint3.setLocation(50, 375);
	    
	    //warning
	    warning = new JLabel("");
	    warning.setSize(250,50);
	    warning.setLocation(450,315);
	  
	    JLabel A=new JLabel("A");
	    JLabel B=new JLabel("B");
	    JLabel C=new JLabel("C");
	    JLabel D=new JLabel("D");
	    JLabel E=new JLabel("E");
	    JLabel F=new JLabel("R");
	    A.setSize(20, 20);
	    B.setSize(20, 20);
	    C.setSize(20, 20);
	    D.setSize(20, 20);
	    E.setSize(20, 20);
	    F.setSize(20, 20);
	    A.setLocation(50, 30);
	    B.setLocation(200, 30);
	    C.setLocation(350, 30);
	    D.setLocation(50, 160);
	    E.setLocation(200, 160);
	    F.setLocation(350, 160);
	    
		add(purpleplayer);
		add(blueplayer);
		add(redplayer);
		add(spongeplayer);
		add(starplayer);
		add(bugplayer);
		add(showLabel1);
		add(showLabel2);
		add(player1);
		add(player2);		
		add(hint1);
		add(hint2);
		add(hint3);
		add(warning);
		add(A);
		add(B);
		add(C);
		add(D);
		add(E);
		add(F);
		setVisible(true);
		
		//the button to start the game
		play = new JButton("Play!!");
		play.setLocation(350,300);
		play.setSize(play.getPreferredSize());
		play.addActionListener(this);
		add(play);
		
		//decide the selected character
		select1 = new JButton("Player1 selected!!");
		select1.setLocation(50,300);
		select1.setSize(select1.getPreferredSize());
		select1.addActionListener(this);
		add(select1);
		select2 = new JButton("Player2 selected!!");
		select2.setLocation(200,300);
		select2.setSize(select2.getPreferredSize());
		select2.addActionListener(this);
		add(select2);
	}
	public void run(){
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				Game.map[i][j]=Game.model_map[i][j];
			}
		}
		playWindow=new PlayFrame();
		playWindow.setVisible(true);
	}
	
	public void close(){
		this.dispose();
	}
	public void actionPerformed(ActionEvent e){
		
		JButton selectedBtn=(JButton)e.getSource();
		
		
		
		if(selectedBtn==select1 && selectFinish1==0){
			System.out.println("Player1 finish selecting!!");
			selectFinish1=1;
		}
		else if(selectedBtn==select2 && selectFinish2==0){
			System.out.println("Player2 finish selecting!!");
			selectFinish2=1;
		}
		else if(selectedBtn==play){
			if(selectFinish1 == 1 && selectFinish2 == 1){
				System.out.println("Game start!!");
				run();
				this.dispose();
			}
			else{
				if(selectFinish1 == 0 && selectFinish2 == 0){
					warning.setText("player1 and player2 aren't selected.");
				}
				else if(selectFinish1 == 0 && selectFinish2 != 0){
					warning.setText("player1 isn't selected.");
				}
				else if(selectFinish1 != 0 && selectFinish2 == 0){
					warning.setText("player2 isn't selected.");
				}
			}
		}
		
		if(selectFinish1==0){
			if(selectedBtn==purpleplayer){				
				character1='A';
				showLabel1.setIcon(new ImageIcon(purple));
			}else if(selectedBtn==blueplayer){				
				character1='B';	
				showLabel1.setIcon(new ImageIcon(blue));
			}else if(selectedBtn==redplayer){				
				character1='C';	
				showLabel1.setIcon(new ImageIcon(red));
			}else if(selectedBtn==spongeplayer){				
				character1='D';	
				showLabel1.setIcon(new ImageIcon(sponge));
			}else if(selectedBtn==starplayer){				
				character1='E';	
				showLabel1.setIcon(new ImageIcon(star));
			}else if(selectedBtn==bugplayer){				
				character1='F';	
				showLabel1.setIcon(new ImageIcon(bug));
			}		
		}
		else if(selectFinish2==0){
			if(selectedBtn==purpleplayer){				
				character2='A';			
				showLabel2.setIcon(new ImageIcon(purple));
			}else if(selectedBtn==blueplayer){				
				character2='B';	
				showLabel2.setIcon(new ImageIcon(blue));
			}else if(selectedBtn==redplayer){				
				character2='C';	
				showLabel2.setIcon(new ImageIcon(red));
			}else if(selectedBtn==spongeplayer){				
				character2='D';	
				showLabel2.setIcon(new ImageIcon(sponge));
			}else if(selectedBtn==starplayer){				
				character2='E';	
				showLabel2.setIcon(new ImageIcon(star));
			}else if(selectedBtn==bugplayer){				
				character2='F';	
				showLabel2.setIcon(new ImageIcon(bug));
			}
		}
	}	
	
}