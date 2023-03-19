package com.tjoeun.spring.service;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.MemberDAO;
import com.tjoeun.spring.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Lazy
	@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;	
	
	//1. ?•„?´?”” ì¤‘ë³µì²´í¬
	public boolean checkID(String member_id) {
		String checking_id = memberDAO.checkID(member_id); 
		if(checking_id == null) {	
			return true; //?…? ¥?•œ ?•„?´?””ê°? ì¡´ì¬?•˜ì§? ?•Šê¸°ì— ?…? ¥?•œ ?•„?´?”” ?‚¬?š©ê°??Š¥. 
		} else {	
			return false; //?…? ¥?•œ ?•„?´?”” ?´ë¯? ì¡´ì¬?•˜ê¸°ì— ?‚¬?š©ë¶ˆê?
		}
	}
	
	//2. ?´ë©”ì¼ ì¤‘ë³µì²´í¬ (?šŒ?›ê°??…?‹œ) 
	public boolean checkEmail(String member_email) {
		String checking_email = memberDAO.checkEmail(member_email); 
		if(checking_email == null) {	
			return true; //?…? ¥?•œ ?•„?´?””ê°? ì¡´ì¬?•˜ì§? ?•Šê¸°ì— ?…? ¥?•œ ?•„?´?”” ?‚¬?š©ê°??Š¥. 
		} else {
			return false; //?…? ¥?•œ ?•„?´?”” ?´ë¯? ì¡´ì¬?•˜ê¸°ì— ?‚¬?š©ë¶ˆê?
		}
	}
	
	//2-1. ?´ë©”ì¼ ì¤‘ë³µì²´í¬(?šŒ?›? •ë³´ìˆ˜? •?‹œ)
	public boolean checkEmailInModify(String member_email) {
		System.out.println("====================================================================="); 
		System.out.println("member_email: "+member_email); 
		System.out.println("loginMemberDTO.getMember_email(): " +loginMemberDTO.getMember_email());
	
		String checking_email = memberDAO.checkEmail(member_email); 
		System.out.println("checking_email: "+checking_email); 
		System.out.print(loginMemberDTO.getMember_email().equalsIgnoreCase(checking_email) ); //ê°™ìœ¼ë©? true, ?‹¤ë¥´ë©´ false
	
		if(checking_email == null ) {	
			return true; //?…? ¥?•œ ?•„?´?””ê°? ì¡´ì¬?•˜ì§? ?•Šê¸°ì— ?…? ¥?•œ ?•„?´?”” ?‚¬?š©ê°??Š¥. 
		} else {  //checking_email != null
			if(checking_email.equalsIgnoreCase(loginMemberDTO.getMember_email())){
				return true;
			} else {
				return false; //?…? ¥?•œ ?•„?´?”” ?´ë¯? ì¡´ì¬?•˜ê¸°ì— ?‚¬?š©ë¶ˆê?
			}
		}
	}
	
	
	//3. ?šŒ?›ê°??…
	public void addMemberInfo(MemberDTO joinMemberDTO) {
		memberDAO.addUserInfo(joinMemberDTO);
	}
 
	//ë¡œê·¸?¸
	public void getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		MemberDTO fromDBMemberDTO = memberDAO.getLoginMemberDTO(tmpLoginMemberDTO);
		
		if(fromDBMemberDTO != null) {
			loginMemberDTO.setMember_idx(fromDBMemberDTO.getMember_idx());
			loginMemberDTO.setMember_name(fromDBMemberDTO.getMember_name());
			loginMemberDTO.setMember_id(fromDBMemberDTO.getMember_id());
			loginMemberDTO.setMember_pw(fromDBMemberDTO.getMember_pw());
			loginMemberDTO.setMember_email(fromDBMemberDTO.getMember_email());
	
			loginMemberDTO.setMemberLogin(true);
		}
	}
	
	//?šŒ?›? •ë³´ë?? ?ˆ˜? •?•  ???ƒ ?šŒ?›? •ë³? ê°?? ¸?˜¤ê¸?. 
	public void getModifyMemberDTO(MemberDTO modifyMemberDTO) {
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

	//?šŒ?›? •ë³? ?ˆ˜? • ë²„íŠ¼ ?ˆ„ë¥´ê¸° 
	public void modifyMemberInfo(MemberDTO modifyMemberDTO){
		modifyMemberDTO.setMember_idx(loginMemberDTO.getMember_idx());
		memberDAO.modifyMemberInfo(modifyMemberDTO);
	}
	
	//?šŒ?›?ƒˆ?‡´
	public void delete(MemberDTO deleteMemberDTO) throws Exception{
		memberDAO.delete(deleteMemberDTO);
	}
	

	public String find_id(HttpServletResponse response, String member_email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = memberDAO.find_id(member_email);
		
		if (id == null) {
			out.println("<script>");
			out.println("alert('åª›ï¿½ï¿½ì—¯ï¿½ë§‚ ï¿½ë¸˜ï¿½ì” ï¿½ëµ’åª›ï¿½ ï¿½ë¾¾ï¿½ë’¿ï¿½ë•²ï¿½ë–.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return id;
		}
	}
	
	public MemberDTO find_question(HttpServletResponse response, String member_id) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDTO memberDTO = memberDAO.find_question(member_id);
		
		if (memberDTO == null) {
			out.println("<script>");
			out.println("alert('ï¿½ì‰¶ï¿½ìåª›ï¿½ï¿½ì—¯ï¿½ë–† ?®ê¾¨ì¾²?ºê¾©ë–ï¿½ë–† ï§ëˆĞ¦ï¿½ì“£ ï¿½ì—¯ï¿½ì °ï¿½ë¸¯ï§ï¿½ ï¿½ë¸¡ï¿½ì‘ï¿½ë?²å«„ê³•êµ¹ ï¿½ìƒŠï¿½ï¿½ åª›ï¿½ï¿½ì—¯ï¿½ë§‚ ï¿½ì ™è¹‚ë‹¿ï¿? ï¿½ë¾¾ï¿½ë’¿ï¿½ë•²ï¿½ë–.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return memberDTO;
		} 
	}
	
	public MemberDTO find_password(MemberDTO answerAndId) {
		return memberDAO.find_password(answerAndId);
		
	}
	

	
	
	}