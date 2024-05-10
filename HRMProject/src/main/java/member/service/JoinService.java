package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import exception.DuplicateEmailException;
import exception.DuplicateIdException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

// 회원가입을 위한 기능
// 会員登録を行うための機能
public class JoinService {

  // db내의 데이터 조회를 위해 dao인스턴스화
  // データベース内のデータを檢索するためにdaoをインスタンス化
  private MemberDao memberDao = new MemberDao();

  // JoinRequest객체로 정보를 받아,
  // JoinRequestオブジェクトで情報を受け取り、
  public void join(JoinRequest joinReq) {
    Connection conn = null;
    try {
      // db와의 소통을 위해 connection 생성
      // データベースとの通信のためのconnectionを作成
      conn = ConnectionProvider.getConnection();
      // 자동저장 방지를 위해 오토커밋 끄기
      // 自動 貯蔵防止のため、オートコミットを無効にする
      conn.setAutoCommit(false);

      // 입력받은 아이디, 이메일의 정보가 있는지 조회, 있으면 정보 member에 담기
      // 入力されたID、メールの情報があるかを検索し、あれば情報をmemberに格納
      Member member = memberDao.selectById(conn, joinReq.getMember_id());
      Member member2 = memberDao.selectByEmail(conn, joinReq.getMember_email());

      // 이미 id, 이메일이 있으면 롤백
      // すでにID、メールがある場合はロールバック
      if (member != null) {
        JdbcUtil.rollback(conn);
        throw new DuplicateIdException();
      }
      if (member2 != null) {
        JdbcUtil.rollback(conn);
        throw new DuplicateEmailException();
      }

      // 정보 입력(회원가입)
      // 情報入力（会員登録）
      memberDao.insert(conn,
          new Member(joinReq.getMember_id(), joinReq.getMember_name(), joinReq.getMember_password(),
              joinReq.getMember_passwordHint(), joinReq.getMember_passwordHintAnswer(),
              joinReq.getMember_email(), new Date()));

      // 커밋(저장)
      // コミット（貯蔵）
      conn.commit();

    } catch (SQLException e) {
      // 에러나면 롤백
      // エラーが発生した場合はロールバック
      JdbcUtil.rollback(conn);
      throw new RuntimeException(e);
    } finally {
      // 사용한 connection 닫기
      // 使用したconnectionを閉じる
      JdbcUtil.close(conn);
    }

  }

}
