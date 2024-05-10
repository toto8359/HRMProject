package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import exception.InvalidPasswordConfirmException;
import exception.InvalidPasswordException;
import exception.MemberNotFoundException;
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
      // オートコミットをキャンセル

      // 아이디에 맞는 정보 불러오기
      // 아이디가 없거나, 비번이 틀리면 예외처리
      // IDに対応する情報を取得する
      // IDがない場合や、パスワードが間違っている場合は例外処理を行う
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
      // memberオブジェクトのパスワードを新しいものに変更し、
      // dao.updateにそのオブジェクトを渡してパスワードを変更する
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
