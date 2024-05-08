package auth.service;

public class User {

	// 로그인 성공시, 정보를 담아 보낼 VO

	private String member_id;
	private String member_name;

	//생성자
	public User() {
		super();
	}

	public User(String member_id, String member_name) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
	}

	//게터 세터
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

}
