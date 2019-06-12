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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataManage.ConnectDataBase;
import dataManage.DatabaseUser;

public class DatebaseSwitch extends JFrame implements ActionListener {
	
	private JPanel panel;
	private String title = null;
	private JButton schoolHost = null;
	private JButton localHost = null;
	private JButton submit = null;
	private JButton cancel = null;
	private JLabel userLabel = null;
	private JLabel passwordLabel = null;
	private JTextField user = null;
	private JPasswordField password = null;

	public DatebaseSwitch(String title) {
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
	
	public void setPanel() {
		schoolHost = new JButton("校园网连接");
		schoolHost.setBounds(280, 80, 170, 40);
		schoolHost.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		schoolHost.setForeground(Color.BLUE);
		schoolHost.setOpaque(false);
		schoolHost.setBackground(Color.BLACK);
		schoolHost.addActionListener(this);
		panel.add(schoolHost);
		
		
		localHost = new JButton("本地连接");
		localHost.setBounds(280, 130, 170, 40);
		localHost.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		localHost.setForeground(Color.BLUE);
		localHost.setOpaque(false);
		localHost.setBackground(Color.BLACK);
		localHost.addActionListener(this);
		panel.add(localHost);
	}
	
	private void local() {
		userLabel = new JLabel("账号：");
		setLabel(userLabel, 210, 70);
		
		passwordLabel = new JLabel("密码：");
		setLabel(passwordLabel, 210, 120);
		
		user = new JTextField(100);
		user.setBounds(310, 80, 200, 35);
		user.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		user.setOpaque(false);
		panel.add(user);
		
		password = new JPasswordField(100);
		password.setBounds(310, 130, 200, 35);
		password.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
		password.setOpaque(false);
		panel.add(password);
		
		submit = new JButton("确定");
		submit.addActionListener(this);
		submit.setBounds(600, 200, 90, 40);
		submit.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		submit.setForeground(Color.BLUE);
		submit.setOpaque(false);
		submit.setBackground(Color.BLACK);
		panel.add(submit);
		
		cancel = new JButton("返回");
		cancel.addActionListener(this);
		cancel.setBounds(500, 200, 90, 40);
		cancel.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		cancel.setForeground(Color.BLUE);
		cancel.setOpaque(false);
		cancel.setBackground(Color.BLACK);
		panel.add(cancel);
	}
	
	/*
	 * 设置标签格式
	 */
	public void setLabel(JLabel label, int x, int y) {
		label.setBounds(x, y, 180, 50);
		label.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		label.setOpaque(false);
		panel.add(label);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("校园网连接")) {
			Message.setPlain("更改数据库成功", title);
			DatabaseUser.sshhost();
		} else if(e.getActionCommand().equals("本地连接")) {
			panel.removeAll();
			local();
			panel.updateUI();
		} else if(e.getActionCommand().equals("确定")) {
			String url = "jdbc:mysql://localhost:3306/mysql?useSSL=true&characterEncoding=utf-8";
			String u = user.getText().trim();
			String p = new String(password.getPassword()).trim();
			if(new ConnectDataBase().connectTest(url, u, p)) {
				DatabaseUser.localhost(u, p);
				Message.setPlain("连接成功", title);
			} else {
				DatabaseUser.sshhost();
				Message.setPlain("连接失败", title);
			}
		} else if(e.getActionCommand().equals("返回")) {
			panel.removeAll();
			setPanel();
			panel.updateUI();
		}

	}
	

}
