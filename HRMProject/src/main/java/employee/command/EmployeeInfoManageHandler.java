package employee.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.service.DeleteInfoService;
import employee.service.EmployeeListPagePart;
import employee.service.InfoRequestAll;
import employee.service.ListEmployeeInfoService;
import employee.service.ModifyInfoService;
import employee.service.ReadEmployeeInfoServiece;
import employee.service.RegisterService;
import exception.DuplicateIdException;
import mvc.command.CommandHandler;

public class EmployeeInfoManageHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/employeeInfoManage.jsp";
	RegisterService registerService = new RegisterService();
	ListEmployeeInfoService listEmployeeInfoService = new ListEmployeeInfoService();
	ReadEmployeeInfoServiece readEmployeeInfoServiece = new ReadEmployeeInfoServiece();
	ModifyInfoService modifyInfoService = new ModifyInfoService();
	DeleteInfoService deleteInfoService = new DeleteInfoService();

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
	// getで受け取る場合
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// get으로 받을 경우:
		// 1.사원정보 List 띄우기
		// 2.등록버튼 띄워두기 (get)
		// 3.List의 이름 누르면 해당사원 정보 보여주기
		// 4.사원정보 아래 수정 버튼 누르면 수정창 띄우기
		// 5.삭제 버튼 보여주기
		// getで受け取る場合
		// 1.従業員情報リストを表示する
		// 2.登録ボタンを表示する（get）
		// 3.リストの名前をクリックすると、該当する従業員情報が表示されます
		// 4.従業員情報の下に修正ボタンをクリックすると、修正画面を表示する
		// 5.削除ボタンを表示

		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.従業員情報リストを表示する------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.従業員情報リストを表示する------------------------------------------------------------------------------------------

		// 2.등록버튼 띄워두기(get)---------------------------------------------------------------------------------------------
		// 2.登録ボタンを表示する（get）---------------------------------------------------------------------------------------------
		String registerForm = req.getParameter("registerForm");
		if (registerForm == null || registerForm.isEmpty()) {
			req.setAttribute("registerForm", Boolean.FALSE);// 등록 버튼을 누른 적 없으면 안띄우기  
			// 登録ボタンを押したことがない場合、表示しない
		} else {
			req.setAttribute("registerForm", Boolean.TRUE);// 등록 버튼을 눌렀으면 띄우기
			// 登録ボタンを押した場合、表示する
		}
		// 2.등록버튼 띄워두기(get)---------------------------------------------------------------------------------------------
		// 2.登録ボタンを表示する（get）---------------------------------------------------------------------------------------------

		// 3.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------
		// 3.名前をクリックすると情報を表示する------------------------------------------------------------------------------------------
		String employeeNum = req.getParameter("employeeNum");
		if( !(employeeNum == null || employeeNum.isEmpty()) ) {
			InfoRequestAll infoRequestAll = readEmployeeInfoServiece.getInfoRequestAll(employeeNum);
			req.setAttribute("infoRequestAll", infoRequestAll);
			req.setAttribute("readInfo", Boolean.TRUE);
		}
		// 3.이름 누르면 정보 보여주기------------------------------------------------------------------------------------------
		// 3.名前をクリックすると情報を表示する------------------------------------------------------------------------------------------
		
		// 4.수정버튼 보여주기--------------------------------------------------------------------------------------------------
		// 4.修正ボタンを表示する--------------------------------------------------------------------------------------------------
		String modifyInfo = req.getParameter("modifyInfo");
		String employeeNumMopdify = req.getParameter("employeeNumModify");
		String employeeKnameModify = req.getParameter("employeeKnameModify");
		String employeeEnameMopdify = req.getParameter("employeeEnameMopdify");
		if( !(modifyInfo == null || modifyInfo.isEmpty()) ) {
			req.setAttribute("employeeNumModify", employeeNumMopdify);//사원번호 보여주기   //社員番号を見せる
			req.setAttribute("employeeKnameModify", employeeKnameModify);//국문이름 보여주기   //国文の名前を見せる
			req.setAttribute("employeeEnameMopdify", employeeEnameMopdify);//영문이름 보여주기   //英語の名前を見せる
			req.setAttribute("modifyInfo", Boolean.TRUE);
		}
		// 4.수정버튼 보여주기--------------------------------------------------------------------------------------------------
		// 4.修正ボタンを表示する--------------------------------------------------------------------------------------------------

		return FORM_VIEW;
	}

	// post로 받으면
	// postで受け取る場合
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// post으로 받을 경우
		// 1.사원정보 List 띄우기
		// 2.등록 버튼 눌렀을 때,입력창 보여주기
		// 3.사원정보를 등록했을 때, 해당 사원 정보 보여주기
		// 4.수정 후 저장하기 눌렀을 때, 저장 및 해당사원 정보 보여주기
		// 5.삭제버튼 누르면 해당 사원 정보 삭제 및 삭제했다는 멘트 보여주기
		// postで受け取る場合
		// 1.従業員情報リストを表示する
		// 2.登録ボタンを押した場合、入力画面を表示する
		// 3.従業員情報を登録した場合、該当する従業員情報を表示する
		// 4.修正後に保存ボタンを押した場合、保存して該当する従業員情報を表示する
		// 5.削除ボタンを押すと、該当社員の情報が削除され、削除したというメッセージが表示されます


		//정보를 입력받을 객체 인스턴스화
		// 情報を入力するためのオブジェクトをインスタンス化
		InfoRequestAll joinReq = new InfoRequestAll();
		joinReq.setEmployeePsnl_kname(req.getParameter("employeePsnl_kname"));
		joinReq.setEmployeeNum(req.getParameter("employeeNum"));
		joinReq.setEmployeePsnl_ename(req.getParameter("employeePsnl_ename"));
		joinReq.setEmployeePsnl_isForeigner(req.getParameter("employeePsnl_isForeigner")); // 문자열을 char로 변환  // 文字列をcharに変換
		joinReq.setEmployeePsnl_residentNumber(req.getParameter("employeePsnl_residentNumber"));
		joinReq.setEmployeePsnl_adress(req.getParameter("employeePsnl_adress"));
		joinReq.setEmployeePsnl_phoneNumber(req.getParameter("employeePsnl_phoneNumber"));
		joinReq.setEmployeePsnl_email(req.getParameter("employeePsnl_email"));
		joinReq.setEmployeePsnl_sns(req.getParameter("employeePsnl_sns"));
		joinReq.setEmployeeEply_employType(req.getParameter("employeeEply_employType")); // 문자열을 char로 변환  // 文字列をcharに変換
		joinReq.setEmployeeEply_depart(req.getParameter("employeeEply_depart"));
		joinReq.setEmployeeEply_position(req.getParameter("employeeEply_position"));
		joinReq.setEmployeeEply_join(req.getParameter("employeeEply_join"));
		joinReq.setEmployeeEply_resignation(req.getParameter("employeeEply_resignation")); 
		String modifyInfo = req.getParameter("modifyInfo");//수정상태인지 확인  //修正状態か確認
		
		// 4.삭제 및 메세지 보여주기--------------------------------------------------------------------------------------------
		// 4.削除およびメッセージの表示--------------------------------------------------------------------------------------------
		String employeeNumDelete = req.getParameter("employeeNumDelete");
		String employeeKnameDelete = req.getParameter("employeeKnameDelete");
		if( !(employeeNumDelete == null || employeeNumDelete.isEmpty()) ){
			deleteInfoService.deleteInfo(employeeNumDelete);
			req.setAttribute("readInfo", Boolean.FALSE);//정보창 끄기  //情報ウィンドウをオフにする
			req.setAttribute("deleteSuccess", Boolean.TRUE);//삭제메세지창 켜기  //削除メッセージウィンドウをオンにする
			req.setAttribute("employeePsnl_kname", employeeKnameDelete );//삭제한 사원 이름 보내기  //削除した社員の名前を送る
		}
		// 4.삭제 및 메세지 보여주기--------------------------------------------------------------------------------------------
		// 4.削除およびメッセージの表示--------------------------------------------------------------------------------------------
		
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.従業員情報リストを表示する-------------------------------------------------------------------------------------------------
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EmployeeListPagePart employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
		req.setAttribute("employeeListPagePart", employeeListPagePart);
		//만약 정보를 삭제했다면, list만 띄우고, 돌아가기
		// もし情報を削除した場合、リストだけを表示し、戻る
		if( !(employeeNumDelete == null || employeeNumDelete.isEmpty()) ){
			return FORM_VIEW;
		}
		// 1.사원정보List띄우기-------------------------------------------------------------------------------------------------
		// 1.従業員情報リストを表示する-------------------------------------------------------------------------------------------------

		// 2.등록부-------------------------------------------------------------------------------------------------------------
		// 2.登録部-------------------------------------------------------------------------------------------------------------
		String registerForm = req.getParameter("registerForm");
		if (registerForm == null || registerForm.isEmpty()) {
			req.setAttribute("registerForm", Boolean.FALSE);// 등록 버튼을 누른 적 없으면 안띄우기 // 登録ボタンを押したことがない場合、表示しない
		} else {
			req.setAttribute("registerForm", Boolean.TRUE);// 등록 버튼을 눌렀으면 띄우기 // 登録ボタンを押した場合、表示する
		}

		// 공란 확인을 위해 errors 선언 및 session에 저장
		// 空欄確認のためのerrorsを宣言し、セッションに保存
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		// 공란이 있으면, joinForm페이지로 돌아가기
		// null이 아니기 때문에, 등록창이 열려야만 공란확인이 됨
		// 空欄があれば、joinFormページに戻る
		// nullではないため、登録画面が開いているときにのみ空欄確認が行われます
		joinReq.validate(errors);
		if (!errors.isEmpty()) {
			// 여전히 등록창 띄워두기
			// まだ登録画面を表示
			req.setAttribute("registerForm", Boolean.TRUE);
			//수정상태였으면, 버튼 수정으로 남기기
			//修正状態であれば、ボタン修正で残す
			if( !(modifyInfo == null || modifyInfo.isEmpty()) ) {
				req.setAttribute("modifyInfo", Boolean.TRUE);
			}
			return FORM_VIEW;
		}

		// 새로운 사원등록 정보 저장(회원가입하기)
		// 회원가입 성공을 알리는 페이지 joinSuccess.jsp로 이동
		// 중복 에러 발생시, errors.duplicateId속성에 TRUE 넣기
		// 新しい従業員登録情報の保存（登録する）
		// 登録成功を知らせるページ joinSuccess.jsp に移動
		// 重複エラーが発生した場合、errors.duplicateId属性に TRUE を設定
		try {
			//수정상태였으면, 수정하기
			// 修正状態だった場合、修正する
			if( !(modifyInfo == null || modifyInfo.isEmpty()) ) {
				modifyInfoService.modifyInfo(joinReq);
				req.setAttribute("modifySuccess", Boolean.TRUE);
			}else {
				registerService.Register(joinReq);
				req.setAttribute("joinSuccess", Boolean.TRUE);
			}
			req.setAttribute("employeePsnl_kname", joinReq.getEmployeePsnl_kname());
			employeeListPagePart = listEmployeeInfoService.getEmployeeListPagePart(pageNo);
			req.setAttribute("employeeListPagePart", employeeListPagePart);
			
		} catch (DuplicateIdException e) {
			req.setAttribute("registerForm", Boolean.TRUE);// 여전히 등록창 띄워두기 // まだ登録画面を表示
			//수정상태였으면, 버튼 수정으로 남기기
			// 修正状態だった場合、ボタン修正を維持
			if( !(modifyInfo == null || modifyInfo.isEmpty()) ) {
				req.setAttribute("modifyInfo", Boolean.TRUE);
			}
			errors.put("duplicateResidentNumber", Boolean.TRUE);
			return FORM_VIEW;
		}
		// 2.등록부-------------------------------------------------------------------------------------------------------------
		// 2.登録部----------------------------------------------------------------------------------------------------------------

		// 3.등록한 사원 정보 보여주기------------------------------------------------------------------------------------------
		// 3. 登録した従業員情報を表示する-----------------------------------------------------------------------------------------------------
		req.setAttribute("infoRequestAll", joinReq);
		req.setAttribute("readInfo", Boolean.TRUE);
		// 3.등록한 사원 정보 보여주기------------------------------------------------------------------------------------------
		// 3. 登録した従業員情報を表示する-----------------------------------------------------------------------------------------------------

		// 마지막 돌아갈 페이지
		// get으로 돌아가기 때문에 등록창은 꺼짐
		// 最後に戻るページ
		// getで戻るため、登録画面はオフ
		return FORM_VIEW;
	}

}
