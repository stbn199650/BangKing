package BangKing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JLabel {//implements KeyListener{

	private int xPos, yPos;
	private int bombNum;
	private int speed;
	private boolean isDead;
	private PlayFrame p;
	
	public Player(int startXPos, int startYPos, char index,PlayFrame pf)
	{  
		setPicture(index);//index to select player
		setLocation(startXPos*40,startYPos*40);
		setVisible(true);
		setFocusable(true);
		p=pf;
		xPos = startXPos;
		yPos = startYPos;
		bombNum = 1;
		speed = 1;
		isDead = false;	
	}
	
	public void setPicture(char index)
	{	
		if(index=='A')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("purplePlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else if(index=='B')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bluePlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else if(index=='C')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("redPlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else if(index=='D')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("spongePlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else if(index=='E')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("starPlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		else if(index=='F')setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bugPlayer.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setSize(getPreferredSize());		
	}
	
	public void setXPos(int move){
		
		this.xPos += move;
		setLocation(xPos*40,yPos*40);
	}
	
	public int getXPos(){
		return this.xPos;
	}
	
	public void setYPos(int move){
		this.yPos += move;
		setLocation(xPos*40,yPos*40);
	}
	
	public int getYPos(){
		return this.yPos;
	}
	
	public void setBombNum(int newBombNum){
		this.bombNum += newBombNum;
	}
	
	public int getBombNum(){
		return this.bombNum;
	}
	
	public void setSpeed(int newSpeed){
		this.speed += newSpeed;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setIsDead(boolean changeIsDead){
		this.isDead = changeIsDead;
		setVisible(false);
		Game.map[yPos][xPos]=0;
		System.out.println("gggggoooooooooo");
	}
	
	public boolean getIsDead(){
		return this.isDead;
	}
	
	public Bomb createBomb(int player){
		
		Bomb bomb = new Bomb(this,p);
		
		switch(player){
		case 1:
			Game.map[yPos][xPos] = 4;
			break;
		case 2:
			Game.map[yPos][xPos] = 8;
			break;
		}
	
		
		return bomb;
	}
}	