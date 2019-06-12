package dataManage;

import java.sql.SQLException;

public class UpdataUserInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	/*
	 * 修改密码
	 */
	public void alterPassword(String user, String password) {
		String sql = "UPDATE User SET Password = ? WHERE Id = ?";
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, password);
			conn.preStat.setString(2, user);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据账号修改管理员权限
	 */
	public void alterUserJurisdiction(String Id, Boolean flag) {
		String sql = "UPDATE User SET Administrators = ? WHERE Id = ?";
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setBoolean(1, flag);
			conn.preStat.setString(2, Id);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据账号修改用户名
	 */
	public void alterUserName(String ID, String name) {
		String sql = "UPDATE User SET Name = ? WHERE Id = ?";
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, name);
			conn.preStat.setString(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据账号修改班级
	 */
	public void alterUserClass(String ID, String classes) {
		String sql = "UPDATE User SET Class = ? WHERE Id = ?";
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, classes);
			conn.preStat.setString(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据账号修改性别
	 */
	public void alterUserSex(String ID, String sex) {
		String sql = "UPDATE User SET Sex = ? WHERE Id = ?";
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, sex);
			conn.preStat.setString(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据账号修改邮箱
	 */
	public void alterUserEmail(String ID, String email) {
		String sql = "UPDATE User SET Email = ? WHERE Id = ?";
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, email);
			conn.preStat.setString(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
