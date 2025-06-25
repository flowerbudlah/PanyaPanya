package com.spring.dto;

import java.util.Date;

public class ProductReplyDTO {

	private int product_reply_idx;
	private int product_idx;
	private String product_reply_content;
	private String product_replyer_id;
	public int getProduct_reply_idx() {
		return product_reply_idx;
	}
	public void setProduct_reply_idx(int product_reply_idx) {
		this.product_reply_idx = product_reply_idx;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getProduct_reply_content() {
		return product_reply_content;
	}
	public void setProduct_reply_content(String product_reply_content) {
		this.product_reply_content = product_reply_content;
	}
	public String getProduct_replyer_id() {
		return product_replyer_id;
	}
	public void setProduct_replyer_id(String product_replyer_id) {
		this.product_replyer_id = product_replyer_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getProduct_replyer_name() {
		return product_replyer_name;
	}
	public void setProduct_replyer_name(String product_replyer_name) {
		this.product_replyer_name = product_replyer_name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private Date regdate;
	private String product_replyer_name;
	private String result;

}
