package BangKing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Result extends JFrame implements ActionListener{
	JButton restart;
	JButton exit;
	
	public Result(int judge){
		setSize(500, 520);		
		setLayout(null);
		setLocation(400, 130);
		
		/*background*/
		JPanel imagePanel = null;
	    JLabel  bgLabel = null;
	    ImageIcon background = null;
		background = new ImageIcon(getClass().getResource("GameOver.png"));       
	    bgLabel = new JLabel(background);     
	    bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());   
	    imagePanel = (JPanel) this.getContentPane();
	    imagePanel.setOpaque(false);
	    getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
			    
	    /*button*/		
		restart=new JButton("Restart!!");
		restart.setLocation(80,400);
		restart.setSize(100,30);
		restart.addActionListener(this);
		add(restart);
		exit=new JButton("Exit");				
		exit.setLocation(280,400);
		exit.setSize(100,30);
		exit.addActionListener(this);
		add(exit);
		
		JLabel winner=new JLabel("Winner");
		winner.setSize(100,50);
		winner.setLocation(100,150);
		winner.setForeground(Color.WHITE);
		JLabel loser=new JLabel("Loser");
		loser.setSize(100,50);
		loser.setLocation(100,250);
		loser.setForeground(Color.WHITE);
		JLabel win=new JLabel();
		win.setSize(150,50);
		win.setLocation(250,150);
		win.setForeground(Color.WHITE);
		JLabel lose=new JLabel();
		lose.setSize(150,50);
		lose.setLocation(250,250);
		lose.setForeground(Color.WHITE);
		JLabel tie=new JLabel("Game Tie"); 
		tie.setSize(300, 100);
		tie.setLocation(120, 180);
		tie.setForeground(Color.WHITE);
		winner.setFont(winner.getFont().deriveFont(28.0f));
		win.setFont(win.getFont().deriveFont(28.0f));
		loser.setFont(loser.getFont().deriveFont(28.0f));
		lose.setFont(lose.getFont().deriveFont(28.0f));
		tie.setFont(tie.getFont().deriveFont(42.0f));
		if(judge==0){
			win.setText("Player2");
			lose.setText("Player1");
			add(win);
			add(lose);
			add(winner);
			add(loser);
		}else if(judge==1){
			win.setText("Player1");
			lose.setText("Player2");
			add(win);
			add(lose);
			add(winner);
			add(loser);
		}else if(judge==2){
			add(tie);
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		JButton btn =(JButton)e.getSource();
		if(btn==restart){
			ChoosePlayer play=new ChoosePlayer();
			play.setVisible(true);
			this.dispose();
		}else if(btn==exit){
			System.out.println("exit");
			System.exit(0);
		}
	}
	
}