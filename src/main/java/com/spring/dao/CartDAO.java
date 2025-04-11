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


	public int sum(String member_id) {
		return sqlSessionTemplate.selectOne("cart.sum", member_id);
	}

	public void updateAmount(CartDTO updateAmountCartDTO) {
		sqlSessionTemplate.update("cart.updateAmount", updateAmountCartDTO);
	}




	public void orderInfo(OrderDTO newOrderDTO) {
		sqlSessionTemplate.insert("cart.orderInfo", newOrderDTO);
	}


	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		sqlSessionTemplate.insert("cart.orderInfo_Details", newOrderDetailDTO);
	}


	public void emptyMyCart(String member_id) {
		sqlSessionTemplate.delete("cart.emptyMyCart", member_id);
	}


	public List<OrderDTO> orderPaymentList(String member_id) {
		return sqlSessionTemplate.selectList("cart.orderPaymentList", member_id);
	}

	public List<OrderListDTO> allInformationAboutOrder(OrderDTO idAndOrderIdxOrderDTO) {
		return sqlSessionTemplate.selectList("cart.allInformationAboutOrder", idAndOrderIdxOrderDTO);
	}

}
