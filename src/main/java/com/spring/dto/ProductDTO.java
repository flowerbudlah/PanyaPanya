package com.spring.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	private int category_idx;
	private int product_idx;

	@NotBlank
	private String product_name;

	private Integer product_price;

	@NotBlank
	private String storage_method;

	@NotBlank
	private String expiration_date; 

	private String product_img;

	private MultipartFile product_image_file; 
	private int likeButton;
	private String result;

}