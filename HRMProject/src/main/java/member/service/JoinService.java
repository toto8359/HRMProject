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

//회원가입을 위한 기능
//会員登録を行うための機能
public class JoinService {

	// db내의 데이터 조회를 위해 dao인스턴스화
	// データベース内のデータを取得するためにDAOをインスタンス化します
	private MemberDao memberDao = new MemberDao();

	// JoinRequest객체로 정보를 받아,
	// JoinRequestオブジェクトで情報を受け取り、
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			// db와의 소통을 위해 connection 생성
			// データベースとの通信のためにコネクションを生成します
			conn = ConnectionProvider.getConnection();
			// 자동저장 방지를 위해 오토커밋 끄기
			// 自動保存防止のためにオートコミットを無効にします
			conn.setAutoCommit(false);

			// 입력받은 아이디, 이메일의 정보가 있는지 조회, 있으면 정보 member에 담기
			// 入力されたIDとメールアドレスの情報があるかどうかを調査し、あれば情報をメンバーに格納します
			Member member = memberDao.selectById(conn, joinReq.getMember_id());
			Member member2 = memberDao.selectByEmail(conn, joinReq.getMember_email());

			// 이미 id, 이메일이 있으면 롤백
			// 既にIDやメールアドレスが存在する場合はロールバックします
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			if (member2 != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateEmailException();
			}

			// 정보 입력(회원가입)
			// 情報の入力(会員登録)
			memberDao.insert(conn,
					new Member(joinReq.getMember_id(), joinReq.getMember_name(), joinReq.getMember_password(),
							joinReq.getMember_passwordHint(), joinReq.getMember_passwordHintAnswer(),
							joinReq.getMember_email(), new Date()));

			// 커밋(저장)
			// コミット(保存)
			conn.commit();

		} catch (SQLException e) {
			// 에러나면 롤백
			// エラーが発生したらロールバック
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			// 사용한 connection 닫기
			// 使用したコネクションを閉じる
			JdbcUtil.close(conn);
		}

	}

}
