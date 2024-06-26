package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import exception.MemberNotFoundException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class FindIdService {

  private MemberDao memberDao = new MemberDao();

  public Member findIdByEmail(String member_email) {
    Connection conn = null;
    try {
      conn = ConnectionProvider.getConnection();

      // 이메일에 맞는 정보 불러오기
      // 이메일에 맞는 정보 없으면 예외처리
      // メールに対応する情報を取得する
      // メールに対応する情報がない場合は例外処理
      Member member = memberDao.selectByEmail(conn, member_email);
      if (member == null) {
        throw new MemberNotFoundException();
      }

      // 이메일에 해당하는 member 반환
      // メールに対応するメンバーを返す
      return member;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      JdbcUtil.close(conn);
    }
  }

}
