package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataManage.AlterGenreInform;
import dataManage.FindGenreInform;

public class AlterGenreMenu extends AlterMenu{
	private AlterGenreInform alter = new AlterGenreInform();
	private FindGenreInform find = new FindGenreInform();
	
	private String title;
	
	public AlterGenreMenu(String title) {
		super(title);
		this.title = title;
	}

	public void setPanel() {
		String[] oldGenre = new String[1];
		String[] newGenre = new String[1];
		JLabel label = new JLabel("请输入类名：");
		setLabel(label, 230, 60);
		
		JLabel label2 = new JLabel("修改类名为：");
		setLabel(label2, 230, 120);
		
		JTextField text = new JTextField(50);
		text.getDocument().addDocumentListener(new MyDocumentListener1(oldGenre));
		text.setBounds(390, 73, 150, 30);
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		panel.add(text);
		
		JTextField text2 = new JTextField(50);
		text2.getDocument().addDocumentListener(new MyDocumentListener1(newGenre));
		text2.setBounds(390, 133, 150, 30);
		text2.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		panel.add(text2);
		
		JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(oldGenre[0] == null) {
					Message.setPlain("请输入要添加的类别名", title);
				} else if(!find.judgeExit(oldGenre[0])) {
					alter.addGenre(oldGenre[0]);
					MainMenu.readBookGenre();//从数据库中获取图书类别
					Message.setPlain("添加"+oldGenre[0]+"成功", title);
				} else {
					Message.setPlain("该类别已存在", title);
				}
			}
		});
		setButton(addButton, 270, 190);
		
		JButton alterButton = new JButton("修改");
		alterButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(oldGenre[0] == null) {
					Message.setPlain("请输入要修改的类别名", title);
				} else if(newGenre[0] == null) {
					Message.setPlain("请输入新的类别名", title);
				} else if(newGenre[0].equals(oldGenre[0])) {
					Message.setPlain("新的类别名与修改前的重复", title);
				} else if(find.judgeExit(oldGenre[0])) {
					alter.alterGenre(oldGenre[0], newGenre[0]);
					MainMenu.readBookGenre();//从数据库中获取图书类别
					Message.setPlain("修改成功", title);
				} else {
					Message.setPlain(oldGenre[0]+"不存在", title);
				}
			}
		});
		setButton(alterButton, 420, 190);
	}
	
	/*
	 * 设置按钮格式
	 */
	public void setButton(JButton submit, int x, int y) {
		submit.setBounds(x, y, 90, 40);
		submit.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		submit.setForeground(Color.BLUE);
		submit.setOpaque(false);
		submit.setBackground(Color.BLACK);
		panel.add(submit);
	}
}
