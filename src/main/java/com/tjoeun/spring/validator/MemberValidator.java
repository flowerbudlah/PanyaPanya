package com.tjoeun.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.spring.dto.MemberDTO;

//직접 만든 벨리데이터 
public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MemberDTO loginMemberDTO = (MemberDTO)target;
		
		String dtoName = errors.getObjectName();
		
		System.out.println("dtoName : " + dtoName);
		
		//패스워드를 위 아래 같은 걸 입력해야 
		if(dtoName.equals("joinMemberDTO") || dtoName.equals("modifyMemberDTO")) {
			if(loginMemberDTO.getMember_pw().equals( loginMemberDTO.getMember_pw2() ) == false) { 
				errors.rejectValue("member_pw", "NotEquals");
				errors.rejectValue("member_pw2", "NotEquals");
			}
		}
		
		//회원가입 시 아이디 중복체크, 이메일 중복체크를 반드시 해야 하는 
		if(dtoName.equals("joinMemberDTO")){
			if(loginMemberDTO.isInputMemberID() == false) {	
				errors.rejectValue("member_id", "DontCheckMemberIdExist");
			}
			if(loginMemberDTO.isInputMemberEmail() == false) {	
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}
		}
	
		//회원정보 수정시 이메일 중복체크를 반드시 해야하는 
		if(dtoName.equals("modifyMemberDTO")){
			
			if(loginMemberDTO.isInputMemberEmail() == false) { 
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}
			
		}
	
	
	}
}