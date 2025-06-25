package com.spring.dto;

import javax.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

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

	public int getCategory_idx() {
		return category_idx;
	}
	public void setCategory_idx(int category_idx) {
		this.category_idx = category_idx;
	}
	public int getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(int product_idx) {
		this.product_idx = product_idx;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public String getStorage_method() {
		return storage_method;
	}
	public void setStorage_method(String storage_method) {
		this.storage_method = storage_method;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public MultipartFile getProduct_image_file() {
		return product_image_file;
	}
	public void setProduct_image_file(MultipartFile product_image_file) {
		this.product_image_file = product_image_file;
	}
	public int getLikeButton() {
		return likeButton;
	}
	public void setLikeButton(int likeButton) {
		this.likeButton = likeButton;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	private MultipartFile product_image_file; 
	private int likeButton;
	private String result;

}