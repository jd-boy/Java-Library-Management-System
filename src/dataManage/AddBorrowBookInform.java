package dataManage;

import java.sql.SQLException;

public class AddBorrowBookInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	public AddBorrowBookInform() {}
	
	public void addBorrowBookInform(String[] record) {
		conn.connectDataBase();
		try {
			int i;
			conn.preStat = conn.con.prepareStatement("INSERT INTO BorrowRecord VALUES(?, ?, ?, ?, ?, ?)");
			conn.preStat.setInt(1, Integer.valueOf(record[0]));
			for(i = 1; i < record.length-1; i++) {
				conn.preStat.setString(i+1, record[i]);
			}
			conn.preStat.setBoolean(i+1, Boolean.valueOf(record[4]));
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
