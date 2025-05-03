package com.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReReplyDTO {

	private int product_rereply_idx;
	private int product_reply_idx;
	private int product_idx;
	private String product_rereply_content;
	private String product_rereplyer_id;
	private String product_rereplyer_name;
	private Date regdate;
	private String result;

}
