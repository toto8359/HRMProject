package employee.model;

public class EmployeePsnl {

	private String employeeNum; // 사원번호
	private String employeePsnl_kname; // 국문이름
	private String employeePsnl_ename; // 영문이름
	private String employeePsnl_isForeigner; // 내국인 외국인
	private String employeePsnl_residentNumber; // 주민번호
	private String employeePsnl_adress; // 주소
	private String employeePsnl_phoneNumber; // 전화번호
	private String employeePsnl_email; // 이메일
	private String employeePsnl_sns; // sns계정

	//생성자
	public EmployeePsnl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePsnl(String employeeNum, String employeePsnl_kname, String employeePsnl_ename,
			String employeePsnl_isForeigner, String employeePsnl_residentNumber, String employeePsnl_adress,
			String employeePsnl_phoneNumber, String employeePsnl_email, String employeePsnl_sns) {
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
	}

	//게터 세터
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

}
