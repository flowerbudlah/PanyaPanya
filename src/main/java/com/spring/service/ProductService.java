package com.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.ProductDAO;
import com.spring.dto.ProductDTO;
import com.spring.dto.ProductReReplyDTO;
import com.spring.dto.ProductReplyDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Value("${item.path.load}")
	private String item_path_load;

	public List<ProductDTO> getProductListByCategory(int category_idx) {
		List<ProductDTO> productListByCategory = productDAO.getProductListByCategory(category_idx);
		return productListByCategory;
	}
	
	public ProductDTO getProductDetail(int product_idx) {
		ProductDTO productDetail = productDAO.getProductDetail(product_idx);
		return productDetail;
	}

	private String saveUploadFile(MultipartFile product_image_file) {

		String file_name = product_image_file.getOriginalFilename();

		try {
			product_image_file.transferTo(new File(item_path_load + "/" + file_name));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file_name;
	}

	public void addProduct(ProductDTO newProductDTO) {
		
		MultipartFile product_image_file = newProductDTO.getProduct_image_file();

		String file_name = saveUploadFile(product_image_file);

		newProductDTO.setProduct_img(file_name);

		productDAO.addProduct(newProductDTO);
	}

	public void delete(int product_idx) {
		productDAO.delete(product_idx);
	}

	public void modify(ProductDTO modifyProductDTO) {

		MultipartFile product_image_file = modifyProductDTO.getProduct_image_file();

		if (product_image_file.getSize() > 0) {
			String file_name = saveUploadFile(product_image_file);
			modifyProductDTO.setProduct_img(file_name);
		}
		productDAO.modify(modifyProductDTO);
	}

	public void write(ProductReplyDTO writeProductReplyDTO) {
		productDAO.write(writeProductReplyDTO);
	}

	public List<ProductReplyDTO> replyList(int product_idx) {
		return productDAO.replyList(product_idx);
	}

	public List<ProductReReplyDTO> rereplyList(int product_idx) {
		return productDAO.rereplyList(product_idx);
	}

	// 대댓글 작성
	public void writeReReply(ProductReReplyDTO productReReplyDTO) {
		productDAO.writeReReply(productReReplyDTO);
	}

	public ProductReplyDTO deleteProductReply(int product_reply_idx) {

		ProductReplyDTO productReplyDTO = new ProductReplyDTO();

		int deleteCnt = productDAO.deleteProductReply(product_reply_idx);
		
		if (deleteCnt > 0) {
			productReplyDTO.setResult("SUCCESS");
		} else {
			productReplyDTO.setResult("FAIL");
		}
		
		return productReplyDTO;
	}

	// 상품 추천(좋아요 공감 버튼 기능)
	public ProductDTO like(int product_idx) throws Exception {

		ProductDTO productDTOAfterLike = new ProductDTO();

		int like = productDAO.like(product_idx);

		if (like > 0) {
			productDTOAfterLike.setResult("success"); // 좋아요 성공
		} else {
			productDTOAfterLike.setResult("fail"); // 좋아요 실패
		}

		return productDTOAfterLike;
	}
	
	// 상품대댓글 삭제
	public ProductReReplyDTO deleteProductReReply(int product_rereply_idx) {

		ProductReReplyDTO rereplyDTO = new ProductReReplyDTO();

		int deleteCnt = productDAO.deleteProductReReply(product_rereply_idx);
		
		if (deleteCnt > 0) {
			rereplyDTO.setResult("Success of deleting Product's rereply");
		} else {
			rereplyDTO.setResult("Fail of deleting Product's rereply");
		}

		return rereplyDTO;
	}
}
