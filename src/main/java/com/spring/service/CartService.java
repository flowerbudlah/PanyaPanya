package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CartDAO;
import com.spring.dto.CartDTO;
import com.spring.dto.OrderDTO;
import com.spring.dto.OrderDetailDTO;
import com.spring.dto.OrderListDTO;

@Service
public class CartService {

	@Autowired
	private CartDAO cartDAO;

	// 1. 1) 장바구니 안에 물건을 넣기 전에 이미 그 물건이 있는지 없는지 부터 확인
	public boolean findCartGoods(CartDTO cartDTO) {
		return cartDAO.findCartGoods(cartDTO);
	}

	// 1. 2) 장바구니에 물건 넣기
	public void addGoodsInCart(CartDTO cartDTO) {
		cartDAO.addGoodsInCart(cartDTO);
	}

	// 2. 1) 내 장바구니 안에 들어있는 물건목록 (List of items in my shopping cart)
	public Map<String, List> getMyCart(String member_id) {

		Map<String, List> cartMap = new HashMap<String, List>();

		List<CartDTO> cartList = cartDAO.getMyCart(member_id);

		if (cartList.size() == 0) {
			return null;
			
		} else {
			cartMap.put("cartList", cartList);
			return cartMap;

		}

	}

	// 2. 2) 특정회원의 장바구니 안에있는 물품의 결제금액 (배송비 불포함)
	public int sum(String member_id) {
		return cartDAO.sum(member_id);
	}

	// 2. 3) 장바구니 안에있는 특정물품의 수량변경
	public void updateAmount(CartDTO updateAmountCartDTO) {
		cartDAO.updateAmount(updateAmountCartDTO);
	}

	// 2. 4) 장바구니 주인이 장바구니 안에 있는 물건 비우는 기능(삭제)
	public void delete(int cart_idx) {
		cartDAO.delete(cart_idx);
	}

	// 2. 5) 장바구니안에 들어있는 물품을 결제할 때의 주문정보
	public void orderInfo(OrderDTO newOrderDTO) {
		cartDAO.orderInfo(newOrderDTO);
	}

	// 2. 6) 장바구니안에 들어있는 물품을 결제할 때의 주문상세정보(물건의 종류, 수량 등의 정보까지 포함)
	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		cartDAO.orderInfoDetail(newOrderDetailDTO);
	}

	// 2. 7) 주문이 완료됨과 동시에 장바구니에 있는 물건을 비우는 과정
	public void emptyMyCart(String member_id) {
		cartDAO.emptyMyCart(member_id);
	}

	// 3. 1) 로그인한 특정회원의 주문정보 페이지(주문·결제·배송 정보)로 이동한 경우, 이 특정 회원의 주문결제정보 목록이 출력 
	public List<OrderDTO> orderPaymentList(String member_id) {

		List<OrderDTO> orderPaymentList = cartDAO.orderPaymentList(member_id);

		if (orderPaymentList.size() == 0) {
			return null;
			
		} else {
		
			return orderPaymentList;
		}
	}

	// 3. 2) 특정회원의 주문상세정보(어떤물건을 몇개 구입하였는지 이런정보가 다 출력된다.)
	// (1) 이 정보는 회원 로그인(user sign-in)을 하여 주문결제배송현황 페이지에서 접속가능
	// (2) 관리자 로그인(admin sign-in)하여 주문·결제·배송 현황에 관한 관리자페이지에서 주문내역 자세히 보기에서도 접속가능
	public List<OrderListDTO> allInformationAboutOrder(String order_idx) {
		return cartDAO.allInformationAboutOrder(order_idx);
	}

}