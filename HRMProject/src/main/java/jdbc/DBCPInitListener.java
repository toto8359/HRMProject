package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

// 웹 어플리케이션의 시작 및 종료 시점의 DB커넥션 풀 초기화 및 종료 설정
public class DBCPInitListener implements ServletContextListener {

  @Override

  public void contextInitialized(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    // 웹 애플리케이션의 초기 매개변수에서 풀 구성을 가져옴
    String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
    Properties prop = new Properties();
    try {
      prop.load(new StringReader(poolConfig));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // JDBC 드라이버로드 및 DB 커넥션 풀 설정
    loadJDBCDriver(prop);
    initConnectionPool(prop);

  }

  // JDBC 드라이버 로드
  private void loadJDBCDriver(Properties prop) {
    // JDBC 드라이버 클래스 가져오기
    String driverClass = prop.getProperty("jdbcdriver");
    try {
      // JDBC 드라이버 로드
      Class.forName(driverClass);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException("Failed to load JDBC Driver", ex);
    }
  }

  // DB 커넥션 풀 초기화
  // GenericObjectPool 구성 및 초기화 및 유효성 검사
  // PoolingDriver로 DB 커넥션 풀 등록
  private void initConnectionPool(Properties prop) {
    try {
      // JDBC 연결 정보 가져오기
      String jdbcUrl = prop.getProperty("jdbcUrl");
      String username = prop.getProperty("dbUser");
      String pw = prop.getProperty("dbPass");

      // ConnectionFactory 및 PoolableConnectionFactory 생성
      ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

      PoolableConnectionFactory poolableConnFactory =
          new PoolableConnectionFactory(connFactory, null);
      poolableConnFactory.setValidationQuery("select 1");

      // 데이터베이스 연결 풀 구성 설정
      GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
      poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
      poolConfig.setTestWhileIdle(true);
      int minIdle = getIntProperty(prop, "minIdle", 5);
      poolConfig.setMinIdle(minIdle);
      int maxTotal = getIntProperty(prop, "maxTotal", 50);
      poolConfig.setMaxTotal(maxTotal);

      // 데이터베이스 연결 풀 생성
      GenericObjectPool<PoolableConnection> connectionPool =
          new GenericObjectPool<>(poolableConnFactory, poolConfig);
      poolableConnFactory.setPool(connectionPool);

      // PoolingDriver 등록
      Class.forName("org.apache.commons.dbcp2.PoolingDriver");
      PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
      String poolName = prop.getProperty("poolName");
      driver.registerPool(poolName, connectionPool);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // int 형식으로 값을 반환
  private int getIntProperty(Properties prop, String propName, int defaultValue) {
    String value = prop.getProperty(propName);
    if (value == null)
      return defaultValue;
    return Integer.parseInt(value);
  }

  @Override
  // 종료시 호출
  public void contextDestroyed(ServletContextEvent sce) {
    // TODO Auto-generated method stub
  }

}
