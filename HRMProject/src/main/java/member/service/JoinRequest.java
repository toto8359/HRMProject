package member.service;

import java.util.Map;

public class JoinRequest {

  private String member_id;
  private String member_name;
  private String member_password;
  private String confirmPassword;
  private String member_passwordHint;
  private String member_passwordHintAnswer;
  private String member_email;

  // 생성자
  // コンストラクタ
  public JoinRequest() {
    super();
    // TODO Auto-generated constructor stub
  }

  public JoinRequest(String member_id, String member_name, String member_password,
      String confirmPassword, String member_passwordHint, String member_passwordHintAnswer,
      String member_email) {
    super();
    this.member_id = member_id;
    this.member_name = member_name;
    this.member_password = member_password;
    this.confirmPassword = confirmPassword;
    this.member_passwordHint = member_passwordHint;
    this.member_passwordHintAnswer = member_passwordHintAnswer;
    this.member_email = member_email;
  }

  // 게터 세터
  // ゲッターとセッター
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

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
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

  // 비밀번호와, 비밀번호 확인이 같은지 체크(비밀번호 잘못 저장하지 않도록)
  // パスワードとパスワードの確認が一致しているかチェック（パスワードを誤って保存しないようにするため）
  public boolean isPasswordEqualToConfirm() {
    return member_password != null && member_password.equals(confirmPassword);
  }

  // 회원가입창의 공란 확인, map형태 errors.notMatch로 저장
  // 会員登録画面の空欄を確認し、errors.notMatchに保存
  public void validate(Map<String, Boolean> errors) {
    checkEmpty(errors, member_id, "member_id");
    checkEmpty(errors, member_name, "member_name");
    checkEmpty(errors, member_password, "member_password");
    checkEmpty(errors, confirmPassword, "confirmPassword");
    checkEmpty(errors, member_passwordHint, "member_passwordHint");
    checkEmpty(errors, member_passwordHintAnswer, "member_passwordHintAnswer");
    checkEmpty(errors, member_email, "member_email");
    if (!errors.containsKey("confirmPassword")) {
      if (!isPasswordEqualToConfirm()) {
        errors.put("notMatch", Boolean.TRUE);
      }
    }
  }

  // Map형 errors에, value값이 null이거나 비어있으면, 에러 저장
  // Map形式のerrorsに、value値がnullまたは空の場合、エラーを保存
  private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
    if (value == null || value.isEmpty()) {
      errors.put(fieldName, Boolean.TRUE);
    }
  }
}
