package com.spring.dto;

import java.util.Date;
// Pattern 어노테이션은 String 타입에만 사용.
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberDTO {
	private int member_idx;

	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*")
	private String member_name;

	@Size(min = 2, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_id;

	@Size(min = 2, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_pw;

	@Size(min = 2, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_pw2;

	private String member_email;

	private String member_tel;
	private String member_address;

	private int postcode;

	private String question;
	private String answer;

	private Date registerDate;

	private boolean memberLogin;
	
	private boolean inputMemberID;
	private boolean inputMemberEmail;

	public MemberDTO() {
		this.inputMemberID = false;
		this.inputMemberEmail = false;
	}

	// 관리자 모드로 로그인 시(ID: admin , Password: 1111) 회원관리페이지에서 검색할 경우 필요한 속성
	// In case that Signing in as administrator, the required properties are
	// displayed when Administrator searches specific user's information on the member management page.
	
	// Type은 검색할 대상이 "아이디"인지"이름"인지
	private String type;
	
	// Keyword 검색 대상의 이름
	private String keyword;

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_pw2() {
		return member_pw2;
	}

	public void setMember_pw2(String member_pw2) {
		this.member_pw2 = member_pw2;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_tel() {
		return member_tel;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public boolean isMemberLogin() {
		return memberLogin;
	}

	public void setMemberLogin(boolean memberLogin) {
		this.memberLogin = memberLogin;
	}

	public boolean isInputMemberID() {
		return inputMemberID;
	}

	public void setInputMemberID(boolean inputMemberID) {
		this.inputMemberID = inputMemberID;
	}

	public boolean isInputMemberEmail() {
		return inputMemberEmail;
	}

	public void setInputMemberEmail(boolean inputMemberEmail) {
		this.inputMemberEmail = inputMemberEmail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}