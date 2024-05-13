package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionProvider {

  public static Connection getConnection() throws SQLException {
    // Apache Commons DBCP를 사용하여 HRMProject 데이터베이스에 연결
    // Apache Commons DBCPを使用してHRMProjectデータベースに接続します
    return DriverManager.getConnection("jdbc:apache:commons:dbcp:HRMProject");

  }

}
