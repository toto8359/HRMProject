package employee.model;

import java.util.Date;

public class VOEPrintRecord {

	//재직증명서 발급기록
	//증명서 문서번호, 발급일시, 발급대상 사원번호
	private String recordNumber;
	private Date printDate;
	private String employeeNum;

	public VOEPrintRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VOEPrintRecord(String recordNumber, Date printDate, String employeeNum) {
		super();
		this.recordNumber = recordNumber;
		this.printDate = printDate;
		this.employeeNum = employeeNum;
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

}
