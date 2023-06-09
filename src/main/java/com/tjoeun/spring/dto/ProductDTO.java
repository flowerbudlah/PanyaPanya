package com.tjoeun.spring.dto;

import javax.validation.constraints.NotBlank;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	private int category_idx; //??λ²νΈ λΉ?1, μΌ??2, κΈ°ν??λ¨Έμ?3  
	private int product_idx; //??λ²νΈ  
	
	
	@NotBlank
	private String product_name; //??λͺ?

	private int product_price; //?¨κ°?
	
	@NotBlank
	private String storage_method; //λ³΄κ?λ°©λ²
	
	@NotBlank
	private String expiration_date; //? ?΅κΈ°ν 
	

	private String product_img; //?¬μ§μ΄λ¦? .png
	private MultipartFile product_image_file; //???΄λ―Έμ? ??Ό 
	
	private int likeButton; //μ’μ?(??μΆμ²)
	
	private String result; //κ²°κ³Όκ°? susccess or fail
	
	
	
	
}