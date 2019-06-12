package dataManage;

public class DatabaseUser {
	public static String user = "library";
	public static String url = "jdbc:mysql://218.198.32.182:3306/Library?useSSL=true&characterEncoding=utf-8";
	public static String url1 = "jdbc:mysql://218.198.32.182:3306/mysql?useSSL=true&characterEncoding=utf-8";
	public static String password = "514library";
	
	public static void localhost(String u, String p) {
		user = u;
		password = p;
		url = "jdbc:mysql://localhost:3306/Library?useSSL=true&characterEncoding=utf-8";
		url1 = "jdbc:mysql://localhost:3306/mysql?useSSL=true&characterEncoding=utf-8";
		new CreateDataBase();
	}
	
	public static void sshhost() {
		user = "library";
		password = "514library";
		url = "jdbc:mysql://218.198.32.182:3306/Library?useSSL=true&characterEncoding=utf-8";
		url1 = "jdbc:mysql://218.198.32.182:3306/mysql?useSSL=true&characterEncoding=utf-8";
		new CreateDataBase();
	}
}
