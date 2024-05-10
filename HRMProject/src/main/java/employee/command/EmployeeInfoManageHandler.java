package employee.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.EmployeeListPagePart;
import employee.service.InfoRequestAll;
import employee.service.ListEmployeeInfoService;
import employee.service.ReadEmployeeInfoServiece;
import employee.service.RegisterService;
import exception.DuplicateIdException;
import mvc.command.CommandHandler;

public class EmployeeInfoManageHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/employeeInfoManage.jsp";
	RegisterService registerService = new RegisterService();
	ListEmployeeInfoService listEmployeeInfoService = new ListEmployeeInfoService();
	ReadEmployeeInfoServiece readEmployeeInfoServiece = new ReadEmployeeInfoServiece();

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
		// get으로 받을 경우:
		// 1.사원정보 List 띄우기
		// 2.등록버튼 띄워두기 (get)
		// 3.List의 이름 누르면 해당사원 정보 보여주기

		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------

		// 2.등록버튼 띄워두기(get)---------------------------------------------------------------------------------------------
		String registerForm = req.getParameter("registerForm");
		if (registerForm == null || registerForm.isEmpty()) {
			req.setAttribute("registerForm", Boolean.FALSE);// 등록 버튼을 누른 적 없으면 안띄우기
		} else {
			req.setAttribute("registerForm", Boolean.TRUE);// 등록 버튼을 눌렀으면 띄우기
		}
		// 2.등록버튼 띄워두기(get)---------------------------------------------------------------------------------------------

		// 3.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------
		String employeeNum = req.getParameter("employeeNum");
		if( !(employeeNum == null || employeeNum.isEmpty()) ) {
			InfoRequestAll infoRequestAll = readEmployeeInfoServiece.getInfoRequestAll(employeeNum);
			req.setAttribute("infoRequestAll", infoRequestAll);
			req.setAttribute("readInfo", Boolean.TRUE);
		}
		// 3.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------

		
		return FORM_VIEW;
	}

	// post로 받으면
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// post으로 받을 경우
		// 1.사원정보 List 띄우기
		// 2.등록 버튼 눌렀을 때,입력창 보여주기
		// 3.사원정보를 등록했을 때, 해당 사원 정보 보여주기

		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------

		// 2.등록부-------------------------------------------------------------------------------------------------------------
		String registerForm = req.getParameter("registerForm");
		if (registerForm == null || registerForm.isEmpty()) {
			req.setAttribute("registerForm", Boolean.FALSE);// 등록 버튼을 누른 적 없으면 안띄우기
		} else {
			req.setAttribute("registerForm", Boolean.TRUE);// 등록 버튼을 눌렀으면 띄우기
		}
		
		// 입력받은 정보를 JoinRequest객체 joinReq에 넣기
		InfoRequestAll joinReq = new InfoRequestAll();
		joinReq.setEmployeeNum(req.getParameter("employeeNum"));
		joinReq.setEmployeePsnl_kname(req.getParameter("employeePsnl_kname"));
		joinReq.setEmployeePsnl_ename(req.getParameter("employeePsnl_ename"));
		joinReq.setEmployeePsnl_isForeigner(req.getParameter("employeePsnl_isForeigner")); // 문자열을 char로 변환
		joinReq.setEmployeePsnl_residentNumber(req.getParameter("employeePsnl_residentNumber"));
		joinReq.setEmployeePsnl_adress(req.getParameter("employeePsnl_adress"));
		joinReq.setEmployeePsnl_phoneNumber(req.getParameter("employeePsnl_phoneNumber"));
		joinReq.setEmployeePsnl_email(req.getParameter("employeePsnl_email"));
		joinReq.setEmployeePsnl_sns(req.getParameter("employeePsnl_sns"));
		joinReq.setEmployeeEply_employType(req.getParameter("employeeEply_employType")); // 문자열을 char로 변환
		joinReq.setEmployeeEply_depart(req.getParameter("employeeEply_depart"));
		joinReq.setEmployeeEply_position(req.getParameter("employeeEply_position"));
		joinReq.setEmployeeEply_join(req.getParameter("employeeEply_join"));
		joinReq.setEmployeeEply_resignation(req.getParameter("employeeEply_resignation"));

		// 공란 확인을 위해 errors 선언 및 session에 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 공란이 있으면, joinForm페이지로 돌아가기
		joinReq.validate(errors);
		if (!errors.isEmpty()) {
			req.setAttribute("registerForm", Boolean.TRUE);// 여전히 등록창 띄워두기
			return FORM_VIEW;
		}

		// 새로운 사원등록 정보 저장(회원가입하기)
		// 회원가입 성공을 알리는 페이지 joinSuccess.jsp로 이동
		// 중복 에러 발생시, errors.duplicateId속성에 TRUE 넣기
		try {
			registerService.Register(joinReq);
			req.setAttribute("employeePsnl_kname", joinReq.getEmployeePsnl_kname());
		} catch (DuplicateIdException e) {
			req.setAttribute("registerForm", Boolean.TRUE);// 여전히 등록창 띄워두기
			errors.put("duplicateResidentNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
		// 2.등록부-------------------------------------------------------------------------------------------------------------

		// 3.등록한 사원 정보 보여주기------------------------------------------------------------------------------------------
		req.setAttribute("infoRequestAll", joinReq);
		req.setAttribute("readInfo", Boolean.TRUE);
		// 3.등록한 사원 정보 보여주기------------------------------------------------------------------------------------------

		
		// 마지막 돌아갈 페이지
		// get으로 돌아가기 때문에 등록창은 꺼짐
		return FORM_VIEW;
	}

}
