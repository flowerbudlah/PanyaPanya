package com.spring.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
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

}