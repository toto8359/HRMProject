package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class EmployeeInfoManageHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/employeeInfoManage.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// get으로 받으면
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		
		
		return FORM_VIEW;
	}

	// post로 받으면
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

}
