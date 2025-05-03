package com.spring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {

	private int reply_idx;
	private int post_idx;
	private String reply_content;
	private String replyer_id;
	private String replyer_name;
	private Date regdate;

}
