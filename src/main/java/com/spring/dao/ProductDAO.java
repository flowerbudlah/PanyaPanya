package com.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.ProductDTO;
import com.spring.dto.ProductReReplyDTO;
import com.spring.dto.ProductReplyDTO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 1. 1) 카테고리 별(빵, 케이크, 쿠키) 모든 상품목록 가져오기
	public List<ProductDTO> getProductListByCategory(int category_idx) {
		List<ProductDTO> productListByCategory = sqlSessionTemplate.selectList("product.getProductListByCategory", category_idx);
		return productListByCategory;
	}

	// 1. 2) 신상품 추가 (in 상품목록페이지) Creating
	public void addProduct(ProductDTO newProductDTO) {
		sqlSessionTemplate.insert("product.addProduct", newProductDTO);
	}

	// 2. 1) 특정 상품의 상세정보 가져오기, 상품 상세보기 페이지로 이동 Reading
	public ProductDTO getProductDetail(int product_idx) {
		ProductDTO productDetail = sqlSessionTemplate.selectOne("product.getProductDetail", product_idx);
		return productDetail;
	}

	// 2. 1). (1) 상품 추천 (in 상품 상세페이지) Recommending
	public int like(int product_idx) {
		return sqlSessionTemplate.update("product.like", product_idx);
	}
	
	// 2. 1). (2) 댓글 쓰기 (in 상품 상세페이지) Creating a comment.
	public void write(ProductReplyDTO writeProductReplyDTO) {
		sqlSessionTemplate.insert("product.replyWrite", writeProductReplyDTO);
	}

	// 2. 1). (3) 댓글 목록 가져오기 (in 상품 상세페이지) Taking the list of comments.
	public List<ProductReplyDTO> replyList(int product_idx) {
		return sqlSessionTemplate.selectList("product.replyList", product_idx);
	}

	// 2. 1). (4) 댓글 삭제 (in 상품 상세페이지) Deleting the comment.
	public int deleteProductReply(int product_reply_idx) {
		return sqlSessionTemplate.delete("product.deleteProductReply", product_reply_idx);
	}

	// 2. 1). (5) 대댓글 리스트 (in 상품 상세페이지) Taking the List of Nested comment. 
	public List<ProductReReplyDTO> rereplyList(int product_idx) {
		return sqlSessionTemplate.selectList("product.takeReReplyList", product_idx);
	}

	// 2. 1). (6) 대댓글 쓰기 (in 상품 상세페이지) Writing a nested comment.
	public void writeReReply(ProductReReplyDTO ReReplyDTO) {
		sqlSessionTemplate.insert("product.writeReReply", ReReplyDTO);
	}
 
	// 2. 1). (7) 대댓글 삭제 (in 상품 상세페이지) removing the nested comment
	public int deleteProductReReply(int product_rereply_idx) {
		return sqlSessionTemplate.delete("product.deleteProductReReply", product_rereply_idx);
	}

	// 2. 3) 특정 상품의 상세 정보수정 (in 상품 상세보기 페이지) Updating the detail information of specific goods.
	public void modify(ProductDTO modifyProductDTO) {
		sqlSessionTemplate.update("product.modify", modifyProductDTO);
	}

	// 2. 4) 특정 상품삭제 (in 상품 상세보기 페이지) Deleting the specific goods.
	public void delete(int product_idx) {
		sqlSessionTemplate.delete("product.delete", product_idx);
	}

}