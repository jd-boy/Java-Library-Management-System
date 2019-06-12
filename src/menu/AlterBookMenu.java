package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataManage.FindBookInform;
import dataManage.UpdataBookInform;

public class AlterBookMenu extends AlterMenu {
	private UpdataBookInform updata = new UpdataBookInform();
	private FindBookInform find = new FindBookInform();

	private String title;
	private String[] name;
	private String[] record;
	
	private JComboBox generComBox;
	private JComboBox bookstoreComBox;
	private JComboBox stateComBox;
	
	public AlterBookMenu(String title) {
		super(title);
		this.title = title;
	}
	
	public void setPanel() {
		int x = 100, y = 10;
		record = new String[8];
		String[] str = {"书   号：", "作   者：", "单   价：","类   别：", "书   名：", "出 版 社：", "状   态：", "书   库："};
		
		Font font = new Font("楷体", Font.CENTER_BASELINE, 20);
		
		for(int i = 0; i < str.length; i++) {
			record[i] = null;
			JLabel label = new JLabel(str[i]);
			setLabel(label, x, y);
			
			if(i != 3 && i != 7 && i != 6) {
				JTextField text = new JTextField(100);
				text.getDocument().addDocumentListener(new MyDocumentListener(i, record));
				text.setBounds(x+120, y+13, 150, 30);
				text.setFont(font);
				panel.add(text);
			}
			
			y += 50;
			if(i == 3) {
				x = 430;
				y = 10;
			}
		}
		addComboBox();
		addButton();
	}
	
	public void addComboBox() {
		//类别下拉框
		MainMenu.readBookGenre();//从数据库中读取图书类别
		generComBox = new JComboBox();
		generComBox.addItem("不修改");
		for(String s : MainMenu.bookGenre) {
			generComBox.addItem(s);
		}
		generComBox.setFont(new Font("楷体", Font.CENTER_BASELINE, 22));
		generComBox.setBounds(220, 170, 140, 35);
		generComBox.setOpaque(false);
		panel.add(generComBox);
		
		//馆藏下拉框
		String[] str2 = {"不修改", "实训楼A604", "资讯楼514"};
		bookstoreComBox = new JComboBox();
		for(String s : str2) {
			bookstoreComBox.addItem(s);
		}
		bookstoreComBox.setFont(new Font("楷体", Font.CENTER_BASELINE, 22));
		bookstoreComBox.setBounds(550, 170, 140, 35);
		bookstoreComBox.setOpaque(false);
		panel.add(bookstoreComBox);
		
		//在馆状态下拉框
		stateComBox = new JComboBox();
		stateComBox.addItem("不修改");
		stateComBox.addItem("在馆");
		stateComBox.addItem("外借");
		stateComBox.setFont(new Font("楷体", Font.CENTER_BASELINE, 22));
		stateComBox.setBounds(550, 120, 140, 35);
		stateComBox.setOpaque(false);
		panel.add(stateComBox);
		
	}
	
	private void addButton() {
		JButton submit = new JButton("修改");
		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				record[3] = generComBox.getSelectedItem().toString();
				if(record[3].equals("不修改")) {
					record[3] = null;
				}
				
				record[6] = stateComBox.getSelectedItem().toString();
				if(record[6].equals("不修改")) {
					record[6] = null;
				} else if(record[6].equals("在馆")) {
					record[6] = "true";
				} else {
					record[6] = "false";
				}
				
				record[7] = bookstoreComBox.getSelectedItem().toString();
				if(record[7].equals("不修改")) {
					record[7] = null;
				}
				if((record[0] == null || record[0].length() == 0) && (record[4] == null || record[4].length() == 0)) {
					Message.setPlain("请输入要修改的书号/书名", title);
				} else if(record[4] != null && record[4].length()>0 && find.getBookCount(record[4]) <= 0) {
					Message.setPlain("没有查询到 "+record[4]+" 的信息", title);
				} else if(record[0] != null && record[0].trim().length()>0 && find.getBookName(Integer.valueOf(record[0].trim())) == null) {
					Message.setPlain("没有查询到 "+record[0]+" 号图书的信息", title);
				} else if(record[0] != null && record[0].trim().length() > 0) {
					for(int i = 1; i < record.length; i++) {
						if(record[i] != null && record[i].length() > 0) {
							updata.updateIDInform(record[0], record[i], i);
						}
					}
					Message.setPlain("修改成功", title);
				} else {
					for(int i = 1; i < record.length; i++) {
						if(i != 4 && record[i] != null && record[i].length() > 0) {
							updata.updateNameInform(record[4], record[i], i);
						}
					}
					Message.setPlain("修改成功", title);
				}
			}
		});
		submit.setBounds(350, 240, 90, 40);
		submit.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		submit.setForeground(Color.BLUE);
		submit.setOpaque(false);
		submit.setBackground(Color.BLACK);
		panel.add(submit);
	}
}
