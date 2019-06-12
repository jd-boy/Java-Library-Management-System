package dataManage;

import java.sql.SQLException;

public class AlterGenreInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	/*
	 * 添加图书类别
	 */
	public void addGenre(String genre) {
		int Id = 0;
		String sql = "INSERT INTO BookGenre VALUES(?, ?)";
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT MAX(Id) FROM BookGenre");
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				Id = conn.rs.getInt(1);
			}
			
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, String.valueOf(Id+1));
			conn.preStat.setString(2, genre);
			conn.preStat.executeUpdate();
			conn.allClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 修改图书类别
	 */
	public void alterGenre(String oldGenre, String newGenre) {
		String sql = "UPDATE BookGenre SET Genre = ? WHERE Genre = ?";
		
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement(sql);
			conn.preStat.setString(1, newGenre);
			conn.preStat.setString(2, oldGenre);
			conn.preStat.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
