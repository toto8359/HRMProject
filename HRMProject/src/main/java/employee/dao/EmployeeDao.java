package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import employee.model.EmployeeEply;
import jdbc.JdbcUtil;

public class EmployeeDao {
	
	//두 테이블의 정보를 한 번에 가져올 때
	
	
	// 사원정보 list를 위한 CRUD - Read
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from employeePsnl");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 사원정보 list를 위한 CRUD - Read
	public List<EmployeeEply> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM employeeEply ORDER BY employeeNum DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<EmployeeEply> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertEmployeeEply(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private EmployeeEply convertEmployeeEply(ResultSet rs) throws SQLException {
		return new EmployeeEply(rs.getString("employeeNum"), rs.getString("employeeEply_employType"),
				rs.getString("employeeEply_depart"), rs.getString("employeeEply_position"),
				rs.getString("employeeEply_join"), rs.getString("employeeEply_resignation"));
	}

}
