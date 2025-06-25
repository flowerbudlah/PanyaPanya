package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.AdminDAO;
import com.spring.dto.MemberDTO;
import com.spring.dto.OrderDTO;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;

	// 1. 1) 회원목록이 출력 (in 회원정보 제공하는 관리자 페이지)
	public List<MemberDTO> getAllMember() {
		List<MemberDTO> allMemberList = adminDAO.getAllMember();
		return allMemberList;
	}

	// 1. 2) 특정 회원검색 (in 회원정보 제공하는 관리자 페이지)
	public List<MemberDTO> resultSearchingMember(MemberDTO searchResultMemberDTO) {
		List<MemberDTO> memberList = adminDAO.resultSearchingMember(searchResultMemberDTO);
		return memberList;
	}
	
	// 1. 3) 검색된 회원 수 (in 회원정보 제공하는 관리자 페이지)
	public int numberOfResultSearchingMember(MemberDTO searchResultMemberDTO) {
		int search_result_count = adminDAO.numberOfResultSearchingMember(searchResultMemberDTO);
		return search_result_count;
	}

	// 2. 1) 주문결제정보가 출력 (in 주문, 결제, 배송정보을 제공하는 관리자 페이지)
	public List<OrderDTO> getAllPayment() {
		List<OrderDTO> allPaymentList = adminDAO.getAllPayment();
		return allPaymentList;
	}

	// 2. 2) 배송상황 변경 (in 주문, 결제, 배송정보을 제공하는 관리자 페이지)
	public void changeDeliveryCircumstance(OrderDTO changeDeliveryOrderDTO) {
		adminDAO.changeDeliveryCircumstance(changeDeliveryOrderDTO);
	}

}