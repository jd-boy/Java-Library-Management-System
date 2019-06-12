package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlterMenu extends JFrame{
	
	public JPanel panel;
	
	public AlterMenu(String title) {
		setLayout(null);
		
		TableHeadPane p = new TableHeadPane();
		p.setBounds(0, 0, 800, 150);
		add(p);
		
		panel = new JPanel(null);
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBounds(2, 155, 780, 305);
		setPanel();
		add(panel);
		
		setTitle(title);
		setBounds(550, 200, 800, 500);
		setVisible(true);
	}
	
	public void setPanel() {}
	
	/*
	 * 设置标签格式
	 */
	public void setLabel(JLabel label, int x, int y) {
		label.setBounds(x, y, 180, 50);
		label.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		label.setOpaque(false);
		panel.add(label);
	}
	
	/*
	 * 设置按钮格式
	 */
	public void setButton(JButton submit) {
		submit.setBounds(600, 200, 90, 40);
		submit.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		submit.setForeground(Color.BLUE);
		submit.setOpaque(false);
		submit.setBackground(Color.BLACK);
		panel.add(submit);
	}
	
}
