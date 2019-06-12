package dataManage;

import java.sql.SQLException;

public class AddUserInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	public AddUserInform() {}
	
	public void importData(String[] information) {
		conn.connectDataBase();
		int column = information.length;
		try {
			conn.preStat = conn.con.prepareStatement("INSERT INTO User(Id, Name, Class, Email, Sex, Password, Administrators) VALUES(?, ?, ?, ?, ?, ?, ?)");
			for(int i = 0; i < column-1; i++) {
				conn.preStat.setString(i+1, information[i]);
			}
			conn.preStat.setBoolean(column, Boolean.valueOf(information[column-1]));
			conn.preStat.executeUpdate();
			
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
