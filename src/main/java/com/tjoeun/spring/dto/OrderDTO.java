package com.tjoeun.spring.dto;

import java.util.Date;

import lombok.Data;



@Data
public class OrderDTO {
	
	private String order_idx; //무작위 주문번호
	 
	private String member_id; //
	private int paymentPrice; //결제금액
	
	
	private String sender_name;
	private String sender_address; 
	private int sender_postcode; 
	private String sender_tel; 
	
	private String recipient_name; 
	private String recipient_address;
	private int recipient_postcode; 	
	private String recipient_tel; 
	
	private Date order_date;
	private String memo; 
	private String payment_method; 
	
	private String circumstance; 


}
