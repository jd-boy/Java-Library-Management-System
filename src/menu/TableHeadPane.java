package menu;

/*
 * 给一部分界面的上部提供图片
 */

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TableHeadPane extends JPanel {
	
	private JPanel p;
	
	public TableHeadPane() {
		setLayout(new GridLayout(1, 1));
		
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(p.getClass().getResource("/table_head.jpg"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		add(p);
	}
}
