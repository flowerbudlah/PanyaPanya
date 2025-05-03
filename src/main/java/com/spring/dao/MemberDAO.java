package com.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 1. 1) 회원가입시 아이디 중복체크 기능 (The function of Checking duplicate ID when signing
	// up for membership.)
	public String checkID(String member_id) {
		String checking_id = sqlSessionTemplate.selectOne("member.checkID", member_id);
		return checking_id;
	}

	// 1. 2) 회원가입시 이메일 중복체크 기능 (The function of Checking duplicate Email when
	// signing up for membership.)
	public String checkEmail(String member_email) {
		String checking_email = sqlSessionTemplate.selectOne("member.checkEmail", member_email);
		return checking_email;
	}

	// 1. 3) 회원가입(Signing Up)
	public void addUserInfo(MemberDTO joinMemberDTO) {
		sqlSessionTemplate.insert("member.addMemberInfo", joinMemberDTO);
	}

	// 2. 로그인(Sign in)
	public MemberDTO getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		MemberDTO fromDBMemberDTO = sqlSessionTemplate.selectOne("member.getLoginMember", tmpLoginMemberDTO);
		return fromDBMemberDTO;
	}

	// 3. 1) 회원정보를 수정하기 위해서 수정하고자 하는 회원정보 불러오기 (To modify a member information, Getting the member information you want to edit from DB. )
	public MemberDTO getModifyMemberDTO(int member_idx) {
		MemberDTO fromDBModifyMemberDTO = sqlSessionTemplate.selectOne("member.getModifyMemberDTO", member_idx);
		return fromDBModifyMemberDTO;
	}

	// 3. 2) 회원정보 수정의 완료(Completing the Member information modification. )
	public void modifyMemberInfo(MemberDTO modifyMemberDTO) {
		sqlSessionTemplate.update("member.modifyMemberInfo", modifyMemberDTO);
	}

	// 4. 1) 아이디를 분실했을 경우, 회원가입 시 입력했던 본인의 이메일을 입력해서 아이디를 찾는다.
	// If you forgot your ID, You will find it By Entering Your email address you entered when signing up.
	public String find_id(String member_email) throws Exception {
		return sqlSessionTemplate.selectOne("member.find_id", member_email);
	}

	// 4. 2) 비밀번호를 분실했다면, 본인의 아이디를 입력하고 정보확인 질문을 가져온다.
	// Taking the security question to verify your identity information after entering your ID in case that you have lost password.  
	public MemberDTO find_question(String member_id) throws Exception {
		return sqlSessionTemplate.selectOne("member.find_question", member_id);
	}

	// 4. 3) 본인확인 질문에 알맞은 답을 하면 분실한 비밀번호를 알려준다.
	// If you answer your security question correctly, You can find the lost password.
	public MemberDTO find_password(MemberDTO answerAndId) {
		return sqlSessionTemplate.selectOne("member.find_password", answerAndId);
	}

	// 5. 회원탈퇴 Deleting user account
	public void delete(MemberDTO deleteMemberDTO) throws Exception {
		sqlSessionTemplate.delete("member.delete", deleteMemberDTO);
	}

}