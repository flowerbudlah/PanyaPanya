package com.tjoeun.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductReplyDTO { 
	
	//?��?�� ?��?��보기 ?��?���??��?�� ?��?�� ?��?��?�� ???�� ?��감을 ?��기는 ?��?���?
	private int product_reply_idx; 
	private int product_idx; //?��?�� ?��?��?��
	private String product_reply_content; //?��?�� ?���? 
	private String product_replyer_id; //?��?�� ?��감을 ?��?��?�� ?��?��?�� ?��?��?��
	private Date regdate; //?���??��?��?��
	
	private String product_replyer_name; //?���? ?��?��?�� ?���?
	
	private String result; //?��?�� ?���? ?��?�� ?��?�� ?��공여�?

	
}
