package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();

	public void changePassword(String userId, String curPwd, String newPwd, String newPwdConfirm) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);// 오토커밋 취소

			// 아이디에 맞는 정보 불러오기
			// 아이디가 없거나, 비번이 틀리면 예외처리
			Member member = memberDao.selectById(conn, userId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			if (!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			if (!newPwd.equals(newPwdConfirm)) {
				throw new InvalidPasswordConfirmException();
			}

			// member객체의 비번을 새것으로 바꾸고,
			// dao.update에 그 객체를 넣어 비번바꾸기
			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}