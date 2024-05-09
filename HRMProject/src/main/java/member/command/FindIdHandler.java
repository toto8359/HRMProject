package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.FindIdService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class FindIdHandler implements CommandHandler {

	// 처리가 안되면, 다시 보여줄 페이지
	private static final String FORM_VIEW = "/WEB-INF/view/findIdForm.jsp";
	// Handler에서 사용하기 위한 기능을 가진, Service 인스턴스화
	private FindIdService findIdService = new FindIdService();

	// 1.명령어와 관련된 비즈니스 로직 처리(process함수)
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {// get으로 받으면, pocessForm함수로, 다시 findPwForm페이지로 돌아가기
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {// post로 받으면, 로그인 과정(processSubmit함수)진행하기
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// get으로 받으면, findPwFrom페이지로 돌아가기
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	// post로 받으면, 이메일로 아이디찾기 과정 진행하기
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		// 입력받은 이메일을 저장
		String member_email = req.getParameter("member_email");

		// 공란 확인을 위해 errors 선언 및 session에 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 공란이 있으면, findForm페이지로 돌아가기
		if (member_email == null || member_email.isEmpty()) {
			errors.put("member_email", Boolean.TRUE);
		}
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// email에 맞는 아이디 찾아오고, 세션에 저장하기
		// 찾기 성공 화면으로 전환
		// email에 맞는 아이디가 없으면, 예외처리
		try {
			Member member = findIdService.findIdByEmail(member_email);
			req.setAttribute("member", member);
			return "/WEB-INF/view/findIdSuccess.jsp";
		} catch (MemberNotFoundException e) {
			errors.put("member_id", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
