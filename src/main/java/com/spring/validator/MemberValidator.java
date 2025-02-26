package com.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.dto.MemberDTO;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MemberDTO loginMemberDTO = (MemberDTO) target;

		String dtoName = errors.getObjectName();

		System.out.println("dtoName : " + dtoName);

		// 1. 회원가입시 또는 회원정보수정시 비밀번호 확인을 위한 처리(같은 비밀번호를 입력하지 않으면 다음단계로 진행불가)
		if (dtoName.equals("joinMemberDTO") || dtoName.equals("modifyMemberDTO")) {
			if (loginMemberDTO.getMember_pw().equals(loginMemberDTO.getMember_pw2()) == false) {
				errors.rejectValue("member_pw", "NotEquals");
				errors.rejectValue("member_pw2", "NotEquals");
			}
		}

		// 2. 회원가입 시 아이디 중복체크, 이메일 중복체크를 반드시 해야 하는 처리(이 처리를 진행하지 않으면 다음단계로 진행불가)
		if (dtoName.equals("joinMemberDTO")) {
			if (loginMemberDTO.isInputMemberID() == false) {
				errors.rejectValue("member_id", "DontCheckMemberIdExist");
			}
			if (loginMemberDTO.isInputMemberEmail() == false) {
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}
		}

		// 3. 회원정보 수정시 이메일 중복체크를 반드시 해야하는 처리
		if (dtoName.equals("modifyMemberDTO")) {

			if (loginMemberDTO.isInputMemberEmail() == false) {
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}

		}

	}
}