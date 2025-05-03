package com.spring.dto;

import java.util.Date;
// Pattern 어노테이션은 String 타입에만 사용.
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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

}