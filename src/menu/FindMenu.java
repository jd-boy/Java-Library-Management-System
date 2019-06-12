package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import dataManage.FindBookInform;


public class FindMenu extends JPanel {
	private JLayeredPane layeredPane;//整个界面
	
	private FindBookInform find = new FindBookInform();//数据库中查找
	
	private JPanel box = new JPanel(null);
	private JComboBox comBox = new JComboBox();//下拉选项
	private JTextField text = new JTextField(100);//查询条件
	private String[] condition = new String[1];//文本框中的值
	private MyTableModel dtm = new MyTableModel();//表格的模式
	private JTable table = new JTable();//表格
	
	private String[][] record = new String[1][8];
	private String[] tableHead = {"书号", "类别", "书名", "作者", "出版社", "馆藏", "单价(元)", "状态"};
	
	
	public FindMenu() {
		setLayout(new GridLayout(1, 1));
		
		layeredPane = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon image = new ImageIcon(layeredPane.getClass().getResource("/find.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
				g.drawImage(image.getImage(), 0, 0, this);
			}
		};
		
		showOptionBox();
		addScr();
		add(layeredPane);
	}
	
	/*
	 * 添加下拉选项
	 */
	private void addComBox() {
		String[] str = {"按书名查询", "按书号查询", "按作者查询", "按类别查询", "查询全部图书"};
		for(int i = 0; i < str.length; i++) {
			comBox.addItem(str[i]);
		}
		comBox.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		comBox.setBounds(190, 40, 140, 35);
		comBox.setOpaque(false);
		box.add(comBox);
	}
	
	/*
	 * 添加文本框
	 */
	private void addText() {
		text.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		text.setBounds(380, 40, 155, 33);
		text.getDocument().addDocumentListener(new MyDocumentListener1(condition));
		box.add(text);
	}
	
	/*
	 * 添加检索按钮
	 */
	private void addButton() {
		JButton button = new JButton("检索");
		button.setBackground(new Color(100, 200, 50));
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 22));
		button.setBounds(640, 40, 80, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				String s = comBox.getSelectedItem().toString();
				
				if(s == "按书名查询") {
					flag = 0;
				} else if(s == "按书号查询") {
					flag = 1;
				} else if(s == "按作者查询") {
					flag = 2;
				} else if(s == "按类别查询") {
					flag = 3;
				} else if(s == "查询全部图书") {
					flag = 4;
				}
				record = find.conditionFind(flag, condition);
				if(record == null) {
					Message.setPlain("没有查询到任何记录", "图书查询");
				} else {
					dtm.setDate(tableHead, record);
				}
			}
		});
		
		box.add(button);
	}
	
	/*
	 * 设置选择界面排版
	 */
	private void showOptionBox() {
		
		box.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "查询选项", TitledBorder.LEFT,TitledBorder.TOP,new Font("宋体",1,25)));
		box.setBounds(280, 150, 900, 100);
		box.setOpaque(false);
		
		addComBox();
		addText();
		addButton();
		
		layeredPane.add(box, new Integer(200));
	}
	
	//设置表格格式
	private void addScr() {
		JScrollPane scr = new JScrollPane(table);
		
		dtm.setDate(tableHead, record);
		table.setModel(dtm);
		
		//设置表格中字体
		table.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
		//设置表格行高
		table.setRowHeight(28);
		
		//设置表头格式
		JTableHeader head = table.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 35));
		head.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
		head.setBackground(new Color(51, 102, 255));
		
		// 设置表格中样式
		DefaultTableCellRenderer rend = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (row % 2 == 0)
                    setBackground(Color.pink);
                else if (row % 2 != 0)
                    setBackground(Color.white);
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置表格中字体居中
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        
        for (int i = 0; i < tableHead.length; i++) {
            table.getColumn(tableHead[i]).setCellRenderer(rend);
        }
		
		scr.setBounds(280, 300, 900, 400);
		layeredPane.add(scr, new Integer(200));
	}
	
}
