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

	public boolean findCartGoods(CartDTO cartDTO) {
		return cartDAO.findCartGoods(cartDTO);
	}

	public void addGoodsInCart(CartDTO cartDTO) {
		cartDAO.addGoodsInCart(cartDTO);
	}

	public Map<String, List> getMyCart(String member_id) {

		Map<String, List> cartMap = new HashMap<String, List>();

		List<CartDTO> cartList = cartDAO.getMyCart(member_id);

		if (cartList.size() == 0) {
			return null;
		}

		cartMap.put("cartList", cartList);
		return cartMap;

	}

	public void delete(int cart_idx) {
		cartDAO.delete(cart_idx);
	}

	// 결제금액
	public int sum(String member_id) {
		return cartDAO.sum(member_id);
	}

	public void updateAmount(CartDTO updateAmountCartDTO) {
		cartDAO.updateAmount(updateAmountCartDTO);
	}

	public void orderInfo(OrderDTO newOrderDTO) {
		cartDAO.orderInfo(newOrderDTO);
	}

	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		cartDAO.orderInfoDetail(newOrderDetailDTO);
	}

	public void emptyMyCart(String member_id) {
		cartDAO.emptyMyCart(member_id);
	}

	public List<OrderDTO> orderPaymentList(String member_id) {

		List<OrderDTO> orderPaymentList = cartDAO.orderPaymentList(member_id);

		if (orderPaymentList.size() == 0) {
			return null;
		}
		return orderPaymentList;
	}

	public List<OrderListDTO> allInformationAboutOrder(OrderDTO idAndOrderIdxOrderDTO) {
		return cartDAO.allInformationAboutOrder(idAndOrderIdxOrderDTO);
	}

}