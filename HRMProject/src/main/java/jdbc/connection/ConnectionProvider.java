package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 데이터베이스 연결을 제공하는 클래스
// データベース接続を提供するクラス
public class ConnectionProvider {

  // 데이터베이스 연결을 얻는 메소드
  // データベース接続を取得するメソッド

  public static Connection getConnection() throws SQLException {
    // Apache Commons DBCP를 사용하여 데이터베이스 연결 풀에서 연결을 가져옴
    // Apache Commons DBCPを使用してデータベース接続プールから接続を取得します
    return DriverManager.getConnection("jdbc:apache:commons:dbcp:HRMProject");
  }

}
