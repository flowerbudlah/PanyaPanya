package com.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.MemberDTO;
import com.spring.dto.OrderDTO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 1. 1) 모든 회원목록 가져오기 (in 회원관리 관리자 전용페이지) 
	public List<MemberDTO> getAllMember() {
		List<MemberDTO> allMemberList = sqlSessionTemplate.selectList("admin.getAllMember");
		return allMemberList;
	}
	
	// 1. 2) 특정 회원검색 (in 회원관리 관리자 전용페이지)
	public List<MemberDTO> resultSearchingMember(MemberDTO searchResultMemberDTO) {
		return sqlSessionTemplate.selectList("admin.resultSearchingMember", searchResultMemberDTO);
	}

	// 1. 3) 회원 검색결과 수 (in 회원관리 관리자 전용페이지)
	public int numberOfResultSearchingMember(MemberDTO searchResultMemberDTO) {
		int search_result_count = sqlSessionTemplate.selectOne("admin.numberOfResultSearchingMember", searchResultMemberDTO);
		return search_result_count;
	}

	// 2. 1) 모든 주문 등의 정보에 관한 목록 가져오기 (in 주문·결제·배송 현황 관리자 전용페이지)
	public List<OrderDTO> getAllPayment() {
		List<OrderDTO> allPaymentList = sqlSessionTemplate.selectList("admin.getAllPayment");
		return allPaymentList;
	}

	// 2. 2) 배송상황(결제완료, 배송준비중, 배송중, 배송완료) 변경 (in 주문·결제·배송 현황 관리자 전용페이지)
	public void changeDeliveryCircumstance(OrderDTO changeDeliveryOrderDTO) {
		sqlSessionTemplate.update("admin.changeDeliveryCircumstance", changeDeliveryOrderDTO);
	}

}