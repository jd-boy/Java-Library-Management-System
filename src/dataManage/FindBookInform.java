package dataManage;

import java.sql.SQLException;

public class FindBookInform {
	
	private ConnectDataBase conn = new ConnectDataBase();
	private FindGenreInform findGenre = new FindGenreInform();
	public FindBookInform(){}
	
	/*
	 * 按不同条件查询书籍的全部信息
	 */
	public String[][] conditionFind(int flag, String condition[]){
		String[][] record = null;
		String[] sql = {" WHERE Name like ?", " WHERE Id = ?", " WHERE Author like ?", " WHERE Genre = ?", ""};
		
		conn.connectDataBase();
		
		try {
			conn.preStat = conn.con.prepareStatement("SELECT * FROM Book" + sql[flag]);
			if(flag == 3) {
				conn.preStat.setString(1, findGenre.getID(condition[0]));
			} else if(flag != 4) {
				if(flag != 1) {
					condition[0] += "%";
					condition[0] = "%" + condition[0];
				}
				conn.preStat.setString(1, condition[0]);
			}
			
			conn.rs = conn.preStat.executeQuery();
			conn.rs.last();
			int row = conn.rs.getRow();
			
			if(row == 0) {
				return null;
			}
			
			record = new String[row][8];
			conn.rs.beforeFirst();
			for(int i = 0; conn.rs.next(); i++) {
				int j;
				for(j = 0; j < 7; j++) {
					record[i][j] = conn.rs.getString(j+1);
					if(j == 1) {
						record[i][j] = findGenre.getGenre(record[i][j]);
					}
				}
				if(conn.rs.getBoolean(j+1)) {
					record[i][j] = "在馆";
				} else {
					record[i][j] = "外借";
				}
				
			}
			
			conn.allClose();
			
		} catch (SQLException e) {
			System.out.println("数据库操作错误");
		}
		
		return record;
	}
	
	/*
	 * 根据图书编号获取书名
	 */
	public String getBookName(int ID) {
		String name = null;
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT Name FROM Book WHERE Id = ?");
			conn.preStat.setInt(1, ID);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				name = conn.rs.getString(1);
			}
			conn.rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	/*
	 * 获取该书名的书有多少本
	 */
	public int getBookCount(String name) {
		int count = 0;
		conn.connectDataBase();
		try {
			conn.preStat = conn.con.prepareStatement("SELECT COUNT(Name) FROM Book WHERE Name = ?");
			conn.preStat.setString(1, name);
			conn.rs = conn.preStat.executeQuery();
			if(conn.rs.next()) {
				count = conn.rs.getInt(1);
			}
			conn.allClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
