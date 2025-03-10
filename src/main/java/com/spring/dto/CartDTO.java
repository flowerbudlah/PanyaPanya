package com.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartDTO {

	private int cart_idx;
	private String member_id;
	private String member_name;
	private int product_idx;
	private String product_name;
	private int price;
	private int amount;
	private int totalPrice;
	private String product_img;
	private Date regdate;

}