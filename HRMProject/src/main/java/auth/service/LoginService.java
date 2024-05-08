package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {

	private MemberDao memberDao = new MemberDao();
	
	//아이디 비번 입력받아, 
	public User login(String member_id, String member_password) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			//아디에 맞는 비번 불러와 member에 담기
			Member member = memberDao.selectById(conn, member_id);
			
			//불러온게 없으면 예외처리
			if (member == null) {
				throw new LoginFailException();
			}
			//불러온 비번과, 입력받은 비번이 다르면 예외처리
			if (!member.matchPassword(member_password)) {
				throw new LoginFailException();
			}
			//로그인 성공 및, 계정의 id와 이름 User에 담아 반환
			return new User(member.getMember_id(), member.getMember_name());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
