package BangKing;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ToolNum extends JLabel{
		
	public ToolNum(){
		setIcon(new ImageIcon(new ImageIcon(getClass().getResource("tool_bomb.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setSize(getPreferredSize());
		setVisible(true);
	}
	public void disappear()
	{
		setVisible(false);
		Game.map[this.location().y/40][this.location().x/40]=0;
	}		
}
