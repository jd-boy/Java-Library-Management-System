package menu;

/*
 * 实现了登录的功能
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataManage.FindUserInform;
import dataManage.User;

public class SignMenu extends JPanel {

	private FindUserInform findUser = new FindUserInform();
	
	//密码框
	private JPasswordField passwd = null;
	
	private String user[] = new String[1];
	private String password = null;
	private JPanel panel;
	
	public SignMenu() {
		user[0] = null;
		
		Box box = Box.createHorizontalBox();
		setLayout(new GridLayout(1, 1));
		
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(panel.getClass().getResource("/sign.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		panel.setLayout(null);
		
		init(box);
		panel.add(box);
		
		Font font = new Font("楷体", Font.CENTER_BASELINE, 20);
		JButton sign = new JButton("登录");
		sign.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				password = new String(passwd.getPassword()).trim();
				user[0] = user[0].trim();
				
				int flag = findUser.findUser(user[0], password);
				
				if(flag == 1) {
					User.state = true;
					if(User.ADMINISTRATORS && !User.FLAG) {
						MainMenu.addButton();
						User.FLAG = true;
					} else if(!User.ADMINISTRATORS && User.FLAG){
						MainMenu.removeButton();
						User.FLAG = false;
					}
					MainMenu.removeSignMenu();
				} else if(flag == 2){
					Message.setInformation("此用户未注册", "账号登录");
				} else {
					Message.setInformation("账号/密码错误", "账号登录");
				}
			}
			
		});
		sign.setFont(font);
		sign.setForeground(Color.WHITE);
		sign.setBackground(Color.RED);
		sign.setBounds(200, 400, 200, 40);
		panel.add(sign);
		
		add(panel);
	}
	
	private void init(Box box) {
		JLabel label;
		JTextField text;
		
		Box boxV1 = Box.createVerticalBox();
		Box boxV2 = Box.createVerticalBox();
		
		label = new JLabel("账号：");
		label.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		boxV1.add(label);
		boxV1.add(Box.createVerticalStrut(10));
		label = new JLabel("密码：");
		label.setFont(new Font("楷体", Font.CENTER_BASELINE, 30));
		boxV1.add(label);
		
		text = new JTextField(100);
		text.getDocument().addDocumentListener(new MyDocumentListener1(user));
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		boxV2.add(text);
		
		passwd = new JPasswordField(100);
		passwd.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		boxV2.add(passwd);
		
		box.add(boxV1);
		box.add(boxV2);
		
		box.setBounds(150, 200, 300, 80);
		
	}
	
}
