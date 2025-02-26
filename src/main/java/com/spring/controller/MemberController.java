package com.spring.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.MemberDTO;
import com.spring.service.MemberService;
import com.spring.validator.MemberValidator;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Resource(name = "loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;

	// 1. 1) 회원가입 페이지로 이동한다.
	@GetMapping("/join")
	public String join(@ModelAttribute("joinMemberDTO") MemberDTO joinMemberDTO) {
		return "member/join";
	}

	// 1. 2) 회원가입 완료버튼 누르기(with Error 유효성 검사, BindingResult result)
	@PostMapping("/join_proc")
	public String joinProc(@Valid @ModelAttribute("joinMemberDTO") MemberDTO joinMemberDTO, BindingResult result) {

		if (result.hasErrors()) {
			return "member/join";
		} else {
			memberService.addMemberInfo(joinMemberDTO);
			return "member/join_success";
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		MemberValidator validator = new MemberValidator();
		binder.addValidators(validator);
	}

	// 2. 1) 로그인 페이지로 이동
	@GetMapping("/login")
	public String login(@ModelAttribute("tmpLoginMemberDTO") MemberDTO loginMemberDTO,
			@RequestParam(value = "failure", defaultValue = "false") boolean failure, Model model) {
		model.addAttribute("failure", failure);
		return "member/login";
	}

	// 2. 2) 로그인 버튼 누르기
	@PostMapping("/login_proc")
	public String login_pro(@Valid @ModelAttribute("tmpLoginMemberDTO") MemberDTO tmpLoginMemberDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			return "member/login";
			
		} else {

			// 로그인 버튼을 진짜로 눌렀다.
			memberService.getLoginMemberDTO(tmpLoginMemberDTO);

			if (loginMemberDTO.isMemberLogin() == true) {
				return "member/login_success";
			} else {
				return "member/login_failure";
			}
		}
	}

	// 2. 3) 로그아웃 버튼 누르기
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		loginMemberDTO.setMemberLogin(false);
		session.invalidate();
		return "member/logout";
	}

	// 3. 1) 회원정보 수정페이지로 이동
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyMemberDTO") MemberDTO modifyMemberDTO) {
		memberService.getModifyMemberDTO(modifyMemberDTO);
		return "member/modify";
	}

	// 3. 2) 회원정보 수정 버튼 누르기
	@PostMapping("/modify_proc")
	public String modifyProc(@Valid @ModelAttribute("modifyMemberDTO") MemberDTO modifyMemberDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			return "member/modify";
		} else {
			memberService.modifyMemberInfo(modifyMemberDTO);
			return "member/modify_success";
		}

	}

	// 4. 1) 잃어버린 아이디 찾는 페이지로 이동한다.
	@RequestMapping(value = "/find_id_form")
	public String find_id_form() {
		return "/member/find_id_form";
	}

	// 4. 2)
	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public String find_id(HttpServletResponse response, 
			@RequestParam("member_email") String member_email, Model model) throws Exception {
		
		model.addAttribute("id", memberService.find_id(response, member_email));
		return "/member/find_id";
	}

	// 비번찾기 첫단�? 질문(question)�? member_id �??��?���?
	@RequestMapping(value = "/find_password_question")
	public String find_password_question() {
		return "/member/find_password_question";
	}

	@RequestMapping(value = "/find_password_answer", method = RequestMethod.POST)
	public String find_password_answer(HttpServletResponse response, @RequestParam("member_id") String member_id,
			Model model) throws Exception {

		model.addAttribute("memberDTO", memberService.find_question(response, member_id));
		return "/member/find_password_answer";
	}

	// 비번찾기 ?��?��?�� 질문?�� ???�� ?��?�� ?���? 본인?�� ?��?��?���? 보내�?
	@RequestMapping(value = "/find_password", method = RequestMethod.POST)
	public String find_password(MemberDTO answerAndId, Model model) {

		MemberDTO password = memberService.find_password(answerAndId);

		if (password == null) { // ?���? ?��?��?���? ?��?���?�? ?��?��결과�? ?��?��.
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("password", password.getMember_pw());
		}
		return "/member/find_password_answer";
	}

	// 4.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() throws Exception {
		return "member/delete";
	}

	// ?��?�� ?��?�� post
	@RequestMapping(value = "/delete_proc", method = RequestMethod.POST)
	public String delete_proc(MemberDTO deleteMemberDTO, HttpSession session, RedirectAttributes rttr)
			throws Exception {

		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");
		// 로그?��?�� ?�� ?��?��?�� 존재?��?�� 비�?번호
		String session_pw = loginMemberDTO.getMember_pw();
		// ?��?��?�� deleteMemberDTO�? ?��?�� ?�� ?��?��?��?�� �? 비�?번호
		String deleteMemberDTO_pw = deleteMemberDTO.getMember_pw();

		if (!(session_pw.equals(deleteMemberDTO_pw))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/delete";
		}
		memberService.delete(deleteMemberDTO);
		session.invalidate();
		return "redirect:/main";
	}

}