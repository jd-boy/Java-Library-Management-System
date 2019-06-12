package dataManage;

import java.sql.SQLException;

public class FindGenreInform {
	private ConnectDataBase conn = new ConnectDataBase();
	
	/*
	 * 返回全部类别
	 */
	public String[] findAllGenre() {
		String[] genre = null;
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Genre FROM BookGenre");
			conn.rs = conn.preStat.executeQuery();
			conn.rs.last();
			genre = new String[conn.rs.getRow()];
			conn.rs.beforeFirst();
			for(int i = 0; conn.rs.next(); i++) {
				genre[i] = conn.rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genre;
	}
	
	/*
	 * 判断genre类别是否存在，存在返回true，否则返回false
	 */
	public Boolean judgeExit(String genre) {
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT COUNT(Genre) FROM BookGenre WHERE Genre = ?");
			conn.preStat.setString(1, genre);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next() && conn.rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 返回genre类别的编号
	 */
	public String getID(String genre) {
		String record = null;
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Id FROM BookGenre WHERE Genre = ?");
			conn.preStat.setString(1, genre);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				record = conn.rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	/*
	 * 根据编号返回类别名
	 */
	public String getGenre(String ID) {
		String record = null;
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Genre FROM BookGenre WHERE Id = ?");
			conn.preStat.setString(1, ID);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				record = conn.rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
}
