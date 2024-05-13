package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeEplyDao;
import employee.dao.EmployeePsnlDao;
import jdbc.connection.ConnectionProvider;

public class DeleteInfoService {
	
	EmployeePsnlDao employeePsnlDao = new EmployeePsnlDao();
	EmployeeEplyDao employeeEplyDao = new EmployeeEplyDao();
	
	public void deleteInfo(String employeeNumDelete) {
		try(Connection conn = ConnectionProvider.getConnection()){
			
			employeePsnlDao.delete(conn, employeeNumDelete);
			employeeEplyDao.delete(conn, employeeNumDelete);
			
			//권한이나, 이외 등에 따라 롤백 가능성 생각해두기
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
