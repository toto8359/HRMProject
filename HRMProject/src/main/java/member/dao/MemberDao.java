package member.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;

import member.model.Member;

public class MemberDao {

	//db에서 아이디에 맞는 정보 불러오기
	public Member selectById(Connection conn, String member_id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from member where member_id = ?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("member_id"), rs.getString("member_name"),
						rs.getString("member_password"), rs.getString("member_passwordHint"),
						rs.getString("member_passwordHintAnswer"), rs.getString("member_email"),
						toDate(rs.getTimestamp("member_regDate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	//db에서 이메일에 맞는 정보 불러오기
	public Member selectByEmail(Connection conn, String member_email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select * from member where member_email = ?");
			pstmt.setString(1, member_email);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("member_id"), rs.getString("member_name"),
						rs.getString("member_password"), rs.getString("member_passwordHint"),
						rs.getString("member_passwordHintAnswer"), rs.getString("member_email"),
						toDate(rs.getTimestamp("member_regDate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 시간이 비어있으면, 생성 및 반환
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

	// 입력받은 정보 db입력
	public void insert(Connection conn, Member mem) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("insert into member values(?,?,?,?,?,?,?)");
		pstmt.setString(1, mem.getMember_id());
		pstmt.setString(2, mem.getMember_name());
		pstmt.setString(3, mem.getMember_password());
		pstmt.setString(4, mem.getMember_passwordHint());
		pstmt.setString(5, mem.getMember_passwordHintAnswer());
		pstmt.setString(6, mem.getMember_email());
		pstmt.setTimestamp(7, new Timestamp(mem.getMember_regDate().getTime()));
		pstmt.executeUpdate();

	}

	// 입력받은 정보로, db내의 정보 수정
	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update member set member_name = ?, member_password = ?, member_passwordHint = ?, member_passwordHintAnswer = ?, member_email = ? where member_id = ?")) {
			pstmt.setString(1, member.getMember_name());
			pstmt.setString(2, member.getMember_password());
			pstmt.setString(3, member.getMember_passwordHint());
			pstmt.setString(4, member.getMember_passwordHintAnswer());
			pstmt.setString(5, member.getMember_email());
			pstmt.setString(6, member.getMember_id());
			pstmt.executeUpdate();
		}
	}

}
