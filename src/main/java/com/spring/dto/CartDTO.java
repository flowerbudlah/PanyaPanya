package com.spring.dto;

import java.util.Date;

public class CartDTO {

	// 1. 장바구니 일련번호
	private int cart_idx;
	// 장바구니를 나타내는 객체 CartDTO(Data transfer Object 또는 VO: Value Object)의 일련번호 Card_idx는 장바구니 안에 담긴 하나의 물건의 종류마다 그리고 장바구니의 소유자마다 다르다. 
	// The serial number (int cart_idx) of the CartDTO (Data Transfer Object or Value Object) is unique for each product in the cart and for each cart owner.

	// 2. 장바구니의 주인의 ID
	private String member_id;
	// 3. 장바구니 주인의 이름
	private String member_name;
	// 4. 장바구니 안에 있는 상품의 일련번호
	private int product_idx;
	// 5. 장바구니 안에 있는 상품의 이름
	private String product_name;
	// 6. 장바구니 안에있는 상품의 단가
	private int price;
	// 7. 장바구니 안에 있는 상품의 수량
	private int amount;
	// 8. 장바구니 안에 있는 특정상품(product_idx)의 합계: price x amount = totalPrice
	private int totalPrice;
	// 9. 이 특정상품(product_idx)의 사진 이름
	private String product_img;
	// 10. 이 특정상품(product_idx)를 장바구니 안에 넣은 날
	private Date regdate;
	public int getCart_idx() {
		return cart_idx;
	}
	public void setCart_idx(int cart_idx) {
		this.cart_idx = cart_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}