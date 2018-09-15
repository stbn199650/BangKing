package BangKing;

import java.awt.Image;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	
	private int xPos, yPos;
	private Timer timer;
	PlayFrame p;	
	public Bomb(Player player,PlayFrame pf){
		
		//set bomb x ,y
		xPos = player.getXPos();
		yPos = player.getYPos();
		p=pf;
		//create timer
		timer = new Timer();
		
		//set bomb picture
		setPicture(0);
			
		System.out.println(xPos+" "+yPos);
		setVisible(true);
		
		//explode
		explode();
	}
	
	/*level 0:original
	 * 		1:cross		*/
	public void setPicture(int level){
		switch(level){
		case 0:
			setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bomb.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			setLocation(xPos*40,yPos*40);
			break;
		case 1:
			setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bomb_cross.png")).getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
			setLocation(xPos*40-40,yPos*40-40);
			break;
		}
		setSize(getPreferredSize());
	}
	
	public void explode(){
		timer.schedule(new ExplodeTaskLevel1(this), 1500);//1500
		timer.schedule(new ExplodeTaskLevel2(this,p), 1700);//1700
	}
	
	public void disappear(){
		Game.map[yPos][xPos] = 0;
		setVisible(false);
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
}