package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class AdminMenu extends JPanel {

	private JLayeredPane layered;
	
	public AdminMenu() {
		setLayout(new GridLayout(1, 1));
		layered = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(layered.getClass().getResource("/bookadmin.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(),0, 0, this);
			}
		};
		addButton();
		delButton();
		modifyButton();
		addGenre();
		add(layered);
		setBounds(0, 0, 1500, 800);
		setVisible(true);
		
	}
	
	private void addButton() {
		JButton add = new JButton("添加图书信息");
		add.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBook();
			}
		});
		
		setButton(add, 230);
	}
	
	private void delButton() {
		JButton del = new JButton("删除图书信息");
		
		del.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new DelBookMenu("删除图书信息");
			}
		});
		setButton(del, 310);
	}
	
	private void modifyButton() {
		JButton modify = new JButton("修改图书信息");
		modify.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AlterBookMenu("修改图书信息");
			}
		});
		setButton(modify, 390);
	}
	
	private void addGenre() {
		JButton genre = new JButton("添加图书类别");
		genre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new AlterGenreMenu("修改图书类别");
			}
		});
		setButton(genre, 470);
	}
	
	private void setButton(JButton button, int y) {
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		button.setBounds(200, y, 240, 50);
		button.setOpaque(false);
		button.setBackground(new Color(2, 2, 2));
		button.setForeground(new Color(255, 200, 100));
		layered.add(button, new Integer(200));
	}
}
