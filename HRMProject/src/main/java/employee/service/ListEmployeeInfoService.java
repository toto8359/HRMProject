package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import employee.dao.EmployeeEplyDao;
import jdbc.connection.ConnectionProvider;

public class ListEmployeeInfoService {

	private EmployeeEplyDao employeeEplyDao = new EmployeeEplyDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	private int size = 10; // 한 페이지에 표시할 항목 수 // 1ページあたりの項目数

	// 부분 정보 가져오기
	// 部分情報を取得する
	public EmployeeListPagePart getEmployeeListPagePart(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			int total = employeeEplyDao.selectCount(conn);
			List<InfoRequestPart> content;

			content = employeeEplyDao.select(conn, (pageNum - 1) * size, size);

			return new EmployeeListPagePart(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 전체 정보 가져오기
	// 全体情報の取得
	public EmployeeListPageAll getEmployeeListPageAll(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			int total = employeeEplyDao.selectCount(conn);
			List<InfoRequestAll> content;

			content = employeeDao.select(conn, (pageNum - 1) * size, size);

			return new EmployeeListPageAll(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
