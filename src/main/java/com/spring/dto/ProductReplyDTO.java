package com.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReplyDTO {

	private int product_reply_idx;
	private int product_idx;
	private String product_reply_content;
	private String product_replyer_id;
	private Date regdate;
	private String product_replyer_name;
	private String result;

}
