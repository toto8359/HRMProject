package employee.service;

import java.util.Date;

public class InfoRequestVOERecord {

	//재직증명서 발급대장 출력을 위한 VO
	
	private String recordNumber; //발급대장 문서번호
	private Date printDate; //발급일시
	private String employeeNum; //사원번호
	private String employeePsnl_kname; //국문이름
	private String employeeEply_employType; //고용 형태
	private String employeeEply_depart; //부서
	private String employeeEply_position; //직급

	//생성자
	public InfoRequestVOERecord() {
		super();
	}

	public InfoRequestVOERecord(String recordNumber, Date printDate, String employeeNum, String employeePsnl_kname,
			String employeeEply_employType, String employeeEply_depart, String employeeEply_position) {
		super();
		this.recordNumber = recordNumber;
		this.printDate = printDate;
		this.employeeNum = employeeNum;
		this.employeePsnl_kname = employeePsnl_kname;
		this.employeeEply_employType = employeeEply_employType;
		this.employeeEply_depart = employeeEply_depart;
		this.employeeEply_position = employeeEply_position;
	}

	//게터 세터
	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

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

}
