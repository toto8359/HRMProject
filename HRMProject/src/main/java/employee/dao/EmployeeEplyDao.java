package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import employee.model.EmployeeEply;
import jdbc.JdbcUtil;

public class EmployeeEplyDao {

	// CRUD구현, 그 외 필요한 기능은 추가예정

	// 사원번호에 해당하는 직원 개인 정보 불러오기
	public EmployeeEply selectByEmployeeNum(Connection conn, String employeeNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM employeeEply WHERE employeeNum = ?");
			pstmt.setString(1, employeeNum);
			rs = pstmt.executeQuery();
			EmployeeEply employee = null;
			if (rs.next()) {
				employee = new EmployeeEply(rs.getString("employeeNum"), rs.getString("employeeEply_employType"),
						rs.getString("employeeEply_depart"), rs.getString("employeeEply_position"),
						rs.getString("employeeEply_join"), rs.getString("employeeEply_resignation"));
			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 개인 정보 추가
	public void insert(Connection conn, EmployeeEply employee) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO employeeEply VALUES(?,?,?,?,?,?)");
			pstmt.setString(1, employee.getEmployeeNum());
			pstmt.setString(2, employee.getEmployeeEply_employType());
			pstmt.setString(3, employee.getEmployeeEply_depart());
			pstmt.setString(4, employee.getEmployeeEply_position());
			pstmt.setString(5, employee.getEmployeeEply_join());
			pstmt.setString(6, employee.getEmployeeEply_resignation());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 개인 정보 수정
	public void update(Connection conn, EmployeeEply employee) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("UPDATE employeeEply SET employeeEply_employType=?, employeeEply_depart=?, "
					+ "employeeEply_position=?, employeeEply_join=?, employeeEply_resignation=? "
					+ "WHERE employeeNum=?");
			pstmt.setString(1, employee.getEmployeeEply_employType());
			pstmt.setString(2, employee.getEmployeeEply_depart());
			pstmt.setString(3, employee.getEmployeeEply_position());
			pstmt.setString(4, employee.getEmployeeEply_join());
			pstmt.setString(5, employee.getEmployeeEply_resignation());
			pstmt.setString(6, employee.getEmployeeNum());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 개인 정보 삭제
	public void delete(Connection conn, String employeeNum) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM employeeEply WHERE employeeNum=?");
			pstmt.setString(1, employeeNum);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//사원정보 list를 위한 CRUD - Read
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from employeeEply");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	//사원정보 list를 위한 CRUD - Read
	public List<EmployeeEply> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM (SELECT * FROM employeeEply ORDER BY employeeNum DESC) a WHERE ROWNUM <= ?) WHERE rnum >= ?");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow+1);
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
		return new EmployeeEply(
				rs.getString("employeeNum"),
				rs.getString("employeeEply_employType"),
				rs.getString("employeeEply_depart"),
				rs.getString("employeeEply_position"),
				rs.getString("employeeEply_join"),
				rs.getString("employeeEply_resignation"));
	}
}
