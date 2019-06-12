package dataManage;

import java.sql.SQLException;

import dataManage.ConnectDataBase;

public class UpdataBookInform {
	private ConnectDataBase conn = new ConnectDataBase();
	private FindGenreInform findGenre = new FindGenreInform();
	
	public static int ID_ALTER = 0;
	public static int AUTHOR_ALTER = 1;
	public static int PRICE_ALTER = 2;
	public static int GENRE_ALTER = 3;
	public static int NAME_ALTER = 4;
	public static int PRESS_ALTER = 5;
	public static int STATE_ALter = 6;
	public static int BOOKSTORE_ALter = 7;
	
	private String[] str = {"Id", "Author", "Price", "Genre", "Name", "Press", "State", "BookstoreName"};
	
	public UpdataBookInform() {}
	
	/*
	 * 根据书号修改图书在馆状态
	 */
	public void updateIDState(int ID, Boolean newState) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("UPDATE Book SET State = ? WHERE Id = ?");
			conn.preStat.setBoolean(1, newState);
			conn.preStat.setInt(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据书名修改除书号和书名外其他基本信息
	 */
	public void updateNameInform(String name, String newValue, int flag) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("UPDATE Book SET "+str[flag]+" = ? WHERE Name = ?");
			
			if(flag == 2) {
				conn.preStat.setDouble(1, Double.valueOf(newValue));
			} else if(flag == 3) {
				newValue = findGenre.getID(newValue);
				conn.preStat.setString(1, newValue);
			}   else if(flag == 6) {
				conn.preStat.setBoolean(1, Boolean.valueOf(newValue));
			} else {
				conn.preStat.setString(1, newValue);
			}
			conn.preStat.setString(2, name);
			
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据书号修改除书号外其他信息
	 */
	public void updateIDInform(String ID, String newValue, int flag) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("UPDATE Book SET "+str[flag]+" = ? WHERE Id = ?");
			
			if(flag == 2) {
				conn.preStat.setDouble(1, Double.valueOf(newValue));
			} else if(flag == 3) {
				newValue = findGenre.getID(newValue);
				conn.preStat.setString(1, newValue);
			}  else if(flag == 6) {
				conn.preStat.setBoolean(1, Boolean.valueOf(newValue));
			} else {
				conn.preStat.setString(1, newValue);
			}
			conn.preStat.setInt(2, Integer.valueOf(ID));
			
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据书名和书库，修改所在书库
	 */
	public void updateBookstoreName(String name, String newValue, String oldValue) {
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("UPDATE Book SET BookstoreName = ? WHERE BookstoreName = ? AND Name = ?");
			conn.preStat.setString(1, newValue);
			conn.preStat.setString(2, oldValue);
			conn.preStat.setString(3, name);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
