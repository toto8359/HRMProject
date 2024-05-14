package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeEplyDao;
import employee.dao.EmployeePsnlDao;
import employee.model.EmployeeEply;
import employee.model.EmployeePsnl;
import jdbc.connection.ConnectionProvider;

public class ModifyInfoService {

	EmployeePsnlDao employeePsnlDao = new EmployeePsnlDao();
	EmployeeEplyDao employeeEplyDao = new EmployeeEplyDao();

	// 사원정보 업데이트 메소드
	// 社員情報修正
	public void modifyInfo(InfoRequestAll employee) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			EmployeePsnl employeePsnl = new EmployeePsnl(employee.getEmployeeNum(), employee.getEmployeePsnl_kname(),
					employee.getEmployeePsnl_ename(), employee.getEmployeePsnl_isForeigner(),
					employee.getEmployeePsnl_residentNumber(), employee.getEmployeePsnl_adress(),
					employee.getEmployeePsnl_phoneNumber(), employee.getEmployeePsnl_email(),
					employee.getEmployeePsnl_sns());
			EmployeeEply employeeEply = new EmployeeEply(employee.getEmployeeNum(),
					employee.getEmployeeEply_employType(), employee.getEmployeeEply_depart(),
					employee.getEmployeeEply_position(), employee.getEmployeeEply_join(),
					employee.getEmployeeEply_resignation());
			employeePsnlDao.update(conn, employeePsnl);
			employeeEplyDao.update(conn, employeeEply);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
