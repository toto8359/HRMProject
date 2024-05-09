package employee.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.JoinRequest;
import employee.service.RegisterService;
import exception.DuplicateIdException;
import member.service.DuplicateEmailException;
import mvc.command.CommandHandler;

public class RegisterHandler implements CommandHandler {

	// 처리가 안되면, 다시 보여줄 페이지
	private static final String FORM_VIEW = "/WEB-INF/view/employeeInfoManage.jsp";
	// Handler에서 사용하기 위한 기능을 가진, Service 인스턴스화
	private RegisterService registerService = new RegisterService();

	// 1.명령어와 관련된 비즈니스 로직 처리(process함수)
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {// get으로 받으면, pocessForm함수로, 다시 joinForm페이지로 돌아가기
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {// post로 받으면, 로그인 과정(processSubmit함수)진행하기
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// get으로 받으면, joinForm페이지로 돌아가기
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	// post로 받으면 진행하기
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {

		// 입력받은 정보를 JoinRequest객체 joinReq에 넣기
		JoinRequest joinReq = new JoinRequest();
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
			return FORM_VIEW;
		}

		// 새로운 회원가입 정보 저장(회원가입하기)
		// 회원가입 성공을 알리는 페이지 joinSuccess.jsp로 이동
		// 중복 에러 발생시, errors.duplicateId속성에 TRUE 넣기
		try {
			registerService.Register(joinReq);
			req.setAttribute("employeePsnl_kname", joinReq.getEmployeePsnl_kname());
			return "/WEB-INF/view/registerSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateResidentNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
