package sc2db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String db = "mproject";
	private static String dbUser = "root";
	private static String dbPwd = "root";
	
	private static String url = "jdbc:mysql://localhost/" + db + "?useSSL=false";
	
	private static Connection conn;
	
//	외부 생성 차단
	private DBCon() {
	}
	
	public static Connection getConnection() {
		
		if (conn != null) {
			return conn;
		}
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbUser, dbPwd);
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다. " + e);
		} catch (SQLException e) {
			System.out.println("일반 예외 : " + e);
		}
		
		return conn;
	}
}
