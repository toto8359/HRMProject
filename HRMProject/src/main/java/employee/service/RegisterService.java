package employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import employee.dao.EmployeeEplyDao;
import employee.dao.EmployeePsnlDao;
import employee.model.EmployeeEply;
import employee.model.EmployeePsnl;
import exception.DuplicateIdException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//사원등록을 위한 기능
public class RegisterService {

	// db내의 데이터 조회를 위해 dao인스턴스화
	private EmployeePsnlDao employeePsnlDao = new EmployeePsnlDao();
	private EmployeeEplyDao employeeEplyDao = new EmployeeEplyDao();

	// JoinRequest객체로 정보를 받아,
	public void Register(JoinRequest joinReq) {
		Connection conn = null;
		try {
			// db와의 소통을 위해 connection 생성
			conn = ConnectionProvider.getConnection();
			// 자동저장 방지를 위해 오토커밋 끄기
			conn.setAutoCommit(false);

			// 입력받은 주민번호의 정보가 있는지 조회, 있으면 정보 member에 담기
			EmployeePsnl employeePsnl = employeePsnlDao.selectByResidentNumber(conn,
					joinReq.getEmployeePsnl_residentNumber());

			// 주민번호에 해당하는 정보가 있으면 롤백
			if (employeePsnl != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			// 정보 입력(신상입력)
			employeePsnlDao.insert(conn,
					new EmployeePsnl(joinReq.getEmployeeNum(), joinReq.getEmployeePsnl_kname(),
							joinReq.getEmployeePsnl_ename(), joinReq.getEmployeePsnl_isForeigner(),
							joinReq.getEmployeePsnl_residentNumber(), joinReq.getEmployeePsnl_adress(),
							joinReq.getEmployeePsnl_phoneNumber(), joinReq.getEmployeePsnl_email(),
							joinReq.getEmployeePsnl_sns()));

			employeeEplyDao.insert(conn,
					new EmployeeEply(joinReq.getEmployeeNum(), joinReq.getEmployeeEply_employType(),
							joinReq.getEmployeeEply_depart(), joinReq.getEmployeeEply_position(),
							joinReq.getEmployeeEply_join(), joinReq.getEmployeeEply_resignation()));

			// 커밋(저장)
			conn.commit();

		} catch (SQLException e) {
			// 에러나면 롤백
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			// 사용한 connection 닫기
			JdbcUtil.close(conn);
		}

	}

}