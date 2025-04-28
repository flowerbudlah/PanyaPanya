package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.dao.TopMenuDAO;
import com.spring.dto.BoardDTO;
import com.spring.dto.PostDTO;
import com.spring.service.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@Autowired
	private TopMenuDAO topMenuDAO;

	// 1. 웹 사이트 접속시 등장하는 main화면 (The main Page that appears when you access this website at the first Time.)
	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		
		System.out.println(request.getServletContext().getRealPath("/"));
		
		// 이 사이트에 존재하는 게시판 목록이 등장한다. 
		List<List<PostDTO>> listAll = new ArrayList<List<PostDTO>>();

		for (int i = 1; i <= 2; i++) {

			List<PostDTO> mainList = mainService.getMainList(i);
			listAll.add(mainList);

		}

		model.addAttribute("listAll", listAll);

		List<BoardDTO> boardNameList = topMenuDAO.getTopMenuList();
		model.addAttribute("boardNameList", boardNameList);

		return "main";

	}

	// 2. 메뉴에서 오시는 길(지도)을 선택했을때 구글 지도 페이지로 이동
	@GetMapping("etc/map")
	public String map() {
		return "etc/map";
	}

	// 3. 관리자가 아닌 사용자가 관리자 페이지에 입장했을경우 이동하는 페이지
	@GetMapping("etc/not_admin")
	public String notAdmin() {
		return "etc/not_admin";
	}

}
