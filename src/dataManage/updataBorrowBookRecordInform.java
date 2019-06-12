package dataManage;

import java.sql.SQLException;

public class updataBorrowBookRecordInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	public void alterBorrowBookState(int ID, Boolean state) {
		String sql = "UPDATE BorrowRecord SET State = ? WHERE BookId = ?";
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setBoolean(1, state);
			conn.preStat.setInt(2, ID);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
