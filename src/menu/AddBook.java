package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import dataManage.AddBookInform;
import dataManage.FindGenreInform;

public class AddBook extends JFrame{
	private FindGenreInform findGenre = new FindGenreInform();
	
	public AddBook() {
		setLayout(null);
		TableHeadPane panel = new TableHeadPane();
		panel.setBounds(0, 0, 1000, 200);
		add(panel);
		addInform();
		setTitle("添加图书");
		setBounds(550, 200, 1018, 690);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//输入信息后，将信息存入inform中
	private void addInform() {
		AddBookInform addBook = new AddBookInform();
		
		int x = 150, y = 50;
		String[] str = {"图书类别：", "图书名称：", "图书作者：", "出 版 社：",
				 		"馆    藏：", "单    价：", "数    量："};
		String[] inform = new String[8];
		JTextField[] text = new JTextField[str.length];
		
		JPanel p = new JPanel(null);
		
		//添加类别
		MainMenu.readBookGenre();//从数据库中读取图书类别
		JComboBox gener = new JComboBox(MainMenu.bookGenre);
		gener.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		gener.setBounds(280, 65, 150, 35);
		gener.setOpaque(false);
		p.add(gener);
		
		//添加馆藏
		String[] s1 = {"资讯楼514", "实训楼A604", "资讯楼503"};
		JComboBox local = new JComboBox(s1);
		local.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		local.setBounds(630, 65, 150, 35);
		local.setOpaque(false);
		p.add(local);
		
		for(int i = 0; i < str.length; i++) {
			JLabel label = new JLabel(str[i]);
			label.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
			label.setBounds(x, y, 200, 60);
			
			if(i != 0 && i != 4) {
				text[i] = new JTextField(50);
				text[i].getDocument().addDocumentListener(new MyDocumentListener(i, inform));
				text[i].setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
				text[i].setBounds(x+130, y+15, 150, 35);
				p.add(text[i]);
			}
			
			y += 50;
			
			p.add(label);
			
			if(i == 3) {
				x = 500;
				y = 50;
			}
		}
		p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "添加图书信息", TitledBorder.LEFT,TitledBorder.TOP,new Font("楷体",1,25)));
		p.setBounds(0, 200, 1000, 450);
		
		//添加确定按钮，确定最终的数据
		JButton button = new JButton("确定");
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		button.setBounds(450, 350, 80, 50);
		button.setBackground(new Color(100, 150, 200));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inform[0] = gener.getSelectedItem().toString();
				inform[0] = findGenre.getID(inform[0]);
				inform[4] = local.getSelectedItem().toString();
				addBook.addInform(inform);
				Message.setPlain("添加成功", "添加图书");
			}
		});
		
		p.add(button);
		
		add(p);
	}
}
