package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.FindPwService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class FindPwHandler implements CommandHandler {

	// 처리가 안되면, 다시 보여줄 페이지
	private static final String FORM_VIEW = "/WEB-INF/view/findPwForm.jsp";
	// Handler에서 사용하기 위한 기능을 가진, Service 인스턴스화
	private FindPwService findPwService = new FindPwService();

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

	// get으로 받으면, findFrom페이지로 돌아가기
	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		return FORM_VIEW;

	}

	// post로 받으면, 이메일로 아이디찾기 과정 진행하기
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 입력받은 이메일을 저장
		String member_id = req.getParameter("member_id");
		String member_passwordHintAnswer = req.getParameter("member_passwordHintAnswer");

		// 공란 확인을 위해 errors 선언 및 session에 저장
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 공란이 있으면, findForm페이지로 돌아가기
		if (member_id == null || member_id.isEmpty()) {
			errors.put("member_id", Boolean.TRUE);
			return FORM_VIEW;
		}

		try {
			Member member = findPwService.findId(member_id);
			req.setAttribute("member_passwordHint", member.getMember_passwordHint());

			//힌트&답 입력칸 표시
			req.setAttribute("answerBlank", Boolean.TRUE);

			if (member_passwordHintAnswer == null || member_passwordHintAnswer.isEmpty()) {
				errors.put("member_passwordHintAnswer", Boolean.TRUE);
				return FORM_VIEW;
			}
			
			//String비교할 때는, equals()써야 함
			if (!member_passwordHintAnswer.equals(member.getMember_passwordHintAnswer())) {
				errors.put("wrongAnswer", Boolean.TRUE);
				return FORM_VIEW;
			}

			req.setAttribute("member", member);
			return "/WEB-INF/view/findPwSuccess.jsp";
		} catch (MemberNotFoundException e) {
			errors.put("noIdFound", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
