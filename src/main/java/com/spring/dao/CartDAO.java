package com.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.CartDTO;
import com.spring.dto.OrderDTO;
import com.spring.dto.OrderDetailDTO;
import com.spring.dto.OrderListDTO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public boolean findCartGoods(CartDTO cartDTO) {
		String result = sqlSessionTemplate.selectOne("cart.findCartGoods", cartDTO);
		return Boolean.parseBoolean(result);
	}

	public void addGoodsInCart(CartDTO cartDTO) {
		sqlSessionTemplate.insert("cart.addGoodsInCart", cartDTO);
	}

	public List<CartDTO> getMyCart(String member_id) {
		return sqlSessionTemplate.selectList("cart.getMyCart", member_id);
	}

	public void delete(int cart_idx) {
		sqlSessionTemplate.delete("cart.delete", cart_idx);
	}

	// 결제금액
	public int sum(String member_id) {
		return sqlSessionTemplate.selectOne("cart.sum", member_id);
	}

	public void updateAmount(CartDTO updateAmountCartDTO) {
		sqlSessionTemplate.update("cart.updateAmount", updateAmountCartDTO);
	}

	// 1. 결제

	// 1) 주문?��?�� ?���?, ?��?��?�� ?��보입?��
	public void orderInfo(OrderDTO newOrderDTO) {
		sqlSessionTemplate.insert("cart.orderInfo", newOrderDTO);
	}

	// 2) 주문 ?��?��?���?(주문번호, ?��?��물품, ?��?��, )
	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		sqlSessionTemplate.insert("cart.orderInfo_Details", newOrderDetailDTO);
	}

	// 3) 카트비우�?
	public void emptyMyCart(String member_id) {
		sqlSessionTemplate.delete("cart.emptyMyCart", member_id);
	}

	// 4) ?��?��?��?��?�� 결제?���? 리스?��
	public List<OrderDTO> orderPaymentList(String member_id) {
		return sqlSessionTemplate.selectList("cart.orderPaymentList", member_id);
	}

	public List<OrderListDTO> allInformationAboutOrder(OrderDTO idAndOrderIdxOrderDTO) {
		return sqlSessionTemplate.selectList("cart.allInformationAboutOrder", idAndOrderIdxOrderDTO);
	}

}
