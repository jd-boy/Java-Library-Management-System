package menu;

/*
 * 实现了注册账号的功能
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dataManage.AddUserInform;
import dataManage.FindUserInform;

public class RegisterMenu extends JPanel {
	private AddUserInform addUser = new AddUserInform();
	private FindUserInform find = new FindUserInform();
	
	private JLayeredPane panel;
	private JPanel p = new JPanel(null);
	private JTextField[] text = new JTextField[7];
	private JRadioButton radioMan = null;
	private JRadioButton radioFemale = null;
	private JPasswordField password;
	JPasswordField repeatPassword;
	
	private String[] user = new String[7];//存储新注册的账号信息
	
	public RegisterMenu() {
		
		setLayout(new GridLayout(1, 1));
		
		panel = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(panel.getClass().getResource("/register.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		
		init();
		panel.add(p);
		add(panel);
	}
	
	private void init() {
		int x = 20, y = 55;
		String[] str = {"账   号：", "姓   名：", "班   级：", "邮   箱：", "密   码：", "重复密码："};
		for(int i = 0, j = 0; i < str.length; i++) {
				
			JLabel label = new JLabel(str[i]);
			label.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
			label.setBounds(100, x, 300, 100);
			x += 40;
			p.add(label);
			
			if(i <= 3) {
				text[j] = new JTextField(100);
				text[j].getDocument().addDocumentListener(new MyDocumentListener(j, user));
				text[j].setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
				text[j].setBounds(250, y, 200, 35);
				y += 40;
				p.add(text[j]);
				j++;
			}
			
			if(i == 3) {
				bulidRadio(x, y);
				x += 45;
				y += 45;
			}
		}
		
		addPasswdField();
		addButton();
		
		p.setOpaque(false);
		p.setBounds(800, 80, 500, 400);
	}
	
	/*
	 * 添加密码框
	 */
	private void addPasswdField() {
		password = new JPasswordField(100);
		password.setEchoChar('*');
		password.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		password.setBounds(250, 260, 200, 35);
		p.add(password);
		
		repeatPassword = new JPasswordField(100);
		repeatPassword.setEchoChar('*');
		repeatPassword.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		repeatPassword.setBounds(250, 301, 200, 35);
		p.add(repeatPassword);
	}
	/*
	 * 添加提交按钮，将注册信息写入数据库
	 */
	private void addButton() {
		JButton button = new JButton("提交");
		button.setBounds(370, 350, 80, 50);
		button.setBackground(new Color(2, 2, 2));
		button.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		button.setOpaque(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(judgeInformation()) {
					user[6] = "false";
					if(!MainMenu.flag) {
						user[6] = "true";
						MainMenu.flag = true;
					}
					addUser.importData(user);
					Message.setInformation("注册成功", "账号注册");
					MainMenu.jumpCard(4);
				}
			}
			
		});
		
		p.add(button);
	}
	
	/*
	 * 判断输入的信息是否正确
	 */
	private boolean judgeInformation() {
		Boolean flag = false;
		
		if(radioMan.isSelected()) {
			user[4] = radioMan.getText();
		} else {
			user[4] = radioFemale.getText();
		}
		
		user[5] = new String(password.getPassword()).trim();
		user[6] = new String(repeatPassword.getPassword()).trim();
		
		if(find.getUserPassword(user[0]) != null) {
			Message.setPlain("该账号已存在", "注册账号");
			return false;
		}
		
		for(int i = 0; i < 4; i++) {
			text[i].setBackground(new Color(255, 255, 255));
			if(user[i] == null || user[i].length() == 0) {
				text[i].setBackground(new Color(255, 255, 200));
				flag = true;
			}
		}
		if(flag) {
			Message.setInformation("数值不能为空", "注册账号");
			return false;
		}
		
		if(user[5].length() < 6 || user[5] == null) {
			Message.setInformation("密码长度需大于6", "注册账号");
			return false;
		}
		
		if(user[5].compareTo(user[6]) != 0) {
			password.setBackground(new Color(255, 255, 200));
			repeatPassword.setBackground(new Color(255, 255, 200));
			Message.setInformation("两次密码不一致", "注册账号");
			return false;
		}
		
		
		return true;
	}
	
	/*
	 * 添加复选框
	 */
	private void bulidRadio(int x, int y) {
		ButtonGroup group = new ButtonGroup();
		radioMan = new JRadioButton("男");
		radioFemale = new JRadioButton("女");
		radioMan.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		radioFemale.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		
		group.add(radioMan);
		group.add(radioFemale);
		
		radioMan.setBounds(270, y, 50, 35);
		radioFemale.setBounds(370, y, 50, 35);
		
		radioMan.setOpaque(false);
		radioFemale.setOpaque(false);
		p.add(radioMan);
		p.add(radioFemale);
		
		JLabel label = new JLabel("性   别：");
		label.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
		label.setBounds(100, x, 300, 100);
		p.add(label);
		
	}
	
}
