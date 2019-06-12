package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import dataManage.FindBorrowBookInform;
import dataManage.User;

public class BorrowRecord extends JFrame{
	
	private FindBorrowBookInform find = new FindBorrowBookInform();
	
	private String[] tableHead = {"书号", "书名", "借书日期", "截止日期", "状态"};
	private String[][] record;
	
	public BorrowRecord() {
		
		setLayout(null);
		
		TableHeadPane p = new TableHeadPane();
		p.setBounds(0, 0, 800, 200);
		add(p);
		
		addTable();
		
		setTitle("借书记录");
		setBounds(550, 200, 800, 550);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(record[0][0] == null) {
			Message.setPlain("小图查询到您还没有借书记录哦，还等什么呢", "借书记录");
		}
	}
	
	private void addTable() {
		
		JTable table = new JTable();
		JScrollPane scr = new JScrollPane(table);
		MyTableModel mod = new MyTableModel();
		record = find.getBorrowBookInform(User.ID, 1);//获取该用户的借书记录
		if(record == null) {
			record = new String[1][5];
			record[0][0] = null;
		}
		mod.setDate(tableHead, record);
		table.setModel(mod);
		table.setFont(new Font("楷体", Font.CENTER_BASELINE, 18));//设置表格字体
		table.setRowHeight(28);//设置表格行高
		
		//设置表头格式
		JTableHeader head = table.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 35));
		head.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		
		DefaultTableCellRenderer rend = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置表格中字体居中
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
		};
		for (int i = 0; i < tableHead.length; i++) {
            table.getColumn(tableHead[i]).setCellRenderer(rend);
        }
		
		scr.setBounds(1, 210, 783, 300);
		add(scr);
		
	}
}
