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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dataManage.AddRegulationsInform;
import dataManage.User;

public class BriefIntr extends JPanel implements ActionListener{
	
	private JPanel panel;
	private JButton edit;
	private JButton complete;
	private JTextArea textArea;
	private AddRegulationsInform addRegulations = new AddRegulationsInform();
	
	public BriefIntr() {
		
		setLayout(new GridLayout(1, 1));
		
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(panel.getClass().getResource("/briefintr.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		panel.setLayout(null);
		addTextArea();
		add(panel);
	}
	
	private void addTextArea() {
		String str = addRegulations.getRegulations();
		textArea = new JTextArea(str);
		textArea.setEditable(false);//设置文本不能被修改
		textArea.setWrapStyleWord(true);//设置断行不断字
		textArea.setLineWrap(true);//设置文本域自动换行
		textArea.setOpaque(false);
		textArea.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		textArea.setBounds(500, 200, 750, 500);
		panel.add(textArea);
	}
	
	public void addButton() {
		Font font = new Font("楷体", Font.CENTER_BASELINE, 25);
		
		edit = new JButton("修改");
		edit.addActionListener(this);
		edit.setForeground(new Color(255, 100, 255));
		edit.setFont(font);
		edit.setBounds(1200, 700, 100, 60);
		edit.setBackground(Color.BLUE);
		edit.setOpaque(false);
		panel.add(edit);
	}
	
	public void removeButton() {
		panel.remove(edit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(edit.getText().equals("修改")) {
			edit.setText("完成");
			textArea.setEditable(true);
		} else if(edit.getText().equals("完成")){
			edit.setText("修改");
			textArea.setEditable(false);
			addRegulations.setRegulations(textArea.getText());
		}
	}
}
