package employee.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.InfoRequestAll;
import employee.service.RegisterService;
import exception.DuplicateIdException;
import mvc.command.CommandHandler;

public class RegisterHandler implements CommandHandler {

	// 처리가 안되면, 다시 보여줄 페이지
	// 処理がされない場合、再表示されるページ
	private static final String FORM_VIEW = "/WEB-INF/view/employeeInfoManage.jsp";
	// Handler에서 사용하기 위한 기능을 가진, Service 인스턴스화
	// Handlerで使用するための機能を持つ、Serviceのインスタンス化
	private RegisterService registerService = new RegisterService();

	// 1.명령어와 관련된 비즈니스 로직 처리(process함수)
	// 1. コマンドに関連するビジネスロジックの処理（process関数）
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {// get으로 받으면, pocessForm함수로, 다시 joinForm페이지로 돌아가기
			// getで受け取った場合、processForm関数へ、再びjoinFormページに戻る
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {// post로 받으면, 로그인 과정(processSubmit함수)진행하기
			// postで受け取った場合、ログインプロセス（processSubmit関数）を実行する
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	// get으로 받으면, joinForm페이지로 돌아가기
	// getで受け取った場合、joinFormページへ戻る
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	// post로 받으면 진행하기
	// postで受け取った場合、進行する
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String registerForm = req.getParameter("registerForm");
		if(registerForm == null || registerForm.isEmpty()) {
			req.setAttribute("registerForm", Boolean.FALSE);//등록 버튼을 누른 적 없으면 안띄우기
			// 登録ボタンを押したことがなければ非表示
		}else {
			req.setAttribute("registerForm", Boolean.TRUE);//등록 버튼을 눌렀으면 띄우기
			// 登録ボタンを押した場合、表示
		}

		// 입력받은 정보를 JoinRequest객체 joinReq에 넣기
		// 入力された情報をJoinRequestオブジェクトに入れる
		InfoRequestAll joinReq = new InfoRequestAll();
		joinReq.setEmployeeNum(req.getParameter("employeeNum"));
		joinReq.setEmployeePsnl_kname(req.getParameter("employeePsnl_kname"));
		joinReq.setEmployeePsnl_ename(req.getParameter("employeePsnl_ename"));
		joinReq.setEmployeePsnl_isForeigner(req.getParameter("employeePsnl_isForeigner")); // 문자열을 char로 변환 // 文字列をcharに変換
		joinReq.setEmployeePsnl_residentNumber(req.getParameter("employeePsnl_residentNumber"));
		joinReq.setEmployeePsnl_adress(req.getParameter("employeePsnl_adress"));
		joinReq.setEmployeePsnl_phoneNumber(req.getParameter("employeePsnl_phoneNumber"));
		joinReq.setEmployeePsnl_email(req.getParameter("employeePsnl_email"));
		joinReq.setEmployeePsnl_sns(req.getParameter("employeePsnl_sns"));
		joinReq.setEmployeeEply_employType(req.getParameter("employeeEply_employType")); // 문자열을 char로 변환 // 文字列をcharに変換
		joinReq.setEmployeeEply_depart(req.getParameter("employeeEply_depart"));
		joinReq.setEmployeeEply_position(req.getParameter("employeeEply_position"));
		joinReq.setEmployeeEply_join(req.getParameter("employeeEply_join"));
		joinReq.setEmployeeEply_resignation(req.getParameter("employeeEply_resignation"));

		// 공란 확인을 위해 errors 선언 및 session에 저장
		// 空欄確認のため、errors宣言およびsessionに保存
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 공란이 있으면, joinForm페이지로 돌아가기
		// 空欄があれば、joinFormページに戻る
		joinReq.validate(errors);
		if (!errors.isEmpty()) {
			req.setAttribute("registerForm", Boolean.TRUE);//여전히 등록창 띄워두기  // まだ登録ウィンドウを表示
			return FORM_VIEW;
		}

		// 새로운 회원가입 정보 저장(회원가입하기)
		// 회원가입 성공을 알리는 페이지 joinSuccess.jsp로 이동
		// 중복 에러 발생시, errors.duplicateId속성에 TRUE 넣기
		// 新しい登録情報を保存（登録する）
		// 登録成功を通知するページjoinSuccess.jspへ移動
		// 重複エラーが発生した場合、errors.duplicateId属性にTRUEを入れる
		try {
			registerService.Register(joinReq);
			req.setAttribute("employeePsnl_kname", joinReq.getEmployeePsnl_kname());
			return FORM_VIEW;
		} catch (DuplicateIdException e) {
			req.setAttribute("registerForm", Boolean.TRUE);//여전히 등록창 띄워두기  // まだ登録ウィンドウを表示
			errors.put("duplicateResidentNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
