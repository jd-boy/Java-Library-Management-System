package dataManage;

import java.sql.SQLException;

public class AddRegulationsInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	public void setRegulations(String text) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("UPDATE Regulations SET Regulations = ?");
			conn.preStat.setString(1, text);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRegulations() {
		String text = null;
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Regulations FROM Regulations");
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				text = conn.rs.getString(1);
			}
			conn.allClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
}
