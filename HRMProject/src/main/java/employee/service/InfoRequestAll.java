package employee.service;

import java.util.Map;

public class InfoRequestAll {

	private String employeeNum; // 사원번호 // 社員番号
	private String employeePsnl_kname; // 국문이름 // 国名
	private String employeePsnl_ename; // 영문이름 // 英語名
	private String employeePsnl_isForeigner; // 내국인 외국인 //在留資格（国内、外国）
	private String employeePsnl_residentNumber; // 주민번호 // 住民番号
	private String employeePsnl_adress; // 주소 // 住所
	private String employeePsnl_phoneNumber; // 전화번호 // 電話番号
	private String employeePsnl_email; // 이메일 // メール
	private String employeePsnl_sns; // sns계정 // SNSアカウント
	private String employeeEply_employType; // 고용 형태(정규직, 비정규직) // 雇用形態（正社員、非正社員）
	private String employeeEply_depart; // 부서 // 部署
	private String employeeEply_position; // 직급 // 役職
	private String employeeEply_join; // 입사날짜 // 入社日
	private String employeeEply_resignation; // 퇴사날짜 // 退社日

	// 사원등록창의 공란 확인, map형태 errors.notMatch로 저장
	// 社員登録画面の空白チェック、Map形式のerrors.notMatchに保存
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, employeeNum, "employeeNum");
		checkEmpty(errors, employeePsnl_kname, "employeePsnl_kname");
		checkEmpty(errors, employeePsnl_ename, "employeePsnl_ename");
		checkEmpty(errors, employeePsnl_residentNumber, "employeePsnl_residentNumber");
		checkEmpty(errors, employeePsnl_adress, "employeePsnl_adress");
		checkEmpty(errors, employeePsnl_phoneNumber, "employeePsnl_phoneNumber");
		checkEmpty(errors, employeePsnl_email, "employeePsnl_email");
		checkEmpty(errors, employeePsnl_sns, "employeePsnl_sns");
		checkEmpty(errors, employeeEply_depart, "employeeEply_depart");
		checkEmpty(errors, employeeEply_position, "employeeEply_position");
		checkEmpty(errors, employeeEply_join, "employeeEply_join");
//		checkEmpty(errors, employeeEply_resignation, "employeeEply_resignation"); //필수정보 아니면 주석처리하여 비우기  // 必須情報でない場合はコメントアウトして空にする
	}

	// Map형 errors에, value값이 null이거나 비어있으면, 에러 저장
	// Map形式のerrorsに、valueがnullまたは空の場合、エラーを保存
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}

	// 생성자
	// コンストラクタ
	public InfoRequestAll() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfoRequestAll(String employeeNum, String employeePsnl_kname, String employeePsnl_ename,
			String employeePsnl_isForeigner, String employeePsnl_residentNumber, String employeePsnl_adress,
			String employeePsnl_phoneNumber, String employeePsnl_email, String employeePsnl_sns,
			String employeeEply_employType, String employeeEply_depart, String employeeEply_position,
			String employeeEply_join, String employeeEply_resignation) {
		super();
		this.employeeNum = employeeNum;
		this.employeePsnl_kname = employeePsnl_kname;
		this.employeePsnl_ename = employeePsnl_ename;
		this.employeePsnl_isForeigner = employeePsnl_isForeigner;
		this.employeePsnl_residentNumber = employeePsnl_residentNumber;
		this.employeePsnl_adress = employeePsnl_adress;
		this.employeePsnl_phoneNumber = employeePsnl_phoneNumber;
		this.employeePsnl_email = employeePsnl_email;
		this.employeePsnl_sns = employeePsnl_sns;
		this.employeeEply_employType = employeeEply_employType;
		this.employeeEply_depart = employeeEply_depart;
		this.employeeEply_position = employeeEply_position;
		this.employeeEply_join = employeeEply_join;
		this.employeeEply_resignation = employeeEply_resignation;
	}

	// 게터 세터
	// ゲッター・セッター
	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getEmployeePsnl_kname() {
		return employeePsnl_kname;
	}

	public void setEmployeePsnl_kname(String employeePsnl_kname) {
		this.employeePsnl_kname = employeePsnl_kname;
	}

	public String getEmployeePsnl_ename() {
		return employeePsnl_ename;
	}

	public void setEmployeePsnl_ename(String employeePsnl_ename) {
		this.employeePsnl_ename = employeePsnl_ename;
	}

	public String getEmployeePsnl_isForeigner() {
		return employeePsnl_isForeigner;
	}

	public void setEmployeePsnl_isForeigner(String employeePsnl_isForeigner) {
		this.employeePsnl_isForeigner = employeePsnl_isForeigner;
	}

	public String getEmployeePsnl_residentNumber() {
		return employeePsnl_residentNumber;
	}

	public void setEmployeePsnl_residentNumber(String employeePsnl_residentNumber) {
		this.employeePsnl_residentNumber = employeePsnl_residentNumber;
	}

	public String getEmployeePsnl_adress() {
		return employeePsnl_adress;
	}

	public void setEmployeePsnl_adress(String employeePsnl_adress) {
		this.employeePsnl_adress = employeePsnl_adress;
	}

	public String getEmployeePsnl_phoneNumber() {
		return employeePsnl_phoneNumber;
	}

	public void setEmployeePsnl_phoneNumber(String employeePsnl_phoneNumber) {
		this.employeePsnl_phoneNumber = employeePsnl_phoneNumber;
	}

	public String getEmployeePsnl_email() {
		return employeePsnl_email;
	}

	public void setEmployeePsnl_email(String employeePsnl_email) {
		this.employeePsnl_email = employeePsnl_email;
	}

	public String getEmployeePsnl_sns() {
		return employeePsnl_sns;
	}

	public void setEmployeePsnl_sns(String employeePsnl_sns) {
		this.employeePsnl_sns = employeePsnl_sns;
	}

	public String getEmployeeEply_employType() {
		return employeeEply_employType;
	}

	public void setEmployeeEply_employType(String employeeEply_employType) {
		this.employeeEply_employType = employeeEply_employType;
	}

	public String getEmployeeEply_depart() {
		return employeeEply_depart;
	}

	public void setEmployeeEply_depart(String employeeEply_depart) {
		this.employeeEply_depart = employeeEply_depart;
	}

	public String getEmployeeEply_position() {
		return employeeEply_position;
	}

	public void setEmployeeEply_position(String employeeEply_position) {
		this.employeeEply_position = employeeEply_position;
	}

	public String getEmployeeEply_join() {
		return employeeEply_join;
	}

	public void setEmployeeEply_join(String employeeEply_join) {
		this.employeeEply_join = employeeEply_join;
	}

	public String getEmployeeEply_resignation() {
		return employeeEply_resignation;
	}

	public void setEmployeeEply_resignation(String employeeEply_resignation) {
		this.employeeEply_resignation = employeeEply_resignation;
	}

}
