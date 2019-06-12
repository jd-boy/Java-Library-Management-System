package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataManage.FindUserInform;
import dataManage.UpdataUserInform;

public class AlterJurisdictionMenu extends AlterMenu {
	private FindUserInform find = new FindUserInform();
	private UpdataUserInform updata = new UpdataUserInform();
	
	private String title;

	public AlterJurisdictionMenu(String title) {
		super(title);
		this.title = title;
	}
	
	public void setPanel() {
		String[] user = new String[1];
		
		JLabel label = new JLabel("输入账号：");
		setLabel(label, 200, 80);
		
		JTextField text = new JTextField(50);
		text.getDocument().addDocumentListener(new MyDocumentListener1(user));
		text.setBounds(350, 92, 150, 30);
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		panel.add(text);
		
		JButton submit = new JButton("添加");
		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				user[0] = user[0].trim();
				if(user[0].length()>0 && find.getUserPassword(user[0]) != null) {
					updata.alterUserJurisdiction(user[0], true);
					Message.setPlain("添加权限成功", title);
				} else {
					Message.setPlain("小图提醒您，请正确输入账号哦", title);
				}
			}
		});
		setButton(submit);
		
		JButton del = new JButton("删除");
		del.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				user[0] = user[0].trim();
				if(user[0].length()>0 && find.getUserPassword(user[0]) != null) {
					updata.alterUserJurisdiction(user[0], false);
					Message.setPlain("删除权限成功", title);
				} else {
					Message.setPlain("小图提醒您，请正确输入账号哦", title);
				}
			}
		});
		del.setBounds(500, 200, 90, 40);
		del.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		del.setForeground(Color.BLUE);
		del.setOpaque(false);
		del.setBackground(Color.BLACK);
		panel.add(del);
	}
	
}
