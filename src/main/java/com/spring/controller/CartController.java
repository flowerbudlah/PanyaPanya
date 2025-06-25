package com.spring.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.CartDTO;
import com.spring.dto.MemberDTO;
import com.spring.dto.OrderDTO;
import com.spring.dto.OrderDetailDTO;
import com.spring.dto.OrderListDTO;
import com.spring.service.CartService;

@Controller
@RequestMapping("/mypage")
public class CartController {

	@Autowired
	private CartService cartService;

	// 1. 장바구니 안에 물건을 추가하는 처리 (Adding items to your shopping cart.)
	@PostMapping("/cart/")
	public @ResponseBody String addGoodsInCart(CartDTO cartDTO, HttpSession session) {

		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");
		cartDTO.setMember_id(loginMemberDTO.getMember_id());

		// 장바구니 안에 이미 그 물건이 있는지 없는지 확인
		boolean istAlreadyExisted = cartService.findCartGoods(cartDTO);
		System.out.println("istAlreadyExisted : " + istAlreadyExisted);

		// 해당 물건은 이미 장바구니안에 존재. 따라서 새롭게 그 물건을 추가할 필요는 없다. 
		if (istAlreadyExisted) {
			return "already_existed";
			
		// 해당 물건은 장바구니안에 존재하지 않기때문에 
		} else {
			// 새롭게 장바구니에 넣는다.
			cartService.addGoodsInCart(cartDTO);
			return "add_success";
		}
	}

	// 2. 특정회원의 장바구니 탐색
	@RequestMapping(value = "/cart/cartlist/{member_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String myCart(@PathVariable("member_id") String member_id, Model model) {

		Map<String, List> cartMap = cartService.getMyCart(member_id);
		model.addAttribute("cartMap", cartMap);

		// 베송비 포함하지 않은 결제금액
		int paymentMoney = cartService.sum(member_id);
		model.addAttribute("paymentMoney", paymentMoney);

		return "mypage/cart/cartlist";
	}

	// 3. 장바구니 주인이 장바구니 안에 있는 물건 비우는 기능(삭제)
	@GetMapping("/cart/cartlist/delete")
	public String delete(@RequestParam("cart_idx") int cart_idx, HttpSession session) {
		
		cartService.delete(cart_idx);

		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");

		return "redirect:/mypage/cart/cartlist/" + loginMemberDTO.getMember_id();
	}

	// 4. 장바구니 안에 있는 특정물건의 수량변경
	@PostMapping("/cart/cartlist/updateAmount")
	public String updateAmount(@ModelAttribute("updateAmountCartDTO") CartDTO updateAmountCartDTO,
			HttpSession session) {

		cartService.updateAmount(updateAmountCartDTO);
		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");
		return "redirect:/mypage/cart/cartlist/" + loginMemberDTO.getMember_id();
	}

	// 5. 주문결제
	@PostMapping("/order")
	public String order(HttpSession session, OrderDTO newOrderDTO, OrderDetailDTO newOrderDetailDTO) {
		
		// 로그인한 존재
		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");
		String member_id = loginMemberDTO.getMember_id();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String subNum = "";

		// 주문번호 중 끝부분 ex) // 20250204_070671 중 070671로 6자리 
		for (int i = 1; i <= 6; i++) {
			subNum += (int) (Math.random() * 10);
		}

		// 주문번호 ex) 20250204_070671
		String order_idx = ymd + "_" + subNum;

		// 주문정보
		newOrderDTO.setOrder_idx(order_idx);
		newOrderDTO.setMember_id(member_id);
		cartService.orderInfo(newOrderDTO);

		// 주문상세정보
		newOrderDetailDTO.setOrder_idx(order_idx);
		newOrderDetailDTO.setMember_id(member_id);
		cartService.orderInfoDetail(newOrderDetailDTO);

		// 주문이 완료됨과 동시에 장바구니에 있는 물건을 비우는 과정
		cartService.emptyMyCart(member_id);
		return "redirect:/mypage/order/orderInfo";
	}

	// 6. 로그인한 회원의 주문정보 페이지(주문·결제·배송 정보)로 이동
	@GetMapping("/order/orderInfo")
	public String orderPaymentList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {

		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");
		String member_id = loginMemberDTO.getMember_id();
		
		// 세션이 만료되어서 로그인이 풀린경우 (If user's session expires and The user are logged out.)
		if (session.getAttribute("loginMemberDTO") == null) {
		    response.sendRedirect("/member/login");
		    return "redirect:/member/login";
		}

		List<OrderDTO> orderPaymentList = cartService.orderPaymentList(member_id);
		model.addAttribute("orderPaymentList", orderPaymentList);

		return "mypage/order/orderInfo";
	}

	// 7. 로그인한 회원의 주문상세 정보 페이지로 이동
	@GetMapping("/order/orderInfo_Details")
	public String getOrderList(@RequestParam("order_idx") String order_idx, Model model) {

		List<OrderListDTO> allInformationAboutOrder = cartService.allInformationAboutOrder(order_idx);
		model.addAttribute("allInformationAboutOrder", allInformationAboutOrder);

		return "mypage/order/orderInfo_Details";

	}

}