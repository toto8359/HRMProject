package member.model;

import java.util.Date;

public class Member {

  // Member 테이블에 정보를 담아 전달하기 위한 객체
  // Member テーブルに情報を格納して渡すためのオブジェクト
  private String member_id;
  private String member_name;
  private String member_password;
  private String member_passwordHint;
  private String member_passwordHintAnswer;
  private String member_email;
  private Date member_regDate;

  // 생성자
  // コンストラクタ
  public Member() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Member(String member_id, String member_name, String member_password,
      String member_passwordHint, String member_passwordHintAnswer, String member_email,
      Date member_regDate) {
    super();
    this.member_id = member_id;
    this.member_name = member_name;
    this.member_password = member_password;
    this.member_passwordHint = member_passwordHint;
    this.member_passwordHintAnswer = member_passwordHintAnswer;
    this.member_email = member_email;
    this.member_regDate = member_regDate;
  }

  // 게터 세터
  // ゲッター・セッター
  public String getMember_id() {
    return member_id;
  }

  public void setMember_id(String member_id) {
    this.member_id = member_id;
  }

  public String getMember_name() {
    return member_name;
  }

  public void setMember_name(String member_name) {
    this.member_name = member_name;
  }

  public String getMember_password() {
    return member_password;
  }

  public void setMember_password(String member_password) {
    this.member_password = member_password;
  }

  public String getMember_passwordHint() {
    return member_passwordHint;
  }

  public void setMember_passwordHint(String member_passwordHint) {
    this.member_passwordHint = member_passwordHint;
  }

  public String getMember_passwordHintAnswer() {
    return member_passwordHintAnswer;
  }

  public void setMember_passwordHintAnswer(String member_passwordHintAnswer) {
    this.member_passwordHintAnswer = member_passwordHintAnswer;
  }

  public String getMember_email() {
    return member_email;
  }

  public void setMember_email(String member_email) {
    this.member_email = member_email;
  }

  public Date getMember_regDate() {
    return member_regDate;
  }

  public void setMember_regDate(Date member_regDate) {
    this.member_regDate = member_regDate;
  }

  // 비밀번호 비교 후 참거짓으로 반환
  // パスワード比較後、真偽値を返す
  public boolean matchPassword(String pwd) {
    return member_password.equals(pwd);
  }

  // 비밀번호 바꾸기 위함
  // パスワード変更のため
  public void changePassword(String newPwd) {
    this.member_password = newPwd;
  }
}
