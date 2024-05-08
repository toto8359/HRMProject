package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//데이터베이스 연결을 제공하는 클래스
public class ConnectionProvider {

	// 데이터베이스 연결을 얻는 메소드
	public static Connection getConnection() throws SQLException {
		// Apache Commons DBCP를 사용하여 데이터베이스 연결 풀에서 연결을 가져옴
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:HRMProject");
	}

}
