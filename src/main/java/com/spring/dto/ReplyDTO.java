package com.spring.dto;

import java.util.Date;

public class ReplyDTO {

	private int reply_idx;
	private int post_idx;
	private String reply_content;
	private String replyer_id;
	private String replyer_name;
	private Date regdate;

	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReplyer_id() {
		return replyer_id;
	}
	public void setReplyer_id(String replyer_id) {
		this.replyer_id = replyer_id;
	}
	public String getReplyer_name() {
		return replyer_name;
	}
	public void setReplyer_name(String replyer_name) {
		this.replyer_name = replyer_name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}