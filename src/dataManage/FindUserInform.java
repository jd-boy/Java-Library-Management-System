package dataManage;

import java.sql.SQLException;

public class FindUserInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	private int RIGHT = 1;
	private int NON_EXISTENT = 2;
	private int ERROR = 3;
	
	public FindUserInform() {}
	
	/*
	 * 登录时的用户查找
	 */
	public int findUser(String id, String password) {
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT COUNT(Id) FROM User WHERE Id = ?");
			conn.preStat.setString(1, id);
			conn.rs = conn.preStat.executeQuery();
			conn.rs.first();
			if(conn.rs.getInt(1) == 1) {
				conn.preStat = conn.con.prepareStatement("SELECT * FROM User WHERE Id = ? and Password = ?");
				conn.preStat.setString(1, id);
				conn.preStat.setString(2, password);
				conn.rs = conn.preStat.executeQuery();
				if(conn.rs.next()) {
					User.ID = conn.rs.getString(1);
					User.NAME = conn.rs.getString(2);
					User.CLASS = conn.rs.getString(3);
					User.EMAIL = conn.rs.getString(4);
					User.SEX = conn.rs.getString(5);
					User.PASSWORD = conn.rs.getString(6);
					User.ADMINISTRATORS = conn.rs.getBoolean(7);
					conn.allClose();
					return RIGHT;
				} else {
					conn.allClose();
					return ERROR;
				}
				
			} else {
				conn.allClose();
				return NON_EXISTENT;
			}
			
			
		} catch (SQLException e) {
			System.out.println("数据库操作错误");
		}
		return ERROR;
		
	}
	
	/*
	 * 查询全部用户信息，并返回
	 */
	public String[][] findAllUser(){
		String[][] record = null;
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT * FROM User");
			conn.rs = conn.preStat.executeQuery();
			conn.rs.last();
			int row = conn.rs.getRow();
			if(row == 0) {
				return null;
			}
			
			int column = conn.rs.getMetaData().getColumnCount();
			record = new String[row][column];
			conn.rs.beforeFirst();
			for(int i = 0; conn.rs.next(); i++) {
				for(int j = 0; j < column; j++) {
					record[i][j] = conn.rs.getString(j+1);
				}
			}
			conn.allClose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return record;
	}
	
	/*
	 * 查找用户密码，并返回密码
	 */
	public String getUserPassword(String user) {
		conn.connectDataBase();
		
		String record = null;
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Password FROM User WHERE Id = ?");
			conn.preStat.setString(1, user);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				record = conn.rs.getString(1);
			}
			conn.allClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return record;
	}
}
