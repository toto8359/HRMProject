package employee.model;

public class EmployeeEply {

	private String employeeNum; // 사원번호
	private String employeeEply_employType; // 고용 형태(정규직, 비정규직)
	private String employeeEply_depart; // 부서
	private String employeeEply_position; // 직급
	private String employeeEply_join; // 입사날짜
	private String employeeEply_resignation; // 퇴사날짜
	
	//생성자
	public EmployeeEply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeEply(String employeeNum, String employeeEply_employType, String employeeEply_depart,
			String employeeEply_position, String employeeEply_join, String employeeEply_resignation) {
		super();
		this.employeeNum = employeeNum;
		this.employeeEply_employType = employeeEply_employType;
		this.employeeEply_depart = employeeEply_depart;
		this.employeeEply_position = employeeEply_position;
		this.employeeEply_join = employeeEply_join;
		this.employeeEply_resignation = employeeEply_resignation;
	}

	//게터 세터
	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
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
