package BangKing;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.bind.Marshaller.Listener;

public class Box extends JLabel{
	
	int beBomb;
	int map_i,map_j;
	
	public Box(int i,int j)
	{
		setIcon(new ImageIcon(new ImageIcon(getClass().getResource("Box.jpg")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		setSize(getPreferredSize());
		beBomb=0;
		map_i=i;
		map_j=j;
	}
	public void setBeBomb()
	{
		beBomb=1;
		setVisible(false);
		Game.map[map_i][map_j]=0;
	}

}