package dataManage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDataBase {
	private Connection con = null;
	private PreparedStatement preStat = null;
	private String url = DatabaseUser.url;
	private String url1 = DatabaseUser.url1;
	private String user = DatabaseUser.user;
	private String password = DatabaseUser.password;
	
	//创建library数据库
	public CreateDataBase(){
		//连接驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("数据库JDBC-MySQL驱动未连接");
		}
		
		//连接数据库
		try {
			con = DriverManager.getConnection(url, user, password);
			con.close();
		} catch (SQLException e) {
			
			//ֻ若没有Library数据库则创建数据库
			try {
				
				con = DriverManager.getConnection(url1, user, password);
				preStat = con.prepareStatement("CREATE DATABASE Library");//创建Library数据库
				preStat.executeLargeUpdate();
				close();
				con = DriverManager.getConnection(url, user, password);
				
				createUserTable();
				createBookGenre();
				createBookTable();
				createBorrowRecord();
				close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//创建User用户表
	private void createUserTable() throws SQLException {
		String sql = "CREATE TABLE User(" + "Id VARCHAR(50) PRIMARY KEY," + "Name VARCHAR(50) NOT NULL,"
			  + "Class VARCHAR(50) NOT NULL," + "Email VARCHAR(40) NOT NULL," + "Sex VARCHAR(3),"
			  + "Password VARCHAR(50) NOT NULL," + "Administrators BOOLEAN DEFAULT FALSE)";
		preStat = con.prepareStatement(sql);
		preStat.executeUpdate();
	}
	
	//创建图书类别表
		private void createBookGenre() throws SQLException {
			String sql = "CREATE TABLE BookGenre(" + "Id VARCHAR(50) PRIMARY KEY,"
				       + "Genre VARCHAR(50))";
			preStat = con.prepareStatement(sql);
			preStat.executeUpdate();
		}
	
	//创建Book表
	private void createBookTable() throws SQLException {
		String sql = "CREATE TABLE Book (" + "Id INT PRIMARY KEY," + "Genre VARCHAR(50) NOT NULL,"
			  + "Name VARCHAR(100) NOT NULL," + "Author VARCHAR(50) NOT NULL,"
			  + "Press VARCHAR(50) NOT NULL,"+ "BookstoreName VARCHAR(50)," + "Price DOUBLE(5, 2),"
			  + "State boolean NOT NULL)";
		preStat = con.prepareStatement(sql);
		preStat.executeUpdate();
	}
	
	//创建借书记录表
	private void createBorrowRecord() throws SQLException {
		String sql = "CREATE TABLE BorrowRecord(" + "BookId INT," + "BookName VARCHAR(50),"+  "User VARCHAR(50),"
			  + "StartTime DATE NOT NULL," + "EndTime DATE NOT NULL," + "State boolean NOT NULL,"
			  + "CONSTRAINT d FOREIGN KEY(BookId) REFERENCES Book(Id),"
			  + "CONSTRAINT c FOREIGN KEY(User) REFERENCES User(Id));";
		preStat = con.prepareStatement(sql);
		preStat.executeUpdate();
	}
	
	//创建管理规定表
	private void createRegulations() throws SQLException{
		String sql = "CREATE TABLE Regulations(Regulations TEXT)";
		preStat = con.prepareStatement(sql);
		preStat.executeUpdate();
	}
	
	
	private void close() throws SQLException {
			con.close();
			preStat.close();
	}
	
}
