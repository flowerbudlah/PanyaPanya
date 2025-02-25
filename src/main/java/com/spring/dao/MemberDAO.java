package com.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public String checkID(String member_id) {
		String checking_id = sqlSessionTemplate.selectOne("member.checkID", member_id);
		return checking_id;
	}

	public String checkEmail(String member_email) {
		String checking_email = sqlSessionTemplate.selectOne("member.checkEmail", member_email);
		return checking_email;
	}

	public void addUserInfo(MemberDTO joinMemberDTO) {
		sqlSessionTemplate.insert("member.addMemberInfo", joinMemberDTO);
	}

	public MemberDTO getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		MemberDTO fromDBMemberDTO = sqlSessionTemplate.selectOne("member.getLoginMember", tmpLoginMemberDTO);
		return fromDBMemberDTO;
	}

	// 회원정보수정
	public MemberDTO getModifyMemberDTO(int member_idx) {
		MemberDTO fromDBModifyMemberDTO = sqlSessionTemplate.selectOne("member.getModifyMemberDTO", member_idx);
		return fromDBModifyMemberDTO;
	}

	// 회원정보수정버튼누르고
	public void modifyMemberInfo(MemberDTO modifyMemberDTO) {
		sqlSessionTemplate.update("member.modifyMemberInfo", modifyMemberDTO);
	}

	public void delete(MemberDTO deleteMemberDTO) throws Exception {
		sqlSessionTemplate.delete("member.delete", deleteMemberDTO);
	}

	public String find_id(String member_email) throws Exception {
		return sqlSessionTemplate.selectOne("member.find_id", member_email);
	}

	public MemberDTO find_question(String member_id) throws Exception {
		return sqlSessionTemplate.selectOne("member.find_question", member_id);
	}

	public MemberDTO find_password(MemberDTO answerAndId) {
		return sqlSessionTemplate.selectOne("member.find_password", answerAndId);
	}

}