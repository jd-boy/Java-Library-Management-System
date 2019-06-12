package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataManage.FindUserInform;
import dataManage.UpdataUserInform;

public class AlterPassword extends AlterMenu {

	private FindUserInform find = new FindUserInform();
	private UpdataUserInform updata = new UpdataUserInform();
	
	private String[] user;
	private String[] passwd;
	
	public AlterPassword(String title) {
		super(title);
	}
	
	public void setPanel() {
		addComponent();
	}
	
	/*
	 * 添加容器
	 */
	private void addComponent() {
		user = new String[1];
		passwd = new String[1];
		
		//添加提示信息标签
		JLabel userLabel = new JLabel("输入账号：");
		JLabel passwdLabel = new JLabel("输入密码：");
		
		setLabel(userLabel, 210, 66);
		setLabel(passwdLabel, 210, 126);
		
		//添加输入文本框
		JTextField userText = new JTextField(100);
		userText.setBounds(350, 80, 150, 30);
		userText.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		userText.getDocument().addDocumentListener(new MyDocumentListener1(user));
		panel.add(userText);
		
		JTextField passwdText = new JTextField(100);
		passwdText.setBounds(350, 138, 150, 30);
		passwdText.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		passwdText.getDocument().addDocumentListener(new MyDocumentListener1(passwd));
		panel.add(passwdText);
		
		addButton();
		
	}
	
	/*
	 * 添加修改按钮
	 */
	private void addButton() {
		JButton submit = new JButton("修改");
		
		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String oldPassword = null;
				user[0] = user[0].trim();
				if(passwd[0].length() >= 6) {
					oldPassword = find.getUserPassword(user[0]);
					if(oldPassword == null) {
						Message.setPlain("未查询到"+user[0]+"账号的信息", "修改密码");
					} else if(oldPassword.equals(passwd[0])) {
						Message.setPlain("小图提醒，新密码不能与旧密码相同哦", "修改密码");
					} else {
						updata.alterPassword(user[0], passwd[0]);
						Message.setPlain("修改成功", "修改密码");
					}
				} else {
					Message.setPlain("为保证您的账号安全，密码长度请大于6位", "修改密码");
				}
			}
		});
		
		setButton(submit);
		
	}
		
}
