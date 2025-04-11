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

	
	public List<ProductDTO> getProductListByCategory(int category_idx) {
		List<ProductDTO> productListByCategory = sqlSessionTemplate.selectList("product.getProductListByCategory",
				category_idx);
		return productListByCategory;
	}

	public ProductDTO getProductDetail(int product_idx) {
		ProductDTO productDetail = sqlSessionTemplate.selectOne("product.getProductDetail", product_idx);
		return productDetail;
	}

	public void addProduct(ProductDTO newProductDTO) {
		sqlSessionTemplate.insert("product.addProduct", newProductDTO);
	}

	public void delete(int product_idx) {
		sqlSessionTemplate.delete("product.delete", product_idx);
	}

	public void modify(ProductDTO modifyProductDTO) {
		sqlSessionTemplate.update("product.modify", modifyProductDTO);
	}

	public void write(ProductReplyDTO writeProductReplyDTO) {
		sqlSessionTemplate.insert("product.replyWrite", writeProductReplyDTO);
	}

	public List<ProductReplyDTO> replyList(int product_idx) {
		return sqlSessionTemplate.selectList("product.replyList", product_idx);
	}

	public int deleteProductReply(int product_reply_idx) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("product.deleteProductReply", product_reply_idx);
	}

	// 상품 추천(좋아요 기능)
	public int like(int product_idx) {
		return sqlSessionTemplate.update("product.like", product_idx);
	}

	// 대댓글 리스트
	public List<ProductReReplyDTO> rereplyList(int product_idx) {
		return sqlSessionTemplate.selectList("product.takeReReplyList", product_idx);
	}

	// 대댓글 쓰기
	public void writeReReply(ProductReReplyDTO ReReplyDTO) {
		sqlSessionTemplate.insert("product.writeReReply", ReReplyDTO);
	}

	// 상품 상세페이지에서 대댓글 삭제
	public int deleteProductReReply(int product_rereply_idx) {
		return sqlSessionTemplate.delete("product.deleteProductReReply", product_rereply_idx);
	}

}