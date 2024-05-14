package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import employee.service.InfoRequestVOERecord;
import jdbc.JdbcUtil;

public class VOEPrintRecordDao {

    // 재직증명서 인쇄 기록 추가
    public void insert(Connection conn, String employeeNum) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO VOEPrintRecord (recordNumber, printDate, employeeNum) VALUES (recordNumber_seq.nextval, ?, ?)");
            pstmt.setTimestamp(1, toTimestamp(new Date()));
            pstmt.setString(2, employeeNum);
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    // 재직증명서 List를 위한 CRUD-Read
	public List<InfoRequestVOERecord> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM \r\n"
					+ "    (SELECT ROWNUM AS rnum, a.* FROM \r\n"
					+ "        (SELECT * FROM (SELECT\r\n"
					+ "            recordNumber,\r\n"
					+ "            printDate,\r\n"
					+ "            VOEPrintRecord.employeeNum AS employeeNum,\r\n"
					+ "            employeeEply_employType,\r\n"
					+ "            employeeEply_depart,\r\n"
					+ "            employeeEply_position\r\n"
					+ "            FROM VOEPrintRecord\r\n"
					+ "            INNER JOIN employeeEply\r\n"
					+ "            ON VOEPrintRecord.employeeNum = employeeEply.employeeNum) \r\n"
					+ "        ORDER BY recordNumber DESC) \r\n"
					+ "    a WHERE ROWNUM <= ?) \r\n"
					+ "WHERE rnum >= ?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<InfoRequestVOERecord> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertInfoRequestVOERecord(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
    
	private InfoRequestVOERecord convertInfoRequestVOERecord(ResultSet rs) throws SQLException {
		return new InfoRequestVOERecord(
				rs.getString("employeeNum"),
				toDate(rs.getTimestamp("regdate")),
				rs.getString("employeeNum"),
				rs.getString("employeePsnl_kname"),
				rs.getString("employeeEply_depart"),
				rs.getString("employeeEply_position"));
	}
	
	//Date > Timestamp
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	//Timestamp > Date
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}