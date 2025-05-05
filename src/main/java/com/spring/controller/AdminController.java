package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.MemberDTO;
import com.spring.dto.OrderDTO;
import com.spring.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// 1. 1) 관리자 모드로 로그인(ID: admin 비밀번호: 1111)을 했을때 고객의 주문, 결제, 배송정보을 제공하는 관리자 페이지로 접속한다.  
	// When you sign in as administrator (ID: admin , Password: 1111), 
	// you will access into the administrator page that shows you the order, payment and delivery status of customers. 
	@GetMapping("/order_payment_delivery")
	public String order_payment_delivery(Model model) {

		List<OrderDTO> allPaymentList = adminService.getAllPayment();
		model.addAttribute("allPaymentList", allPaymentList);

		return "admin/order_payment_delivery";
	}

	// 1. 2) 주문, 결제, 배송정보을 제공하는 관리자 페이지 안에서 배송상황 변경
	// Changing delivery status In Administrator Only page providing order, payment, and delivery information. 
	@PostMapping("/change")
	public String changeDeliveryCircumstance(@ModelAttribute("changeDeliveryOrderDTO") OrderDTO changeDeliveryOrderDTO) {

		adminService.changeDeliveryCircumstance(changeDeliveryOrderDTO);

		return "redirect:/admin/order_payment_delivery";
	}
	
	// 2. 1) 관리자 모드로 로그인(ID: admin 비밀번호: 1111)을 했을때 쇼핑몰에 가입한 회원목록이 출력되는 관리자 페이지로 접속한다. 
	// When you sign in as administrator mode (ID: admin Password: 1111), 
	// you will access into the administrator page that shows you the list of  members. 
	@GetMapping("/member")
	public String member_management(Model model) {

		List<MemberDTO> allMemberList = adminService.getAllMember();
		model.addAttribute("allMemberList", allMemberList);

		return "admin/member";
	}

	// 2. 2) 쇼핑몰에 가입한 회원목록이 출력되는 관리자 페이지에서 회원검색 
	// The function that You can search a specific member on the administrator Only page that shows The list of Member. 
	@GetMapping("/search_result")
	private String getSearchList(@RequestParam("type") String type, @RequestParam("keyword") String keyword, Model model) throws Exception {

		MemberDTO searchResultMemberDTO = new MemberDTO();
		searchResultMemberDTO.setType(type);
		searchResultMemberDTO.setKeyword(keyword);

		int search_result_count = adminService.numberOfResultSearchingMember(searchResultMemberDTO);
		model.addAttribute("search_result_count", search_result_count);

		List<MemberDTO> memberList = adminService.resultSearchingMember(searchResultMemberDTO);
		model.addAttribute("memberList", memberList);

		return "admin/search_result";
	}

}