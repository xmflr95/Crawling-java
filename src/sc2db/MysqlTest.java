package sc2db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlTest {

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String db = "mproject";
		String dbUser = "root";
		String dbPwd = "root";
		
		String url = "jdbc:mysql://localhost/" + db + "?useSSL=false";
		
		Connection conn;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbUser, dbPwd);
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다. " + e);
		} catch (SQLException e) {
			System.out.println("일반 예외 : " + e);
		}
	}

}
