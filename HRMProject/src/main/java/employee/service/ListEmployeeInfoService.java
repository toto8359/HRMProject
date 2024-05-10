package employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import employee.dao.EmployeeEplyDao;
import employee.model.EmployeeEply;
import jdbc.connection.ConnectionProvider;

public class ListEmployeeInfoService {

   private EmployeeEplyDao	employeeEplyDao = new EmployeeEplyDao();
   private int size = 10;
   
   public EmployeeListPage getEmployeeListPage(int pageNum) {
      try (Connection conn = ConnectionProvider.getConnection()) {
    	  
         int total = employeeEplyDao.selectCount(conn);
         List<EmployeeEply> content;
         
         content = employeeEplyDao.select(conn, (pageNum -1) * size, size);
         
         return new EmployeeListPage(total, pageNum, size, content);
         
      } catch(SQLException e) {
         throw new RuntimeException(e);
      }
   }
}