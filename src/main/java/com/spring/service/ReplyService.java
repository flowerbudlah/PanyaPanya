package com.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.ReplyDAO;
import com.spring.dto.ReplyDTO;

@Service
public class ReplyService {

	@Autowired
	ReplyDAO replyDAO;

	// 1. 특정 게시글에 딸린 댓글들
	public List<ReplyDTO> list(int post_idx) {
		return replyDAO.list(post_idx);
	}

	// 2. 댓글 작성
	public void write(ReplyDTO writeReplyDTO) {
		replyDAO.write(writeReplyDTO);
	}

	// 3. 특정 댓글 삭제
	public void delete(int reply_idx) {
		replyDAO.delete(reply_idx);
	}

}
