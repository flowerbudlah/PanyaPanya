
package com.spring.service;

import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.spring.dao.MemberDAO;
import com.spring.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Lazy
	@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;

	// 1. 1) 회원가입시 아이디 중복체크
	public boolean checkID(String member_id) {

		String checking_id = memberDAO.checkID(member_id);

		// 입력한 아이디는 존재하지 않기에 사용가능
		if (checking_id == null) {

			return true;

		// 입력한 그것은 이미 다른사람이 사용하고있다는 의미
		} else {
			return false;
		}
	}

	// 1. 2) 회원가입시 이메일 중복체크
	public boolean checkEmail(String member_email) {

		String checking_email = memberDAO.checkEmail(member_email);

		if (checking_email == null) {
			return true;
		} else {
			return false;
		}

	}

	// 1. 3) 회원가입(Sign Up)
	public void addMemberInfo(MemberDTO joinMemberDTO) {
		memberDAO.addUserInfo(joinMemberDTO);
	}

	// 2. 로그인(Sign In)
	public void getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		
		MemberDTO fromDBMemberDTO = memberDAO.getLoginMemberDTO(tmpLoginMemberDTO);

		if (fromDBMemberDTO != null) {
			loginMemberDTO.setMember_idx(fromDBMemberDTO.getMember_idx());
			loginMemberDTO.setMember_name(fromDBMemberDTO.getMember_name());
			loginMemberDTO.setMember_id(fromDBMemberDTO.getMember_id());
			loginMemberDTO.setMember_pw(fromDBMemberDTO.getMember_pw());
			loginMemberDTO.setMember_email(fromDBMemberDTO.getMember_email());
		
			loginMemberDTO.setMemberLogin(true);
		}
	}

	// 3. 1) 회원정보 수정시 이메일 중복체크
	public boolean checkEmailInModify(String member_email) {

		String checking_email = memberDAO.checkEmail(member_email);

		// 새롭게 사용하고자 입력한 이메일이 아무도 사용하지 않은 경우
		if (checking_email == null) {
			// 사용가능
			return true;

		// 새롭게 사용하고자 입력한 이메일을 이미 누가 사용하고 있는 경우
		} else {

			// 하지만 이 이메일이 내가 현재 사용하고있는 이메일이라면
			if (checking_email.equalsIgnoreCase(loginMemberDTO.getMember_email())) {
				// 당연 사용가능
				return true;

			} else {
				return false;
			}
		}
	}

	// 3. 2) 회원정보 수정하기 전에 수정 대상 회원정보 가져오고, 
	public void getModifyMemberDTO(MemberDTO modifyMemberDTO) {
		
		// 로그인 회원번호를 넣어서 DB에 있는 모든 나의 정보를 가져온다.  
		MemberDTO fromDBModifyMemberDTO = memberDAO.getModifyMemberDTO(loginMemberDTO.getMember_idx());

		modifyMemberDTO.setMember_id(fromDBModifyMemberDTO.getMember_id());
		modifyMemberDTO.setMember_name(fromDBModifyMemberDTO.getMember_name());
		modifyMemberDTO.setMember_pw(fromDBModifyMemberDTO.getMember_pw());
		modifyMemberDTO.setMember_pw2(fromDBModifyMemberDTO.getMember_pw2());
		modifyMemberDTO.setMember_tel(fromDBModifyMemberDTO.getMember_tel());
		modifyMemberDTO.setMember_address(fromDBModifyMemberDTO.getMember_address());
		modifyMemberDTO.setPostcode(fromDBModifyMemberDTO.getPostcode());
		modifyMemberDTO.setMember_email(fromDBModifyMemberDTO.getMember_email());
		modifyMemberDTO.setQuestion(fromDBModifyMemberDTO.getQuestion());
		modifyMemberDTO.setAnswer(fromDBModifyMemberDTO.getAnswer());

		modifyMemberDTO.setMember_idx(loginMemberDTO.getMember_idx());
	}
	
	// 3. 3) 회원정보 수정 버튼 누르기
	public void modifyMemberInfo(MemberDTO modifyMemberDTO) {
		modifyMemberDTO.setMember_idx(loginMemberDTO.getMember_idx());
		memberDAO.modifyMemberInfo(modifyMemberDTO);
	}

	// 4. 1) 로그인ID 분실한 경우, 회원가입시 입력한 이메일을 입력하여 ID를 찾는다. 
	public String find_id(HttpServletResponse response, String member_email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String id = memberDAO.find_id(member_email);

		// 회원정보가 없는 경우
		if (id == null) {
			out.println("<script>");
			out.println("alert('회원정보가 없습니다. 다시 한번 확인하시고 입력해주세요! Sorry, There is no informaton about you.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		
		// 회원정보가 있는경우
		} else {
			
			return id;
		}
	}

	// 5. 1) 로그인 비밀번호를 분실했을 경우, 우선 ID를 입력하여 본인확인 질문을 가져온다. 
	public MemberDTO find_question(HttpServletResponse response, String member_id) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		MemberDTO memberDTO = memberDAO.find_question(member_id);

		// 존재하지 않는 ID를 입력한 경우
		if (memberDTO == null) {
			out.println("<script>");
			out.println("alert('잘못된 답을 입력하셨습니다. 다시한번 확인하시고 입력해주세요! You entered an incorrect answer. Please check and enter it again!');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			// 가져올 본인확인 질문도 없다.
			return null;
		
		// 존재하는 ID를 입력한 경우
		} else {
			// 본인확인용 질문을 가져온다. 
			return memberDTO;
		}
	}

	// 5. 2) 본인확인 질문에 제대로 응답했다면 비밀번호를 알려준다.
	public MemberDTO find_password(MemberDTO answerAndId) {

		return memberDAO.find_password(answerAndId);
	}

	// 6. 회원탈퇴
	public void delete(MemberDTO deleteMemberDTO) throws Exception {
		memberDAO.delete(deleteMemberDTO);
	}
}