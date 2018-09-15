package BangKing;

import java.util.TimerTask;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ExplodeTaskLevel1 extends TimerTask{
	
	private Bomb bomb;
	File soundeffect;
	
	public ExplodeTaskLevel1(Bomb bomb){
		this.bomb = bomb;
		
		//sound effect
		soundeffect = new File("explode.WAV");
	}
	
	public void run(){
		System.out.println("Bomb!!Task1!");
		
		//change bomb picture to cross
		bomb.setPicture(1);
		PlaySound(soundeffect);
	}
	
	//sound effect
	static void PlaySound(File Sound){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		}
		catch(Exception e){}
	}
}