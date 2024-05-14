package employee.service;

import java.util.Date;

public class InfoRequestVOERecord {

	private String recordNumber;
	private Date printDate;
	private String employeeNum;
	private String employeePsnl_kname;
	private String employeeEply_employType;
	private String employeeEply_depart;
	private String employeeEply_position;

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
