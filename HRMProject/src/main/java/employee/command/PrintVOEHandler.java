package employee.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.AddPrintRecordService;
import employee.service.EmployeeListPagePart;
import employee.service.InfoRequestAll;
import employee.service.ListEmployeeInfoService;
import employee.service.ReadEmployeeInfoServiece;
import mvc.command.CommandHandler;

public class PrintVOEHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/printVOEForm.jsp";
	ListEmployeeInfoService listEmployeeInfoService = new ListEmployeeInfoService();
	ReadEmployeeInfoServiece readEmployeeInfoServiece = new ReadEmployeeInfoServiece();
	AddPrintRecordService addPrintRecordService = new AddPrintRecordService();

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
	// GETで受け取る場合
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// get으로 받을 경우:
		// 1.사원정보 List 띄우기
		// 2.List의 이름 누르면 해당사원 정보 보여주기
		// GETで受け取る場合:
   		// 1.社員情報のリストを表示する
    		// 2.リストの名前をクリックすると、その社員の情報を表示する

		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.社員情報リストを表示する-------------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.社員情報リストを表示する-------------------------------------------------------------------------------------------------
   
		
		// 2.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------
		// 2.名前をクリックすると情報を表示する------------------------------------------------------------------------------------------
    
		String employeeNum = req.getParameter("employeeNum");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
		req.setAttribute("dateToday", date);
		if( !(employeeNum == null || employeeNum.isEmpty()) ) {
			InfoRequestAll infoRequestAll = readEmployeeInfoServiece.getInfoRequestAll(employeeNum);
			req.setAttribute("infoRequestAll", infoRequestAll);
			req.setAttribute("readInfo", Boolean.TRUE);
		}
		// 2.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------
		// 2.名前をクリックすると情報を表示する------------------------------------------------------------------------------------------
    
		return FORM_VIEW;
	}

	// post로 받으면
	// POSTで受け取る場合
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// post으로 받을 경우
		// 1.사원정보 List 띄우기
		// 2.인쇄버튼 누르면 인쇄대장 기록추가
		// POSTで受け取る場合
    		// 1.社員情報のリストを表示する
    		// 2.印刷ボタンをクリックすると印刷台帳に記録を追加する
		
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.社員情報リストを表示する-------------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.社員情報リストを表示する-------------------------------------------------------------------------------------------------
    
		
		// 2.인쇄버튼 누르면 인쇄대장 기록추가----------------------------------------------------------------------------------
		String employeeNumPrint = req.getParameter("employeeNumPrint");
		if( !(employeeNumPrint == null || employeeNumPrint.isEmpty()) ) {
			addPrintRecordService.insertVOEPrintRecord(employeeNumPrint);
		}
		// 2.인쇄버튼 누르면 인쇄대장 기록추가----------------------------------------------------------------------------------
		// 2.印刷ボタンをクリックすると印刷台帳に記録を追加する----------------------------------------------------------------------------------
    
		// 마지막 돌아갈 페이지
		// 最後に戻るページ
		return FORM_VIEW;
	}
}
