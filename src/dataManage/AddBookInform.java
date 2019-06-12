package dataManage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBookInform {
	
	public AddBookInform() {}
	
	public void addInform(String[] inform) {
		ConnectDataBase conn = new ConnectDataBase();
		conn.connectDataBase();
		
		int ID = 0;
		int num = Integer.valueOf(inform[6]).intValue();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT MAX(Id) FROM Book");
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				ID = conn.rs.getInt(1)+1;
			} 
			
			conn.preStat = conn.con.prepareStatement("INSERT INTO Book VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			while(num > 0) {
				conn.preStat.setInt(1, ID++);
				for(int i = 2; i < 7; i++) {
					conn.preStat.setString(i, inform[i-2]);
				}
				conn.preStat.setDouble(7, Double.valueOf(inform[5]).doubleValue());
				conn.preStat.setBoolean(8, true);
				conn.preStat.executeUpdate();
				num--;
			}
			
			conn.allClose();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
