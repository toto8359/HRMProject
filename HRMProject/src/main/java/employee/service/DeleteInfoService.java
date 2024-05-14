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
			
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
