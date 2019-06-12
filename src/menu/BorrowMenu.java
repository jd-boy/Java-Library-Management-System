package menu;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataManage.AddBorrowBookInform;
import dataManage.FindBookInform;
import dataManage.FindBorrowBookInform;
import dataManage.UpdataBookInform;
import dataManage.User;
import dataManage.updataBorrowBookRecordInform;

public class BorrowMenu extends JPanel {
	
	private JLayeredPane layeredPane;
	private FindBookInform findBookInform = new FindBookInform();
	private AddBorrowBookInform addBorrowBookInform = new AddBorrowBookInform();
	private FindBorrowBookInform findBorrowBookInform = new FindBorrowBookInform();
	private UpdataBookInform updataBookInform = new UpdataBookInform();
	private updataBorrowBookRecordInform updataBorrow = new updataBorrowBookRecordInform();
	
	private JTextField text = null;
	
	/*
	 * 表示检索结果
	 * 		0表示书库中没有此书
	 * 		1表示此书已经外借
	 * 		2表示可以借阅
	 * 		3表示未检索图书
	 */
	private int flag = 3;
	
	private String[] bookID = new String[1];
	private String[] record = new String[6];
	private String[][] bookInform = null;//存储检索的书籍信息
	private JLabel[] label = new JLabel[7];

	public BorrowMenu() {
		
		setLayout(new GridLayout(1, 1));
		
		layeredPane = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(layeredPane.getClass().getResource("/borrow.jpg"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		
		addLabelInform();
		addButton();
		addTextField();
		add(layeredPane);
	}
	
	/*
	 * 添加输入书号的文本框
	 */
	private void addTextField() {
		JTextField text = new JTextField(100);
		text.setBounds(700, 260, 150, 30);
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		text.getDocument().addDocumentListener(new MyDocumentListener1(bookID));
		layeredPane.add(text);
	}
	
	/*
	 * 添加检索、借阅、还书按钮
	 */
	private void addButton() {
		JButton retrieval = new JButton("检索");
		retrieval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bookID[0].trim();
				bookInform = findBookInform.conditionFind(1, bookID);
				if(bookInform == null) {
					flag = 0;
					Message.setPlain("小图已经尽力了，没有查询到"+bookID[0]+"号图书信息", "图书借阅");
				} else if(bookInform[0][7].equals("外借")) {
					flag = 1;
					addBookInformLabel();
					Message.setPlain("小图查询到"+bookID[0]+"号图书已经借出去了呢", "图书借阅");
				} else {
					flag = 2;
					addBookInformLabel();
				}
			}
		});
		setButton(retrieval, 870, 254);
		
		/*
		 * 添加借书按钮
		 */
		JButton borrow = new JButton("借阅");
		borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag == 2) {
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					cal.add(Calendar.MONTH, 1);
					record[0] = bookID[0];
					record[1] = findBookInform.getBookName(Integer.valueOf(bookID[0]));
					record[2] = User.ID;
					record[3] = sdf.format(d);
					record[4] = sdf.format(cal.getTime());
					record[5] = "false";
					updataBookInform.updateIDState(Integer.valueOf(bookID[0]), false);
					addBorrowBookInform.addBorrowBookInform(record);
					flag = 3;
					Message.setPlain("借阅成功，请在"+record[4]+"之前还书", "图书借阅");
				} else if(flag == 0) {
					Message.setPlain("没有检索到"+bookID[0]+"号图书哦", "图书借阅");
				} else if(flag == 1) {
					Message.setPlain("小图偷偷告诉你哦"+bookID[0]+"号图书已经被别人借走了", "图书借阅");
				} else if(flag == 3) {
					Message.setPlain("小图提醒您请先检索书籍哦", "图书借还");
				}
			}
			
		});
		setButton(borrow, 890, 540);
		
		/*
		 * 添加还书按钮
		 */
		JButton returnBookButton = new JButton("还书");
		returnBookButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(flag == 3) {
					Message.setPlain("小图提醒您请先检索书籍哦", "图书借还");
				} else if(flag == 0) {
					Message.setPlain("没有检索到"+bookID[0]+"号图书哦", "图书归还");
				} else if(findBorrowBookInform.findBorrowBook(User.ID, Integer.valueOf(bookID[0]))) {
					updataBookInform.updateIDState(Integer.valueOf(bookID[0]), true);
					updataBorrow.alterBorrowBookState(Integer.valueOf(bookID[0]), true);
					flag = 3;
					Message.setPlain("还书成功", "图书归还");
				} else {
					Message.setPlain("小图查找到您还没有借这本书哦", "图书归还");
				}
			}
		});
		setButton(returnBookButton, 500, 540);
	}
	
	
	/*
	 * 设置按钮格式
	 */
	private void setButton(JButton button, int x, int y) {
		button.setBounds(x, y, 90, 40);
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		button.setForeground(new Color(230, 50, 50));
		button.setOpaque(false);
		button.setBackground(Color.BLACK);
		layeredPane.add(button);
	}
	
	
	
	/*
	 * 添加标签提示信息
	 */
	private void addLabelInform() {
		int x = 600;
		int y = 360;
		String[] str = {"书名：", "作者：", "状态："};
		
		label[0] = new JLabel("请输入书号：");
		setLabelFont(label[0], 500, 250);
		
		for(int i = 1; i < 4; i++) {
			label[i] = new JLabel(str[i-1]);
			setLabelFont(label[i], x, y);
			y += 60;
		}
		
		x = 690;
		y = 360;
		for(int i = 4; i < 7; i++) {
			label[i] = new JLabel();
			setLabelFont(label[i], x, y);
			y += 60;
		}
	}
	
	/*
	 * 添加检索到的书籍的信息
	 */
	private void addBookInformLabel() {
		label[4].setText(bookInform[0][2]);
		
		label[5].setText(bookInform[0][3]);
		
		label[6].setText(bookInform[0][7]);
	}
	
	/*
	 * 设置标签的格式
	 */
	private void setLabelFont(JLabel l, int x, int y) {
		l.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		l.setBounds(x, y, 450, 50);
		l.setOpaque(false);
		layeredPane.add(l);
	}
	
}
