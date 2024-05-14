package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import employee.service.InfoRequestAll;
import jdbc.JdbcUtil;

public class EmployeeDao {
	
	//두 테이블의 정보를 한 번에 가져올 때
	// 2つのテーブルの情報を一度に取得する場合
	
	//사원번호로 읽어들이기
	// 社員番号で読み取る
	public InfoRequestAll selectByEmployeeNum(Connection conn, String employeeNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select\r\n"
					+ "employeePsnl.employeeNum as employeeNum,\r\n"
					+ "employeePsnl_kname,--국문이름\r\n"
					+ "employeePsnl_ename,--영문이름\r\n"
					+ "employeePsnl_isForeigner,--내국인 외국인\r\n"
					+ "employeePsnl_residentNumber,--주민번호\r\n"
					+ "employeePsnl_adress,--주소\r\n"
					+ "employeePsnl_phoneNumber,--전화번호\r\n"
					+ "employeePsnl_email,--이메일\r\n"
					+ "employeePsnl_sns,--sns계정\r\n"
					+ "employeeEply_employType,--고용 형태(정규직 비정규직)\r\n"
					+ "employeeEply_depart,--부서\r\n"
					+ "employeeEply_position,--직급\r\n"
					+ "employeeEply_join,--입사날짜\r\n"
					+ "employeeEply_resignation--퇴사날짜 \r\n"
					+ "from employeePsnl \r\n"
					+ "inner join employeeEply\r\n"
					+ "on employeePsnl.employeeNum = employeeEply.employeeNum\r\n"
					+ "WHERE employeePsnl.employeeNum = ?");
			pstmt.setString(1, employeeNum);
			rs = pstmt.executeQuery();
			InfoRequestAll employee = null;
			if (rs.next()) {
				employee = convertEmployeeEply(rs);
			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	// 사원정보 list를 위한 CRUD - Read
	// 社員情報リストのためのCRUD - Read
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
	// 社員情報リストのためのCRUD - Read
	public List<InfoRequestAll> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM \r\n"
					+ "    (SELECT ROWNUM AS rnum, a.* FROM \r\n"
					+ "        (SELECT * FROM (select\r\n"
					+ "            employeePsnl.employeeNum as employeeNum,\r\n"
					+ "            employeePsnl_kname,--국문이름\r\n"
					+ "            employeePsnl_ename,--영문이름\r\n"
					+ "            employeePsnl_isForeigner,--내국인 외국인\r\n"
					+ "            employeePsnl_residentNumber,--주민번호\r\n"
					+ "            employeePsnl_adress,--주소\r\n"
					+ "            employeePsnl_phoneNumber,--전화번호\r\n"
					+ "            employeePsnl_email,--이메일\r\n"
					+ "            employeePsnl_sns,--sns계정\r\n"
					+ "            employeeEply_employType,--고용 형태(정규직 비정규직)\r\n"
					+ "            employeeEply_depart,--부서\r\n"
					+ "            employeeEply_position,--직급\r\n"
					+ "            employeeEply_join,--입사날짜\r\n"
					+ "            employeeEply_resignation--퇴사날짜 \r\n"
					+ "            from employeePsnl \r\n"
					+ "            inner join employeeEply\r\n"
					+ "            on employeePsnl.employeeNum = employeeEply.employeeNum) \r\n"
					+ "        ORDER BY employeeNum DESC) \r\n"
					+ "    a WHERE ROWNUM <= 4) \r\n"
					+ "WHERE rnum >= 0;");
			pstmt.setInt(1, startRow + size);
			pstmt.setInt(2, startRow + 1);
			rs = pstmt.executeQuery();
			List<InfoRequestAll> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertEmployeeEply(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private InfoRequestAll convertEmployeeEply(ResultSet rs) throws SQLException {
		return new InfoRequestAll(
				rs.getString("employeeNum"), 
				rs.getString("employeePsnl_kname"),
				rs.getString("employeePsnl_ename"),
				rs.getString("employeePsnl_isForeigner"),
				rs.getString("employeePsnl_residentNumber"),
				rs.getString("employeePsnl_adress"),
				rs.getString("employeePsnl_phoneNumber"),
				rs.getString("employeePsnl_email"),
				rs.getString("employeePsnl_sns"),
				rs.getString("employeeEply_employType"),
				rs.getString("employeeEply_depart"), 
				rs.getString("employeeEply_position"),
				rs.getString("employeeEply_join"), 
				rs.getString("employeeEply_resignation"));
	}

}
