package employee.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.dao.VOEPrintRecordDao;
import employee.service.ListVOEPrintRecordService;
import employee.service.VOEPrintRecordPage;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class ListVOEPrintRecordHandler implements CommandHandler{

	ListVOEPrintRecordService listVOEPrintRecordService = new ListVOEPrintRecordService(); 
	VOEPrintRecordDao voePrintRecordDao = new VOEPrintRecordDao();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Connection conn = ConnectionProvider.getConnection();
		int count = voePrintRecordDao.selectCount(conn);
		req.setAttribute("count", count);
		
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
