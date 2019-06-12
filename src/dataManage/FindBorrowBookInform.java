package dataManage;

import java.sql.SQLException;

/*
 * 实现查找借书记录
 */

public class FindBorrowBookInform {
	
	private ConnectDataBase c = new ConnectDataBase();
	
	
	public FindBorrowBookInform() {}
	
	/*
	 * 返回某个用户的全部借书记录
	 * 		flag = 1 不返回账号信息
	 * 		flag = 2 返回账号信息
	 */
	public String[][] getBorrowBookInform(String User, int flag){
		String[][] record = null;
		String sql = "SELECT BookId, BookName, StartTime, EndTime, State FROM BorrowRecord WHERE User = ? ORDER BY StartTime DESC";
		c.connectDataBase();
		
		if(flag == 2) {
			sql = "SELECT User, BookId, BookName, StartTime, EndTime, State FROM BorrowRecord WHERE User = ? ORDER BY StartTime DESC";
		}
		
		try {
			c.preStat = c.con.prepareStatement(sql);
			c.preStat.setString(1, User);
			c.rs = c.preStat.executeQuery();
			
			if(c.rs.next()) {
				c.rs.last();
				int row = c.rs.getRow();
				int column = c.rs.getMetaData().getColumnCount();
				record = new String[row][column];
				c.rs.beforeFirst();
				
				for(int i = 0; c.rs.next(); i++) {
					int j;
					for(j = 0; j < column-1; j++) {
						record[i][j] = c.rs.getString(j+1);
					}
					if(Boolean.valueOf(c.rs.getBoolean(j+1))) {
						record[i][j] = "已还";
					} else {
						record[i][j] = "未还";
					}
					
				}
			}
			c.allClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	/*
	 * 查询用户是否有某本书没有还，没还返回true，还了返回false
	 */
	public Boolean findBorrowBook(String user, int ID) {
		String sql = "SELECT COUNT(BookId) FROM BorrowRecord WHERE BookId = ? AND State = false AND User = ?";
		
		c.connectDataBase();
		
		try {
			c.preStat = c.con.prepareStatement(sql);
			c.preStat.setInt(1, ID);
			c.preStat.setString(2, user);
			c.rs = c.preStat.executeQuery();
			if(c.rs.next() && c.rs.getInt(1) == 1) {
				return true;
			}
			c.allClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 查询全部借书记录
	 */
	public String[][] findAllBorrowBook(){
		String[][] record = null;
		String sql = "SELECT User, BookId, BookName, StartTime, EndTime, State FROM BorrowRecord ORDER BY StartTime DESC";
		
		c.connectDataBase();
		
		try {
			c.preStat = c.con.prepareStatement(sql);
			c.rs = c.preStat.executeQuery();
			
			if(c.rs.next()) {
				c.rs.last();
				int row = c.rs.getRow();
				int column = c.rs.getMetaData().getColumnCount();
				record = new String[row][column];
				c.rs.beforeFirst();
				
				for(int i = 0; c.rs.next(); i++) {
					int j;
					for(j = 0; j < column-1; j++) {
						record[i][j] = c.rs.getString(j+1);
					}
					if(Boolean.valueOf(c.rs.getBoolean(j+1))) {
						record[i][j] = "已还";
					} else {
						record[i][j] = "未还";
					}
					
				}
			}
			c.allClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record; 
	}
}
