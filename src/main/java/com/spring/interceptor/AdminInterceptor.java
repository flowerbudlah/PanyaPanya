package com.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.dto.MemberDTO;

// 로그인을 한 사람이 관리자가 아니면 접근할수 없도록 설정한 방해 
// This is Interceptor that the logged-in person cannot access unless he is an administrator. 
public class AdminInterceptor implements HandlerInterceptor {

	@Lazy@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		// 세션을 통해서 가져온 로그인한 사람의 정보
		// The information of the Loggined Person taken from The session
		MemberDTO loginMemberDTO = (MemberDTO) session.getAttribute("loginMemberDTO");

		// 만약 로그인한 사람이 인덱스 번호 1(관리자)이라면 가능
		if (loginMemberDTO.getMember_idx() == 1) {
			return true;

		// 그러나 관리자가 아니라면 접근불가
		} else {

			// 요청한 아래의 경로의 페이지에 접속하면서 접속불가라는 뜻(잘못된 접근입니다.)을 보여줄것.
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/etc/not_admin");
			return false;
		}

	}
}