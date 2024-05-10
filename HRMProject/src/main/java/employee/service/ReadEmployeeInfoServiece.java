package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDao;
import jdbc.connection.ConnectionProvider;

public class ReadEmployeeInfoServiece {

	EmployeeDao employeeDao = new EmployeeDao();

	public InfoRequestAll getInfoRequestAll(String employeeNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			InfoRequestAll infoRequestAll = employeeDao.selectByEmployeeNum(conn, employeeNum);
			return infoRequestAll;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
