package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import exception.MemberNotFoundException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class FindPwService {

	private MemberDao memberDao = new MemberDao();

	public Member findId(String member_id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			// 아이디에 맞는 정보 불러오기
			// 아이디가 없거나, 비번이 틀리면 예외처리
			Member member = memberDao.selectById(conn, member_id);
			if (member == null) {
				throw new MemberNotFoundException();
			}

			// 아이디에 해당하는 정보 반환
			return member;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
