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

	// 1. 1) 장바구니에 물건을 넣기 전에 그 물건이 이미 장바구니안에 존재하는지 여부를 체크
	// Before adding an item to your cart, Checking if the item already exists in your cart.
	public boolean findCartGoods(CartDTO cartDTO) {
		String result = sqlSessionTemplate.selectOne("cart.findCartGoods", cartDTO);
		return Boolean.parseBoolean(result);
	}

	// 1. 2) 장바구니에 물건 추가 (장바구니에 물건을 넣기 전에 이미 물건이 장바구니에 존재하면 추가불가능, 그 물건이 장바구니에 없는 경우에는 새롭게 물건 추가 가능)
	// If The item already exists in the cart before adding it to your cart, The item cannot be added. 
	// But, If the item does not exist in the cart, The item can be added newly.
	public void addGoodsInCart(CartDTO cartDTO) {
		sqlSessionTemplate.insert("cart.addGoodsInCart", cartDTO);
	}

	// 2. 1) 내 장바구니 안에있는 물건 목록 불러오기 (Taking a list of Goods in my cart.)
	public List<CartDTO> getMyCart(String member_id) {
		return sqlSessionTemplate.selectList("cart.getMyCart", member_id);
	}

	// 2. 2) 내 장바구니 안에 있는 특정물건을 없애기 (Removing specific items from my shopping cart.)
	public void delete(int cart_idx) {
		sqlSessionTemplate.delete("cart.delete", cart_idx);
	}

	// 2. 3) 장바구니 안에있는 물품의 수량변경 (Change the quantity of goods in My cart.)
	public void updateAmount(CartDTO updateAmountCartDTO) {
		sqlSessionTemplate.update("cart.updateAmount", updateAmountCartDTO);
	}

	// 2. 4) 현재 내 장바구니 안에있는 물건 가격의 합(배송비 불포함) 
	// The total price of the items in my cart (excluding shipping costs)
	public int sum(String member_id) {
		return sqlSessionTemplate.selectOne("cart.sum", member_id);
	}

	// 2. 5) 장바구니 안에 있는 물품 결제 (주문정보 Order idx 생성 Ex. order_idx: 20250204_070671 )
	// Paying for items in your cart (Creating order information)
	public void orderInfo(OrderDTO newOrderDTO) {
		sqlSessionTemplate.insert("cart.orderInfo", newOrderDTO);
	}

	// 2. 6) 주문 상세정보 생성 (같은 Order Idx 안에 물품별로 상이한 order_detail_idx 생성)
	// Creating order details (Creating different orderDetail_idx for each item within the same Order_Idx)
	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		sqlSessionTemplate.insert("cart.orderInfo_Details", newOrderDetailDTO);
	}

	// 2. 7) 주문을 완료하면 장바구니가 비어진다.
	// User's cart will be empty once user complete his order.
	public void emptyMyCart(String member_id) {
		sqlSessionTemplate.delete("cart.emptyMyCart", member_id);
	}

	// 3. 1) 주문번호별 총 결제금액, 배송현황, 결제일시 등의 정보 목록 (in 관리자 페이지)
	// Taking the List of Order information such as total payment amount, 
	// delivery status, payment date, etc. by order number (in administrator only page)
	public List<OrderDTO> orderPaymentList(String member_id) {
		return sqlSessionTemplate.selectList("cart.orderPaymentList", member_id);
	}

	// 3. 2) 특정회원의 주문 결제 배송현황 상세목록 (구매한 물품별 상세정보)
	// 관리자페이지에서 관리자가 확인가능하며, 사용자의 결제내역에서도 볼 수 있다.
	public List<OrderListDTO> allInformationAboutOrder(String order_idx) {
		return sqlSessionTemplate.selectList("cart.allInformationAboutOrder", order_idx);
	}

}