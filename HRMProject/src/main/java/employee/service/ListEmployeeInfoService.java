package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeDao;
import employee.dao.EmployeeEplyDao;
import jdbc.connection.ConnectionProvider;

public class ListEmployeeInfoService {

   private EmployeeEplyDao	employeeEplyDao = new EmployeeEplyDao();
   private EmployeeDao	employeeDao = new EmployeeDao();
   private int size = 10;
   
   public EmployeeListPagePart getEmployeeListPagePart(int pageNum) {
	   try (Connection conn = ConnectionProvider.getConnection()) {
		   
		   int total = employeeEplyDao.selectCount(conn);
		   List<InfoRequestPart> content;
		   
		   content = employeeEplyDao.select(conn, (pageNum -1) * size, size);
		   
		   return new EmployeeListPagePart(total, pageNum, size, content);
		   
	   } catch(SQLException e) {
		   throw new RuntimeException(e);
	   }
   }
   
   public EmployeeListPageAll getEmployeeListPageAll(int pageNum) {
	   try (Connection conn = ConnectionProvider.getConnection()) {
		   
		   int total = employeeEplyDao.selectCount(conn);
		   List<InfoRequestAll> content;
		   
		   content = employeeDao.select(conn, (pageNum -1) * size, size);
		   
		   return new EmployeeListPageAll(total, pageNum, size, content);
		   
	   } catch(SQLException e) {
		   throw new RuntimeException(e);
	   }
   }
}