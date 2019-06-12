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

import dataManage.User;

public class AccountNumberAdminMenu extends JPanel{
	
	private JLayeredPane layered;
	private JButton alterButton;
	private JButton BorrowBookRecordButton;
	private JButton exitSignButton;
	private JButton bookAdminButton;
	private JButton alterJurisButton;
	private JButton alterPasswdButton;
	private JButton switchDatebaseUser;
	
	public AccountNumberAdminMenu() {
		
		setLayout(new GridLayout(1, 1));
		
		layered = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(layered.getClass().getResource("/account.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		
		addUserButton();
		if(User.ADMINISTRATORS) {
			addAdminButton();
		}
		
		add(layered);
		
	}
	
	/*
	 * 普通用户显示的界面
	 */
	private void addUserButton() {
		alterButton = new JButton("修改账号");
		setButtonFormat(alterButton, 750, 300);
		alterButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AlterUserMenu("修改账号");
			}
		});
		layered.add(alterButton);
		
		BorrowBookRecordButton = new JButton("借书记录");
		setButtonFormat(BorrowBookRecordButton, 750, 360);
		BorrowBookRecordButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(User.ADMINISTRATORS) {
					new AdminBorrowRecord();
				} else {
					new BorrowRecord();
				}
			}
		});
		layered.add(BorrowBookRecordButton);
		
		exitSignButton = new JButton("退出登录");
		setButtonFormat(exitSignButton, 750, 420);
		exitSignButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainMenu.addSignMenu();
			}
		});
		layered.add(exitSignButton);
		
	}
	
	/*
	 * 管理员显示的界面
	 */
	private void addAdminButton() {
		
		exitSignButton.setBounds(750, 600, 200, 50);
		
		bookAdminButton = new JButton("图书管理");
		setButtonFormat(bookAdminButton, 750, 420);
		bookAdminButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainMenu.addBookAdminMenu();
			}
		});
		layered.add(bookAdminButton);
		
		alterJurisButton = new JButton("修改权限");
		setButtonFormat(alterJurisButton, 750, 480);
		alterJurisButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AlterJurisdictionMenu("修改权限");
			}
		});
		layered.add(alterJurisButton);
		
		alterPasswdButton = new JButton("修改密码");
		setButtonFormat(alterPasswdButton, 750, 540);
		alterPasswdButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new AlterPassword("修改密码");
			}
		});
		layered.add(alterPasswdButton);
		
		switchDatebaseUser = new JButton("切换数据库");
		setButtonFormat(switchDatebaseUser, 960, 300);
		switchDatebaseUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DatebaseSwitch("切换数据库");
			}
		});
		layered.add(switchDatebaseUser);
	}
	
	/*
	 * 设置按钮格式
	 */
	private void setButtonFormat(JButton button, int x, int y) {
		button.setOpaque(false);
		button.setBackground(Color.BLACK);
		button.setBounds(x, y, 200, 50);
		button.setForeground(new Color(200, 50, 150));
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
	}
	
}
