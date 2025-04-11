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

	public List<MemberDTO> getAllMember() {

		List<MemberDTO> allMemberList = adminDAO.getAllMember();
		return allMemberList;
	}

	public List<OrderDTO> getAllPayment() {
		List<OrderDTO> allPaymentList = adminDAO.getAllPayment();
		return allPaymentList;
	}

	public void changeDeliveryCircumstance(OrderDTO changeDeliveryOrderDTO) {
		adminDAO.changeDeliveryCircumstance(changeDeliveryOrderDTO);
	}

	public int numberOfResultSearchingMember(MemberDTO searchResultMemberDTO) {
		int search_result_count = adminDAO.numberOfResultSearchingMember(searchResultMemberDTO);
		return search_result_count;
	}

	public List<MemberDTO> resultSearchingMember(MemberDTO searchResultMemberDTO) {
		List<MemberDTO> memberList = adminDAO.resultSearchingMember(searchResultMemberDTO);
		return memberList;
	}

}