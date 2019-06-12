package menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataManage.DelBook;
import dataManage.FindBookInform;

public class DelBookMenu extends AlterMenu {
	private DelBook del = new DelBook();
	private FindBookInform find = new FindBookInform();
	
	private JComboBox comBox;
	
	private String[] value;
	private String title;
	
	public DelBookMenu(String title) {
		super(title);
		this.title = title;
	}
	
	public void setPanel() {
		value = new String[1];
		value[0] = null;
		//添加标签
		JLabel typeLabel = new JLabel("删除方式：");
		JLabel valueLabel = new JLabel("关 键 字：");
		
		setLabel(typeLabel, 210, 66);
		setLabel(valueLabel, 210, 126);
		
		addComBox();
		
		JTextField text = new JTextField(100);
		text.setBounds(350, 137, 150, 30);
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 20));
		text.getDocument().addDocumentListener(new MyDocumentListener1(value));
		panel.add(text);
		
		addSubmitButton();
	}
	
	/*
	 * 添加选择删除方式的下拉框
	 */
	private void addComBox() {
		comBox = new JComboBox();
		String[] str = {"按书名删除", "按书号删除"};
		for(int i = 0; i < str.length; i++) {
			comBox.addItem(str[i]);
		}
		comBox.setFont(new Font("楷体", Font.CENTER_BASELINE, 22));
		comBox.setBounds(350, 75, 140, 35);
		comBox.setOpaque(false);
		panel.add(comBox);
	}
	
	/*
	 * 添加修改按钮
	 */
	public void addSubmitButton() {
		JButton submit = new JButton("修改");
		submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String type = comBox.getSelectedItem().toString().trim();
				
				if(value[0] == null) {
					Message.setPlain("请输入要删除的图书", title);
				} else if(type.equals("按书号删除")) {
					int ID = Integer.valueOf(value[0]);
					if(find.getBookName(ID) != null) {
						del.delBook_ID(ID);
						Message.setPlain("删除成功", title);
					} else {
						Message.setPlain("小图没有查询到"+ID+"号图书的信息", title);
					}
				} else {
					if(find.getBookCount(value[0]) > 0) {
						del.delBook_Name(value[0]);
						Message.setPlain("删除成功", title);
					} else {
						Message.setPlain("小图没有查询到 "+value[0]+" 这本书", title);
					}
				}
			}
		});
		
		setButton(submit);
	}
}
