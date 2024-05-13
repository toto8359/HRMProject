package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 리소스 닫기 및 롤백 처리
// リソースを閉じてロールバック処理
public class JdbcUtil {

  // ResultSet 닫기
  // ResultSetを閉じる
  public static void close(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ex) {
      }
    }
  }

  // Statement 닫기
  // Statementを閉じる
  public static void close(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException ex) {
      }
    }
  }

  // Connection 닫기
  // Connectionを閉じる
  public static void close(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ex) {
      }
    }
  }

  // 트랜잭션 롤백
  // トランザクションのロールバック
  public static void rollback(Connection conn) {
    if (conn != null) {
      try {
        conn.rollback();
      } catch (SQLException ex) {
      }
    }
  }
}
