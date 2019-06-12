package menu;

/*
 * 设置表格模型的类
 */

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L; 
	private String[] tableHead;
	private String[][] str;
	
	public void setDate(String[] tableHead, String[][] str) {
		this.tableHead = tableHead;
		this.str = str;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return tableHead.length;
	}

	@Override
	public int getRowCount() {
		return str.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return str[rowIndex][columnIndex];
	}
	
	public String getColumnName(int column) {
		return tableHead[column];
	}
}
