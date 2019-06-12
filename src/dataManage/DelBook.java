package dataManage;

import java.sql.SQLException;

public class DelBook {
	
	private ConnectDataBase conn = new ConnectDataBase();
	
	/*
	 * 按书名删除图书
	 */
	public void delBook_Name(String book_name) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("DELETE FROM Book WHERE Name = ?");
			conn.preStat.setString(1, book_name);
			conn.preStat.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("数据库操作错误");
		}
	}
	
	/*
	 * 按书号删除图书
	 */
	public void delBook_ID(int ID) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("DELETE FROM Book WHERE Id = ?");
			conn.preStat.setInt(1, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
