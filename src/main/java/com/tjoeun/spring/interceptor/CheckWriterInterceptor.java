package com.tjoeun.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.spring.dto.PostDTO;
import com.tjoeun.spring.dto.MemberDTO;
import com.tjoeun.spring.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	/*
	 1) ??¬ λ‘κ·Έ?Έ? ?¬?? index κ°?? Έ?΄
	  
	 2) BoardService? getContentInfo ? κ²μκΈ?? κ°?? Έ?€λ―?λ‘? ??±? ?¬?? indexλ²νΈκ°? ??. 
	  	?΄ λ©μ?λ₯? ?ΈμΆν΄? ??±? ?¬?? indexλ²νΈ κ°?? Έ?΄
	  
	  XML λ°©μ??? ??μ£Όμ?΄ ?λ―?λ‘? ??±?λ₯? ??±?μ§? ??? ?¨
	*/
	
	@Resource(name="loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;
	
	@Autowired
	private BoardService boardService;
	
	//λ‘κ·Έ?Έ? ?¬?κ³? ??±? ?¬??΄ κ°μ?μ§? κ²??¬?κΈ?--?΄λ²? Interceptor? ?? ?  ??? ?­? ?  ?λ§? λ°μ??λ‘? ?¨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  	
		/*???Όλ‘? μ£Όμλ°μ? ??, Request κ°μ²΄? ???₯? κ°μ getParameterλ‘? κ°?? Έ?΄*/
		int post_idx = Integer.parseInt(request.getParameter("post_idx"));
  	
		/*Request κ°μ²΄ ?? ???₯? content_idx(κΈ?λ²νΈ)? ?΄?Ή?? ContentDTO κ°?? Έ?€κΈ?(content_wirter_idx κ°? ???΄?)*/ 
		PostDTO tmpPostDTO  = boardService.getPostInfo(post_idx);
  	
  	// λ‘κ·Έ?Έ? ?¬?κ³? κΈ? ?΄ ?¬?? ??λ²νΈ(idx)κ°? ?ΌμΉνμ§? ??Όλ©? BoardController? "/board/not_writer" λ‘? ?΄??κ²? ?¨
  	if(tmpPostDTO.getPost_writer_idx() != loginMemberDTO.getMember_idx() ) {
  		String contextPath = request.getContextPath();
  			response.sendRedirect(contextPath + "/board/not_writer");
  				return false;
  	}
  		return true;
  }
  

}
