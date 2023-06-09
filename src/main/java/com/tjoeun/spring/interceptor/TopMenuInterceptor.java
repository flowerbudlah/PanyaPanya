package com.tjoeun.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.spring.dto.BoardDTO;
import com.tjoeun.spring.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{

	@Autowired
	private TopMenuService topMenuService;
	
	// XML λ°©μ??? ?? μ£Όμ?΄ ?΄λ£¨μ΄μ§?
	// Annotation λ°©μ??λ§? ? ?¨
	// interceptor??? ??μ£Όμ?Όλ‘? Bean? μ£Όμλ°μ? λͺ»νλ―?λ‘? interceptor?? ?¬?©?? κ°μ²΄?€?? interceptorλ₯? ?±λ‘ν? κ³³μ? <-- ServletAppContext.java
	// Bean? μ£Όμλ°μ? ??±?λ‘? λ°μ? ?¬?©?΄?Ό ?¨
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		List<BoardDTO> topMenuList = topMenuService.getTopMenuList();
		
		request.setAttribute("topMenuList", topMenuList);
		
		return true;
	}

}
