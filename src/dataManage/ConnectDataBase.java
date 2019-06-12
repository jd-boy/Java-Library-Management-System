package dataManage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDataBase {
	public ResultSet rs = null;
	public Connection con = null;
	public PreparedStatement preStat = null;
	private String url = DatabaseUser.url;
	private String user = DatabaseUser.user;
	private String password = DatabaseUser.password;
	
	//连接数据库
	public void connectDataBase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//测试连接数据库
		public Boolean connectTest(String url, String user, String password) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				return false;
			}
			
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				return false;
			}
			return true;
		}
	
	/*
	 * 关闭所有连接
	 */
	public void allClose() {
		try {
			rs.close();
			con.close();
			preStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//关闭部分连接
	public void close() {
		try {
			con.close();
			preStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
