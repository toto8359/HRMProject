package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 리소스 닫기 및 롤백 처리
public class JdbcUtil {

  // ResultSet 닫기
  public static void close(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ex) {
      }
    }
  }

  // Statement 닫기
  public static void close(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException ex) {
      }
    }
  }

  // Connection 닫기
  public static void close(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ex) {
      }
    }
  }

  // 트랜잭션 롤백
  public static void rollback(Connection conn) {
    if (conn != null) {
      try {
        conn.rollback();
      } catch (SQLException ex) {
      }
    }
  }
}
