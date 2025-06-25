package com.spring.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class PostDTO {

	private int post_idx; // 게시글 일련번호

	@NotBlank
	private String post_subject; // 글 제목

	@NotBlank
	private String post_text; // 글 내용

	private int post_writer_idx;

	private String post_writer_name;

	private int post_board_idx;

	private Date post_date; // 글 작성일

	private String post_file;
	private MultipartFile upload_file;

	private int post_read_count;
	private int reply_cnt;

	private String keyword;
	private String type;
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	public String getPost_subject() {
		return post_subject;
	}
	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}
	public String getPost_text() {
		return post_text;
	}
	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}
	public int getPost_writer_idx() {
		return post_writer_idx;
	}
	public void setPost_writer_idx(int post_writer_idx) {
		this.post_writer_idx = post_writer_idx;
	}
	public String getPost_writer_name() {
		return post_writer_name;
	}
	public void setPost_writer_name(String post_writer_name) {
		this.post_writer_name = post_writer_name;
	}
	public int getPost_board_idx() {
		return post_board_idx;
	}
	public void setPost_board_idx(int post_board_idx) {
		this.post_board_idx = post_board_idx;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getPost_file() {
		return post_file;
	}
	public void setPost_file(String post_file) {
		this.post_file = post_file;
	}
	public MultipartFile getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}
	public int getPost_read_count() {
		return post_read_count;
	}
	public void setPost_read_count(int post_read_count) {
		this.post_read_count = post_read_count;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}