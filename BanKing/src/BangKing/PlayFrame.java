package BangKing;

import java.awt.Component;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class PlayFrame extends JFrame implements KeyListener{
	
	static Player player1;
	static Player player2;
	static Object[][] thing=new Object[15][15];
	
	Image purple,blue,red,sponge,star,bug;
	JLabel bombNum1,bombNum2,speed1,speed2;
	static JLabel isDead1,isDead2;
	
	public PlayFrame(){
		
	    setSize(800, 632);
		setLocation(230, 70);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
	    /*set background*/
	    ImageIcon background = new ImageIcon(getClass().getResource("background.png"));       
	    JLabel bgLabel = new JLabel(background);     
	    bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());   
	    JPanel imagePanel = (JPanel) this.getContentPane();
	    imagePanel.setOpaque(false);
	    getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
	    
	    /*set box*/
	    for(int i=0;i<15;i++){
	    	for(int j=0;j<15;j++)
	    	{
	    		if(Game.map[i][j]==1)
	    		{
	    			thing[i][j]=new Box(i,j);	
	    			((Component) thing[i][j]).setLocation(40*j,40*i);
	    	    	((Component) thing[i][j]).setVisible(true);
	    			//b[i][j].setVisible(false);
	    			add((Component) thing[i][j]);
	    		}else if(Game.map[i][j]==2)
	    		{
	    			/*add toolNum*/
	    			thing[i][j]=new ToolNum();	
	    			((Component) thing[i][j]).setLocation(40*j,40*i);
	    	    	((Component) thing[i][j]).setVisible(true);
	    			add((Component) thing[i][j]);
	    		}else if(Game.map[i][j]==3)
	    		{
	    			thing[i][j]=new ToolSpeed();	
	    			((Component) thing[i][j]).setLocation(40*j,40*i);
	    	    	((Component) thing[i][j]).setVisible(true);
	    			add((Component) thing[i][j]);
	    		}
	    		else if(Game.map[i][j]==5)
	    		{
	    			/*add player1*/
	    		    player1=new Player(j,i,ChoosePlayer.character1,this);
	    		    player1.addKeyListener(this);
	    		    add(player1);
	    		}else if(Game.map[i][j]==6)
	    		{
	    			player2=new Player(j,i,ChoosePlayer.character2,this);
	    		    player2.addKeyListener(this);
	    		    add(player2);
	    		}
	    	}
	    }

	    /*set player state*/
	    //title//
	    JLabel title1=new JLabel("Player1");
	    JLabel title2=new JLabel("Player2");
	    title1.setSize(50, 20);
	    title2.setSize(50, 20);
	    title1.setLocation(640, 10);
	    title2.setLocation(640, 315);
	    add(title1);
	    add(title2);
	    //image//
	    try{
	    	purple = ImageIO.read(getClass().getResource("purplePlayer.png"));
	    	blue = ImageIO.read(getClass().getResource("bluePlayer.png"));
	    	red = ImageIO.read(getClass().getResource("redPlayer.png"));
	    	sponge = ImageIO.read(getClass().getResource("spongePlayer.png"));
	    	star = ImageIO.read(getClass().getResource("starPlayer.png"));
	    	bug = ImageIO.read(getClass().getResource("bugPlayer.png"));
	    }
	    catch(Exception e){
	    	System.out.println(e);
	    }	    
	    JLabel image1 = new JLabel();
	    JLabel image2 = new JLabel();
	    image1.setSize(100, 100);
	    image2.setSize(100, 100);
	    image1.setLocation(640, 40);
	    image2.setLocation(640, 345);
	    switch(ChoosePlayer.character1){
	    	case 'A': image1.setIcon(new ImageIcon(purple)); break;
	    	case 'B': image1.setIcon(new ImageIcon(blue)); break;
	    	case 'C': image1.setIcon(new ImageIcon(red)); break;
	    	case 'D': image1.setIcon(new ImageIcon(sponge)); break;
	    	case 'E': image1.setIcon(new ImageIcon(star)); break;
	    	case 'F': image1.setIcon(new ImageIcon(bug)); break;
	    }
	    switch(ChoosePlayer.character2){
    	case 'A': image2.setIcon(new ImageIcon(purple)); break;
    	case 'B': image2.setIcon(new ImageIcon(blue)); break;
    	case 'C': image2.setIcon(new ImageIcon(red)); break;
    	case 'D': image2.setIcon(new ImageIcon(sponge)); break;
    	case 'E': image2.setIcon(new ImageIcon(star)); break;
    	case 'F': image2.setIcon(new ImageIcon(bug)); break;
	    }
	    add(image1);
	    add(image2);
	    //attribute -> bombNum, speed, isDead//
	    bombNum1=new JLabel("Bomb Number = 1");
	    bombNum2=new JLabel("Bomb Number = 1");
	    speed1=new JLabel("Speed = 1");
	    speed2=new JLabel("Speed = 1");
	    isDead1=new JLabel("State = Alive");
	    isDead2=new JLabel("State = Alive");
	    bombNum1.setSize(200,20);
	    bombNum2.setSize(200,20);
	    speed1.setSize(200,20);
	    speed2.setSize(200,20);
	    isDead1.setSize(200,20);
	    isDead2.setSize(200,20);
	    bombNum1.setLocation(640,150);
	    bombNum2.setLocation(640,455);
	    speed1.setLocation(640,165);
	    speed2.setLocation(640,470);
	    isDead1.setLocation(640,180);
	    isDead2.setLocation(640,485);
	    add(bombNum1);
	    add(bombNum2);
	    add(speed1);
	    add(speed2);
	    add(isDead1);
	    add(isDead2);    
	  
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e)
	{
			int keyCode = e.getKeyCode();
			int distance, jumpRange;
			int startXPos,startYPos;
			
			int distance2, jumpRange2 = 0;
			int startXPos2,startYPos2;
			
			switch(keyCode)
			{
					case KeyEvent.VK_UP:
						
						startXPos=player1.getXPos();
						startYPos=player1.getYPos();
						
						for(distance=1;distance<=player1.getSpeed();distance++)
						{
							if(startYPos-distance>=0 && (Game.map[startYPos-distance][startXPos]==1 
									|| Game.map[startYPos-distance][startXPos]==4 || Game.map[startYPos-distance][startXPos]==8))
								break;
						}
						
						if(distance>player1.getSpeed())jumpRange = player1.getSpeed();
						else jumpRange = distance;
										
						
						for(int i=1;i<=jumpRange;i++)
						{
							if(startYPos-i>=0)
							{
								if(Game.map[startYPos-i][startXPos]==2){//toolNum
								
									((ToolNum)thing[startYPos-i][startXPos]).disappear();
									Game.map[startYPos-i][startXPos]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos-i+1][startXPos]=6;//6,7overlap
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos-i+1][startXPos]=0;
									player1.setYPos(-1);
									player1.setBombNum(1);
									System.out.println(player1.getYPos());
									bombNum1.setText("Bomb Number = " + player1.getBombNum());
									
								}							
								else if(Game.map[startYPos-i][startXPos]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos-i][startXPos]).disappear();
									Game.map[startYPos-i][startXPos]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos-i+1][startXPos]=6;//6,7overlap
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos-i+1][startXPos]=0;
									player1.setYPos(-1);
									player1.setSpeed(1);
									System.out.println(player1.getYPos());
									speed1.setText("Speed = " + player1.getSpeed());
								}							
								else if(Game.map[startYPos-i][startXPos]==0 || Game.map[startYPos-i][startXPos]==6){//space or other player
								
									if(Game.map[startYPos-i][startXPos]==0)Game.map[startYPos-i][startXPos]=5;
									else Game.map[startYPos-i][startXPos]=7;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos-i+1][startXPos]=6;//6,7overlap
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos-i+1][startXPos]=0;
									player1.setYPos(-1);
									System.out.println(player1.getYPos());
								}
								
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
								
							}
						}
						break;
					case KeyEvent.VK_DOWN:
						
						startXPos=player1.getXPos();
					    startYPos=player1.getYPos();
						
						for(distance=1;distance<=player1.getSpeed();distance++)
						{
							if(startYPos+distance<15 && (Game.map[startYPos+distance][startXPos]==1 
									|| Game.map[startYPos+distance][startXPos]==4 ||Game.map[startYPos+distance][startXPos]==8))
								break;
						}
						
						if(distance>player1.getSpeed())jumpRange = player1.getSpeed();
						else jumpRange = distance;
						
						
						for(int i=1;i<=jumpRange;i++)
						{
							if(startYPos+i<15)
							{
								if(Game.map[startYPos+i][startXPos]==2){//toolNum
									((ToolNum)thing[startYPos+i][startXPos]).disappear();
									Game.map[startYPos+i][startXPos]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos+i-1][startXPos]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos+i-1][startXPos]=0;
									player1.setYPos(1);
									player1.setBombNum(1);
									bombNum1.setText("Bomb Number = " + player1.getBombNum());
								}							
								else if(Game.map[startYPos+i][startXPos]==3){//toolSpeed
									((ToolSpeed)thing[startYPos+i][startXPos]).disappear();
									Game.map[startYPos+i][startXPos]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos+i-1][startXPos]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos+i-1][startXPos]=0;
									player1.setYPos(1);
									player1.setSpeed(1);
									speed1.setText("Speed = " + player1.getSpeed());
								}							
								else if(Game.map[startYPos+i][startXPos]==0 || Game.map[startYPos+i][startXPos]==6){
									if(Game.map[startYPos+i][startXPos]==0)Game.map[startYPos+i][startXPos]=5;//space or other player
									else Game.map[startYPos+i][startXPos]=7;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos+i-1][startXPos]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos+i-1][startXPos]=0;
									player1.setYPos(1);
								}
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
							}
						}
						break;
					case KeyEvent.VK_RIGHT:
			
						startXPos=player1.getXPos();
					    startYPos=player1.getYPos();
						
						for(distance=1;distance<=player1.getSpeed();distance++){
							if(startXPos+distance<15 && (Game.map[startYPos][startXPos+distance]==1 
									|| Game.map[startYPos][startXPos+distance]==4 || Game.map[startYPos][startXPos+distance]==8))
								break;
						}
						
						if(distance>player1.getSpeed())	jumpRange = player1.getSpeed();
						else jumpRange = distance;						
						
						for(int i=1;i<=jumpRange;i++)
						{
							if(startXPos+i<15)
							{
								if(Game.map[startYPos][startXPos+i]==2){//toolNum
									((ToolNum)thing[startYPos][startXPos+i]).disappear();
									Game.map[startYPos][startXPos+i]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos+i-1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos+i-1]=0;
									player1.setXPos(1);
									player1.setBombNum(1);
									bombNum1.setText("Bomb Number = " + player1.getBombNum());
								}							
								else if(Game.map[startYPos][startXPos+i]==3){//toolSpeed
									((ToolSpeed)thing[startYPos][startXPos+i]).disappear();
									Game.map[startYPos][startXPos+i]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos+i-1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos+i-1]=0;
									player1.setXPos(1);
									player1.setSpeed(1);
									speed1.setText("Speed = " + player1.getSpeed());
								}							
								else if(Game.map[startYPos][startXPos+i]==0 || Game.map[startYPos][startXPos+i]==6){
									if(Game.map[startYPos][startXPos+i]==0)Game.map[startYPos][startXPos+i]=5;//space or other player
									else Game.map[startYPos][startXPos+i]=7;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos+i-1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos+i-1]=0;
									player1.setXPos(1);
								}
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
							}
						}
						break;
					case KeyEvent.VK_LEFT:
						
						startXPos=player1.getXPos();
					    startYPos=player1.getYPos();
						
						for(distance=1;distance<=player1.getSpeed();distance++){
							if(startXPos-distance>=0 && (Game.map[startYPos][startXPos-distance]==1 
									|| Game.map[startYPos][startXPos-distance]==4 || Game.map[startYPos][startXPos-distance]==8))
								break;
						}
						
						if(distance>player1.getSpeed())jumpRange = player1.getSpeed();
						else jumpRange = distance;
						
					
						for(int i=1;i<=jumpRange;i++)
						{
							if(startXPos-i>=0)
							{
								if(Game.map[startYPos][startXPos-i]==2){//toolNum
								
									((ToolNum)thing[startYPos][startXPos-i]).disappear();
									Game.map[startYPos][startXPos-i]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos-i+1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos-i+1]=0;
									player1.setXPos(-1);
									player1.setBombNum(1);
									bombNum1.setText("Bomb Number = " + player1.getBombNum());
								}							
								else if(Game.map[startYPos][startXPos-i]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos][startXPos-i]).disappear();
									Game.map[startYPos][startXPos-i]=5;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos-i+1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos-i+1]=0;
									player1.setXPos(-1);
									player1.setSpeed(1);
									speed1.setText("Speed = " + player1.getSpeed());
								}							
								else if(Game.map[startYPos][startXPos-i]==0 || Game.map[startYPos][startXPos-i]==6){
									
									if(Game.map[startYPos][startXPos-i]==0)Game.map[startYPos][startXPos-i]=5;//space or other player
									else Game.map[startYPos][startXPos-i]=7;
									if(Game.map[player1.getYPos()][player1.getXPos()]==7)Game.map[startYPos][startXPos-i+1]=6;
									else if(Game.map[player1.getYPos()][player1.getXPos()]==4){}//keep 4
									else if(Game.map[player1.getYPos()][player1.getXPos()]==8){}//keep 8
									else Game.map[startYPos][startXPos-i+1]=0;
									player1.setXPos(-1);
								}
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
							}
						}
						break;
					case KeyEvent.VK_W:
						
						startXPos2=player2.getXPos();
						startYPos2=player2.getYPos();
						
						for(distance2=1;distance2<=player2.getSpeed();distance2++)
						{
							if(startYPos2-distance2>=0 && (Game.map[startYPos2-distance2][startXPos2]==1 
									|| Game.map[startYPos2-distance2][startXPos2]==4 || Game.map[startYPos2-distance2][startXPos2]==8))
								break;
						}
						
						if(distance2>player2.getSpeed())jumpRange2 = player2.getSpeed();
						else jumpRange2 = distance2;
										
						
						for(int i=1;i<=jumpRange2;i++)
						{
							if(startYPos2-i>=0)
							{
								if(Game.map[startYPos2-i][startXPos2]==2){//toolNum
								
									((ToolNum)thing[startYPos2-i][startXPos2]).disappear();
									Game.map[startYPos2-i][startXPos2]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2-i+1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2-i+1][startXPos2]=0;
									player2.setYPos(-1);
									player2.setBombNum(1);
									System.out.println(player2.getYPos());
									bombNum2.setText("Bomb Number = " + player2.getBombNum());
									
								}							
								else if(Game.map[startYPos2-i][startXPos2]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos2-i][startXPos2]).disappear();
									Game.map[startYPos2-i][startXPos2]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2-i+1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2-i+1][startXPos2]=0;
									player2.setYPos(-1);
									player2.setSpeed(1);
									System.out.println(player2.getYPos());
									speed2.setText("Speed = " + player2.getSpeed());
									
								}							
								else if(Game.map[startYPos2-i][startXPos2]==0 || Game.map[startYPos2-i][startXPos2]==5){//space or other player
								
									if(Game.map[startYPos2-i][startXPos2]==0)Game.map[startYPos2-i][startXPos2]=6;
									else Game.map[startYPos2-i][startXPos2]=7;  
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2-i+1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2-i+1][startXPos2]=0;
									player2.setYPos(-1);
									System.out.println(player2.getYPos());
								}
								
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
								
							}
						}
						break;
					
					case KeyEvent.VK_S:
						
						startXPos2=player2.getXPos();
						startYPos2=player2.getYPos();
						
						for(distance2=1;distance2<=player2.getSpeed();distance2++)
						{
							if(startYPos2+distance2<15 && (Game.map[startYPos2+distance2][startXPos2]==1 
									|| Game.map[startYPos2+distance2][startXPos2]==4 || Game.map[startYPos2+distance2][startXPos2]==8))
								break;
						}
						
						if(distance2>player2.getSpeed())jumpRange2 = player2.getSpeed();
						else jumpRange2 = distance2;
										
						
						for(int i=1;i<=jumpRange2;i++)
						{
							if(startYPos2+i<15)
							{System.out.println("text");
								if(Game.map[startYPos2+i][startXPos2]==2){//toolNum
								
									((ToolNum)thing[startYPos2+i][startXPos2]).disappear();
									Game.map[startYPos2+i][startXPos2]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2+i-1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2+i-1][startXPos2]=0;
									player2.setYPos(1);
									player2.setBombNum(1);
									System.out.println(player2.getYPos());
									bombNum2.setText("Bomb Number = " + player2.getBombNum());
									
								}							
								else if(Game.map[startYPos2+i][startXPos2]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos2+i][startXPos2]).disappear();
									Game.map[startYPos2+i][startXPos2]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2+i-1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2+i-1][startXPos2]=0;
									player2.setYPos(1);
									player2.setSpeed(1);
									System.out.println(player2.getYPos());
									speed2.setText("Speed = " + player2.getSpeed());
									
								}							
								else if(Game.map[startYPos2+i][startXPos2]==0 || Game.map[startYPos2+i][startXPos2]==5){//space or other player
								
									if(Game.map[startYPos2+i][startXPos2]==0)Game.map[startYPos2+i][startXPos2]=6;
									else Game.map[startYPos2+i][startXPos2]=7;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2+i-1][startXPos2]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2+i-1][startXPos2]=0;
									player2.setYPos(1);
									System.out.println(player2.getYPos());
								}
								
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
								
							}
						}
						break;
					case KeyEvent.VK_D:
						
						startXPos2=player2.getXPos();
						startYPos2=player2.getYPos();
						
						for(distance2=1;distance2<=player2.getSpeed();distance2++)
						{
							if(startYPos2+distance2>=0 && (Game.map[startYPos2][startXPos2+distance2]==1 
									|| Game.map[startYPos2][startXPos2+distance2]==4 || Game.map[startYPos2][startXPos2+distance2]==8))
								break;
						}
						
						if(distance2>player2.getSpeed())jumpRange2 = player2.getSpeed();
						else jumpRange2 = distance2;
										
						
						for(int i=1;i<=jumpRange2;i++)
						{
							if(startXPos2+i<15)
							{
								if(Game.map[startYPos2][startXPos2+i]==2){//toolNum
								
									((ToolNum)thing[startYPos2][startXPos2+i]).disappear();
									Game.map[startYPos2][startXPos2+i]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2+i-1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2+i-1]=0;
									player2.setXPos(1);
									player2.setBombNum(1);
									System.out.println(player2.getYPos());
									bombNum2.setText("Bomb Number = " + player2.getBombNum());
									
								}							
								else if(Game.map[startYPos2][startXPos2+i]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos2][startXPos2+i]).disappear();
									Game.map[startYPos2][startXPos2+i]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2+i-1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2+i-1]=0;
									player2.setXPos(1);
									player2.setSpeed(1);
									System.out.println(player2.getYPos());
									speed2.setText("Speed = " + player2.getSpeed());
									
								}							
								else if(Game.map[startYPos2][startXPos2+i]==0 || Game.map[startYPos2][startXPos2+i]==5){//space or other player
								
									if(Game.map[startYPos2][startXPos2+i]==0)Game.map[startYPos2][startXPos2+i]=6;
									else Game.map[startYPos2][startXPos2+i]=7;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2+i-1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2+i-1]=0;
									player2.setXPos(1);
									System.out.println(player2.getYPos());
								}
								
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
								
							}
						}
						break;
					case KeyEvent.VK_A:
						
						startXPos2=player2.getXPos();
						startYPos2=player2.getYPos();
						
						for(distance2=1;distance2<=player2.getSpeed();distance2++)
						{
							if(startXPos2-distance2>=0 && (Game.map[startYPos2][startXPos2-distance2]==1 
									|| Game.map[startYPos2][startXPos2-distance2]==4 || Game.map[startYPos2][startXPos2-distance2]==8))
								break;
						}
						
						if(distance2>player2.getSpeed())jumpRange2 = player2.getSpeed();
						else jumpRange2 = distance2;
										
						
						for(int i=1;i<=jumpRange2;i++)
						{
							if(startXPos2-i>=0)
							{
								if(Game.map[startYPos2][startXPos2-i]==2){//toolNum
								
									((ToolNum)thing[startYPos2][startXPos2-i]).disappear();
									Game.map[startYPos2][startXPos2-i]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2-i+1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2-i+1]=0;
									player2.setXPos(-1);
									player2.setBombNum(1);
									System.out.println(player2.getYPos());
									bombNum2.setText("Bomb Number = " + player2.getBombNum());
									
								}							
								else if(Game.map[startYPos2][startXPos2-i]==3){//toolSpeed
								
									((ToolSpeed)thing[startYPos2][startXPos2-i]).disappear();
									Game.map[startYPos2][startXPos2-i]=6;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2-i+1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2-i+1]=0;
									player2.setXPos(-1);
									player2.setSpeed(1);
									System.out.println(player2.getYPos());
									speed2.setText("Speed = " + player2.getSpeed());
									
								}							
								else if(Game.map[startYPos2][startXPos2-i]==0 || Game.map[startYPos2][startXPos2-i]==5){//space or other player
								
									if(Game.map[startYPos2][startXPos2-i]==0)Game.map[startYPos2][startXPos2-i]=6;
									else Game.map[startYPos2][startXPos2-i]=7;
									if(Game.map[player2.getYPos()][player2.getXPos()]==7)Game.map[startYPos2][startXPos2-i+1]=5;
									else if(Game.map[player2.getYPos()][player2.getXPos()]==4){}//keep 4
									else if(Game.map[player2.getYPos()][player2.getXPos()]==8){}//keep 8
									else Game.map[startYPos2][startXPos2-i+1]=0;
									player2.setXPos(-1);
									System.out.println(player2.getYPos());
								}
								
								for(int k=0;k<15;k++)
								{
									for(int j=0;j<15;j++)
									{
										System.out.print(Game.map[k][j]);
									}
									System.out.println("\n");
								}
								
							}
						}
						break;
					case KeyEvent.VK_SPACE:
						//player1
						
						//limit the number of bomb
						int count = 0;
						for(int i=0;i<15;i++){
							for(int j=0;j<15;j++){
								if(Game.map[i][j]==4)
									count++;
							}
						}
						System.out.println("count1 = "+count);
						
						if(count < player1.getBombNum()){
							System.out.println("Player1 Bomb");
							Bomb bomb = player1.createBomb(1);
							add(bomb);
						}
						break;
					case KeyEvent.VK_X:
						//player2
						
						//limit the number of bomb
						int count2 = 0;
						for(int i=0;i<15;i++){
							for(int j=0;j<15;j++){
								if(Game.map[i][j]==8)
									count2++;
							}
						}
						System.out.println("count2 = "+count2);
						
						if(count2 < player2.getBombNum()){
							System.out.println("Player2 Bomb");
							Bomb bomb2 = player2.createBomb(2);
							add(bomb2);
						}
						break;
					default:
						break;						
						
			}
	}
	
}