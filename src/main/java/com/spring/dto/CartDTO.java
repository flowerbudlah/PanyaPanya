package com.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
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

}