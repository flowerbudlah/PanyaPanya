package com.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.dto.PostDTO;
import com.spring.dto.MemberDTO;
import com.spring.service.BoardService;

// 글쓴이가 아니면 해당 게시글을 수정하거나 삭제할 권한이 없는 그런경우의 방해하는 처리
// This method have the function that makes the logged-in person not allow to modify or delete the post 
// in case that he wants to modify or delete a post even though he is not the author of the post. 
public class CheckWriterInterceptor implements HandlerInterceptor {

	@Resource(name = "loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;

	@Autowired
	private BoardService boardService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		int post_idx = Integer.parseInt(request.getParameter("post_idx"));

		PostDTO tmpPostDTO = boardService.getPostInfo(post_idx);

		if (tmpPostDTO.getPost_writer_idx() != loginMemberDTO.getMember_idx()) {
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;

		} else {
			
			return true;
			
		}

	}

}
