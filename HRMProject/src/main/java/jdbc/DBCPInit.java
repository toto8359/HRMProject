package jdbc;

import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


// DB 커넥션 풀 초기화
// データベースコネクションプールの初期化
public class DBCPInit extends HttpServlet {

  @Override
  public void init() throws ServletException {
    loadJDBCDriver();
    initConnectionPool();
  }

  // Oracle JDBC 드라이버 로드
  // Oracle JDBCドライバのロード
  private void loadJDBCDriver() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException("fail to load JDBC Driver", ex);
    }
  }


  // DB 커넥션 풀 초기화
  // データベースコネクションプールの初期化
  private void initConnectionPool() {
    try {
      // DB 연결 정보
      // データベース接続情報
      String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
      String dbId = "system";
      String dbPw = "1234";

      // ConnectionFactory 및 PoolableConnectionFactory를 생성하고 설정
      // ConnectionFactoryおよびPoolableConnectionFactoryの作成と設定
      ConnectionFactory cf = new DriverManagerConnectionFactory(jdbcDriver, dbId, dbPw);

      PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, null);
      pcf.setValidationQuery("select 1");

      // 커넥션 풀의 설정을 구성
      // コネクションプールの設定を構成
      GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
      pc.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
      pc.setTestWhileIdle(true);
      pc.setMinIdle(4);
      pc.setMaxTotal(50);

      // 연결 풀을 생성하고 설정
      // コネクションプールの作成と設定
      GenericObjectPool<PoolableConnection> cp = new GenericObjectPool<>(pcf, pc);
      pcf.setPool(cp);

      // PoolingDriver를 로드, 커넥션 풀을 등록
      // PoolingDriverのロードとコネクションプールの登録
      Class.forName("org.apache.commons.dbcp2.PoolingDriver"); // 코드 확인
      // 코드 확인
      PoolingDriver pd = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
      pd.registerPool("HRMProject", cp);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
