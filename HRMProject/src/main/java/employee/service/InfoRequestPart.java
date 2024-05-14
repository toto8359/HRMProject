package employee.service;

import java.util.Map;

public class InfoRequestPart {

	private String employeeNum; // 사원번호			// 社員番号
	private String employeePsnl_kname; // 국문이름		// 国名
	private String employeeEply_depart; // 부서		// 部署
	private String employeeEply_position; // 직급		// 役職

	// 사원등록창의 공란 확인, map형태 errors.notMatch로 저장
	// 社員登録画面の空白チェック、Map形式のerrors.notMatchに保存
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, employeeNum, "employeeNum");
		checkEmpty(errors, employeePsnl_kname, "employeePsnl_kname");
		checkEmpty(errors, employeeEply_depart, "employeeEply_depart");
		checkEmpty(errors, employeeEply_position, "employeeEply_position");
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
	public InfoRequestPart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InfoRequestPart(String employeeNum, String employeePsnl_kname, String employeeEply_depart,
			String employeeEply_position) {
		super();
		this.employeeNum = employeeNum;
		this.employeePsnl_kname = employeePsnl_kname;
		this.employeeEply_depart = employeeEply_depart;
		this.employeeEply_position = employeeEply_position;
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

}
