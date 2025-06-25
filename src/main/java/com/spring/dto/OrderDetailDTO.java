package com.spring.dto;

public class OrderDetailDTO {
	
	// 특정유저 A가 바나나와 사과를 동시에 구입할 경우, 이런 경우의 주문번호(EX. 20250204_070671)는 하나로 동일하다. 
	// 그러나, 주문상세 일련번호는 2개로 나뉩니다. 바나나에 대한 주문상세 일련번호 1개, 사과에 대한 주문상세 일련번호 1개로 총 2개가 생성됩니다. 
	// 즉, 물건별로 상이합니다.
	
	private int order_detail_idx; // 주문상세 일련번호
	private String order_idx; // 주문번호 ex. 20250204_070671
	private int product_idx; // 주문이 들어간 상품의 일련번호
	private int amount; // 주문이 들어간 위 상품(product_idx) 수량
	private String member_id; // 주문한 사람의 ID
	public int getOrder_detail_idx() {
		return order_detail_idx;
	}
	public void setOrder_detail_idx(int order_detail_idx) {
		this.order_detail_idx = order_detail_idx;
	}
	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

}