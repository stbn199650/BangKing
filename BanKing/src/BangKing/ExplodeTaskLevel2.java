package BangKing;

import java.util.TimerTask;

public class ExplodeTaskLevel2 extends TimerTask{
	
private Bomb bomb;
	PlayFrame p;
	
	public ExplodeTaskLevel2(Bomb bomb,PlayFrame pf){
		this.bomb = bomb;
		p=pf;
	}
	
	public void run(){
		System.out.println("Bomb!!Task2!");

		this.detect();
		
		bomb.disappear();
		System.out.println("DISSAPEAR");
		
	}
	
	public void detect(){
		
		/*check if out of array bound*/
		boolean left = true, right = true, up = true, down = true;
		if(bomb.getXPos()-1 < 0)  left = false;
		if(bomb.getXPos()+1 > 14) right = false;
		if(bomb.getYPos()-1 < 0)  up = false;
		if(bomb.getYPos()+1 > 14) down = false;
		
		
		/*destroy box(es)*/
		if(left && Game.map[bomb.getYPos()][bomb.getXPos()-1] == 1)
			((Box)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()-1]).setBeBomb();
		if(right && Game.map[bomb.getYPos()][bomb.getXPos()+1] == 1)
			((Box)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()+1]).setBeBomb();
		if(up && Game.map[bomb.getYPos()-1][bomb.getXPos()] == 1)
			((Box)PlayFrame.thing[bomb.getYPos()-1][bomb.getXPos()]).setBeBomb();
		if(down && Game.map[bomb.getYPos()+1][bomb.getXPos()] == 1)
			((Box)PlayFrame.thing[bomb.getYPos()+1][bomb.getXPos()]).setBeBomb();
		
		
		/*destroy tool(s)*/
		//1.ToolNum
		if(left && Game.map[bomb.getYPos()][bomb.getXPos()-1] == 2)
			((ToolNum)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()-1]).disappear();
		if(right && Game.map[bomb.getYPos()][bomb.getXPos()+1] == 2)
			((ToolNum)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()+1]).disappear();
		if(up && Game.map[bomb.getYPos()-1][bomb.getXPos()] == 2)
			((ToolNum)PlayFrame.thing[bomb.getYPos()-1][bomb.getXPos()]).disappear();
		if(down && Game.map[bomb.getYPos()+1][bomb.getXPos()] == 2)
			((ToolNum)PlayFrame.thing[bomb.getYPos()+1][bomb.getXPos()]).disappear();
		
		//2.ToolSpeed
		if(left && Game.map[bomb.getYPos()][bomb.getXPos()-1] == 3)
			((ToolSpeed)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()-1]).disappear();
		if(right && Game.map[bomb.getYPos()][bomb.getXPos()+1] == 3)
			((ToolSpeed)PlayFrame.thing[bomb.getYPos()][bomb.getXPos()+1]).disappear();
		if(up && Game.map[bomb.getYPos()-1][bomb.getXPos()] == 3)
			((ToolSpeed)PlayFrame.thing[bomb.getYPos()-1][bomb.getXPos()]).disappear();
		if(down && Game.map[bomb.getYPos()+1][bomb.getXPos()] == 3)
			((ToolSpeed)PlayFrame.thing[bomb.getYPos()+1][bomb.getXPos()]).disappear();
		
		
		/*kill player(s)*/
		//player1
		if( (left&&(Game.map[bomb.getYPos() ][bomb.getXPos()-1] == 5))  ||
			(right&&(Game.map[bomb.getYPos() ][bomb.getXPos()+1] == 5)) ||
			(up&&(Game.map[bomb.getYPos()-1][bomb.getXPos()  ]== 5))    ||
			(down&&(Game.map[bomb.getYPos()+1][bomb.getXPos()  ]== 5)) ){
			
				//kill player
				PlayFrame.player1.setIsDead(true);
				PlayFrame.isDead1.setText("State = Dead");
		}
		
		//player2
		if( (left&&(Game.map[bomb.getYPos() ][bomb.getXPos()-1] == 6))  ||
			(right&&(Game.map[bomb.getYPos() ][bomb.getXPos()+1] == 6)) ||
			(up&&(Game.map[bomb.getYPos()-1][bomb.getXPos()  ]== 6))    ||
			(down&&(Game.map[bomb.getYPos()+1][bomb.getXPos()  ]== 6)) ){
				
				//kill player2
				PlayFrame.player2.setIsDead(true);
				PlayFrame.isDead2.setText("State = Dead");
			
		}
		
		//player1 + player2
		if( (left&&(Game.map[bomb.getYPos() ][bomb.getXPos()-1] == 7))  ||
			(right&&(Game.map[bomb.getYPos() ][bomb.getXPos()+1] == 7)) ||
			(up&&(Game.map[bomb.getYPos()-1][bomb.getXPos()  ]== 7))    ||
			(down&&(Game.map[bomb.getYPos()+1][bomb.getXPos()  ]== 7)) ){
				
				//kill player1 + player2
				PlayFrame.player1.setIsDead(true);
				PlayFrame.isDead1.setText("State = Dead");
				PlayFrame.player2.setIsDead(true);
				PlayFrame.isDead2.setText("State = Dead");
				
			}
		
		
		/*call result window*/
		if(PlayFrame.player1.getIsDead() || PlayFrame.player2.getIsDead()){
			callResult();
		}
	
	}//detect
	
	public void callResult(){
		
		//tie
		if(PlayFrame.player1.getIsDead() && PlayFrame.player2.getIsDead()){
			Result result = new Result(2);
			result.show();
			p.dispose();
		}
		//player1 win
		else if(!PlayFrame.player1.getIsDead() && PlayFrame.player2.getIsDead()){
			Result result = new Result(1);
			result.show();
			p.dispose();
		}
		//player2 win
		else if(PlayFrame.player1.getIsDead() && !PlayFrame.player2.getIsDead()){
			Result result = new Result(0);
			result.show();
			p.dispose();
		}
		
	}
	
}