package menu;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainInterface extends JPanel {
	
	private JPanel panel;
	
	public MainInterface() {
		
		setLayout(new GridLayout(1, 1));
		
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(panel.getClass().getResource("/interface.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		
		add(panel);
	}
}
