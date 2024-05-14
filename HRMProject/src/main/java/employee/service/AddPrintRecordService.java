package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.VOEPrintRecordDao;
import jdbc.connection.ConnectionProvider;

public class AddPrintRecordService {

	VOEPrintRecordDao voePrintRecordDao = new VOEPrintRecordDao();

	public void insertVOEPrintRecord(String employeeNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			voePrintRecordDao.insert(conn, employeeNum);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
