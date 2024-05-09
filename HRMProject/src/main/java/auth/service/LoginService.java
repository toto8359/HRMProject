package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {

  private MemberDao memberDao = new MemberDao();

  // 아이디 비번 입력받아,
  // IDとパスワードを入力してもらい、
  public User login(String member_id, String member_password) {

    try (Connection conn = ConnectionProvider.getConnection()) {

      // 아이디에 맞는 정보를 불러와 member에 담기
      // IDに対応する情報を呼び出して、それをメンバーに入れる
      Member member = memberDao.selectById(conn, member_id);

      // 불러온게 없으면 예외처리
      // 呼び出されたものがなければ例外 処理
      if (member == null) {
        throw new LoginFailException();
      }
      // 불러온 비번과, 입력받은 비번이 다르면 예외처리
      // 保存されたパスワードと入力されたパスワードが異なる場合は、例外
      // 処理を行います
      if (!member.matchPassword(member_password)) {
        throw new LoginFailException();
      }
      // 로그인 성공 및, 계정의 id와 이름 User에 담아 반환
      // ログイン成功時、アカウントのIDと名前をユーザ (User)に詰めて返します
      return new User(member.getMember_id(), member.getMember_name());

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
