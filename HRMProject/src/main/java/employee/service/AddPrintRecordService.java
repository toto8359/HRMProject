package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.VOEPrintRecordDao;
import jdbc.connection.ConnectionProvider;

public class AddPrintRecordService {

	VOEPrintRecordDao voePrintRecordDao = new VOEPrintRecordDao();
	
	//재직증명서 발급시, 재직증명서 발급대장에 기록 추가
	//在職証明書の発行時、在職証明書の発行台帳に記録を追加
	public void insertVOEPrintRecord(String employeeNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			voePrintRecordDao.insert(conn, employeeNum);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
