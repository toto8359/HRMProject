package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.EmployeeListPagePart;
import employee.service.ListEmployeeInfoService;
import mvc.command.CommandHandler;

public class ListEmployeeInfoHandler implements CommandHandler {
	
		ListEmployeeInfoService listEmployeeInfoService = new ListEmployeeInfoService();

	   @Override
	   public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

	      String pageNoVal = req.getParameter("pageNo");
	      int pageNo = 1;
	      if(pageNoVal != null) {
	         pageNo = Integer.parseInt(pageNoVal);
	      }
	      EmployeeListPagePart employeeListPage = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
	      req.setAttribute("employeeListPage", employeeListPage);
	      
	      return "/WEB-INF/view/employeeInfoManage.jsp";

	   }
}
