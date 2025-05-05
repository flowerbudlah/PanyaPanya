package com.spring.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

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

//	배포 전 로컬에서 상품등록을 하기 위해서 상품 이미지를 업로드할 때는 아래의 코드가 유효하였으나 서버 이미지 업로드방식으로 변경한 뒤 아래의 코드는 무효
// The following code worked when uploading images locally before deployment(web hosting), but became invalid after switching to a server-based image upload method(imgur).
//	@Value("${item.path.load}")
//	private String item_path_load;

	// 1. 1) 카테고리 별(Ex. 빵, 케이크, 과자 등) 상품종류의 진열(상품의 목록이 나와있는 부분, Display of products by category)
	public List<ProductDTO> getProductListByCategory(int category_idx) {
		List<ProductDTO> productListByCategory = productDAO.getProductListByCategory(category_idx);
		return productListByCategory;
	}

	// 1. 2) 신규 상품 등록
	public void addProduct(ProductDTO newProductDTO) {

		MultipartFile product_image_file = newProductDTO.getProduct_image_file();

		// 상품의 이미지 업로드
		String file_name = saveUploadFile(product_image_file);

		newProductDTO.setProduct_img(file_name);

		productDAO.addProduct(newProductDTO);
	}
	
	// 1. 3) Uploading product images.
	private String saveUploadFile(MultipartFile product_image_file) {

		try {

			String clientId = "e957565cfb7c026";
			URL url = new URL("https://api.imgur.com/3/image");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Client-ID " + clientId);

			// 파일 내용을 Base64로 인코딩
			byte[] imageBytes = product_image_file.getBytes();
			String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

			String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encodedImage, "UTF-8");

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();
			writer.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();

			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			reader.close();

			JSONObject json = new JSONObject(sb.toString()); // 응답 JSON 파싱

			String link = json.getJSONObject("data").getString("link"); // 업로드된 이미지 링크

			return link; // DB에 저장할 링크 반환

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}

		// 배포 전 로컬에서 상품등록을 하기 위해서 상품 이미지를 업로드할 때는 아래의 코드가 유효하였으나 서버 이미지 업로드방식으로 변경한 뒤 아래의 코드는 무효
		// String file_name = product_image_file.getOriginalFilename();

		// try {
		// product_image_file.transferTo(new File(item_path_load + "/" + file_name));
		// } catch (IllegalStateException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// return file_name;
	}

	// 2. 1) 상품 상세정보 보기 Reading 
	// 제품 상세보기 페이지에서 상품에 대한 자세한 설명이 나와있는 부분 (The section that provides a detailed description of the product on the product details page. )
	public ProductDTO getProductDetail(int product_idx) {
		ProductDTO productDetail = productDAO.getProductDetail(product_idx);
		return productDetail;
	}
	
	// 2. 2) 상품 추천 (좋아요 공감 버튼 기능) Giving it a thumbs up if you agree
	public ProductDTO like(int product_idx) throws Exception {

		ProductDTO productDTOAfterLike = new ProductDTO();

		int like = productDAO.like(product_idx);

		if (like > 0) {
			// 좋아요 성공
			productDTOAfterLike.setResult("success");
		} else {
			// 좋아요 실패
			productDTOAfterLike.setResult("fail");
		}

		return productDTOAfterLike;
	}

	// 2. 3) 상품 상세보기 페이지에서 댓글달기 (writing a comment on the product details page)
	public void write(ProductReplyDTO writeProductReplyDTO) {
		productDAO.write(writeProductReplyDTO);
	}

	// 2. 4) 상품 상세보기 페이지에서 댓글들 가져오기 (Taking comments from product details page)
	public List<ProductReplyDTO> replyList(int product_idx) {
		return productDAO.replyList(product_idx);
	}

	// 2. 5) 상품상세보기 페이지에서 댓글삭제 (Removing the comment in product's detail page. )
	public ProductReplyDTO deleteProductReply(int product_reply_idx) {

		ProductReplyDTO productReplyDTO = new ProductReplyDTO();

		int deleteCnt = productDAO.deleteProductReply(product_reply_idx);

		if (deleteCnt > 0) {
			productReplyDTO.setResult("successOfRemovingTheComment");
		} else {
			productReplyDTO.setResult("failOfRemovingTheComment");
		}

		return productReplyDTO;
	}
	
	// 2. 6) 상품 상세보기 페이지에서 대댓글(Nested comment) 작성
	public void writeReReply(ProductReReplyDTO productReReplyDTO) {
		productDAO.writeReReply(productReReplyDTO);
	}

	// 2. 7) 상품 상세보기 페이지에서 대댓글들 가져오기 (Taking Nested comments from the product details page)
	public List<ProductReReplyDTO> rereplyList(int product_idx) {
		return productDAO.rereplyList(product_idx);
	}

	// 2. 8) 상품 상세보기 페이지에서 대댓글 삭제 Removing the nested comments in the product's details page 
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

	// 3. 등록된 상품 정보 수정 (Editing the details of a specific product.)
	public void modify(ProductDTO modifyProductDTO) {

		MultipartFile product_image_file = modifyProductDTO.getProduct_image_file();

		if (product_image_file.getSize() > 0) {
			String file_name = saveUploadFile(product_image_file);
			modifyProductDTO.setProduct_img(file_name);
		}
		productDAO.modify(modifyProductDTO);
	}

	// 4. 등록된 상품 삭제 Removing the specific product
	public void delete(int product_idx) {
		productDAO.delete(product_idx);
	}

}