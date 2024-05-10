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
  // 웹 어플리케이션의 초기 매개변수를 가져와서 Properties 객체로 변환
  // JDBC 드라이버로드 및 커넥션 풀 설정
  public void contextInitialized(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
    Properties prop = new Properties();
    try {
      prop.load(new StringReader(poolConfig));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    loadJDBCDriver(prop);
    initConnectionPool(prop);

  }

  // JDBC 드라이버 로드
  private void loadJDBCDriver(Properties prop) {
    String driverClass = prop.getProperty("jdbcdriver");
    try {
      Class.forName(driverClass);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException("Failed to load JDBC Driver", ex);
    }
  }

  // DB 커넥션 풀 초기화
  // GenericObjectPool 구성 및 초기화,
  // PoolingDriver로 DB 커넥션 풀 등록
  private void initConnectionPool(Properties prop) {
    try {
      String jdbcUrl = prop.getProperty("jdbcUrl");
      String username = prop.getProperty("dbUser");
      String pw = prop.getProperty("dbPass");

      ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

      PoolableConnectionFactory poolableConnFactory =
          new PoolableConnectionFactory(connFactory, null);
      poolableConnFactory.setValidationQuery("select 1");

      GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
      poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
      poolConfig.setTestWhileIdle(true);
      int minIdle = getIntProperty(prop, "minIdle", 5);
      poolConfig.setMinIdle(minIdle);
      int maxTotal = getIntProperty(prop, "maxTotal", 50);
      poolConfig.setMaxTotal(maxTotal);

      GenericObjectPool<PoolableConnection> connectionPool =
          new GenericObjectPool<>(poolableConnFactory, poolConfig);
      poolableConnFactory.setPool(connectionPool);

      Class.forName("org.apache.commons.dbcp2.PoolingDriver");
      PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
      String poolName = prop.getProperty("poolName");
      driver.registerPool(poolName, connectionPool);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

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
