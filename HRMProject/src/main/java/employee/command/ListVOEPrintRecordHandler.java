package employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.ListVOEPrintRecordService;
import employee.service.VOEPrintRecordPage;
import mvc.command.CommandHandler;

public class ListVOEPrintRecordHandler implements CommandHandler{

	ListVOEPrintRecordService listVOEPrintRecordService = new ListVOEPrintRecordService(); 

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		VOEPrintRecordPage voePrintRecordPage = listVOEPrintRecordService.getVOEPrintRecord(pageNo);
		req.setAttribute("voePrintRecordPage", voePrintRecordPage);
		
		return "/WEB-INF/view/listVOEPrintRecord.jsp";
		
	}

}
