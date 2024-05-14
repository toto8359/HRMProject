package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeDao;
import jdbc.connection.ConnectionProvider;

public class ReadEmployeeInfoServiece {

	EmployeeDao employeeDao = new EmployeeDao();

	// DB에서 사원번호를 통해 사원 정보 읽어오기
	// DBから社員番号を通じて社員情報を読み込む
	public InfoRequestAll getInfoRequestAll(String employeeNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			InfoRequestAll infoRequestAll = employeeDao.selectByEmployeeNum(conn, employeeNum);
			return infoRequestAll;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
