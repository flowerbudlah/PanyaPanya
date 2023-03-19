package com.tjoeun.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.CartDAO;
import com.tjoeun.spring.dto.CartDTO;
import com.tjoeun.spring.dto.OrderDTO;
import com.tjoeun.spring.dto.OrderDetailDTO;
import com.tjoeun.spring.dto.OrderListDTO;



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
	
	
	// ?��바구?�� 목록
	public Map<String, List> getMyCart(String member_id) {
		
		Map<String, List> cartMap = new HashMap<String, List>();
		
		List<CartDTO> cartList = cartDAO.getMyCart(member_id);
		
		if (cartList.size() == 0) {
			return null;
		}
		
		cartMap.put("cartList", cartList);
			return cartMap;
				
	}
	

	//?��바구?��?��?��?��?�� �? 물건 ?��?�� �??���?
	public void delete(int cart_idx) {
		cartDAO.delete(cart_idx);
	}

	//?��바구?�� 금액?���?
	public int sum(String member_id) {
		return cartDAO.sum(member_id);
	}
	
	//?��바구?��?��?��?��?�� 물건?�� ?��?���?�? 
	public void updateAmount(CartDTO updateAmountCartDTO) {
		cartDAO.updateAmount(updateAmountCartDTO); 
	}
	
	//1.결제
	//1) 주문?��?�� ?���?, ?��?��?�� ?��보입?��
	public void orderInfo(OrderDTO newOrderDTO) {
		cartDAO.orderInfo(newOrderDTO);
	}
	
	
	//2) 주문 ?��?��?���?(주문번호, ?��?��물품, ?��?��, )
	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		cartDAO.orderInfoDetail(newOrderDetailDTO); 
	}
	
	//3) 카트비우�?
	public void emptyMyCart(String member_id) {
		cartDAO.emptyMyCart(member_id); 
	}
	
	//4) ?��?��?��?��?�� 결제?���? 리스?��
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