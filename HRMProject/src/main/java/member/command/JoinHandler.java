package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DuplicateEmailException;
import exception.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {

	// 처리가 안되면, 다시 보여줄 페이지
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	// Handler에서 사용하기 위한 기능을 가진, Service 인스턴스화
	private JoinService joinService = new JoinService();

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

	// post로 받으면, 회원가입 과정 진행하기
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 입력받은 정보를 JoinRequest객체 joinReq에 넣기
		JoinRequest joinReq = new JoinRequest();
		joinReq.setMember_id(req.getParameter("member_id"));
		joinReq.setMember_name(req.getParameter("member_name"));
		joinReq.setMember_password(req.getParameter("member_password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		joinReq.setMember_passwordHint(req.getParameter("member_passwordHint"));
		joinReq.setMember_passwordHintAnswer(req.getParameter("member_passwordHintAnswer"));
		joinReq.setMember_email(req.getParameter("member_email"));

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
			joinService.join(joinReq);
			req.setAttribute("member_name", joinReq.getMember_name());
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		} catch (DuplicateEmailException e) {
			errors.put("duplicateEmail", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}
