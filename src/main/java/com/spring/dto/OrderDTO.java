package com.spring.dto;

import java.util.Date;

public class OrderDTO {

	private String order_idx;
	private String member_id;
	private int paymentPrice;
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

	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(int paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_address() {
		return sender_address;
	}
	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
	}
	public int getSender_postcode() {
		return sender_postcode;
	}
	public void setSender_postcode(int sender_postcode) {
		this.sender_postcode = sender_postcode;
	}
	public String getSender_tel() {
		return sender_tel;
	}
	public void setSender_tel(String sender_tel) {
		this.sender_tel = sender_tel;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_address() {
		return recipient_address;
	}
	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
	}
	public int getRecipient_postcode() {
		return recipient_postcode;
	}
	public void setRecipient_postcode(int recipient_postcode) {
		this.recipient_postcode = recipient_postcode;
	}
	public String getRecipient_tel() {
		return recipient_tel;
	}
	public void setRecipient_tel(String recipient_tel) {
		this.recipient_tel = recipient_tel;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getCircumstance() {
		return circumstance;
	}
	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

}