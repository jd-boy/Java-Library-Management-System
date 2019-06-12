package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import dataManage.FindBorrowBookInform;
import dataManage.User;

public class AdminBorrowRecord extends JFrame implements ActionListener{
	private FindBorrowBookInform find = new FindBorrowBookInform();
	
	private JTextField text;
	private JLabel label;
	private JButton button;
	private JTable table;
	private JScrollPane scr;
	private MyTableModel mod;
	private String[] tableHead = {"账号", "书号", "书名", "借书日期", "截止日期", "状态"};
	private String[][] record;
	
	public AdminBorrowRecord() {
		
		setLayout(null);
		
		TableHeadPane p = new TableHeadPane();
		p.setBounds(0, 0, 982, 220);
		add(p);
		
		addTable();
		addCompele();
		setTitle("借书记录");
		setBounds(550, 200, 1000, 748);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if(record[0][0] == null) {
			Message.setPlain("小图查询到您还没有借书记录哦，还等什么呢", "借书记录");
		}
	}
	
	private void addTable() {
		
		table = new JTable();
		scr = new JScrollPane(table);
		mod = new MyTableModel();
		record = find.findAllBorrowBook();//获取该用户的借书记录
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
		
		scr.setBounds(1, 300, 983, 405);
		add(scr);
	}
	
	private void addCompele() {
		label = new JLabel("输入账号：");
		label.setForeground(new Color(255, 100, 200));
		label.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		label.setBounds(310, 230, 150, 50);
		add(label);
		
		text = new JTextField(50);
		text.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		text.setBounds(445, 240, 200, 30);
		add(text);
		
		button = new JButton("检索");
		button.addActionListener(this);
		button.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
		button.setBackground(Color.red);
		button.setOpaque(false);
		button.setBounds(800, 238, 100, 40);
		add(button);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		record = find.getBorrowBookInform(text.getText().trim(), 2);
		if(record == null) {
			record = new String[1][5];
			Message.setPlain("暂无借书记录或账号不存在", "借书记录");
		}
		mod.setDate(tableHead, record);
	}
}
