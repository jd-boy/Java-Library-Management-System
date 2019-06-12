package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dataManage.FindUserInform;
import dataManage.UpdataUserInform;
import dataManage.User;

public class AlterUserMenu extends AlterMenu {
	
	private UpdataUserInform updata = new UpdataUserInform();
	
	private JRadioButton radioMan;
	private JRadioButton radioFemale;
	private String title;
	private String[] name;
	private String[] classes;
	private String[] email;
	private String sex;
	private String[] inform;

	public AlterUserMenu(String title) {
		super(title);
		this.title = title;
	}
	
	public void setPanel() {
		name = new String[1];
		classes = new String[1];
		email = new String[1];
		
		name[0] = classes[0] = email[0] = sex = null;
		
		Font font = new Font("楷体", Font.CENTER_BASELINE, 20);
		
		JLabel userLabel = new JLabel("账号：");
		JLabel idLabel = new JLabel(User.ID);
		JLabel nameLabel = new JLabel("姓名：");
		JLabel classLabel = new JLabel("班级：");
		JLabel emailLabel = new JLabel("邮箱：");
		JLabel sexLabel = new JLabel("性别：");
		
		setLabel(idLabel, 330, 40);
		setLabel(userLabel, 235, 40);
		setLabel(nameLabel, 235, 80);
		setLabel(classLabel, 235, 120);
		setLabel(emailLabel, 235, 160);
		setLabel(sexLabel, 235, 200);
		
		
		JTextField nameText = new JTextField(User.NAME);
		nameText.setColumns(50);
		nameText.getDocument().addDocumentListener(new MyDocumentListener1(name));
		nameText.setBounds(330, 92, 150, 30);
		nameText.setFont(font);
		panel.add(nameText);
		
		JTextField classText = new JTextField(User.CLASS);
		classText.setColumns(50);
		classText.getDocument().addDocumentListener(new MyDocumentListener1(classes));
		classText.setBounds(330, 132, 150, 30);
		classText.setFont(font);
		panel.add(classText);
		
		JTextField emailText = new JTextField(User.EMAIL);
		emailText.setColumns(50);
		emailText.getDocument().addDocumentListener(new MyDocumentListener1(email));
		emailText.setBounds(330, 172, 150, 30);
		emailText.setFont(font);
		panel.add(emailText);
		
		//添加复选框，选择性别
		ButtonGroup group = new ButtonGroup();
		radioMan = new JRadioButton("男");
		radioFemale = new JRadioButton("女");
		radioMan.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		radioFemale.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		if(User.SEX.equals("男")) {
			radioMan.setSelected(true);
		} else {
			radioFemale.setSelected(true);
		}
		
		group.add(radioMan);
		group.add(radioFemale);
		
		radioMan.setBounds(330, 212, 50, 35);
		radioFemale.setBounds(430, 212, 50, 35);
		
		radioMan.setOpaque(false);
		radioFemale.setOpaque(false);
		panel.add(radioMan);
		panel.add(radioFemale);
		
		addButton();
		
	}
	
	public void addButton() {
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(radioMan.isSelected()) {
					sex = radioMan.getText();
				} else if(radioFemale.isSelected()) {
					sex = radioFemale.getText();
				}
				if(name[0] != null && name[0].length() > 0 && !name[0].equals(User.NAME)) {
					User.NAME = name[0];
					updata.alterUserName(User.ID, name[0]);
				}
				if(classes[0] != null && classes[0].length() > 0 && !classes[0].equals(User.CLASS)) {
					User.CLASS = classes[0];
					updata.alterUserClass(User.ID, classes[0]);
				}
				if(email[0] != null && email[0].length() > 0 && !email[0].equals(User.EMAIL)) {
					User.EMAIL = email[0];
					updata.alterUserEmail(User.ID, email[0]);
				}
				if(sex != null && !sex.equals(User.SEX)) {
					User.SEX = sex;
					updata.alterUserSex(User.ID, sex);
				}
				Message.setPlain("修改成功", title);
			}
		});
		button.setBounds(600, 210, 90, 40);
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		button.setForeground(Color.BLUE);
		button.setOpaque(false);
		button.setBackground(Color.BLACK);
		panel.add(button);
	}
	

}
