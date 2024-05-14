package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.VOEPrintRecordDao;
import jdbc.connection.ConnectionProvider;

public class ListVOEPrintRecordService {
	
	VOEPrintRecordDao voePrintRecordDao = new VOEPrintRecordDao();
	private int size = 10;
	
	//재직증명서 발급대장 출력
	//在職証明書発行台帳出力
	public VOEPrintRecordPage getVOEPrintRecord(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			int total = voePrintRecordDao.selectCount(conn);
			List<InfoRequestVOERecord> content;

			content = voePrintRecordDao.select(conn, (pageNum - 1) * size, size);

			return new VOEPrintRecordPage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
