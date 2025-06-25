package com.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.dto.BoardDTO;
import com.spring.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor {

	@Autowired
	private TopMenuService topMenuService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		List<BoardDTO> topMenuList = topMenuService.getTopMenuList();

		request.setAttribute("topMenuList", topMenuList);

		return true;
	}

}
