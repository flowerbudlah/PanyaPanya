package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.ProductDTO;
import com.spring.dto.ProductReReplyDTO;
import com.spring.dto.ProductReplyDTO;

import com.spring.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 1. 1) 상품이 카테고리별(케이크, 빵, 쿠키 등)로 분류되어 있는 메뉴 페이지로 이동
	@GetMapping("/product_by_category")
	public String product_by_category(@RequestParam("category_idx") int category_idx, Model model) {
		model.addAttribute("category_idx", category_idx);

		List<ProductDTO> productListByCategory = productService.getProductListByCategory(category_idx);
		model.addAttribute("productListByCategory", productListByCategory);

		return "product/product_by_category";
	}

	// 1. 2) 상품 상세보기
	@GetMapping("/product_detail")
	public String product_detail(@RequestParam("product_idx") int product_idx, Model model) {

		model.addAttribute("product_idx", product_idx);

		ProductDTO productDetail = productService.getProductDetail(product_idx);
		model.addAttribute("productDetail", productDetail);

		// 해당 상품에 달린 댓글
		List<ProductReplyDTO> productReply = productService.replyList(product_idx);
		model.addAttribute("productReply", productReply);

		// 해당 상품에 달린 대댓글
		List<ProductReReplyDTO> productReReply = productService.rereplyList(product_idx);
		model.addAttribute("productReReply", productReReply);

		return "product/product_detail";
	}

	// 2. 상품 추천
	@RequestMapping("/product_detail/like")
	public @ResponseBody ProductDTO like(HttpServletRequest request, HttpServletResponse response, int product_idx)  throws Exception {
		ProductDTO productDTOAfterLike = productService.like(product_idx);
		return productDTOAfterLike;
	}
	
	// 3. 1) 상품에 대한 댓글 달기
	@PostMapping("/product_detail/write")
	public String write(ProductReplyDTO writeProductReplyDTO, @RequestParam("product_idx") int product_idx, Model model) throws Exception {

		model.addAttribute("product_idx", product_idx);

		productService.write(writeProductReplyDTO);

		return "redirect:/product/product_detail?product_idx=" + writeProductReplyDTO.getProduct_idx();
	}
	
	// 3. 2) 상품에 대한 댓글 삭제
	@RequestMapping("/product_detail/deleteProductReply")
	public @ResponseBody ProductReplyDTO deleteProductReply(HttpServletRequest request, HttpServletResponse response, int product_reply_idx) throws Exception {

		ProductReplyDTO productReplyDTO = productService.deleteProductReply(product_reply_idx);
		return productReplyDTO;
	
	}

	// 3. 3) 상품에 대한 대댓글 달기
	@PostMapping("/product_detail/writeReReply")
	public String writeReReply(ProductReReplyDTO ReReplyDTO, @RequestParam("product_reply_idx") int product_reply_idx, @RequestParam("product_idx") int product_idx, Model model) throws Exception {

		model.addAttribute("product_reply_idx", product_reply_idx);
		productService.writeReReply(ReReplyDTO);

		return "redirect:/product/product_detail?product_idx=" + product_idx;
	
	}
	
	// 3. 4) 상품에 대한 대댓글 삭제 
	@RequestMapping("/product_detail/deleteProductReReply")
	public @ResponseBody ProductReReplyDTO deleteProductReReply(HttpServletRequest request, HttpServletResponse response, int product_rereply_idx) throws Exception {
		
		ProductReReplyDTO rereplyDTO = productService.deleteProductReReply(product_rereply_idx);
		return rereplyDTO;
	}

	// 4. 장바구니에서 해당 상품 제거하기 Deleting the Goods in user's cart.
	@GetMapping("/delete")
	public String delete(int product_idx, @RequestParam("category_idx") int category_idx, Model model) {

		model.addAttribute("category_idx", category_idx);
		productService.delete(product_idx);
		return "redirect:/product/product_by_category?category_idx={category_idx}";
	}

	// 5. 1) 새로운 상품등록을 위해 상품상세보기 페이지에서 업로드페이지로 이동 (관리자만 가능)
	// Going to the upload page from the product details page to register new product. administrator only has this right. 
	@GetMapping("/upload")
	public String upload(@ModelAttribute("newProductDTO") ProductDTO newProductDTO) {

		return "product/upload";
	}

	// 5. 2) 상품 업로드완료 버튼을 눌르고 새상품 등록을 진행
	@PostMapping("/upload_proc")
	public String addProduct(@Valid @ModelAttribute("newProductDTO") ProductDTO newProductDTO, BindingResult result,
			MultipartFile product_image_file, Model model) {

		if (result.hasErrors() && product_image_file.isEmpty()
				&& !(newProductDTO.getProduct_price() instanceof Integer)) {

			model.addAttribute("priceErrorMessasge", "가격은 숫자만 입력가능합니다.");
			model.addAttribute("imageUploadingErrorMessage", "상품등록페이지이기 때문에 이미지를 업로드하셔야 합니다.");

			return "product/upload";

		} else if (!result.hasErrors() && product_image_file.isEmpty()) {

			model.addAttribute("imageUploadingErrorMessage", "상품등록페이지이기 때문에 이미지를 업로드하셔야 합니다.");

			return "product/upload";

		} else if (!(newProductDTO.getProduct_price() instanceof Integer)) {

			model.addAttribute("priceErrorMessasge", "가격은 숫자만 입력가능합니다.");

			return "product/upload";

		} else {

			productService.addProduct(newProductDTO);
			
			return "redirect:/product/product_by_category?category_idx=" + newProductDTO.getCategory_idx();

		}

	}

	// 5. 3) 등록된 상품정보를 수정하기 위해서 상품수정 페이지로 이동
	@GetMapping("/modify")
	public String modify(@RequestParam("category_idx") int category_idx, @RequestParam("product_idx") int product_idx,
			@ModelAttribute("modifyProductDTO") ProductDTO modifyProductDTO, Model model) {

		model.addAttribute("category_idx", category_idx);
		model.addAttribute("product_idx", product_idx);

		ProductDTO fromDBProductDTO = productService.getProductDetail(product_idx);

		modifyProductDTO.setProduct_name(fromDBProductDTO.getProduct_name());
		modifyProductDTO.setProduct_price(fromDBProductDTO.getProduct_price());
		modifyProductDTO.setStorage_method(fromDBProductDTO.getStorage_method());
		modifyProductDTO.setExpiration_date(fromDBProductDTO.getExpiration_date());
		modifyProductDTO.setProduct_img(fromDBProductDTO.getProduct_img());
		modifyProductDTO.setCategory_idx(category_idx);

		return "product/modify";
	}

	// 5. 4) 상품수정페이지에서 상품수정 버튼 누른다. 
	@PostMapping("/modify_proc")
	public String modifyProc(@Valid @ModelAttribute("modifyProductDTO") ProductDTO modifyProductDTO,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "product/modify";
		}

		productService.modify(modifyProductDTO);

		return "redirect:/product/product_by_category?category_idx=" + modifyProductDTO.getCategory_idx();
	}

}