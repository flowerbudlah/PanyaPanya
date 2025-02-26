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

	@GetMapping("/order_payment_delivery")
	public String order_payment_delivery(Model model) {

		List<OrderDTO> allPaymentList = adminService.getAllPayment();
		model.addAttribute("allPaymentList", allPaymentList);

		return "admin/order_payment_delivery";
	}

	@GetMapping("/member")
	public String member_management(Model model) {

		List<MemberDTO> allMemberList = adminService.getAllMember();
		model.addAttribute("allMemberList", allMemberList);

		return "admin/member";
	}

	@PostMapping("/change")
	public String changeDeliveryCircumstance(
			@ModelAttribute("changeDeliveryOrderDTO") OrderDTO changeDeliveryOrderDTO) {

		adminService.changeDeliveryCircumstance(changeDeliveryOrderDTO);

		return "redirect:/admin/order_payment_delivery";
	}

	// ?��?���??��
	@GetMapping("/search_result")
	private String getSearchList(@RequestParam("type") String type, @RequestParam("keyword") String keyword,
			Model model) throws Exception {

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