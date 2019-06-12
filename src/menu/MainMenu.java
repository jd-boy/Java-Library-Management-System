package menu;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import dataManage.CreateDataBase;
import dataManage.FindGenreInform;
import dataManage.FindUserInform;

public class MainMenu extends JFrame {
	private static FindGenreInform findGenre = new FindGenreInform();
	
	private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public static String[] bookGenre;//存储所有图书类别
	public static Boolean flag = false;//标记是否存在管理员账号，不存在为false，否则为true
	
	private MainInterface mainInter = new MainInterface();
	private FindMenu findMenu = new FindMenu();
	private static BriefIntr briefIntr = new BriefIntr();
	private static BorrowMenu borrowMenu = null;
	private static AdminMenu adminMenu = new AdminMenu();
	private static RegisterMenu registerMenu = new RegisterMenu();
	private static SignMenu signMenu = new SignMenu();
	private static AccountNumberAdminMenu accNumAdmin = null;
	
	public MainMenu() {
		
		new CreateDataBase();//如果没有数据库则创建数据库
		addMainMenu(); 
		
		
		setTitle("图书管理系统");
		setIconImage(new ImageIcon(this.getClass().getResource("/desktop.jpg")).getImage());
		setBounds(200, 50, 1500, 900);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
		
		judgeAdministrators();
		
	}
	
	/*
	 * 给管理员添加修改规章制度的按钮
	 */
	public static void addButton() {
		briefIntr.addButton();
	}
	
	/*
	 * 移除修改规章制度按钮
	 */
	public static void removeButton() {
		briefIntr.removeButton();
	}
	
	/*
	 * 从数据库中读取图书类别
	 */
	public static void readBookGenre() {
		bookGenre = findGenre.findAllGenre();
	}
	
	/*
	 * 判断若数据库中不存在管理员账号则flag为false，否则为true
	 */
	private void judgeAdministrators() {
		FindUserInform find = new FindUserInform();
		if(find.findAllUser() == null) {
			flag = false;
		} else {
			flag = true;
		}
	}
	
	private void addMainMenu() {
		
		tabbedPane.add("主界面", mainInter);
		tabbedPane.add("图书管理规定", briefIntr);
		tabbedPane.add("图书查询", findMenu);
		tabbedPane.add("注册", registerMenu);
		tabbedPane.add("登录", signMenu);
		add(tabbedPane);
		
	}
	
	/*
	 * 在登录成功后关闭登录和注册菜单，并添加账号管理界面
	 */
	public static void removeSignMenu() {
		tabbedPane.remove(registerMenu);
		tabbedPane.remove(signMenu);
		
		borrowMenu = new BorrowMenu();
		accNumAdmin = new AccountNumberAdminMenu();
		tabbedPane.add("图书借还", borrowMenu);
		tabbedPane.add("账号管理", accNumAdmin);
		jumpCard(2);
	}
	
	/*
	 * 退出登录后添加登录和注册界面，关闭账号管理界面，
	 * 若图书管理界面打开，则将其一起关闭
	 */
	public static void addSignMenu() {
		tabbedPane.remove(borrowMenu);
		tabbedPane.remove(accNumAdmin);
		if(tabbedPane.indexOfComponent(adminMenu) != -1) {
			tabbedPane.remove(adminMenu);
		}
		tabbedPane.add("注册", registerMenu);
		tabbedPane.add("登录", signMenu);
		jumpCard(0);
	}
	
	/*
	 * 添加图书管理界面
	 */
	public static void addBookAdminMenu() {
		tabbedPane.add("图书管理", adminMenu);
		jumpCard(5);
	}
	
	/*
	 * 页面跳转
	 */
	public static void jumpCard(int index) {
		tabbedPane.setSelectedIndex(index);
	}
	
	
}
