package BangKing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.*;

public class ToolSpeed extends JLabel{
	

	public ToolSpeed(){
		setIcon(new ImageIcon(new ImageIcon(getClass().getResource("tool_speed.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setSize(getPreferredSize());
		setVisible(true);
	}
	public void disappear()
	{
		setVisible(false);
		Game.map[this.location().y/40][this.location().x/40]=0;
	}		
	
}
