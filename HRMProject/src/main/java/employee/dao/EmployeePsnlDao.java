package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.model.EmployeePsnl;
import jdbc.JdbcUtil;

public class EmployeePsnlDao {

	// CRUD구현
	// CRUDの実装

	// 사원번호에 해당하는 직원 정보 불러오기
	// 社員番号に該当する従業員個人情報を読み込み
	public EmployeePsnl selectByEmployeeNum(Connection conn, String employeeNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM employeePsnl WHERE employeeNum = ?");
			pstmt.setString(1, employeeNum);
			rs = pstmt.executeQuery();
			EmployeePsnl employee = null;
			if (rs.next()) {
				employee = new EmployeePsnl(rs.getString("employeeNum"), rs.getString("employeePsnl_kname"),
						rs.getString("employeePsnl_ename"), rs.getString("employeePsnl_isForeigner"),
						rs.getString("employeePsnl_residentNumber"), rs.getString("employeePsnl_adress"),
						rs.getString("employeePsnl_phoneNumber"), rs.getString("employeePsnl_email"),
						rs.getString("employeePsnl_sns"));
			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 주민번호에 해당하는 직원 개인 정보 불러오기
	// 住民番号に該当する職員の個人情報を読み込む
	public EmployeePsnl selectByResidentNumber(Connection conn, String residentNumber) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM employeePsnl WHERE employeePsnl_residentNumber = ?");
			pstmt.setString(1, residentNumber);
			rs = pstmt.executeQuery();
			EmployeePsnl employee = null;
			if (rs.next()) {
				employee = new EmployeePsnl(rs.getString("employeeNum"), rs.getString("employeePsnl_kname"),
						rs.getString("employeePsnl_ename"), rs.getString("employeePsnl_isForeigner"),
						rs.getString("employeePsnl_residentNumber"), rs.getString("employeePsnl_adress"),
						rs.getString("employeePsnl_phoneNumber"), rs.getString("employeePsnl_email"),
						rs.getString("employeePsnl_sns"));

			}
			return employee;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 정보 추가
	// 従業員情報の追加
	public void insert(Connection conn, EmployeePsnl employee) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO employeePsnl VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, employee.getEmployeeNum());
			pstmt.setString(2, employee.getEmployeePsnl_kname());
			pstmt.setString(3, employee.getEmployeePsnl_ename());
			pstmt.setString(4, employee.getEmployeePsnl_isForeigner());
			pstmt.setString(5, employee.getEmployeePsnl_residentNumber());
			pstmt.setString(6, employee.getEmployeePsnl_adress());
			pstmt.setString(7, employee.getEmployeePsnl_phoneNumber());
			pstmt.setString(8, employee.getEmployeePsnl_email());
			pstmt.setString(9, employee.getEmployeePsnl_sns());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 정보 수정
	// 従業員情報の修正
	public void update(Connection conn, EmployeePsnl employee) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"UPDATE employeePsnl SET employeePsnl_kname=?, employeePsnl_ename=?, employeePsnl_isForeigner=?, "
							+ "employeePsnl_residentNumber=?, employeePsnl_adress=?, employeePsnl_phoneNumber=?, employeePsnl_email=?, employeePsnl_sns=? "
							+ "WHERE employeeNum=?");
			pstmt.setString(1, employee.getEmployeePsnl_kname());
			pstmt.setString(2, employee.getEmployeePsnl_ename());
			pstmt.setString(3, employee.getEmployeePsnl_isForeigner());
			pstmt.setString(4, employee.getEmployeePsnl_residentNumber());
			pstmt.setString(5, employee.getEmployeePsnl_adress());
			pstmt.setString(6, employee.getEmployeePsnl_phoneNumber());
			pstmt.setString(7, employee.getEmployeePsnl_email());
			pstmt.setString(8, employee.getEmployeePsnl_sns());
			pstmt.setString(9, employee.getEmployeeNum());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 직원 정보 삭제
	// 従業員情報の削除
	public void delete(Connection conn, String employeeNum) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM employeePsnl WHERE employeeNum=?");
			pstmt.setString(1, employeeNum);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
