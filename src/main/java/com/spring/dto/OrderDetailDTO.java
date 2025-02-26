package com.spring.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {

	private int order_detail_idx; 
	private String order_idx; //주문번호 
	private int product_idx; 
	private int amount; 
	private String member_id; 
	
}
