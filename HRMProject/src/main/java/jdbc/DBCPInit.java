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

public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool() {
		try {
			String jdbcDriver = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbId = "system";
			String dbPw = "1234";

			ConnectionFactory cf = new DriverManagerConnectionFactory(jdbcDriver, dbId, dbPw);

			PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, null);
			pcf.setValidationQuery("select 1");

			GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
			pc.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			pc.setTestWhileIdle(true);
			pc.setMinIdle(4);
			pc.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> cp = new GenericObjectPool<>(pcf, pc);
			pcf.setPool(cp);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver"); // 코드 확인
			// 코드 확인
			PoolingDriver pd = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			pd.registerPool("HRMProject", cp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
