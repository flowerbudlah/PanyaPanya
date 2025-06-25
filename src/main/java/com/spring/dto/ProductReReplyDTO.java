package com.spring.dto;

import java.util.Date;

public class ProductReReplyDTO {

	private int product_rereply_idx;
	private int product_reply_idx;
	private int product_idx;
	private String product_rereply_content;
	private String product_rereplyer_id;
	private String product_rereplyer_name;
	private Date regdate;
	private String result;
	public int getProduct_rereply_idx() {
		return product_rereply_idx;
	}
	public void setProduct_rereply_idx(int product_rereply_idx) {
		this.product_rereply_idx = product_rereply_idx;
	}
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
	public String getProduct_rereply_content() {
		return product_rereply_content;
	}
	public void setProduct_rereply_content(String product_rereply_content) {
		this.product_rereply_content = product_rereply_content;
	}
	public String getProduct_rereplyer_id() {
		return product_rereplyer_id;
	}
	public void setProduct_rereplyer_id(String product_rereplyer_id) {
		this.product_rereplyer_id = product_rereplyer_id;
	}
	public String getProduct_rereplyer_name() {
		return product_rereplyer_name;
	}
	public void setProduct_rereplyer_name(String product_rereplyer_name) {
		this.product_rereplyer_name = product_rereplyer_name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}